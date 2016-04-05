package com.ktds.oph.questionAndAnswer.vo;

import java.util.List;
import com.ktds.oph.util.web.Paging;

public class QuestionAndAnswerListVO {
	
	private List<QuestionAndAnswerVO> questions;
	private Paging paging;
	
	public List<QuestionAndAnswerVO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionAndAnswerVO> questions) {
		this.questions = questions;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
