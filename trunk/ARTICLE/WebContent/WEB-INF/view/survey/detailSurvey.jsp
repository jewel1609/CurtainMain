<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function () {
		
		//수정
		$("#modifySurveyInfoBtn").click(function() {
			
			if (confirm("정말 수정하시겠습니까?")) {
				var form = $("#modifyForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/modifySurvey" />");
				form.submit();
			}
		});
		
	});
</script>

<div class="container">
  <h2>설문 리스트</h2>
  <table class="table">
    <thead>
      <tr>
        <th>설문ID</th>
        <th>설문 제목</th>
        <th>답변1</th>
        <th>답변2</th>
		<th>답변3</th>
		<th>답변4</th>
		<th>설문날짜</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="modifyForm">
			      <input type="hidden" name="surveyId" value="${survey.surveyId}"/>
			      <tr>
			        <td>${survey.surveyId}</td>  
			        <td><input type="text" id="surveyTitle" name="surveyTitle" value="${survey.surveyTitle}"/> </td>
			        <td><input type="text" id="surveyAnswer1" name="surveyAnswer1" value="${survey.surveyAnswer1}"/></td>
			       	<td><input type="text" id="surveyAnswer2" name="surveyAnswer2" value="${survey.surveyAnswer2}"/></td>
			        <td><input type="text" id="surveyAnswer3" name="surveyAnswer3" value="${survey.surveyAnswer3}"/></td>
			        <td><input type="text" id="surveyAnswer4" name="surveyAnswer4" value="${survey.surveyAnswer4}"/></td>
			        <td><span>${survey.surveyDate}</span></td>
			      </tr>
    	</form>
   		<tr>
			<td colspan="6">
				<span id="modifySurveyInfoBtn" style="cursor: pointer;">수정하기</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>