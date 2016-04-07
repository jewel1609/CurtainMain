<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function () {
		
		$("#deleteClaimArticleBtn").click(function() {
		
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#claimArticleForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/deleteArticle" />");
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
        <th>글ID</th>
        <th>제목</th>
        <th>내용</th>
        <th>등록날짜</th>
		<th>수정날짜</th>
		<th>글타입ID</th>
		<th>이메일</th>
		<th>게시판ID</th>
		<th>소계열ID</th>
		<th>대학ID</th>
		<th>조회수</th>
		<th>좋아요</th>
		<th>싫어요</th>
		<th>스크랩수</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="claimArticleForm">
			      <tr>
			      	<input type="hidden" name="articleId" value="${claimArticleInfo.articleId}"/>
			        <td>${claimArticleInfo.articleId}</td>
			        <td>${claimArticleInfo.articleTitle}</td>
			       	<td>${claimArticleInfo.articleDesc}</td>
			        <td>${claimArticleInfo.articleRegisterDate}</td>
			        <td>${claimArticleInfo.articleModifyDate}</td>
			        <td>${claimArticleInfo.articleTypeId}</td>
			        <td>${claimArticleInfo.email}</td>
			        <td>${claimArticleInfo.boardId}</td>
			        <td>${claimArticleInfo.majorGroupId}</td>
			        <td>${claimArticleInfo.univId}</td>
			        <td>${claimArticleInfo.hits}</td>
			        <td>${claimArticleInfo.articleLikes}</td>
			        <td>${claimArticleInfo.articleDislike}</td>
			        <td>${claimArticleInfo.articleScrab}</td>
			        
			      </tr>
    	</form>
   		<tr>
			<td colspan="14">
				<span id="deleteClaimArticleBtn" style="cursor: pointer;">글 삭제하기</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>