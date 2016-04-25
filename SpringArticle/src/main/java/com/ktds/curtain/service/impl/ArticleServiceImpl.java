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
	public ModelAndView getAllList(int pageNo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/list");
		view.addObject("articleListVO", articleBiz.getAllList(pageNo));
		return view;
	}


	@Override
	public ModelAndView deleteOneArticle(String articleId) {
		
		ModelAndView view = new ModelAndView();
		articleBiz.deleteOneArticle(articleId);
		
		view.setViewName("redirect:/list");
		
		return view;
	}

	@Override
	public ModelAndView modifyOneArticle(ArticleVO articleVO) {
		
		boolean result = articleBiz.modifyOneArticle(articleVO);
		if( result ) {
			
		}
		else {
			throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("article/detail");
		return view;
	}

	
	@Override
	public ModelAndView viewWritePage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/write");
		return view;
	}
	
	
}
