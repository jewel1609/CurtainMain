package com.ktds.curtain.qa.biz;

import java.util.List;

import com.ktds.curtain.qa.dao.QuestionAndAnswerDAO;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

public class QuestionAndAnswerBiz {
	
	private QuestionAndAnswerDAO questionAndAnswerDAO;
	
	public QuestionAndAnswerBiz () {
		questionAndAnswerDAO = new QuestionAndAnswerDAO();
	}

	public void registerQuestion(QuestionAndAnswerVO questionAndAnswerVO, String memberType) {
		
		if ( questionAndAnswerVO.getQuestionTitle() == null 
				|| questionAndAnswerVO.getQuestionTitle().length() == 0 ) {
			return;
		}
		
		if ( questionAndAnswerVO.getQuestionDescription() == null
				|| questionAndAnswerVO.getQuestionDescription().length() == 0 ) {
			return;
		}
		
		if ( questionAndAnswerVO.getEmail() == null || questionAndAnswerVO.getEmail().length() == 0 ) {
			return;
		}
		
		if ( memberType.equals("1") && questionAndAnswerVO.getMemberTypeId() > 3 
				&& questionAndAnswerVO.getMemberTypeId() < 1) {
			return;
		}
		else if ( memberType.equals("2") && questionAndAnswerVO.getMemberTypeId() == 4 ) {
			return;
		}
		else {
			questionAndAnswerDAO.registerQuestion(questionAndAnswerVO);
		}
	}

	public List<QuestionAndAnswerVO> getMyQuestionsByEmail(String email) {
		
		if (email != null) {
			
			return questionAndAnswerDAO.getMyQuestionsByEmail(email);
		}
		
		return null;
	}
}
