<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

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

<div class="w3-container" style="margin-top:0px;">    
   <div class="w3-row" >
      <div class="w3-col m7 wrapper"
         style="border-right:1px solid #bababa; margin-left: 334px; margin-right: 100px; height: 905px; overflow: auto; background-color:#F3F3F3;">
       	
     		 <div class="w3-row w3-white" style="border-bottom: 1px solid #bababa;">
				 <div class="w3-margin-4" style="float:left; padding-left:10px;">
				 	<h2>문의하기</h2>
				</div>
					<div style="float: left; margin-top: 30px;">
						<p style="padding-left: 20px; margin-bottom: 0px;">
							이용하시면서 불편한 사항이나 개선점이 있다면 언제든지 알려주세요.
						</p>
					</div>
				</div>
		
       <div class="w3-padding">

			<form id="btnFormByQuestion">
			<table class="w3-table w3-white w3-border w3-bordered">
				<tr>
					<td width="200px"
						style="padding-left: 20px; padding-top: 15px;">제목</td>
					<td><input id="questionTitle" name="questionTitle"
						class="form-control" type="text" placeholder="포인트가 갑자기 사라졌어요.">
					</td>
				</tr>
				<tr>
					<td style="padding-left: 20px; padding-top: 15px;">문의 내용</td>
					<td><textarea id="questionDescription"
							name="questionDescription" class="w3-col m12 form-control" rows="5"
							style="height: 500px; margin-bottom: 5px; padding: 0 5px 0 5px;"
							placeholder="일요일 오전 10시경 포인트가 갑자기 사라졌어요." ></textarea></td>
				</tr>
			</table>
			<div style="float: right; margin-top: 20px; width: 200px;">
				<div style="float: right; width: 80px;">
					<div id="btnFAQ" class="btn btn-default" style="float: right; border-color: rgb(255, 51, 0); color: rgb(255, 51, 0);">
						취소
					</div>
				</div>
				<div style="float: right; width: 80px;">
					<div id="btnDoRegisterQuestion" class="btn btn-default" style="float: right; border-color: rgb(255, 51, 0); color: rgb(255, 51, 0);">
						문의하기
					</div>
				</div>
			</div>
		</form>			
	</div>
</div>
