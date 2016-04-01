<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function () {
	
	$("#btnLogin").click(function() {
		var form = $("#loginForm");
		form.attr("method", "post");
		form.attr("action", "/doLogin");
		form.submit();
	});
});
</script>

<div class="container">
  <h2>로그인</h2>

  <form role="form" id="loginForm">
    <div class="form-group">
      <label for="inputdefault">아이디</label>
      <input class="form-control input-lg" id="userId" name="userId" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputsm">비밀번호</label>
      <input class="form-control input-lg" id="userPw" name="userPw" type="password"/>
    </div>
  </form>

  <input type="button" class="btn btn-default" id="btnLogin" value="로그인"/>
</div>

</body>
</html>
