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

/**
 * @author Harsh Jain
 *
 * 
 */

@RestController
@RequestMapping(path = "/surveyShrike")
@CrossOrigin(origins = "*")
public class SurveyShrikeController {

	@Autowired
	private SurveyDetailsService surveyDetailsService;

	@Autowired
	UserDetailsService userDetailsService;

	Logger logger = Logger.getLogger(SurveyShrikeController.class.getName());

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

	@GetMapping(path = "/getAllSurvey")
	public List<Survey> getSurveys() {
		return surveyDetailsService.getAllSurveys();
	}

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
