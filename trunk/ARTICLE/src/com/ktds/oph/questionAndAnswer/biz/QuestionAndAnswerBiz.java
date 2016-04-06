package com.ktds.oph.questionAndAnswer.biz;

import java.util.List;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.questionAndAnswer.dao.QuestionAndAnswerDAO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerListVO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerSearchVO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerVO;
import com.ktds.oph.util.web.Paging;

public class QuestionAndAnswerBiz {
	
	private QuestionAndAnswerDAO questionAndAnswerDAO;
	
	public QuestionAndAnswerBiz () {
		questionAndAnswerDAO = new QuestionAndAnswerDAO();
	}

	public QuestionAndAnswerListVO getQuestionList(QuestionAndAnswerSearchVO searchVO, MemberVO member) {
		int allQuestionCount = 0;
		List<QuestionAndAnswerVO> questions = null;
		
		if ( searchVO.getSearchType().equals("1") ) {
			allQuestionCount = questionAndAnswerDAO.getAllQuestionCount(searchVO);
		}
		else if ( searchVO.getSearchType().equals("2") ) {
			allQuestionCount = questionAndAnswerDAO.getAllQuestionCountByEmail(searchVO);
		}
		else if ( searchVO.getSearchType().equals("3") ) {
			allQuestionCount = questionAndAnswerDAO.getAllQuestionCountByIsCheck(searchVO);
		}
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allQuestionCount);
		paging.setPageNumber(searchVO.getPageNo()+"");
		
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		if (member.getMemberTypeId() == 6) {
			if ( searchVO.getSearchType().equals("1") ) {
				questions = questionAndAnswerDAO.getQuestions(searchVO);
			}
			else if ( searchVO.getSearchType().equals("2") ) {
				questions = questionAndAnswerDAO.getQuestionsByEmail(searchVO);
			}
			else if ( searchVO.getSearchType().equals("3") ) {
				questions = questionAndAnswerDAO.getQuestionsByIsCheck(searchVO);
			}
		}
		
		QuestionAndAnswerListVO questionAndAnswerListVO = new QuestionAndAnswerListVO();
		questionAndAnswerListVO.setQuestions(questions);
		questionAndAnswerListVO.setPaging(paging);
		return questionAndAnswerListVO;
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

	public void addNewAnswer(QuestionAndAnswerVO questionAndAnswerVO, MemberVO member) {
		if(member.getMemberTypeId() == 6) {
			questionAndAnswerDAO.addNewAnswer(questionAndAnswerVO);
		}
	}

	public boolean modifyAnswer(QuestionAndAnswerVO question, MemberVO member) {
		int updateCount = 0;
		
		if (member.getMemberTypeId() == 6) {
			updateCount = questionAndAnswerDAO.modifyAnswer(question);
			
			if (updateCount > 0) {
				return true;
			}
		}
		return false;
	}

}
