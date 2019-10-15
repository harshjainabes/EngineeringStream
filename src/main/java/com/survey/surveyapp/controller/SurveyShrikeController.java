package com.survey.surveyapp.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.survey.surveyapp.entity.SurveyDetail;
import com.survey.surveyapp.entity.UserDetail;
import com.survey.surveyapp.model.Answer;
import com.survey.surveyapp.model.Survey;
import com.survey.surveyapp.model.SurveyshrikeException;
import com.survey.surveyapp.model.UserInfo;
import com.survey.surveyapp.service.SurveyDetailsService;
import com.survey.surveyapp.service.UserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Harsh Jain
 *
 * 
 */

@RestController
@RequestMapping(path = "/surveyShrike")
@CrossOrigin(origins = "*")
@Api(description = "Survey Controller", tags = { "Survey Controller" })
public class SurveyShrikeController {

	@Autowired
	private SurveyDetailsService surveyDetailsService;

	@Autowired
	UserDetailsService userDetailsService;

	Logger logger = Logger.getLogger(SurveyShrikeController.class.getName());

	@ApiOperation(value = "Use this end point to add a user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User Added Successfully"),
			@ApiResponse(code = 400, message = "Bad Request, this request is malfornmed"),
			@ApiResponse(code = 401, message = "Authentication failed"),
			@ApiResponse(code = 403, message = "Not enough rights"),
			@ApiResponse(code = 404, message = "URL not found")

	})
	@PostMapping(path = "/addUserInfo", consumes = "application/json")
	public String addUserDetails(@RequestBody UserInfo userInfo) throws Exception {

		if (null == userDetailsService.findUserByEmail(userInfo.getEmail())) {
			userDetailsService.createUser(userInfo);
		}
		else {
			return "user already exists";
		}
		return "user created";
	}

	@ApiOperation(value = "Use this end point to create a survey")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Survey Created Successfully"),
			@ApiResponse(code = 400, message = "Bad Request, this request is malfornmed"),
			@ApiResponse(code = 401, message = "Authentication failed"),
			@ApiResponse(code = 403, message = "Not enough rights"),
			@ApiResponse(code = 404, message = "URL not found")

	})
	@PostMapping(path = "/createSurvey", consumes = "application/json")
	public String createSurvey(@RequestBody Survey survey) throws Exception {

		UserDetail findUserByEmail = userDetailsService
				.findUserByEmail(survey.getEmail());

		if (null == findUserByEmail)
			throw new SurveyshrikeException(HttpStatus.FORBIDDEN.value(),
					survey.getEmail() + " is not a valid user!!!");
		else {

			surveyDetailsService.createSurvey(survey, findUserByEmail);
			return "Survey Created";
		}
	}

	@ApiOperation(value = "Use this end point to get all surveys")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Survey Retreived Successfully"),
			@ApiResponse(code = 400, message = "Bad Request, this request is malfornmed"),
			@ApiResponse(code = 401, message = "Authentication failed"),
			@ApiResponse(code = 403, message = "Not enough rights"),
			@ApiResponse(code = 404, message = "URL not found")

	})
	@GetMapping(path = "/getAllSurvey")
	public List<Survey> getSurveys() {
		return surveyDetailsService.getAllSurveys();
	}

	@ApiOperation(value = "Use this end point to get surveys for a user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Survey Retreived Successfully"),
			@ApiResponse(code = 400, message = "Bad Request, this request is malfornmed"),
			@ApiResponse(code = 401, message = "Authentication failed"),
			@ApiResponse(code = 403, message = "Not enough rights"),
			@ApiResponse(code = 404, message = "URL not found")

	})
	@GetMapping(path = "/getSurveyByUser")
	public List<Survey> getSurveyByUser(@RequestParam("email") String email) {

		if (null != userDetailsService.findUserByEmail(email)) {
			return surveyDetailsService.getSurveyByUser(email);
		}
		else {
			throw new SurveyshrikeException(HttpStatus.FORBIDDEN.value(),
					email + " is not a valid user!!!");
		}

	}

	@ApiOperation(value = "Use this end point to respond to a survey")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Survey Responded Successfully"),
			@ApiResponse(code = 400, message = "Bad Request, this request is malfornmed"),
			@ApiResponse(code = 401, message = "Authentication failed"),
			@ApiResponse(code = 403, message = "Not enough rights"),
			@ApiResponse(code = 404, message = "URL not found")

	})
	@PostMapping(path = "/postSurveyAnswer", consumes = "application/json")
	public String postSurveyAnswer(@RequestBody Answer surveyAnswer) throws Exception {

		SurveyDetail surveyById = surveyDetailsService
				.getSurveyById(surveyAnswer.getSurveyId());

		UserDetail userDetails = userDetailsService
				.findUserByEmail(surveyAnswer.getEmail());

		if (null != surveyById) {
			if (null != userDetails) {

				surveyDetailsService.createSurveyAnswer(surveyAnswer, userDetails);
				return "Surver answer posted successfully";
			}
			else {
				throw new SurveyshrikeException(HttpStatus.FORBIDDEN.value(),
						surveyAnswer.getEmail() + " is not a valid user!!!");
			}
		}
		else {
			throw new SurveyshrikeException(HttpStatus.FORBIDDEN.value(),
					surveyAnswer.getSurveyId() + " is not a valid survey id!!!");
		}
	}

	@ApiOperation(value = "Use this end point to get response to a survey for a user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Survey Answer retreived Successfully"),
			@ApiResponse(code = 400, message = "Bad Request, this request is malfornmed"),
			@ApiResponse(code = 401, message = "Authentication failed"),
			@ApiResponse(code = 403, message = "Not enough rights"),
			@ApiResponse(code = 404, message = "URL not found")

	})
	@GetMapping(path = "/getSurveyAnswerBySurveyIdAndUserEmail")
	public Answer getSurveyAnswerBySurveyIdAndUserEmail(
			@RequestParam("surveyId") int surveyId, @RequestParam("email") String email)
			throws Exception {

		SurveyDetail surveyById = surveyDetailsService.getSurveyById(surveyId);

		UserDetail userDetails = userDetailsService.findUserByEmail(email);

		if (null != surveyById) {
			if (null != userDetails) {
				return surveyDetailsService.getSurveyAnswerBySurveyIdAndEmail(surveyById,
						userDetails);
			}
			else {
				throw new SurveyshrikeException(HttpStatus.FORBIDDEN.value(),
						email + " is not a valid user!!!");
			}
		}
		else {
			throw new SurveyshrikeException(HttpStatus.FORBIDDEN.value(),
					surveyId + " is not a valid survey id!!!");
		}
	}

}
