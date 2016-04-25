package com.ktds.curtain.service.impl;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.curtain.biz.ArticleBiz;
import com.ktds.curtain.service.ArticleService;
import com.ktds.curtain.vo.ArticleVO;

public class ArticleServiceImpl implements ArticleService{
	
	private ArticleBiz articleBiz;

	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}

	@Override
	public ModelAndView getOneArticle(String articleId) {
		
		ArticleVO article = articleBiz.getOneArticle(articleId);
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("article/detail");
		view.addObject("article", article);
		
		return view;
		
	}
	
	@Override
	public ModelAndView viewWritePage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/write");
		return view;
	}
	
	
}
