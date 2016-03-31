package com.ktds.curtain.keyword.biz;

import java.util.List;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.keyword.dao.KeywordDAO;
import com.ktds.curtain.util.nanum.NounExtractor;

public class KeywordBiz {
	NounExtractor nounExtractor;
	KeywordDAO keywordDAO;
	
	public KeywordBiz () {
		nounExtractor = new NounExtractor();
		keywordDAO = new KeywordDAO();
	}
	
	public void setNounByArticle (ArticleVO article) {
		List<String> nouns = nounExtractor.nounExtractorByTitle(article.getArticleTitle());
		System.out.println("여기까지가 끝인가보오!!!");
		keywordDAO.setNounByArticleList(nouns);
	}
	
}
