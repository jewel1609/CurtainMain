package com.ktds.curtain.survey.biz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.ktds.curtain.survey.dao.SurveyDAO;
import com.ktds.curtain.survey.vo.SurveyStatsVO;
import com.ktds.curtain.survey.vo.SurveyVO;

public class SurveyBiz {
	
	private SurveyDAO surveyDAO;
	
	public SurveyBiz (){
		surveyDAO = new SurveyDAO();
	}

	public SurveyVO showTodaySurvey(String mTime) {
		return surveyDAO.selectTodaySurvey(mTime);
	}
	
	public SurveyStatsVO getSurveyStats(String mTime) {
		return surveyDAO.selectSurveyStats(mTime);
	}

	public boolean statsUpdateTodaySurvey(int choiceNumber, int surveyId) {
		
		if ( choiceNumber == 1) {
			surveyDAO.upDateFirst(surveyId);
			return true;
		}
		else if ( choiceNumber == 2 ) {
			surveyDAO.upDateSecond(surveyId);
			return true;
		}
		else if ( choiceNumber == 3 ) {
			surveyDAO.upDateThird(surveyId);
			return true;
		}
		else if ( choiceNumber == 4 ) {
			surveyDAO.upDateFourth(surveyId);
			return true;
		}
		return false;
		
	}

	public void upDateIsVote(String email, int surveyId) {
		surveyDAO.upDateIsVote(email, surveyId);
	}
	
	

}
