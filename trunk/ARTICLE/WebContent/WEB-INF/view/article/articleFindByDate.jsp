<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function () {
	$("#articleFindeByDateBtn").click(function() {
			var form = $("#articleFindByDateForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/articleList" />");
			form.submit();
		});
});
</script>

<div class="container">
  <h2>날짜별 게시글 검색</h2>      
  <table class="table">
    <thead>
       <tr>
		<td colspan="6">게시글 기간을 선택하세요.</td>
      </tr>
    </thead>
    <tbody>
    	<tr>
    	<td colspan="6">
		    	<form id="articleFindByDateForm">
			        <input type="date" name="startDate"/>부터
			        <input type="date" name="endDate"/>까지
			    </form> 
		    </td>
	    </tr>
	    <tr>
	  	  <td colspan="6">
	  	  	<span id="articleFindeByDateBtn" style="cursor: pointer;">찾기</span>
	  	  </td>
	    <tr>
    </tbody>
  </table>
</div>
	
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>