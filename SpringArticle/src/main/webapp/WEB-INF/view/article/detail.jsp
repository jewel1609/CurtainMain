<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>detail Page</title>
</head>
<body>

	게시물ID : ${article.articleId } <br/>
	번호 : ${article.articleNumber } <br/>
	작성자 : ${article.writer } <br/>
	제목 : ${article.subject } <br/>
	내용 : ${article.description } <br/>
	등록일자 : ${article.createdDate } <br/>
	수정일자 : ${article.modifiedDate } <br/>
	
	<a href="/SpringArticle/delete/${article.articleId }">삭제</a>&nbsp;&nbsp;&nbsp;
	<a href="/SpringArticle/modify/${article.articleId }">수정</a>&nbsp;&nbsp;&nbsp;
	<a href="/SpringArticle/list">리스트로</a>
</body>
</html>