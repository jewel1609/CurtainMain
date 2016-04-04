package com.ktds.curtain.history.vo;

public interface Description {

	public static final String VISIT_MAIN_PAGE = "[%s]가 메인 페이지에 접근했습니다.";
	public static final String VISIT_LOGIN_PAGE ="[%s]가 로그인 페이지에 접근했습니다.";
	public static final String LOGIN ="[%s] 님이 로그인 했습니다.";
	public static final String LOGIN_FAIL = "[%s]가 [%s]로 로그인을 시도했지만, 실패 했습니다.";
	public static final String ALREADY_LOGIN = "[%s] 님이 이미 로그인되어, 학과 게시판으로 이동합니다.";
	
	public static final String VISIT_ARTICLE_MAJOR = "[%s]가 학과 게시판에 접근했습니다.";
	public static final String VISIT_ARTICLE_UNIV = "[%s]가 대학 게시판에 접근했습니다.";
	public static final String VISIT_ARTICLE_FREE = "[%s]가 자유 게시판에 접근했습니다.";
	public static final String VISIT_ARTICLE_PRO = "[%s]가 홍보 게시판에 접근했습니다.";
	
	public static final String VISIT_ARTICLE_SECRET_ONE = "[%s]가 비밀게시판(한겹)에 접근했습니다.";
	public static final String VISIT_ARTICLE_SECRET_TWO = "[%s]가 비밀게시판(두겹)에 접근했습니다.";
	
	public static final String VISIT_MY_PAGE = "[%s]가 마이페이지에 접근했습니다.";
	public static final String VISIT_FAQ_QNA = "[%s]가 FAQ/Q&A페이지에 접근했습니다.";
	
	public static final String WRITE_MAJOR = "[%s]가 학과 게시판에 글을 작성했습니다.";
	public static final String WRITE_UNIV = "[%s]가 대학 게시판에 글을 작성했습니다.";
	
	
	
	
	public static final String LIST = "[%s]님이 목록보기 페이지에 접근했습니다.";
	public static final String LIST_PAGING = "[%s]님이 [%s]번째 페이지로 이동했습니다.";
	public static final String DETAIL = "[%s]님이 [%s]번째 글을 읽었습니다.";
	public static final String LIST_SEARCH = "[%s]님이 목록보기 페이지에서 [%s]로 [%s]를 검색했습니다.";
	
	public static final String DETAIL_DESCRIPTION ="제목 : [%s]<br/>글쓴이 : [%s]<br/>내용 : [%s]<br/>";
	
	
}
