<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>커튼 회원 가입</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function () {
	
	$("#btnRegister").click(function() {
		var form = $("#registerForm");
		form.attr("method", "post");
		form.attr("action", "/doRegister");
		form.submit();
	});
});
	
</script>
<body>

<div class="container">
  <h2>회원 가입</h2>
  <p>아래 항목을 입력한 뒤 회원가입합니다.</p>
  <form role="form" id="registerForm">
    <div class="form-group">
      <label for="inputdefault">아이디</label>
      <input class="form-control input-lg" id="userId" name="userId" type="text" placeholder="Enter ID"/>
    </div>
    <div class="form-group">
      <label for="inputlg">닉네임</label>
      <input class="form-control input-lg" id="niceName" name="niceName" type="text" placeholder="Enter NickName"/>
    </div>
    <div class="form-group">
      <label for="inputsm">비밀번호</label>
      <input class="form-control input-lg" id="userPw" name="userPw" type="text" placeholder="Enter Password"/>
    </div>
        <div class="form-group">
      <label for="inputsm">이메일</label>
      <input class="form-control input-lg" id="userEmail" name="userEmail" type="text" placeholder="Enter Email">
    </div>

    <input type="button" class="btn btn-default" id="btnRegister" value="등록"/>
  </form>
</div>

</body>
</html>
