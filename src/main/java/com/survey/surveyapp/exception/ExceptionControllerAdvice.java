package com.survey.surveyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.survey.surveyapp.model.SurveyshrikeException;
import com.survey.surveyapp.model.SurveyshrikeExceptionResponse;

/**
 * @author Harsh Jain
 *
 * 
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(SurveyshrikeException.class)
	public ResponseEntity<SurveyshrikeExceptionResponse> handleMyException(
			SurveyshrikeException ex) {

		SurveyshrikeExceptionResponse exceptionResponse = new SurveyshrikeExceptionResponse();
		exceptionResponse.setErrorCode(ex.getErrorCode());

		exceptionResponse.setErrorMessage(ex.getErrorMessage());

		return new ResponseEntity<SurveyshrikeExceptionResponse>(exceptionResponse,
				HttpStatus.BAD_REQUEST);
	}
}
