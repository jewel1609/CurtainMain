<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

<style type="text/css">

::-webkit-input-placeholder { /* WebKit, Blink, Edge */
	color: #868F84;
}

:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	color: #868F84;
	opacity: 1;
}

::-moz-placeholder { /* Mozilla Firefox 19+ */
	color: #868F84;
	opacity: 1;
}

:-ms-input-placeholder { /* Internet Explorer 10-11 */
	color: #868F84;
}

</style>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Bootstrap Template</title>

<!-- Bootstrap Core CSS -->
<link href="<c:url value="resource/css/main/bootstrap.min.css"/>" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="resource/css/main/modern-business.css"/>" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<c:url value="resource/css/main/font-awesome.min.css"/>" rel="stylesheet"
	type="text/css">
	
<script
src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">

<!-- 말풍선 -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/member/arrowBox.css" />" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<%
	Cookie[] cookies = request.getCookies();
	String userId = "";
	String userPassword = "";
	String autoLogin = "";
	
	if ( cookies != null ) {
		for ( Cookie cookie : cookies ) {
			if ( cookie.getName().equals("userId")) {
				userId = cookie.getValue();
			}
			else if ( cookie.getName().equals("userPassword")) {
				userPassword = cookie.getValue();
			}
			else if ( cookie.getName().equals("autoLogin")) {
				autoLogin = cookie.getValue();
			}
		}
	}
%>

<script type="text/javascript">

	$(document).ready( function () {
		
		var autoLogin = "<%=autoLogin%>";
		if (autoLogin == "true") {
			$("#userId").val("<%=userId%>");
			$("#userPassword").val("<%=userPassword%>");
			$("#autoLoginCheckBox").click();
			
			var div = $("#insertStartButton");
			div.html("<button type=\"button\" id=\"btnStart\" class=\"btn btn-primary btn-lg\">시작하기</button>");
			div.show();
			
		}
		else {
			var div = $("#insertStartButton");
			div.html("<button type=\"button\" id=\"btnRegister\" class=\"btn btn-primary btn-lg\">가입하기</button>");
			div.show();
		}
		
		$(document).on("click","#btnRegister",function () {
			var form = $("#btnForm");
			form.attr("method", "get");
			form.attr("action", "<c:url value="/registStdMember"/> ");
			form.submit();
		});
		
		$(document).on("click","#btnStart",function () {
			var form = $("#btnForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/doLogin"/> ");
			form.submit();
		});
		
		$("#closeModal").click(function() {
			$("#loginModal").modal('hide');
		});
		
		$("#btnDoLogin").click(function() {
			var userId = $("#userId").val();
			userId = $.trim(userId);
			if (userId == "") {
				$("#userId").focus();
				return;
			}

			var userPassword = $("#userPassword").val();
			userPassword = $.trim(userPassword);
			if (userPassword == "") {
				$("#userPassword").focus();
				return;
			}

			var form = $("#btnForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/doLogin"/> ");
			form.submit();
		});
		
		$("#userPassword").keyup(function(e) {
			if (e.keyCode == 13) {
				//Enter 입력했다면..
				$("#btnDoLogin").click();
			}
		});

	});
</script>

</head>
<body>

	<!-- login Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="margin-top: 250px;"
		>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a href="/registStdMember" class="btn btn-primary btn-lg" style="width: 49%;">
						회원가입
					</a>
					<button id="closeModal" type="button" class="btn btn-primary btn-lg"
						data-dismiss="modal-lg" aria-hidden="true" style="width: 49%;">닫기</button>
				</div>
				<form id="btnForm">
					<div class="arrow_box" style="width: 100%;">
						<div class="modal-body" style="overflow-y: auto; height: 265px;">
							아이디<input id="userId" name="userId" class="w3-input" type="text" placeholder="이메일을 입력하세요."/>
							비밀번호<input id="userPassword" name="userPassword" class="w3-input" type="password" placeholder="비밀번호를 입력하세요"/>
							<div style="width: 140px; float:left;">
								자동로그인 하기 <input id="autoLoginCheckBox" name="autoLoginCheckBox" type="checkbox" class="w3-check" value="1"/>
							</div>
							<div id="btnDoLogin" class="btn btn-primary btn-lg" style="margin-top: 20px; margin-bottom:5px; width: 100%; text-align: center;">시작하기</div>
							<a style=""> 비밀번호를 잊으셨나요? </a>
						</div>
						<div class="modal-footer" style="border-width:4px; height: 50px; text-align: center;">
							<a style="padding-bottom: 10px;">
								페이스북으로 로그인하기
							</a>
							
						</div>
					</div>
					
					<div style="clear: both; height: 10px;"></div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main.jsp">커튼</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${sessionScope._MEMBER_ eq null}">
						<li>
							<a id="btnLoginModal" href="#loginModal" data-toggle="modal" type="button">로그인</a>
						</li>
					</c:if>
					<c:if test="${sessionScope._MEMBER_ ne null}">
						<li>
							<a id="btnLogout" href="<c:url value="/doLogout" />" type="button">로그아웃</a>
						</li>
					</c:if>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	
	
	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill"
					style="background-image: url('http://placehold.it/1900x1080&amp;text=Slide One');"></div>
				<div class="carousel-caption">
					<h2>소개 페이지1</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('http://placehold.it/1900x1080&amp;text=Slide Two');"></div>
				<div class="carousel-caption">
					<h2>소개 페이지2</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('http://placehold.it/1900x1080&amp;text=Slide Three');"></div>
				<div class="carousel-caption">
					<h2>소개 페이지3</h2>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>

	<!-- Page Content -->
	<div class="container">

		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">커튼 속으로 입장하세요!</h1>
			</div>
			<div id="insertStartButton">
			</div>
		</div>

		<!-- Features Section -->
		<div class="row">

			<div class="col-md-6">
				<h2 class="page-header">
					같은 전공을 가진 사람들끼리<br /> 터놓고 이야기하세요!
				</h2>
				<p>
					전국의 나와 같은 전공을 가진 사람들끼리 <br /> 학과 커튼을 열어 고민을 나누어보세요.
				</p>

			</div>

			<div class="col-md-6">
				<img class="img-responsive" src="http://placehold.it/700x450" alt="">

				<br />
				<br />
				<br />
				<br />

			</div>
			<div class="col-md-6">
				<h2 class="page-header">
					매일 달라지는 이벤트에 참여해보세요.<br />
					<br /> 회원 등급에 따라 <br /> 더 깊은 속얘기를 나눌 수 있는 <br /> 비밀 커튼 속으로 입장할수
					있습니다.
				</h2>

			</div>
			<div class="col-md-6">
				<img class="img-responsive" src="http://placehold.it/700x450" alt="">
			</div>
		</div>
		<!-- /.row -->
		
		<!-- Footer -->
		<footer>
			<div class="well">
				<div class="row">
					<div class="col-md-8">
						<p>문의하기 | 개인정보취급방침 | 제휴/광고 | master@curtain.com</p>
					</div>
				</div>
			</div>


		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>

</body>

</html>
