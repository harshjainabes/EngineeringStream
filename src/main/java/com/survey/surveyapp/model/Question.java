package com.survey.surveyapp.model;

import java.util.List;

/**
 * @author Harsh Jain
 *
 * 
 */
public class Question {

	private String question;

	private int inputType;

	private boolean answerRequired;

	private List<QuestionOption> questionOption;

	private int questionId;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getInputType() {
		return inputType;
	}

	public void setInputType(int inputType) {
		this.inputType = inputType;
	}

	public boolean isAnswerRequired() {
		return answerRequired;
	}

	public void setAnswerRequired(boolean answerRequired) {
		this.answerRequired = answerRequired;
	}

	public List<QuestionOption> getQuestionOption() {
		return questionOption;
	}

	public void setQuestionOption(List<QuestionOption> questionOption) {
		this.questionOption = questionOption;
	}

}
