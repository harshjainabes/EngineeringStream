package com.survey.surveyapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.surveyapp.entity.SurveyDetail;
/**
 * @author Harsh Jain
 *
 * 
 */
@Repository
@Transactional
public interface SurveyDetailRepository extends JpaRepository<SurveyDetail, Long> {

	List<SurveyDetail> getSurveyDetailsByUserDetailEmail(String email);

}
