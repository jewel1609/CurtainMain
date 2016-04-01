<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>

<div class="container">
  <h2>커튼 게시판</h2>
  <p>자유롭게 이용하세요.</p>
            
  <table class="table">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>글쓴이의 ID</th>
        <th>글내용</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
		      <tr>
					<td>${article.articleId}</td>
					<td>${article.title}</td>
					<td>${article.nickName}</td>
					<td>${article.memberId}</td>
					<td>${article.descript}</td>
					<td>${article.hits}</td>
			</tr>
			<tr>
				<c:forEach items="${article.fileList}" var="file">
				<td colspan="6">
					<a href="/download?fileId=${file.fileId}&articleId=${article.articleId}">
						${file.fileName}
					</a>
				</td>
			</c:forEach>
			</tr>
    </tbody>
  </table>
</div>
<div class="container">
  <ul class="pager">
    <li class="previous"><a href="/list">뒤로가기</a></li>
    <c:set var="loginId" value="${ sessionScope._MEMBER_.memberId}"/>
	<c:if test="${ loginId eq article.memberId }">
    <li class="previous"><a href="/update?articleId=${article.articleId}">수정</a></li>
    <li class="previous"><a href="delete?articleId=${article.articleId}">삭제</a></li>
    </c:if>
    <li class="next"><a href="/addRecommend?articleId=${article.articleId}" >
          <span class="glyphicon glyphicon-thumbs-up">&nbsp;${article.recommends}</span>
        </a>
     </li>

  </ul>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>