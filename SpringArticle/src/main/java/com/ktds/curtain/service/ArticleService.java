package com.ktds.curtain.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.curtain.vo.ArticleVO;

public interface ArticleService {

	public ModelAndView getOneArticle(String articleId);

	public ModelAndView viewWritePage();

	public ModelAndView insertNewArticle(ArticleVO articleVO, Errors errors);
	
	public ModelAndView getAllList(int pageNo);
	
	public ModelAndView deleteOneArticle(String articleId);

	public ModelAndView modifyOneArticle(ArticleVO articleVO, Errors errors);

	public ModelAndView viewModifyPage(String articleId);

	public ModelAndView doRecommendArticle(String articleId);

	public ModelAndView doAddHitAction(String articleId);

}
