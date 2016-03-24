package com.ktds.curtain.qa.biz;

import java.util.List;

import com.ktds.curtain.qa.dao.QuestionAndAnswerDAO;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

public class QuestionAndAnswerBiz {
	
	private QuestionAndAnswerDAO questionAndAnswerDAO;
	
	public QuestionAndAnswerBiz () {
		questionAndAnswerDAO = new QuestionAndAnswerDAO();
	}

	public void registerQuestion(QuestionAndAnswerVO questionAndAnswerVO, String email, String memberType) {
		
		if ( questionAndAnswerVO.getQuestionTitle() == null 
				|| questionAndAnswerVO.getQuestionTitle().length() == 0 ) {
			return;
		}
		
		if ( questionAndAnswerVO.getQuestionDescription() == null
				|| questionAndAnswerVO.getQuestionDescription().length() == 0 ) {
			return;
		}
		
		if ( email == null || email.length() == 0 ) {
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

	public List<QuestionAndAnswerVO> getMyQuestionsByStudentEmail(String email) {
		
		if (email != null) {
			
			return questionAndAnswerDAO.getMyQuestionsByStudentEmail(email);
		}
		
		return null;
	}
}
