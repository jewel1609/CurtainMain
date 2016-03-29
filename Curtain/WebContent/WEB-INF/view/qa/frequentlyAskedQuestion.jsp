<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<script type="text/javascript">
	

	$(document).ready(function() {
		
		var degreeByBtnFirstFAQ = 180;
		var degreeByBtnSecondFAQ = 180;
		
		$("#firstFAQ").hide();
		$("#secondFAQ").hide();
		
		$("#btnFirstFAQ").mouseover( function () {
			$("#btnFirstFAQ").css("cursor","pointer");
		});
		
		$("#btnFirstFAQ").click(function(){
			
			var span = $(this).children(":eq(1)");
			rotate(span,degreeByBtnFirstFAQ);
			
			if(degreeByBtnFirstFAQ == 180) {
				degreeByBtnFirstFAQ = 0;
			}
			else {
				degreeByBtnFirstFAQ = 180;
			}
			
			$("#firstFAQ").slideToggle();
		});
		
		$("#btnSecondFAQ").mouseover( function () {
			$("#btnSecondFAQ").css("cursor","pointer");
		});
		
		$("#btnSecondFAQ").click(function(){
			
			var span = $(this).children(":eq(1)");
			rotate(span, degreeByBtnSecondFAQ);
			
			if(degreeByBtnSecondFAQ == 180) {
				degreeByBtnSecondFAQ = 0;
			}
			else {
				degreeByBtnSecondFAQ = 180;
			}
			
			$("#secondFAQ").slideToggle();
		});
		
		$("#btnMyQuestion").click( function () {
			var form = $(".btnForm");
			form.attr("method", "post" );
			form.attr("action", "/myQuestion");
			form.submit();
		});
		
		$("#btnDoRegisterQuestion").click( function () {
			var form = $(".btnForm");
			form.attr("method", "post" );
			form.attr("action", "/registerQuestion");
			form.submit();
		});
		
		
		
	});
	
	function rotate(div, degree) {
		div.css({
			WebkitTransform : 'rotate('+ degree +'deg)'
		});
		div.css({
			'-moz-transform' : 'rotate('+ degree +'deg)'
		});
		div.css({'transform' : 'rotate('+ degree +'deg)'
		});
		/* timer = setTimeout(function() {
			++degree;
			rotate(div);
		}, 5); */
	}
</script>
	
	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px; ">
		<div class="w3-row">
			<div class="w3-col m7 w3-main" style="margin-left: 350px;">
				<div class="w3-row-padding w3-margin-top">
					<div class="w3-col m12">
					
						<div style="float:left; margin: 0px 0px 20px 0px">
							<div style="float: left; margin-top: 7px; padding-left: 20px;">
								<h1
									style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
									□  도움말</h1>
							</div>
							<div style="float: left;">
								<p style="padding-left: 20px; margin-bottom: 0px;">
									커튼의 서비스에 대해 자주 묻는 질문들 입니다.</p>
								<p style="padding-left: 20px; margin-top: 0px; text-align: left;">
									궁금하신 서비스를 선택해주세요.
								</p>
							</div>
						</div>
						
						<div style="clear: both;"></div>
					
						<div class="w3-white w3-round-large" style="padding: 10px; height: 350px; overflow-y: auto;">
							<div class="w3-container w3-card-2" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;" >
								<div id="btnFirstFAQ">
									<div style="float: left; margin-left: 20px;">
										<ul>
											<li><h4 style="display: inline;">포인트는 어떻게 사용하나요?</h4></li>
										</ul>
									</div>
									<div style="float: right;">▽</div>
									<div style="clear: both;"></div>
								</div>
								<div id="firstFAQ" style="margin-top: 20px;">
									홍보글을 올리는데 사용할 수 있습니다.
								</div>
							</div>
							<div class="w3-container w3-card-2" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;">
								<div id="btnSecondFAQ">
									<div  style="float: left; margin-left: 20px;">
										<ul>
											<li><h4 style="display: inline;">포인트 유효기간은 어떻게 되나요?</h4></li>
										</ul>
									</div>
									<div style="float: right;">▽</div>
									<div style="clear: both;"></div>
								</div>
								<div id="secondFAQ" style="margin-top: 20px;">
									기업회원의 경우 유효기간은 없으며 대학생 화원의 경우에는 유효기간은 1년 입니다.
								</div>
							</div>
						</div>
						
						<div style="float:left; margin: 20px 0px 20px 0px">
							<div style="float: left; margin-top: 7px; padding-left: 20px;">
								<h1
									style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
									□  내가 한 질문들</h1>
							</div>
							<div style="float: left;">
								<p style="padding-left: 20px; margin-top: 15px;">
									커튼의 서비스에 대한 회원님의 질문입니다.</p>
							</div>
						</div>
						
						<div style="clear: both;"></div>
						
						<div class="w3-container w3-card-2" style="height:50px; text-align: left; margin-bottom: 10px; padding: 10px 10px 10px 10px;">
							<div style="float: left; margin-left: 20px;">
								<ul>
									<li><h4 style="display: inline;">내가 질문한 글 보기</h4></li>
								</ul>
							</div>
							<form class="btnForm">
								<div id="btnMyQuestion" class="btn btn-primary" style="float: right;
									 border: 0px currentColor; border-image: none; 
									 background-color: rgb(255, 51, 0); color: white;">
									확인하기
								</div>
							</form>
						</div>
						
						<div style="float:left; margin: 20px 0px 20px 0px;">
							<div style="display: inline-block; float:left; margin: 0px 0px 20px 0px">
								<div style="float: left; margin-top: 7px; padding-left: 20px;">
									<h1
										style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
										□  문의하기</h1>
								</div>
								<div style="float: left; margin-top: 17px;">
									<p style="padding-left: 20px; margin-bottom: 0px;">이용하시면서
										불편한 사항이나 개선점이 있다면 언제든지 알려주세요.</p>
									<div style="clear: both;"></div>
								</div>
							</div>
						</div>
						
						<div style="clear: both;"></div>
						
						<div class="w3-container w3-card-2" style="height:50px; margin-bottom: 10px; padding: 10px 10px 10px 10px;">
							<div style="float: left; margin-left: 20px;">
								<ul>
									<li>
										<h4 style="display: inline;">
											궁금한 항목에 대한 도움말이 없으시다면, 커튼고객센터로 문의해 주세요.
										</h4>
									</li>
								</ul>
							</div>
							<div style="float: right; ">
								<form class="btnForm">
									<div id="btnDoRegisterQuestion" class="btn btn-primary" style="float: right;
										 border: 0px currentColor; border-image: none; 
										 background-color: rgb(255, 51, 0); color: white;">
										문의하기
									</div>
								</form>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>