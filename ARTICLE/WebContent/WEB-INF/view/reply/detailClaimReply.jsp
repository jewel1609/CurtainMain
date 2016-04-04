<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function () {
		
		//수정
		$("#deleteClaimArticleBtn").click(function() {
		
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#claimArticleForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/deleteClaimReply" />");
				form.submit();
			}
		});
		
	});
</script>

<div class="container">
  <h2>신고게시글 상세페이지</h2>
  <table class="table">
    <thead>
      <tr>
        <th>댓글ID</th>
        <th>댓글내용</th>
        <th>등록날짜</th>
		<th>수정날짜</th>
		<th>글ID</th>
		<th>상위댓글ID</th>
		<th>댓글순서</th>
		<th>댓글좋아요</th>
		<th>게시판ID</th>
		<th>댓글싫어요</th>
		<th>이메일</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="claimArticleForm">
			      <tr>
			      	<input type="hidden" name="articleId" value="${claimReplyInfo.replyId}"/>
			        <td>${claimReplyInfo.replyId}</td>
			        <td>${claimReplyInfo.replyDesc}</td>
			       	<td>${claimReplyInfo.replyRegisterDate}</td>
			        <td>${claimReplyInfo.replyModifyDate}</td>
			        <td>${claimReplyInfo.articleId}</td>
			        <td>${claimReplyInfo.parentReplyId}</td>
			        <td>${claimReplyInfo.replyOrder}</td>
			        <td>${claimReplyInfo.replyLikes}</td>
			        <td>${claimReplyInfo.boardId}</td>
			        <td>${claimReplyInfo.replyDislikes}</td>
			        <td>${claimReplyInfo.email}</td>
			      </tr>
    	</form>
   		<tr>
			<td colspan="14">
				<span id="deleteClaimArticleBtn" style="cursor: pointer;">댓글 삭제하기</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>