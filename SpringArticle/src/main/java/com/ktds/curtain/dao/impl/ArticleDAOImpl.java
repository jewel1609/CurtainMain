package com.ktds.curtain.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.curtain.dao.ArticleDAO;
import com.ktds.curtain.vo.ArticleVO;

public class ArticleDAOImpl extends SqlSessionDaoSupport implements ArticleDAO {

	@Override
	public ArticleVO getOneArticle(String articleId) {
		return getSqlSession().selectOne("ArticleDAO.getOneArticle",articleId);
	}
	
	
	
}
