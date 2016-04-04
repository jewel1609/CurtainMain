package com.ktds.oph.major.vo;

import java.util.List;

import com.ktds.oph.util.web.Paging;

public class MajorListVO {
	private List<MajorVO> MajorList;
	private Paging paging;

	public List<MajorVO> getMajorList() {
		return MajorList;
	}
	public void setMajorList(List<MajorVO> majorList) {
		MajorList = majorList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

}
