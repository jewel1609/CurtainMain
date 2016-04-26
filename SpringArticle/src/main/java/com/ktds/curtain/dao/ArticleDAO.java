package com.ktds.curtain.dao;

import java.util.List;

import com.ktds.curtain.vo.ArticleSearchVO;
import com.ktds.curtain.vo.ArticleVO;

public interface ArticleDAO {

	public ArticleVO getOneArticle(String articleId);

	public int getTotalCount();

	public void deleteOneArticle(String articleId);

	public int modifyOneArticle(ArticleVO articleVO);

	public List<ArticleVO> getAllList(ArticleSearchVO articleSearchVO);

	public int doRecommendArticle(String articleId);

	public int doAddHitAction(String articleId);
}
