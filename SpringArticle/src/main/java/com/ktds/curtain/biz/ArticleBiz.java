package com.ktds.curtain.biz;

import com.ktds.curtain.vo.ArticleListVO;
import com.ktds.curtain.vo.ArticleVO;

public interface ArticleBiz {

	public ArticleVO getOneArticle(String articleId);
	
	public boolean insertNewArticle(ArticleVO articleVO);

	public ArticleListVO getAllList(int pageNo);

	public void deleteOneArticle(String articleId);

	public boolean modifyOneArticle(ArticleVO articleVO);

	public boolean doRecommendArticle(String articleId);

	public boolean doAddHitAction(String articleId);

}
