package com.ktds.curtain.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.curtain.service.ArticleService;
import com.ktds.curtain.vo.ArticleVO;


@Controller
public class ArticleController {
	
	private ArticleService articleService;
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/write")
	public ModelAndView viewWritePage () {
		return articleService.viewWritePage();
	}
	
	@RequestMapping("/detail/{articleId}")
	public ModelAndView viewDetailPage(@PathVariable String articleId){
		return articleService.getOneArticle(articleId);
	}
	
	@RequestMapping("/modify/{articleId}")
	public ModelAndView viewModifyPage(@PathVariable String articleId){
		return articleService.getOneArticle(articleId);
	}
	
	@RequestMapping("/doModifyAction")
	public ModelAndView doModifyAction(@Valid ArticleVO articleVO, Errors errors){
		return articleService.modifyOneArticle(articleVO);
	}
}
