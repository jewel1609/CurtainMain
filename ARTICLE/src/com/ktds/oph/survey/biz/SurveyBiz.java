package com.ktds.oph.survey.biz;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.survey.dao.SurveyDAO;

public class SurveyBiz {

	private SurveyDAO surveyDAO;
	
	public SurveyBiz(){
		surveyDAO = new SurveyDAO();
		
	}
	
	public void insertSurvey(String surveyTitle, String surveyAnswer1, String surveyAnswer2, String surveyAnswer3,
			String surveyAnswer4, String surveyDate, MemberVO member) {
		
		if( member.getMemberTypeId() == 6 ) {
			surveyDAO.insertSurvey(surveyTitle, surveyAnswer1,  surveyAnswer2,  surveyAnswer3,
									surveyAnswer4,  surveyDate,  member);
		}
		
	}
}
