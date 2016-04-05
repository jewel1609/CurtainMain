package com.ktds.oph.operationHistory.vo;

public interface Description {
	
	public static final String VISIT_ADMIN_PAGE = "[%s]가 관리자 페이지에 접근했습니다.";
	public static final String VISIT_LOGIN_PAGE = "[%s]가 로그인 페이지에 접근했습니다.";
	public static final String VISIT_ADMIN_HOME = "[%s]가 관리자페이지 홈에 접근했습니다.";
	
	
	public static final String LOGIN_SUCCESS = "[%s]가 로그인에 성공했습니다.";
	public static final String LOGIN_FAIL = "[%s]가 로그인에 실패했습니다.";
	
	
	
	
	public static final String DETAIL_LOGIN_SUCCESS = "성공한 계정 아이디 : [%s] <br/> 패스워드:[%s]";
	public static final String DETAIL_LOGIN_FAIL = "시도한 계정 아이디:[%s] <br/> 패스워드:[%s]";
	
	
}
