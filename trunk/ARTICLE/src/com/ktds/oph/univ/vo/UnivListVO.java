package com.ktds.oph.univ.vo;

import java.util.List;

import com.ktds.oph.util.web.Paging;

public class UnivListVO {
	
	private List<UnivVO> univList;
	private Paging paging;

	public List<UnivVO> getUnivList() {
		return univList;
	}
	public void setUnivList(List<UnivVO> univList) {
		this.univList = univList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	

}
