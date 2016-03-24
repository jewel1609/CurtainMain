package com.ktds.curtain.file.biz;

import java.util.List;

import com.ktds.curtain.file.dao.FileDAO;
import com.ktds.curtain.file.vo.FileVO;

public class FileBiz {
	
	private FileDAO fileDAO;

	public FileBiz() {
		fileDAO = new FileDAO();
	}

	public void insertFile(FileVO file) {
		fileDAO.insertOneFile(file);
		
	}

}
