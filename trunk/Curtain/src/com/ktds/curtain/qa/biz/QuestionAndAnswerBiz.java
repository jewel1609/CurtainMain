package com.ktds.curtain.qa.biz;

import com.ktds.curtain.qa.dao.QuestionAndAnswerDAO;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

public class QuestionAndAnswerBiz {
	
	private QuestionAndAnswerDAO questionAndAnswerDAO;
	
	public QuestionAndAnswerBiz () {
		questionAndAnswerDAO = new QuestionAndAnswerDAO();
	}

	public void registerQuestion(QuestionAndAnswerVO questionAndAnswerVO, String email, String memberType) {
		
		// TODO validation check 주석
		if ( questionAndAnswerVO.getQuestionTitle() == null 
				&& questionAndAnswerVO.getQuestionTitle().length() == 0 ) {
			return;
		}
		
		if ( questionAndAnswerVO.getQuestionDescription() == null
				&& questionAndAnswerVO.getQuestionDescription().length() ==0 ) {
			return;
		}
		
		if ( email == null && email.length() == 0 ) {
			return;
		}
		
		if ( memberType.equals("1") ) {
			questionAndAnswerDAO.registerQuestionByStudent(questionAndAnswerVO, email);
		}
		else if ( memberType.equals("2") ) {
			questionAndAnswerDAO.registerQuestionByCompany(questionAndAnswerVO, email);
		}
		else {
			return;
		}
	}
}
