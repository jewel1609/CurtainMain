package com.ktds.curtain.qa.biz;

import java.util.List;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.qa.dao.QuestionAndAnswerDAO;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

public class QuestionAndAnswerBiz {
	
	private QuestionAndAnswerDAO questionAndAnswerDAO;
	
	public QuestionAndAnswerBiz () {
		questionAndAnswerDAO = new QuestionAndAnswerDAO();
	}

	public void registerQuestion(QuestionAndAnswerVO questionAndAnswerVO) {
		
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
		
		questionAndAnswerVO.setQuestionDescription(
				questionAndAnswerVO.getQuestionDescription().replace("\n", "<br />"));
		
		questionAndAnswerDAO.registerQuestion(questionAndAnswerVO);
	}

	public List<QuestionAndAnswerVO> getMyQuestionsByEmail(MemberVO member) {
		
		if (member != null) {
			return questionAndAnswerDAO.getMyQuestionsByEmail(member);
		}
		
		return null;
	}

	public void isSetIsCheckedByQuestionId(String questionId) {
		
		try {
			questionAndAnswerDAO.setIsCheckedByQuestionId(Integer.parseInt(questionId));
		}
		catch (NumberFormatException nfe) {
			return;
		}
		
	}

	public QuestionAndAnswerVO getMyQuestionByQuestionId(String questionId) {
		try {
			return questionAndAnswerDAO.getMyQuestionByQuestionId(Integer.parseInt(questionId));
		}
		catch (NumberFormatException nfe) {
			return null;
		}
	}
}
