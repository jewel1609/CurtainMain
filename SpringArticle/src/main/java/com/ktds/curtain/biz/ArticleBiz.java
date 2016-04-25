package com.ktds.curtain.biz;

import com.ktds.curtain.vo.ArticleListVO;
import com.ktds.curtain.vo.ArticleVO;

public interface ArticleBiz {

	public ArticleVO getOneArticle(String articleId);

	public ArticleListVO getAllList(int pageNo);

	public void deleteOneArticle(String articleId);

	public boolean modifyOneArticle(ArticleVO articleVO);

}
