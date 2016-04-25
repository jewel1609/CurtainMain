package com.ktds.curtain.dao;

import com.ktds.curtain.vo.ArticleVO;

public interface ArticleDAO {

	public ArticleVO getOneArticle(String articleId);

	public void deleteOneArticle(String articleId);

	public int modifyOneArticle(ArticleVO articleVO);
}
