/**
 * 
 */
package com.survey.surveyapp.service;

import java.util.List;

import com.survey.surveyapp.entity.SurveyDetail;
import com.survey.surveyapp.entity.UserDetail;
import com.survey.surveyapp.model.Answer;
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

	SurveyDetail getSurveyById(int skillsurveyId);

	void createSurveyAnswer(Answer surveyAnswer, UserDetail userDetails);

	Answer getSurveyAnswerBySurveyIdAndEmail(SurveyDetail surveyById,
			UserDetail userDetails);

}
