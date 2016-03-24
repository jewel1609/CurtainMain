package com.ktds.curtain.qa.vo;

public class QuestionAndAnswerVO {

	private int questionId;
	private String questionTitle;
	private String questionDescription;
	private String answerDescription;
	private String questionDate;
	private String answerDate;
	private String studentEmail;
	private String checkedQuestion;
	private String companyEmail;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public String getAnswerDescription() {
		return answerDescription;
	}
	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription;
	}
	public String getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getCheckedQuestion() {
		return checkedQuestion;
	}
	public void setCheckedQuestion (String checkedQuestion) {
		this.checkedQuestion = checkedQuestion;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	
}
