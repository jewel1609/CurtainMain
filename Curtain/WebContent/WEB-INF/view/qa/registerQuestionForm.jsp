<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#qna").mouseleave(function(){
				$("#qna").css('background-color', '#a9d039');
				$("#qna").css('color', '#ffffff');
			});
			$("#qna").css('background-color', '#a9d039');
			$("#qna").css('color', '#ffffff');
			
			
			$("#btnDoRegisterQuestion").click(function() {
	
				var title = $("#questionTitle").val();
				title = $.trim(title);
				if (title == "") {
					$("#questionTitle").focus();
					return;
				}
	
				var description = $("#questionDescription").val();
				description = $.trim(description);
				if (description == "") {
					$("#questionDescription").focus();
					return;
				}
				
				var form = $("#btnFormByQuestion");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/doRegisterQuestion"/> ");
				form.submit();
			});
	
			$("#btnFAQ").click(function() {
				var form = $("#btnFormByQuestion");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/questionAndAnswer"/> ");
				form.submit();
			});
		});
	</script>

	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">
		<div class="w3-row">
			<div class="w3-col m7 w3-main" style="margin-left: 350px;">
				<div class="w3-row-padding w3-margin-top">
					<div class="w3-col m12">
						<div style="float:left; margin: 20px 0px 20px 0px;">
							<div style="display: inline-block; float:left; margin: 0px 0px 20px 0px">
								<div style="float: left; margin-top: 7px; padding-left: 20px;">
									<h1
										style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
										□  문의하기</h1>
								</div>
								<div style="float: left; margin-top: 17px;">
									<p style="padding-left: 20px; margin-bottom: 0px;">
										이용하시면서 불편한 사항이나 개선점이 있다면 언제든지 알려주세요.
									</p>
								</div>
							</div>
						</div>
						
						<div style="clear: both;"></div>
						
						<div class="w3-card-2 w3-white w3-round-large"
							style="padding: 10px; height: 650px;">
							<form id="btnFormByQuestion">
								<table class="w3-table w3-border w3-bordered">
									<tr>
										<td width="200px"
											style="padding-left: 20px; padding-top: 15px;">제목</td>
										<td><input id="questionTitle" name="questionTitle"
											class="w3-input" type="text" placeholder="포인트가 갑자기 사라졌어요.">
										</td>
									</tr>
									<tr>
										<td style="padding-left: 20px; padding-top: 15px;">문의 내용</td>
										<td><textarea id="questionDescription"
												name="questionDescription" class="w3-col m12" rows="5"
												style="height: 500px; margin-bottom: 5px; padding: 0 5px 0 5px;"
												placeholder="일요일 오전 10시경 포인트가 갑자기 사라졌어요." ></textarea></td>
									</tr>
								</table>
								<div style="float: right; margin-top: 20px; width: 200px;">
									<div style="float: right; width: 80px;">
										<div id="btnFAQ" class="btn btn-primary" style="float: right;
											 border: 0px currentColor; border-image: none; 
											 background-color: rgb(255, 51, 0); color: white;">
											뒤로가기
										</div>
									</div>
									<div style="float: right; width: 80px;">
										<div id="btnDoRegisterQuestion" class="btn btn-primary" style="float: right;
											 border: 0px currentColor; border-image: none; 
											 background-color: rgb(255, 51, 0); color: white;">
											문의하기
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
