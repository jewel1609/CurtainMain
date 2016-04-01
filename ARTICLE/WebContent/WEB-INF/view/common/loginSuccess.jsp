<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 text-left">
			<h1>Welcome ${member.nickName} 님!</h1>
			<p>로그인에 성공했습니다!</p>
			<hr>
			<h3>커튼소개</h3>
			<p>커튼은 대학생을 위한 익명 게시판입니다! 많은 사랑 부탁드려요.</p>
		</div>
		<div class="col-sm-2 sidenav"></div>
	</div>
</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>