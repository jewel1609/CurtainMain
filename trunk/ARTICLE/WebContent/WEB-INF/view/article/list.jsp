<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>

<div class="container">
  <h2>커튼 게시판</h2>
  <p>자유롭게 이용하세요.</p>
  총 ${articles.paging.totalArticleCount} 건의 게시물이 있습니다.          
  <table class="table">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>조회수</th>
		<th>추천수</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${articles.articleList}" var="article"> 
		      <tr>
		        <td>${article.articleId}</td>
		        <td>
					<a href = "/addHit?articleId=${article.articleId}">
						${article.title}
					</a>
				</td>
		        <td>${article.nickName}</td>
		       	<td>${article.hits}</td>
		        <td>${article.recommends}</td>
		      </tr>
    	</c:forEach> 
    				<tr>
				<td colspan="5">
					<form id="searchForm">
						${ articles.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</form>
				</td>
			</tr>
    </tbody>
  </table>
   <a href="/write"><button type="button" class="btn btn-default" id="btnWrite">글쓰기</button></a>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>