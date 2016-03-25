package com.ktds.curtain.articleLike.biz;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.articleLike.dao.ArticleLikeDAO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;

public class ArticleLikeBiz {
	
	private ArticleLikeDAO articleLikeDAO;
	private ArticleDAO articleDAO;
	
	public ArticleLikeBiz() {
		articleLikeDAO = new ArticleLikeDAO();
		articleDAO = new ArticleDAO();
	}
	
	public boolean isExistLikeData(ArticleLikeVO articleLikeVO) {
		return articleLikeDAO.selectLikeCount(articleLikeVO) > 0;
	}

	public void insertOrDeleteLikeData(ArticleLikeVO articleLikeVO) {
		if (isExistLikeData(articleLikeVO)) {
			articleLikeDAO.deleteLike(articleLikeVO);
			articleDAO.minusLikeCount(articleLikeVO);
		}
		else {
			articleLikeDAO.insertLike(articleLikeVO);
			articleDAO.plusLikeCount(articleLikeVO);
		}
	}
	
	public int getArticleLikes(ArticleLikeVO articleLikeVO) {
		return articleDAO.getArticleLikes(articleLikeVO);
	}

}
