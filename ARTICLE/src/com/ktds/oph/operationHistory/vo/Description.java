package com.ktds.oph.operationHistory.vo;

public interface Description {
	
	public static final String VISIT_ADMIN_PAGE = "[%s]가 관리자 페이지에 접근했습니다.";
	public static final String VISIT_LOGIN_PAGE = "[%s]가 로그인 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_HOME = "[%s]님이 관리자페이지 홈에 접근했습니다.";
	public static final String VISIT_ADMIN_MEMBER_PAGE = "[%s] 님이 회원관리 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_UNIV_PAGE = "[%s] 님이 대학 관리 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_MAJOR_PAGE = "[%s] 님이 학과 관리 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_CLAIM_PAGE = "[%s] 님이 신고된 글  페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_SURVEY_ADD_PAGE = "[%s] 님이 설문등록 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_SURVEY_LIST_PAGE = "[%s] 님이 설문리스트 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_PROHIBITED_PAGE = "[%s] 님이 금칙어등록 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_ARTICLE_PAGE = "[%s] 님이 게시글관리 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_HISTORY_PAGE = "[%s] 님이 히스토리확인 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_QUESTION_PAGE = "[%s] 님이 질문관리 페이지에 접근했습니다.";
	
	
	public static final String LOGIN_SUCCESS = "[%s]가 로그인에 성공했습니다.";
	public static final String LOGIN_FAIL = "[%s]가 로그인에 실패했습니다.";
	
	
	
	
	public static final String DETAIL_LOGIN_SUCCESS = "성공한 계정 아이디 : [%s] <br/> 패스워드:[%s]";
	public static final String DETAIL_LOGIN_FAIL = "시도한 계정 아이디:[%s] <br/> 패스워드:[%s]";
	
	
}
