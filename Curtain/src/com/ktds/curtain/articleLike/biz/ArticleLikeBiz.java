package com.ktds.curtain.articleLike.biz;

import com.ktds.curtain.articleLike.dao.ArticleLikeDAO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;

public class ArticleLikeBiz {
	
	private ArticleLikeDAO articleLikeDAO;
	
	public ArticleLikeBiz() {
		articleLikeDAO = new ArticleLikeDAO();
	}
	
	public boolean isExistLikeData(ArticleLikeVO articleLikeVO) {
		return articleLikeDAO.selectLikeCount(articleLikeVO) > 0;
	}

	public void insertOrDeleteLikeData(ArticleLikeVO articleLikeVO) {
		if (isExistLikeData(articleLikeVO)) {
			articleLikeDAO.deleteLike(articleLikeVO);
//			minus
		}
		else {
			articleLikeDAO.insertLike(articleLikeVO);
		}
		
	}
	
//	private void 

}
