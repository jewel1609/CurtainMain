package com.ktds.curtain.history.vo;

public interface Description {

	public static final String VISIT_MAIN_PAGE = "[%s]가 메인 페이지에 접근했습니다.";
	public static final String VISIT_LOGIN_PAGE ="[%s]가 로그인 페이지에 접근했습니다.";
	public static final String VISIT_REGIST_PAGE ="[%s]가 회원가입 페이지에 접근했습니다.";

	public static final String REGIST_STD_MEMBER ="[%s]가 학생회원으로 가입했습니다.";
	public static final String REGIST_COMP_MEMBER ="[%s]가 기업회원으로 가입했습니다.";
	
	
	
	public static final String LOGIN ="[%s] 님이 로그인 했습니다.";
	public static final String LOGIN_FAIL = "[%s]가 [%s]로 로그인을 시도했지만, 실패 했습니다.";
	public static final String ALREADY_LOGIN = "[%s] 님이 이미 로그인되어, 학과 게시판으로 이동합니다.";
	
	public static final String VISIT_ARTICLE_MAJOR = "[%s] 님이 학과 게시판에 접근했습니다.";
	public static final String VISIT_ARTICLE_UNIV = "[%s] 님이 대학 게시판에 접근했습니다.";
	public static final String VISIT_ARTICLE_FREE = "[%s] 님이 자유 게시판에 접근했습니다.";
	public static final String VISIT_ARTICLE_PRO = "[%s] 님이 홍보 게시판에 접근했습니다.";
	
	public static final String VISIT_ARTICLE_SECRET_ONE = "[%s] 님이 비밀게시판(한겹)에 접근했습니다.";
	public static final String VISIT_ARTICLE_SECRET_TWO = "[%s] 님이 비밀게시판(두겹)에 접근했습니다.";
	
	public static final String VISIT_MY_PAGE = "[%s] 님이 마이페이지에 접근했습니다.";
	public static final String VISIT_MODIFY_PAGE = "[%s] 님이 회원정보변경페이지에 접근했습니다.";
	public static final String VISIT_SECEDE_PAGE = "[%s] 님이 회원 탈퇴페이지에 접근했습니다.";
	
	public static final String VISIT_FAQ_QNA = "[%s] 님이 FAQ/Q&A페이지에 접근했습니다.";
	public static final String VISIT_MY_QUESTION = "[%s] 님이 내가 한 질문들 페이지에 접근했습니다.";
	public static final String VISIT_REGIST_QUESTION = "[%s] 님이 문의하기 페이지에 접근했습니다.";
	
	public static final String VISIT_DETAIL = "[%s] 님이 [%s]번의 글을 읽었습니다.";
	public static final String VISIT_DETAIL_QUESTION = "[%s] 님이  문의한 글 [%s]번을 읽었습니다.";
	
	public static final String WRITE_MAJOR = "[%s] 님이 학과 게시판에 글을 작성했습니다.";
	public static final String WRITE_UNIV = "[%s] 님이 대학 게시판에 글을 작성했습니다.";
	public static final String WRITE_FREE = "[%s] 님이 자유게시판에 글을 작성했습니다.";
	public static final String WRITE_ONE_LAYER = "[%s] 님이 비밀게시판(한겹)에 글을 작성했습니다.";
	public static final String WRITE_TWO_LAYER = "[%s] 님이 비밀게시판(두겹)에 글을 작성했습니다.";
	public static final String WRITE_PROMOTION = "[%s] 님이 홍보게시판에 글을 작성했습니다.";
	public static final String WRITE_QUESTION = "[%s] 님이 문의글을 작성했습니다.";

	public static final String MODIFY_MEMBER_INFO = "[%s] 님이 회원정보를 변경했습니다.";
	public static final String MODIFY_MEMBER_PASSWORD = "[%s] 님이 비밀번호를 변경했습니다.";
	public static final String MODIFY_MEMBER_PASSWORD_FAIL = "[%s] 님이 비밀번호를 변경에 실패했습니다.";
	
	public static final String SECEDE_MEMBER = "[%s]님이 회원탈퇴하셨습니다.";
	
	
			
	public static final String LIST = "[%s]님이 목록보기 페이지에 접근했습니다.";
	public static final String LIST_PAGING = "[%s]님이 [%s]번째 페이지로 이동했습니다.";
	public static final String DETAIL = "[%s]님이 [%s]번째 글을 읽었습니다.";
	public static final String LIST_SEARCH = "[%s]님이 목록보기 페이지에서 [%s]로 [%s]를 검색했습니다.";
	
	public static final String DETAIL_DESCRIPTION ="제목 : [%s]<br/>글쓴이 : [%s]<br/>내용 : [%s]<br/>";
	public static final String DETAIL_MEMBER_INFO = "변경한 일반이메일 : [%s]<br/> 변경한 닉네임 : [%s]";
	public static final String DETAIL_MEMBER_PASSWORD = "변경한 패스워드 : [%s]";
	public static final String DETAIL_SECEDE_MEMBER = "탈퇴 이메일 : [%s] <br/> 탈퇴 사유 : [%s]";
	public static final String DETAIL_REGIST_STD_MEMBER = "가입 학생 대학 : [%s] <br/> 학과 : [%s] <br/> 이메일 : [%s] <br/> 닉네임: [%s]";
	public static final String DETAIL_REGIST_COMP_MEMBER = "가입 기업 회사명 : [%s] <br/> 핸드폰 번호 : [%s] <br/> 회사이메일 : [%s] <br/> 닉네임: [%s]";
	
	
}
