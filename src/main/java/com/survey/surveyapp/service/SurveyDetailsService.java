/**
 * 
 */
package com.survey.surveyapp.service;

import java.util.List;

import com.survey.surveyapp.entity.UserDetail;
import com.survey.surveyapp.model.Survey;
/**
 * @author Harsh Jain
 *
 * 
 */
public interface SurveyDetailsService {

	List<Survey> getAllSurveys();

	void createSurvey(Survey survey, UserDetail userDetail);

	List<Survey> getSurveyByUser(String email);

}
