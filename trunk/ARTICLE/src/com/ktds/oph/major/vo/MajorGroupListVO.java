package com.ktds.oph.major.vo;

import java.util.List;

import com.ktds.oph.util.web.Paging;

public class MajorGroupListVO {
	private List<MajorGroupVO> MajorGroupList;
	private Paging paging;

	public List<MajorGroupVO> getMajorGroupList() {
		return MajorGroupList;
	}
	public void setMajorGroupList(List<MajorGroupVO> majorGroupList) {
		MajorGroupList = majorGroupList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
