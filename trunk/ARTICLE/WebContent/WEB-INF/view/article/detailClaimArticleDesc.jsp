<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function () {
		
		$("#backBtn").click(function() {
			window.history.back();
		});
		
	});
</script>

<div class="container">
  <h2>신고게시글 상세페이지</h2>
  <table class="table">
    <thead>
      <tr>
        <th>번호</th>
        <th>이메일</th>
        <th>신고된 글 ID</th>
        <th>신고된 댓글 ID</th>
		<th>신고날짜</th>
		<th>신고내용</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="claimArticleForm">
			      <tr>
			        <td>${claimInfo.articleClaimId}</td>
			        <td>${claimInfo.email}</td>
			       	<td>${claimInfo.articleId}</td>
			        <td>${claimInfo.replyId}</td>
			        <td>${claimInfo.claimDate}</td>
			        <td>${claimInfo.claimText}</td>
			        
			      </tr>
    	</form>
   		<tr>
			<td colspan="6">
				<span id="backBtn" style="cursor: pointer;">뒤로가기</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>