<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#firstFAQ").hide();
		$("#secondFAQ").hide();
		
		$("#btnFirstFAQ").click(function(){
			$("#firstFAQ").toggle();
		});
		
		$("#btnSecondFAQ").click(function(){
			$("#secondFAQ").toggle();
		});
		
	});
</script>

	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px; ">
		<div class="w3-row">
			<div class="w3-col m7 w3-main" style="margin-left: 350px;">
				<div class="w3-row-padding w3-margin-top">
					<div class="w3-col m12">
					
						<div style="display: inline-block; margin: 0px 0px 20px 0px">
							<div style="float: left; margin-top: 7px;">
								<h1
									style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
									도움말</h1>
							</div>
							<div style="float: left;">
								<p style="padding-left: 20px; margin-bottom: 0px;">
									커튼의 서비스에 대해 자주 묻는 질문들 입니다.</p>
								<p style="padding-left: 20px; margin-top: 0px; text-align: left;">
									궁금하신 서비스를 선택해주세요.
								</p>
							</div>
							<div style="clear: both;"></div>
						</div>
					
						<div class="w3-card-2 w3-white w3-round-large" style="padding: 10px; height: 500px; overflow-y: auto;">
							<div id="btnFirstFAQ" class="w3-container w3-card-2" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;" >
								<div style="float: left; margin-left: 20px;">
									<ul>
										<li><h4 style="display: inline;">포인트는 어떻게 사용하나요?</h4></li>
									</ul>
								</div>
								<div style="float: right;">▽</div>
								<div style="clear: both;"></div>
								<div id="firstFAQ" style="margin-top: 20px;">
									홍보글을 올리는데 사용할 수 있습니다.
								</div>
							</div>
							<div id="btnSecondFAQ" class="w3-container w3-card-2" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;">
								<div style="float: left; margin-left: 20px;">
									<ul>
										<li><h4 style="display: inline;">포인트 유효기간은 어떻게 되나요?</h4></li>
									</ul>
								</div>
								<div style="float: right;">▽</div>
								<div style="clear: both;"></div>
								<div id="secondFAQ" style="margin-top: 20px;">
									기업회원의 경우 유효기간은 없으며 대학생 화원의 경우에는 유효기간은 1년 입니다.
								</div>
							</div>
						</div>
						
						<div class="w3-container w3-card-2" style="text-align: left; margin-bottom: 10px; padding: 10px 10px 10px 10px;">
							<div style="float: left; margin-left: 20px;">
								<ul>
									<li><h4 style="display: inline;">내가 질문한 글 보기</h4></li>
								</ul>
							</div>
							<div style="float: right;">
								<a href="<c:url value="/myQuestion" />" > 확인하기</a>
							</div>
						</div>
													
						<div class="w3-container w3-card-2" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;">
							<div style="float: left; margin-left: 20px;">
								<ul>
									<li>
										<h4 style="display: inline;">
											궁금한 항목에 대한 도움말이 없으시다면, 커튼고객센터로 문의해 주세요.
										</h4>
									</li>
								</ul>
							</div>
							<div style="float: right;">
								<a href="<c:url value="/registerQuestion" />" style="text-decoration: none;">
									<div style="background-color: red;">문의하기</div>
								</a>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>