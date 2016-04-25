package com.ktds.curtain.dao;

import com.ktds.curtain.vo.ArticleVO;

public interface ArticleDAO {

	public ArticleVO getOneArticle(String articleId);

	public int modifyOneArticle(ArticleVO articleVO);
}
