<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form:form commandName="ArticleVO" method="post" action="/SpringArticle/doModifyAction" >
	글쓴이 : ${article.writer }<br/>
	<input type="hidden" name="articleId" value= "${article.articleId}" />
	<input type="hidden" name="writer" value= "${article.writer}" />
	<input type="text" name="subject" placeholder="글 제목을 입력하세요." value="${article.subject }"/><br/>
	<textarea name="description" placeholder="글 내용을 입력하세요.">${article.description }</textarea>
	<input type="submit" value="수정" />
	<input type="reset" value="취소" />
</form:form>
</body>
</html>