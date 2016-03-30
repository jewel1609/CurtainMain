package com.ktds.curtain.articleScrab.biz;


import com.ktds.curtain.articleScrab.dao.ScrabDAO;
import com.ktds.curtain.articleScrab.vo.ArticleScrabVO;

public class ScrabBiz {
	
	private ScrabDAO scrabDAO;
	//private ArticleDAO articleDAO;
	
	public ScrabBiz(){
		scrabDAO = new ScrabDAO();
	}

	/**
	 * 스크랩 수 추가/삭제
	 * @param scrabVO
	 */
	public void insertOrDeleteScrabData(ArticleScrabVO scrabVO) {
		
		if( isExistScrabData(scrabVO) ){
			scrabDAO.deleteScrab(scrabVO);
		}
		else {
			scrabDAO.insertScrab(scrabVO);
		}
	}

	/**
	 * 스크랩된 게시글인지 아닌지 여부
	 * @param scrabVO
	 * @return
	 */
	public boolean isExistScrabData(ArticleScrabVO scrabVO) {
		return scrabDAO.selectScrabCount(scrabVO) > 0;
	}

}
