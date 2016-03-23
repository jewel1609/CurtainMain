<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="http://www.w3schools.com/lib/w3-theme-teal.css">
<link href='https://fonts.googleapis.com/css?family=RobotoDraft'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "RobotoDraft", "Roboto", sans-serif;
}

.w3-sidenav a {
	padding: 16px;
	font-weight: bold
}
</style>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<body>

	<nav class="w3-sidenav w3-collapse w3-white w3-animate-left w3-card-2"
		style="z-index: 3; width: 250px; height: 400px; margin-top: 185px;">
		<a href="#" class="w3-border-bottom w3-large"><img
			src="http://www.w3schools.com/images/w3schools.png"
			style="width: 80%;"></a> <a href="javascript:void(0)"
			onclick="w3_close()"
			class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>
		<div class="w3-accordion">
			<a onclick="myAccordion('demo')" href="javascript:void(0)">학생 게시판<i
				class="fa fa-caret-down"></i></a>
			<div id="demo"
				class="w3-accordion-content w3-animate-left w3-padding">
				<a href="#">컴퓨터공학</a> <a href="#">밀당대학교</a>
			</div>
		</div>
		<a href="#">홍보게시판</a> <a href="#">마이페이지</a> <a href="#">Q & A</a>
	</nav>

	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer"></div>

	<div class="w3-main">

		<div id="myTop" class="w3-top w3-container w3-padding-8"
			style="background-color: #A9D039; border: 0px;">
			<i
				class="fa fa-bars w3-opennav w3-hide-large w3-xlarge w3-margin-left w3-margin-right"
				onclick="w3_open()"></i>
		</div>

		<header class="w3-container w3-padding-15 "
			style="background-color: #A9D039; border: 0px; padding: 20px;">
			<h1 class="w3-xxxlarge w3-padding-5" style="margin-left: 10px;">커튼</h1>
		</header>
	</div>


	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">

		<article class="container">
			<div class="page-header">
				<h1>
					회원가입 <small>basic form</small>
				</h1>
			</div>
			<div class="col-md-6 col-md-offset-3">
				<form role="form">
					<div class="form-group">
						<label for="InputEmail">이메일 주소</label> <input type="email"
							class="form-control" id="InputEmail" placeholder="이메일 주소">
					</div>
					<div class="form-group">
						<label for="InputPassword1">비밀번호</label> <input type="password"
							class="form-control" id="InputPassword1" placeholder="비밀번호">
					</div>
					<div class="form-group">
						<label for="InputPassword2">비밀번호 확인</label> <input type="password"
							class="form-control" id="InputPassword2" placeholder="비밀번호 확인">
						<p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
					</div>
					<div class="form-group">
						<label for="username">이름</label> <input type="text"
							class="form-control" id="username" placeholder="이름을 입력해 주세요">
					</div>
					<div class="form-group">
						<label for="username">휴대폰 인증</label>
						<div class="input-group">
							<input type="tel" class="form-control" id="username"
								placeholder="- 없이 입력해 주세요"> <span
								class="input-group-btn">
								<button class="btn btn-success">
									인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
								</button>
							</span>
						</div>
					</div>
					<div class="form-group">
						<label for="username">인증번호 입력</label>
						<div class="input-group">
							<input type="text" class="form-control" id="username"
								placeholder="인증번호"> <span class="input-group-btn">
								<button class="btn btn-success">
									인증번호 입력<i class="fa fa-edit spaceLeft"></i>
								</button>
							</span>
						</div>
					</div>
					<div class="form-group">
						<label>약관 동의</label>
						<div data-toggle="buttons">
							<label class="btn btn-primary active"> <span
								class="fa fa-check"></span> <input id="agree" type="checkbox"
								autocomplete="off" checked>
							</label> <a href="#">이용약관</a>에 동의합니다.
						</div>
					</div>
					<div class="form-group text-center">
						<button type="submit" class="btn btn-info">
							회원가입<i class="fa fa-check spaceLeft"></i>
						</button>
						<button type="submit" class="btn btn-warning">
							가입취소<i class="fa fa-times spaceLeft"></i>
						</button>
					</div>
				</form>
			</div>

			<div class="col-md-12">
				<div class="page-header">
					<h1>
						회원가입 <small>horizontal form</small>
					</h1>
				</div>
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputEmail">이메일</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputEmail" type="email"
								placeholder="이메일">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPassword">비밀번호</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputPassword" type="password"
								placeholder="비밀번호">
							<p class="help-block">숫자, 특수문자 포함 8자 이상</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호
							확인</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputPasswordCheck"
								type="password" placeholder="비밀번호 확인">
							<p class="help-block">비밀번호를 한번 더 입력해주세요.</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputName">이름</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputName" type="text"
								placeholder="이름">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputNumber">휴대폰번호</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input type="tel" class="form-control" id="inputNumber"
									placeholder="- 없이 입력해 주세요" /> <span class="input-group-btn">
									<button class="btn btn-success">
										인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
									</button>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputNumberCheck">인증번호
							확인</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input class="form-control" id="inputNumberCheck" type="text"
									placeholder="인증번호"> <span class="input-group-btn">
									<button class="btn btn-success" type="button">
										인증번호 확인<i class="fa fa-edit spaceLeft"></i>
									</button>
								</span>
							</div>
							<p class="help-block">전송된 인증번호를 입력해주세요.</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputAgree">약관
							동의</label>
						<div class="col-sm-6" data-toggle="buttons">
							<label class="btn btn-warning active"> <input id="agree"
								type="checkbox" autocomplete="off" chacked> <span
								class="fa fa-check"></span>
							</label> <a href="#">이용약관</a> 에 동의 합니다.
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 text-center">
							<button class="btn btn-primary" type="submit">
								회원가입<i class="fa fa-check spaceLeft"></i>
							</button>
							<button class="btn btn-danger" type="submit">
								가입취소<i class="fa fa-times spaceLeft"></i>
							</button>
						</div>
					</div>
				</form>
				<hr>
			</div>
		</article>




	</div>

	<footer class="w3-container"
		style="background-color: #A9D039; border: 0px;">
		<h5>Footer</h5>
		<p>Footer information goes here</p>
	</footer>



	<script>
		function w3_open() {
			document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
			document.getElementsByClassName("w3-overlay")[0].style.display = "block";
		}
		function w3_close() {
			document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
			document.getElementsByClassName("w3-overlay")[0].style.display = "none";
		}
	</script>

	<script>
		window.onscroll = function() {
			myFunction()
		};

		function myFunction() {
			if (document.body.scrollTop > 80
					|| document.documentElement.scrollTop > 80) {
				document.getElementById("myTop").classList.add("w3-card-4");
			} else {
				document.getElementById("myTop").classList.remove("w3-card-4");
			}
		}

		function myAccordion(id) {
			document.getElementById(id).classList.toggle("w3-show");
			document.getElementById(id).previousElementSibling.classList
					.toggle("w3-theme");
		}
	</script>

</body>
</html>
