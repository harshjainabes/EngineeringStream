/**
 * 
 */
package com.socgen.survey.surveyshrikeapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Harsh Jain
 *
 * 
 */

public class Answer {

	private String answerId;
	private String answerDesc;
	/**
	 * @return the answerId
	 */
	public String getAnswerId() {
		return answerId;
	}
	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	/**
	 * @return the answerDesc
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}
	/**
	 * @param answerDesc the answerDesc to set
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
	
	
	
}
