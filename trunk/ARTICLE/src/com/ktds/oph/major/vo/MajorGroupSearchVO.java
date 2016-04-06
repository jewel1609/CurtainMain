package com.ktds.oph.major.vo;

public class MajorGroupSearchVO {
	private int pageNo;
	private int startIndex;
	private int endIndex;
	private String searchKeyword;
	private String searchType;
	private int majorGroupId;

	public int getMajorGroupId() {
		return majorGroupId;
	}
	public void setMajorGroupId(int majorGroupId) {
		this.majorGroupId = majorGroupId;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}