<%@page import="com.ktds.curtain.history.vo.OperationHistoryVO"%>
<%@page import="com.ktds.curtain.history.vo.ActionCode"%>
<%@page import="com.ktds.curtain.history.vo.BuildDescription"%>
<%@page import="com.ktds.curtain.history.vo.Description"%>
<%@page import="com.ktds.curtain.history.biz.OperationHistoryBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

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
<%
	OperationHistoryBiz historyBiz = new OperationHistoryBiz();
	OperationHistoryVO historyVO = new OperationHistoryVO();
	historyVO.setIp(request.getRemoteHost());
	historyVO.setUrl(request.getRequestURI());
	historyVO.setActionCode(ActionCode.MAIN);
	historyVO.setDescription( BuildDescription.get(Description.VISIT_MAIN_PAGE, request.getRemoteHost()));
	System.out.println("메인접근");
	historyBiz.addHistory(historyVO);
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="<c:url value="resource/css/main/bootstrap.min.css"/>" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="resource/css/main/modern-business.css"/>" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<c:url value="resource/css/main/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">

<!-- 말풍선 -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/member/arrowBox.css" />" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

 
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

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>

</body>

</html>
