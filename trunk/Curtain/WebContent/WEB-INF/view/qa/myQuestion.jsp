<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

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
		
		$("#btnFAQ").click(function() {
			var form = $("#btnFormByMyQuestion");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/questionAndAnswer"/> ");
			form.submit();
		});
		
		$(".btnQuestion").mouseover( function () {
			$(".btnQuestion").css("cursor","pointer");
		});
		
		$(".btnQuestion").click( function () {
			var form = $(this).parent();
			form.attr("method", "post");
			form.attr("action", "<c:url value="/checkMyQuestion"/> ");
			form.submit();
		});
	});
</script>

  <div class="w3-row" >
     <div class="w3-col m7 w3-main wrapper"
         style="border-right:1px solid #bababa; margin-left: 334px; margin-right: 100px; height: 905px; overflow: auto; background-color:#F3F3F3; ">
         
		 <div class="w3-row" style="border-bottom: 1px solid #bababa; background-color:white;">
				<div style="float:left; margin: 20px 10px 20px 10px;">
					<div style="display:inline-block; float:left; margin: 0px 0px 10px 0px">
						<div style="float: left; margin-top:7px; padding-left: 20px;">
							<h2 style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
								내가 한 질문들</h2>
						</div>
						<div style="float: left; margin-top:17px;">
							<p style="padding-left: 20px; margin-bottom: 0px;">
								커튼의 서비스에 대한 회원님의 질문입니다.
							</p>
						</div>
					</div>
				</div>
				<form id="btnFormByMyQuestion">
					<div style="float: right; width: 80px; margin-top: 30px; margin-right:10px;">
						<div id="btnFAQ" class="btn btn-default" style="float: right;">
							뒤로가기
						</div>
					</div>
				</form>
				
				<div style="clear: both;"></div>
		</div>
				
		<div class="w3-row-padding" style="background-color:#F3F3F3;">
            <div class="w3-col m12" align="left" >
	          <div class="w3-card w3-white w3-round-large" style="margin:10px;">
					<c:forEach items="${questions}" var="question">
						<form> 
							<div class="btnQuestion w3-container" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;" >
								<table class="w3-table">
									<tr>
										<td style="margin-left: 115px;">
											<c:if test="${question.answerDescription ne null && question.isChecked eq 'N' }">
												<span class="eventNew" style="color: red;">new</span>
											</c:if>
											<span>${fn:substring(question.questionDate, 0, 16)}</span>
										</td>
										<td style="text-align: right;">
											<c:if test="${question.answerDescription ne null}">
												<span class="eventNew" style="color: red;">답변완료</span>
											</c:if>
											<c:if test="${question.answerDescription eq null }">
												<span class="eventNew" style="color: red;">답변 미완료</span>
											</c:if>
											<input class="questionId" name="questionId" type="hidden" value="${question.questionId}"/>
										</td>
										
									</tr>
									<tr>
										<td colspan="2">
											<b>문의 제목 : ${question.questionTitle} </b>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											문의 내용 : ${question.questionDescription}
										</td>
									</tr>
								</table>
							</div>
						</form>
					</c:forEach>
				</div>
					</div>
				</div>
			</div>
					<div style="clear: both;"></div>
		</div>


	