package com.ktds.oph.questionAndAnswer.vo;

import java.util.List;
import com.ktds.oph.util.web.Paging;

public class QuestionAndAnswerListVO {
	
	private List<QuestionAndAnswerVO> questionList;
	private Paging paging;
	
	public List<QuestionAndAnswerVO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<QuestionAndAnswerVO> questionList) {
		this.questionList = questionList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
