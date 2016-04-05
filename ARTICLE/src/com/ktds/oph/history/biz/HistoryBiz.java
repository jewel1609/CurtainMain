package com.ktds.oph.history.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.history.dao.HistoryDAO;
import com.ktds.oph.history.vo.HistoryListVO;
import com.ktds.oph.history.vo.HistorySearchVO;
import com.ktds.oph.history.vo.HistoryVO;
import com.ktds.oph.univ.vo.UnivListVO;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.web.Paging;

public class HistoryBiz {
	
	private HistoryDAO historyDAO;
	
	public HistoryBiz() {
		historyDAO = new HistoryDAO();
	}

	public HistoryListVO getAllHistory(HistorySearchVO historySearchVO, HistoryVO historyVO) {
		int allHistoryCount = historyDAO.getAllHistoryCount(historyVO);
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allHistoryCount);
		paging.setPageNumber(historySearchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		historySearchVO.setStartIndex( paging.getStartArticleNumber() );
		historySearchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<HistoryVO> historys = new ArrayList<HistoryVO>();
		historys = historyDAO.getAllHistory(historySearchVO, historyVO);
		// paging 바꾸는 이유 검색 때문에
		
		HistoryListVO historyList = new HistoryListVO();
		historyList.setHistoryList(historys);
		historyList.setPaging(paging);
		
		// 몇 번째 페이지
		
		
		return historyList;
		
	}

}
