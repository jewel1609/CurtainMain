<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>

<div class="container">
  <h2>게시글 목록</h2>
  총 ${articles.paging.totalArticleCount} 건의 게시물이 있습니다.          
  <table class="table">
    <thead>
      <tr>
        <th>게시글ID</th>
        <th>제목</th>
        <th>내용</th>
        <th>등록일</th>
		<th>수정일</th>
		<th>카테고리</th>
		<th>EMAIL</th>
		<th>게시판ID</th>
		<th>소계열ID</th>
		<th>대학ID</th>
		<th>조회수</th>
		<th>좋아요</th>
		<th>싫어요</th>
		<th>스크랩</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${articles.articleList}" var="article"> 
		      <tr>
		        <td>${article.articleId}</td>
		        <td>${article.articleTitle }</td>
		        <td>${article.articleDesc }</td>
		        <td>${article.articleRegisterDate }</td>
		        <td>${article.articleModifyDate }</td>
		        <td>${article.articleTypeId }</td>
		        <td>${article.email }</td>
		        <td>${article.boardId }</td>
		        <td>${article.majorGroupId }</td>
		        <td>${article.univId }</td>
		        <td>${article.hits }</td>
		        <td>${article.articleLikes }</td>
		        <td>${article.articleDislike }</td>
		        <td>${article.articleScrab }</td>
		      </tr>
    	</c:forEach> 
    				<tr>
				<td colspan="9">
					<form id="searchForm">
						<input type="hidden" name="startDate" value="${startDate }"/>
						<input type="hidden" name="endDate" value="${endDate }"/>
						${ articles.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</form>
				</td>
			</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>