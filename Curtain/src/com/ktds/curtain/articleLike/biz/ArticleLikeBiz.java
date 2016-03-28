package com.ktds.curtain.articleLike.biz;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.articleLike.dao.ArticleLikeDAO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.member.vo.MemberVO;

public class ArticleLikeBiz {
	
	private ArticleLikeDAO articleLikeDAO;
	private ArticleDAO articleDAO;
	
	public ArticleLikeBiz() {
		articleLikeDAO = new ArticleLikeDAO();
		articleDAO = new ArticleDAO();
	}
	
	public boolean isExistLikeData(ArticleLikeVO articleLikeVO, MemberVO member) {
		return articleLikeDAO.selectLikeCount(articleLikeVO, member) > 0;
	}

	public void insertOrDeleteLikeData(ArticleLikeVO articleLikeVO, MemberVO member) {
		if (isExistLikeData(articleLikeVO, member)) {
			articleLikeDAO.deleteLike(articleLikeVO, member);
			articleDAO.minusLikeCount(articleLikeVO);
		}
		else {
			articleLikeDAO.insertLike(articleLikeVO, member);
			articleDAO.plusLikeCount(articleLikeVO);
		}
	}
	
	public int getArticleLikes(ArticleLikeVO articleLikeVO) {
		return articleDAO.getArticleLikes(articleLikeVO);
	}

}
