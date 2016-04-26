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

<form:form commandName="ArticleVO" method="post" action="/SpringArticle/doWriteAction" >
	<input type="text" name="subject" placeholder="글 제목을 입력하세요."/><br/>
	<form:errors path="subject"/>
	<input type="text" name="writer" placeholder="글 쓴이를 입력하세요."/><br/>
	<form:errors path="writer"/>
	<textarea name="description" placeholder="글 내용을 입력하세요."></textarea>
	<input type="submit" value="제출" />
</form:form>
</body>
</html>