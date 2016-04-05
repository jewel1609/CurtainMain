package com.ktds.oph.operationHistory.biz;

import com.ktds.oph.operationHistory.dao.OperationHistoryDAO;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;

public class OperationHistoryBiz {

	private OperationHistoryDAO dao;
	
	public OperationHistoryBiz() {
		dao = new OperationHistoryDAO();
	}
	
	public void addHistory(OperationHistoryVO historyVO) {
		
		dao.insertHistory(historyVO);
		
	}

}
