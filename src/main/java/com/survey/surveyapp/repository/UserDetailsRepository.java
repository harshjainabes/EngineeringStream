package com.survey.surveyapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.surveyapp.entity.SurveyDetail;
import com.survey.surveyapp.entity.UserDetail;
import com.survey.surveyapp.model.Answer;

/**
 * @author Harsh Jain
 *
 * 
 */
@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository<UserDetail, Long> {

	UserDetail findFirstById(long id);

	UserDetail findByEmail(String emailId);

	UserDetail findFirstByEmail(String email);

	SurveyDetail getSurveyById(int skillsurveyId);

	void createSurveyAnswer(Answer surveyAnswer, UserDetail userDetails);

	Answer getSurveyAnswerBySurveyIdAndEmail(SurveyDetail surveyById,
			UserDetail userDetails);

}
