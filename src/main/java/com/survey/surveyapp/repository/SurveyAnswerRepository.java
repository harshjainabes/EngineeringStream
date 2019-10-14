package com.survey.surveyapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.surveyapp.entity.SurveyAnswer;

/**
 * @author Harsh Jain
 *
 * 
 */
@Repository
@Transactional
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {

	SurveyAnswer getSurveyAnswerByQuestionIdAndAnsweredBy(int questionId, int userId);
}
