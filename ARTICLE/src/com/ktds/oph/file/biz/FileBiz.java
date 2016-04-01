package com.ktds.oph.file.biz;

import com.ktds.oph.file.dao.FileDAO;
import com.ktds.oph.file.vo.FileVO;

public class FileBiz {
	private FileDAO fileDAO;
	
	public FileBiz() {
		fileDAO = new FileDAO();
		
	}
	
	public boolean doWriteFile(FileVO file) {
		int insertCount = fileDAO.doWriteFile(file);
		return insertCount > 0;
	}

}
