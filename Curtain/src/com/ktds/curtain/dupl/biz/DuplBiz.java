package com.ktds.curtain.dupl.biz;

import com.ktds.curtain.dupl.dao.DuplDAO;
import com.ktds.curtain.dupl.vo.DuplVO;

public class DuplBiz {
	
	private DuplDAO duplDAO;
	
	public DuplBiz() {
		duplDAO = new DuplDAO();
	}
	

	public boolean isExistNickName(String userNickName) {

		return duplDAO.isExistNickName(userNickName) > 0;
		
	}

}
