<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
		
		//설문 전송버튼 누를시
		$("#surveyBtn").click(function(){
			 
			 if( $("#surveyTitle").val() == ""){
				alert("설문 제목을 입력 해주세요");
				return;
				// 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			var form = $("#surveyForm");
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doInsertSurvey" />");
			form.submit();
		
		});
		
		$("#cancleBtn").click(function(){
			window.history.back();
		});
		
	});
</script>


<div class="container">
  <h2>설문설정</h2>
  <p>자유롭게 이용하세요.</p>
   <form id="surveyForm">         
	  <table class="table">
	    <thead>
	      <tr>
	        <th>설문제목</th>
	        <th>답변1</th>
	        <th>답변2</th>
	        <th>답변3</th>
	        <th>답변4</th>
	        <th>날짜</th>
	      </tr>
	    </thead>
	    <tbody>
	    	
			    <tr>
						<td><input type="text" id="surveyTitle" name="surveyTitle" placeholder="설문제목을 입력하세요"></td>
						<td><input type="text" id="surveyAnswer1" name="surveyAnswer1" placeholder="첫번째 답변을 입력하세요"></td>
						<td><input type="text" id="surveyAnswer2" name="surveyAnswer2" placeholder="두번째 답변을 입력하세요"></td>
						<td><input type="text" id="surveyAnswer3" name="surveyAnswer3" placeholder="세번째 답변을 입력하세요"></td>
						<td><input type="text" id="surveyAnswer4" name="surveyAnswer4" placeholder="네번째 답변을 입력하세요"></td>
						<td><input type="date" id="surveyDate" name="surveyDate"></td>
				</tr>
			
	    </tbody>
	  </table>
  </form>
</div>
<div class="container">
  	<input type="button" id="surveyBtn" value="설문 등록"/>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>