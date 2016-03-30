package com.ktds.curtain.prohibitedWord.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.prohibitedWord.dao.ProhibitedWordDAO;

public class ProhibitedWordBiz {
	
	private ProhibitedWordDAO prohibitedWordDAO;
	private List<String> words;
	
	public ProhibitedWordBiz(){
		prohibitedWordDAO = new ProhibitedWordDAO();
		words = new ArrayList<String>();
	}

	/**
	 * 비방어 리스트로 얻어오기
	 * @return
	 */
	public List<String> getProhibitedWordList() {
		words = prohibitedWordDAO.getAllWords();
		return words;
	}

}
