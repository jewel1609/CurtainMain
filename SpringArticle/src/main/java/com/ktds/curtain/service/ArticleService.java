package com.ktds.curtain.service;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.curtain.vo.ArticleVO;

public interface ArticleService {

	public ModelAndView getOneArticle(String articleId);

	public ModelAndView getAllList(int pageNo);
	
	public ModelAndView deleteOneArticle(String articleId);
	
	public ModelAndView modifyOneArticle(ArticleVO articleVO);

	public ModelAndView viewWritePage();
	
}
