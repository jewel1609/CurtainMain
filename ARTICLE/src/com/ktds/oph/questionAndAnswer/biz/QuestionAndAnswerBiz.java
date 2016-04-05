package com.ktds.oph.questionAndAnswer.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.questionAndAnswer.dao.QuestionAndAnswerDAO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerVO;

public class QuestionAndAnswerBiz {
	
	private QuestionAndAnswerDAO questionAndAnswerDAO;
	
	public QuestionAndAnswerBiz () {
		questionAndAnswerDAO = new QuestionAndAnswerDAO();
	}

	public List<QuestionAndAnswerVO> getAllQuestions(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		List<QuestionAndAnswerVO> questions = null;
		
		if (member.getMemberTypeId() == 6) {
			questions = questionAndAnswerDAO.getAllQuestions();
		}

		return questions;
		
	}

	public QuestionAndAnswerVO getQuestionByQuestionId(String questionId) {
		QuestionAndAnswerVO question = null;
		
		try {
			question = questionAndAnswerDAO.getQuestionByQuestionId(Integer.parseInt(questionId));
		}
		catch (NumberFormatException nfe) {
			
		}
		
		return question;
	}

}
