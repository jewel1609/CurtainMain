package com.ktds.curtain.survey.biz;

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

	public void statsUpdateTodaySurvey(int choiceNumber, int surveyId) {
		
		if ( choiceNumber == 1) {
			surveyDAO.upDateFirst(surveyId);
		}
		else if ( choiceNumber == 2 ) {
			surveyDAO.upDateSecond(surveyId);
		}
		else if ( choiceNumber == 3 ) {
			System.out.println("============");
			surveyDAO.upDateThird(surveyId);
			System.out.println("============");
		}
		else if ( choiceNumber == 4 ) {
			surveyDAO.upDateFourth(surveyId);
		}
		
	}

}
