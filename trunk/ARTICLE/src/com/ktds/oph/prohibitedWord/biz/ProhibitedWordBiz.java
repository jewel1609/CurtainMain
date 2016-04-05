package com.ktds.oph.prohibitedWord.biz;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.prohibitedWord.dao.ProhibitedWordDAO;

public class ProhibitedWordBiz {

	private ProhibitedWordDAO prohibitedWordDAO;
	
	public ProhibitedWordBiz(){
		prohibitedWordDAO = new ProhibitedWordDAO();
	}
	
	public boolean insertProhibitedWord(String prohibitedWordName, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			prohibitedWordDAO.insertProhibitedWord(prohibitedWordName,  member);
			return true;
		}
		return false;
	}

}
