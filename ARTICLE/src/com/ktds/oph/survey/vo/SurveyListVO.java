package com.ktds.oph.survey.vo;

import java.util.List;

import com.ktds.oph.util.web.Paging;

public class SurveyListVO {

	private List<SurveyVO> surveyList;
	private Paging paging;
	
	public List<SurveyVO> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<SurveyVO> surveyList) {
		this.surveyList = surveyList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
	
}
