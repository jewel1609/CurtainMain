   <%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function () {
		
		$("#historyBtn").click(function() {
			
			var form = $("#historyForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/showHistoryList" />");
			form.submit();

		});
});
</script>
<div class="container">
  <h2>히스토리확인</h2>
  <p>자유롭게 이용하세요</p>
  <table class="table">
    <thead>
      <tr>
		<td colspan="6">히스토리 기간을 선택하세요.</td>
      </tr>
    </thead>
    
    <tbody>
    	    <tr>
	    		<td colspan="6" >
		    		<form id = "historyForm">
		    			 <input type="date" id ="startDate" name="startDate"/> 부터
		    			 <input type="date" id ="endDate" name="endDate"/> 까지
		    		</form>
	    		</td>
    	</tr>
   		<tr>
			<td colspan="6">
				<span id="historyBtn" style="cursor: pointer;">찾기</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>