package com.ktds.curtain.articleDislike.biz;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.articleDislike.dao.DislikeDAO;
import com.ktds.curtain.articleDislike.vo.DislikeVO;

public class DislikeBiz {

	private DislikeDAO dislikeDAO;
	private ArticleDAO articleDAO;
	
	public DislikeBiz(){
		dislikeDAO = new DislikeDAO();
		articleDAO = new ArticleDAO();
	}
	
	/**
	 * 싫어요 수 추가/삭제
	 * @param dislikeVO
	 */
	public void insertOrDeleteDislikeData(DislikeVO dislikeVO) {
		if( isExistDislikeData(dislikeVO) ) { 
			articleDAO.minusDislikeCount(dislikeVO);
			dislikeDAO.deleteDislike(dislikeVO);
			
		}
		else{
			articleDAO.plusDislikeCount(dislikeVO);
			dislikeDAO.insertDislike(dislikeVO);
		
		}
		
	}

	/**
	 * 싫어요 수 체크
	 * @param dislikeVO
	 * @return
	 */
	public boolean isExistDislikeData(DislikeVO dislikeVO) {	
		System.out.println("싫어요 수:"+dislikeDAO.selectDislikeCount(dislikeVO));
		return dislikeDAO.selectDislikeCount(dislikeVO) > 0;
		
	}

	public int getArticleDislikes(DislikeVO dislikeVO) {
		return articleDAO.getArticleDislikes(dislikeVO);
	}

}
