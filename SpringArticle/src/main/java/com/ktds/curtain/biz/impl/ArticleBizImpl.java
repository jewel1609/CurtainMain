package com.ktds.curtain.biz.impl;

import com.ktds.curtain.biz.ArticleBiz;
import com.ktds.curtain.dao.ArticleDAO;
import com.ktds.curtain.vo.ArticleVO;

public class ArticleBizImpl implements ArticleBiz {

	private ArticleDAO articleDAO;

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public ArticleVO getOneArticle(String articleId) {
		return articleDAO.getOneArticle(articleId);
	}
	
}