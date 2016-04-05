package com.ktds.oph.history.vo;

import java.util.List;

import com.ktds.oph.util.web.Paging;

public class HistoryListVO {
	private List<HistoryVO> historyList;
	private Paging paging;

	public List<HistoryVO> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<HistoryVO> historyList) {
		this.historyList = historyList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
