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
        <th>시작날짜</th>
        <th>마지막날짜</th>
      </tr>
    </thead>
    <tbody>
    	<form id="articleFindByDateForm">
    	<tr>
	        <td><input type="date" name="startDate"/></td>
	        <td><input type="date" name="endDate"/></td>
	      </tr>
	    </form> 
    </tbody>
  </table>
   <input type="button" id="articleFindeByDateBtn" value="게시글 검색"/>
</div>
	
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>