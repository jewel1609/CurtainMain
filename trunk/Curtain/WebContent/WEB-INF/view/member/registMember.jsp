<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>회원가입</title>
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

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">

		<article class="container">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						개인정보 입력
					</h1>
				</div>
			
				<form class="form-horizontal">
				
					<!-- 대학교 이메일 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputUnivEmail">대학교 이메일</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input class="form-control" id="inputEmail" type="email" 
								placeholder="학교 이메일주소를 입력하세요. 일반이메일은 인증메일이 전송되지않습니다."/>
									<span class="input-group-btn">
										<button class="btn btn-success">
											인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
										</button>
									</span>
							</div>
						</div>
					</div>
					
					<!-- 인증번호확인 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputNumberCheck">인증번호 확인</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input class="form-control" id="inputNumberCheck" type="text" 
								placeholder="전송된 인증번호를 입력해주세요."> <span class="input-group-btn">
									<button class="btn btn-success" type="button">
										인증번호 확인<i class="fa fa-edit spaceLeft"></i>
									</button>
								</span>
							</div>
						</div>
					</div>
					
					<!-- 비밀번호 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPassword">비밀번호</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputPassword" type="password"
								placeholder="8-20자리 영문 대소문자, 숫자를 혼합하여 사용.">
						</div>
					</div>
					
					<!-- 비밀번호 재확인 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호 재확인</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputPasswordCheck"
								type="password" placeholder="비밀번호 재확인">
						</div>
					</div>
					<hr>
					
					<!-- 대학교 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputUniv">대학교</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputName" type="text"
								placeholder="대학교를 검색하세요">
						</div>
					</div>
					
					<!-- 학과 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputMajor">학과</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputName" type="text"
								placeholder="학과를 검색하세요.">
						</div>
					</div>
					<hr>
					
					<!-- 보조 이메일 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputEmail">보조 이메일</label>
						<div class="col-sm-6">
								<input class="form-control" id="inputEmail" type="email" 
								placeholder="비밀번호 분실 시 활용됩니다.">
						</div>
					</div>
					<br/>
					
					<!-- 약관 동의 -->
					<h1>
						약관 동의
					</h1><hr>
					<div class="form-group">
						<div class="col-sm-6" data-toggle="buttons">
							개인화면 약관에 동의 <a href="#">내용보기</a>
								<input class="w3-check" id="agree"type="checkbox" autocomplete="off">
						</div>
						
						<div class="col-sm-6" data-toggle="buttons">
							개인정보 수정 및 이용에 동의 <a href="#">내용보기</a>
								<input class="w3-check" id="agree2" type="checkbox" autocomplete="off">
						</div>
					</div>
					
					<!-- 가입하기 버튼 -->
					<div class="form-group">
						<div class="col-sm-12 text-center">
							<input type="button" class="btn btn-primary" value="가입하기">
							<!--  
							<button class="btn btn-danger" type="submit">
								가입취소<i class="fa fa-times spaceLeft"></i>
							</button>
							-->
						</div>
					</div>
				</form>
				<hr>
				
			</div>
		</article>

	</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
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

</html>
