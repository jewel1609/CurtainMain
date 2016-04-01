package com.ktds.oph.article.vo;

public class ArticleSearchVO {
	
	// 검색만을 위한 VO
	
	private int pageNo;
	private int startIndex;
	private int endIndex;

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
