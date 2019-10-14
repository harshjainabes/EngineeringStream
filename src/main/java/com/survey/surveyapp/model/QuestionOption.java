package com.survey.surveyapp.model;

/**
 * @author Harsh Jain
 *
 * 
 */
public class QuestionOption {

	private String optionChoiceName;
	private int optionId;

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOptionChoiceName() {
		return optionChoiceName;
	}

	public void setOptionChoiceName(String optionChoiceName) {
		this.optionChoiceName = optionChoiceName;
	}

}
