package com.ktds.oph.survey.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.survey.dao.SurveyDAO;
import com.ktds.oph.survey.vo.SurveyListVO;
import com.ktds.oph.survey.vo.SurveySearchVO;
import com.ktds.oph.survey.vo.SurveyVO;
import com.ktds.oph.util.web.Paging;

public class SurveyBiz {

	private SurveyDAO surveyDAO;
	
	public SurveyBiz(){
		surveyDAO = new SurveyDAO();
		
	}
	
	public boolean insertSurvey(String surveyTitle, String surveyAnswer1, String surveyAnswer2, String surveyAnswer3,
			String surveyAnswer4, String surveyDate, MemberVO member) {
		int surveyId = 0;
		if( member.getMemberTypeId() == 6 ) {
			surveyDAO.insertSurvey(surveyTitle, surveyAnswer1,  surveyAnswer2,  surveyAnswer3,
									surveyAnswer4,  surveyDate,  member);
			surveyId = surveyDAO.getSurveyId(surveyTitle, surveyAnswer1,  surveyAnswer2,  surveyAnswer3,
					surveyAnswer4,  surveyDate);
			surveyDAO.insertSurveyStats(surveyId);
			return true;
		}
		return false;
	}

	public SurveyListVO getAllSurvey(SurveySearchVO searchVO) {

		int allSurveyCount = surveyDAO.getAllSurveyCount();
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allSurveyCount);
		paging.setPageNumber(searchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		searchVO.setStartIndex( paging.getStartArticleNumber() );
		searchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<SurveyVO> survey = new ArrayList<SurveyVO>();
		survey = surveyDAO.getAllSurvey(searchVO);
		
		SurveyListVO surveyList = new SurveyListVO();
		surveyList.setSurveyList(survey);
		surveyList.setPaging(paging);
		
		return surveyList;
		
	}

	public SurveyVO getSurveyInfoBySurveyId(String surveyId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			return surveyDAO.getSurveyInfoBySurveyId(surveyId);
		}
		return null;
	}

	public void modifySurvey(String surveyId,String surveyTitle, String surveyAnswer1, String surveyAnswer2, String surveyAnswer3,
			String surveyAnswer4, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			surveyDAO.modifyMember(surveyId,surveyTitle,surveyAnswer1,surveyAnswer2,surveyAnswer3,surveyAnswer4,member);
		}
	}

	public void deleteSurvey(String[] deleteSurveyId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			for(String surveyId : deleteSurveyId){
				surveyDAO.deleteSurvey(surveyId);
			}
			
		}
		
	}
}
