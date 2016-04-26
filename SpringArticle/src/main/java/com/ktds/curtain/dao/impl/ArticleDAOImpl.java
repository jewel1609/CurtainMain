package com.ktds.curtain.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.curtain.dao.ArticleDAO;
import com.ktds.curtain.vo.ArticleSearchVO;
import com.ktds.curtain.vo.ArticleVO;

public class ArticleDAOImpl extends SqlSessionDaoSupport implements ArticleDAO {

	@Override
	public ArticleVO getOneArticle(String articleId) {
		return getSqlSession().selectOne("ArticleDAO.getOneArticle",articleId);
	}

	@Override
	public int getTotalCount() {
		return getSqlSession().selectOne("ArticleDAO.getTotalCount");
	}
	
	@Override
	public void deleteOneArticle(String articleId) {
		getSqlSession().delete("ArticleDAO.deleteOneArticle", articleId);
	}

	@Override
	public int modifyOneArticle(ArticleVO articleVO) {
		return getSqlSession().update("ArticleDAO.modifyOneArticle", articleVO);
	}

	@Override
	public List<ArticleVO> getAllList(ArticleSearchVO articleSearchVO) {
		return getSqlSession().selectList("ArticleDAO.getAllList", articleSearchVO);
	}

	@Override
	public int doRecommendArticle(String articleId) {
		return getSqlSession().update("ArticleDAO.doRecommendArticle", articleId);
	}

	@Override
	public int doAddHitAction(String articleId) {
		return getSqlSession().update("ArticleDAO.doAddHitAction", articleId);
	}
	
	
}
