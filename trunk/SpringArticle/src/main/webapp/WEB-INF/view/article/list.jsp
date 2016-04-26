<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/SpringBoard/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${articleListVO.articleList}" var="article">
	${article.articleId} / 
	${article.articleNumber} / 
	<a href="/SpringArticle/addHit/${article.articleId}">${article.subject}</a> / 
	${article.writer} / 
	${article.createdDate} / 
	${article.modifiedDate}
	<br/>

</c:forEach>
<form id="pageForm">
	${articleListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pageForm")}
</form>
list page 입니다.

</body>
</html>