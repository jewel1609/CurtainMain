package com.ktds.curtain.history.biz;

import com.ktds.curtain.history.dao.OperationHistoryDAO;
import com.ktds.curtain.history.vo.OperationHistoryVO;

public class OperationHistoryBiz {

	private OperationHistoryDAO dao;
	
	public OperationHistoryBiz() {
		dao = new OperationHistoryDAO();
	}
	
	public void addHistory(OperationHistoryVO historyVO) {
		
		dao.insertHistory(historyVO);
		
	}

}



