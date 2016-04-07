<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resource/css/article/secretArticle.css" />


<script type="text/javascript">
	

	$(document).ready(function() {
		
		$(".wrapper").css("height", window.innerHeight - 130);
		
		$(window).resize(function() {
			$(".wrapper").css("height", window.innerHeight - 130);
		});
		
		$("#qna").mouseleave(function(){
			$("#qna").css('background-color', '#a9d039');
			$("#qna").css('color', '#ffffff');
		});
		$("#qna").css('background-color', '#a9d039');
		$("#qna").css('color', '#ffffff');
		
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
			var form = $(".btnFormByFreuentlyAskedQuestion");
			form.attr("method", "post" );
			form.attr("action", "/myQuestion");
			form.submit();
		});
		
		$("#btnDoRegisterQuestion").click( function () {
			var form = $(".btnFormByFreuentlyAskedQuestion");
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

   <div class="w3-row" >
      <div class="w3-col m7 wrapper"
         style="border-right:1px solid #bababa; margin-left: 334px; margin-right: 100px; height: 905px; overflow: auto; background-color:#F3F3F3;">
       	
				<!-- 게시판 헤더 -->

		 <div class="w3-row" style="border-bottom: 1px solid #bababa;">
				 <div class="w3-margin-4" style="float:left; padding-left:30px;">
									<h2>FAQ</h2>
						</div>
						<div style="float:left; padding:30px 0 0 20px;">
							    커튼의 서비스에 대해 자주 묻는 질문들 입니다. 궁금하신 서비스를 선택해주세요.
						</div>
					</div>
					
					<div class="w3-row-padding" style="border-bottom:1px solid #BABABA;">
           				 <div class="w3-col m12" align="left" >
							<div style="padding: 10px; overflow: auto;">
							<div class="w3-container w3-card w3-round-large w3-white" style="margin-bottom: 10px; padding: 10px;" >
								<div id="btnFirstFAQ" style="padding-top:10px;">
									<div style="float:left;">
										<ul>
											<li><h4 style="display: inline;">포인트는 무엇인가요?</h4></li>
										</ul>
									</div>
									<div style="float: right;">▼</div>
									<div style="clear: both;"></div>
								</div>
								<div id="firstFAQ" style="margin-top: 20px; padding-left: 30px;">
									학생 회원인 경우 포인트는 홍보게시판을 제외한 게시판에서 글을 쓸 때 10포인트씩 적립됩니다.
									<br /><br />
									하지만 하루에 5개의 게시글만 포인트가 상승합니다.
									<br /><br />
									기업 회원의 경우에는 포인트를 구매 할 수 있습니다.
									<br /><br />
									적립된 포인트는 홍보게시판에 100포인트를 주고 글을 올릴 수 있습니다.
									<br /><br />
									포인트의 유효기간은 기업 회원의 경우 기간은 없으며 학생 회원의 경우에는 기간은 1년 입니다.
								</div>
							</div>
							<div class="w3-container w3-card w3-round-large w3-white" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;">
								<div id="btnSecondFAQ" style="padding-top:10px;">
									<div  style="float: left;">
										<ul>
											<li><h4 style="display:inline;" >회원의 등급은 어떻게 되나요?</h4></li>
										</ul>
									</div>
									<div style="float: right;">▼</div>
									<div style="clear: both;"></div>
								</div>
								<div id="secondFAQ" style="margin-top: 20px; padding-left: 30px;">
									학생 회원은 처음 가입하면 창문 등급이 됩니다.
									<br /><br />
									학생 회원이 다음 조건을 만족하게 되면 등급이 한겹커튼으로 상승하게 됩니다.(총 게시글 5개, 댓글 10개, 투표를 7회 이상)
									<br /><br />
									한겹커튼 등급의 회원은 비밀게시판 중 한겹게시판을 이용 할 수 있습니다.
									<br /><br />
									학생 회원이 다음 조건을 만족하게 되면 등급이 두겹커튼으로 상승하게 됩니다.(총 게시글 15개, 댓글 30개, 투표를 21회 이상)
									<br /><br />
									두겹커튼 등급의 회원은 비밀게시판 중 한겹, 두겹게시판을 이용 할 수 있습니다.
									<br /><br />
									기업 회원의 경우 홍보게시판만 볼 수 있습니다.
								</div>
							</div>
						</div>
						</div>
						</div>
						
						
     		 <div class="w3-row" style="border-bottom: 1px solid #bababa;">
				 <div class="w3-margin-4" style="float:left; padding-left:30px;">
					<h2>내가 한 질문들</h2>
				</div>
				<div style="float: left; padding: 20px; margin-top:10px;">
					<p>커튼의 서비스에 대한 회원님의 질문입니다.</p>
				</div>
			</div>
											
			<div style="height:120px; padding:10px;">
							
				<div class="w3-container w3-card w3-round-large w3-white w3-margin" style="margin-bottom: 10px; padding: 10px;" >
					<div id="btnFirstFAQ" style="padding-top:10px;">
						<div style="float:left;">
							<ul>
								<li><h4 style="display: inline;">내가 질문한 글 보기</h4></li>
							</ul>
						</div>
					
					</div>
					<form class="btnFormByFreuentlyAskedQuestion">
						<div id="btnMyQuestion" class="btn btn-default" style="float:right;">
							확인하기
						</div>
					</form>
				</div>							
		    </div>

			<div class="w3-row" style="border-bottom: 1px solid #bababa; border-top:1px solid #bababa; margin-bottom:10px;">
				<div class="w3-margin-4" style="float:left; padding-left:30px;">
					<h2>문의하기</h2>
				</div>
				<div style="float: left; padding: 20px; margin-top:10px;">
					<p>이용하시면서 불편한 사항이나 개선점이 있다면 언제든지 알려주세요.</p>
				</div>
			</div>
						
			<div style="height:100px; padding:10px;">
							
				<div class="w3-container w3-card w3-round-large w3-white w3-margin" style="margin-bottom: 10px; padding: 10px;" >
					<div id="btnFirstFAQ" style="padding-top:10px;">
						<div style="float:left;">
							<ul>
								<li><h4 style="display: inline;">궁금한 항목에 대한 도움말이 없으시다면, 커튼고객센터로 문의해 주세요.</h4></li>
							</ul>
						</div>
					
					</div>
			<form class="btnFormByFreuentlyAskedQuestion">
			<div id="btnDoRegisterQuestion" class="btn btn-default" style="float: right;">
				문의하기
			</div>
		</form>
	</div>							
   </div>

	</div>
</div>

