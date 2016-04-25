package com.ktds.curtain.service.impl;

import com.ktds.curtain.biz.ArticleBiz;
import com.ktds.curtain.service.ArticleService;

public class ArticleServiceImpl implements ArticleService{
	
	private ArticleBiz articleBiz;

	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}
	
	
}
