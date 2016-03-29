package com.ktds.curtain.survey.biz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.ktds.curtain.survey.dao.SurveyDAO;
import com.ktds.curtain.survey.vo.SurveyVO;

public class SurveyBiz {
	
	private SurveyDAO surveyDAO;
	
	public SurveyBiz (){
		surveyDAO = new SurveyDAO();
	}

	public SurveyVO showTodaySurvey(String mTime) {
		return surveyDAO.selectTodaySurvey(mTime);
	}

}
