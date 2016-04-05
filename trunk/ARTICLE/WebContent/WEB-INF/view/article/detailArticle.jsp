<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>

<div class="container">
  <h2>게시글 상세페이지</h2>
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
	      <tr>
	        <td>${articleInfo.articleId}</td>
	        <td>${articleInfo.articleTitle}</td>
	       	<td>${articleInfo.articleDesc}</td>
	        <td>${articleInfo.articleRegisterDate}</td>
	        <td>${articleInfo.articleModifyDate}</td>
	        <td>${articleInfo.articleTypeId}</td>
	        <td>${articleInfo.email}</td>
	        <td>${articleInfo.boardId}</td>
	        <td>${articleInfo.majorGroupId}</td>
	        <td>${articleInfo.univId}</td>
	        <td>${articleInfo.hits}</td>
	        <td>${articleInfo.articleLikes}</td>
	        <td>${articleInfo.articleDislike}</td>
	        <td>${articleInfo.articleScrab}</td>
	      </tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>