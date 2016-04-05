package com.ktds.curtain.survey.biz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.survey.dao.SurveyDAO;
import com.ktds.curtain.survey.vo.SurveyStatsVO;
import com.ktds.curtain.survey.vo.SurveyVO;

public class SurveyBiz {
	
	private SurveyDAO surveyDAO;
	private MemberBiz memberBiz;
	
	public SurveyBiz (){
		surveyDAO = new SurveyDAO();
		memberBiz = new MemberBiz();
	}

	public SurveyVO showTodaySurvey(String mTime) {
		return surveyDAO.selectTodaySurvey(mTime);
	}
	
	
	public boolean surveyCheck(String email, String mTime) {
		return surveyDAO.surveyCheck(email, mTime) > 0;
	}
	
	public SurveyStatsVO getSurveyStats(String mTime) {
		return surveyDAO.selectSurveyStats(mTime);
	}

	public boolean statsUpdateTodaySurvey(int choiceNumber, int surveyId, HttpServletRequest request) {
		
		if ( choiceNumber == 1) {
			surveyDAO.upDateFirst(surveyId);
			memberBiz.addPointAndModifyMemberType(null, request);
			return true;
		}
		else if ( choiceNumber == 2 ) {
			surveyDAO.upDateSecond(surveyId);
			memberBiz.addPointAndModifyMemberType(null, request);
			return true;
		}
		else if ( choiceNumber == 3 ) {
			surveyDAO.upDateThird(surveyId);
			memberBiz.addPointAndModifyMemberType(null, request);
			return true;
		}
		else if ( choiceNumber == 4 ) {
			surveyDAO.upDateFourth(surveyId);
			memberBiz.addPointAndModifyMemberType(null, request);
			return true;
		}
		return false;
		
	}

	public void upDateIsVote(String email, int surveyId) {
		surveyDAO.upDateIsVote(email, surveyId);
	}

	
	
	

}
