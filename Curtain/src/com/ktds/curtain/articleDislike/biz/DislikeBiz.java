package com.ktds.curtain.articleDislike.biz;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.articleDislike.dao.DislikeDAO;
import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;

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
	public void insertOrDeleteDislikeData(ArticleDislikeVO dislikeVO) {
		// 기존에 싫어요를 했던 게시물이라면
		if ( isExistDislikeData(dislikeVO) ) { 
			articleDAO.minusDislikeCount(dislikeVO);
			dislikeDAO.deleteDislike(dislikeVO);
			
		}
		else {
			articleDAO.plusDislikeCount(dislikeVO);
			dislikeDAO.insertDislike(dislikeVO);
		
		}
		
	}

	/**
	 * 싫어요 수 체크(0아니면 1)
	 * @param dislikeVO
	 * @return
	 */
	public boolean isExistDislikeData(ArticleDislikeVO dislikeVO) {	
		System.out.print("기존에 싫어요 수 체크: ");
		System.out.println(dislikeDAO.selectDislikeCount(dislikeVO) > 0);
		return dislikeDAO.selectDislikeCount(dislikeVO) > 0;
		
	}

	/**
	 * 해당 게시글 아이디의 싫어요 수 가져오기
	 * @param dislikeVO
	 * @return
	 */
	public int getArticleDislikes(ArticleDislikeVO dislikeVO) {
		return articleDAO.getArticleDislikes(dislikeVO);
	}

}
