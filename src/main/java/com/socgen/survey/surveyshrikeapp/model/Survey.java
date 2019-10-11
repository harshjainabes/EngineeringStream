/**
 * 
 */
package com.socgen.survey.surveyshrikeapp.model;

import lombok.Data;

/**
 * @author Harsh Jain
 *
 * 
 */

public class Survey {

	private String surveyId;
	private String surveyDesc;
	private String surveyType;
	private Question[] questions;
	/**
	 * @return the surveyId
	 */
	public String getSurveyId() {
		return surveyId;
	}
	/**
	 * @param surveyId the surveyId to set
	 */
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	/**
	 * @return the surveyDesc
	 */
	public String getSurveyDesc() {
		return surveyDesc;
	}
	/**
	 * @param surveyDesc the surveyDesc to set
	 */
	public void setSurveyDesc(String surveyDesc) {
		this.surveyDesc = surveyDesc;
	}
	/**
	 * @return the surveyType
	 */
	public String getSurveyType() {
		return surveyType;
	}
	/**
	 * @param surveyType the surveyType to set
	 */
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	/**
	 * @return the questions
	 */
	public Question[] getQuestions() {
		return questions;
	}
	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
	
	
	
	
	
	
	
}
