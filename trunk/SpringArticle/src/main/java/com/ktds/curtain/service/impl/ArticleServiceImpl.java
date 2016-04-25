package com.ktds.curtain.service.impl;

import org.springframework.validation.Errors;
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
	public ModelAndView modifyOneArticle(ArticleVO articleVO, Errors errors) {
		
		// 기존의 제목이랑 수정된 제목이 같은지 비교
		//뿌려주는 값이랑 새로 articleVO를 만들어서 받아온거를 set한다음에 
		// 기존의 내용이랑 수정된 내용이 같은지 비교
		
		ModelAndView view = new ModelAndView();
		if( errors.hasErrors() ) {
			view.setViewName("article/detail");
			view.addObject("articleVO", articleVO);
			return view;
		}
		else {
			boolean result = articleBiz.modifyOneArticle(articleVO);
			if ( result ) { 
				view.setViewName("redirect:/detail");
			}
			else { 
				throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
		return view;
	}

	
	@Override
	public ModelAndView viewWritePage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/write");
		return view;
	}

	@Override
	public ModelAndView insertNewArticle(ArticleVO articleVO, Errors errors) {
		ModelAndView view = new ModelAndView();
		if (errors.hasErrors()) {
			view.setViewName("redirect:/write");
			view.addObject("articleVO", articleVO);
		}
		else {
			view.setViewName("article/list");
			boolean result = articleBiz.insertNewArticle(articleVO);
		}
		return view;
	}
	
}
