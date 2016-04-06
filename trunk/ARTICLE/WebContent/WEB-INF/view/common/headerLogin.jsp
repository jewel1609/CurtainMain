<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>커튼에 오신것을 환영합니다.</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="/resource/css/header.css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	
	</script>
	
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/successLogin">커튼</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/successLogin">Home</a></li>
        <c:if test="${ sessionScope._MEMBER_.memberTypeId eq 6 }">
	        <li><a href="/showMember">회원관리</a></li>
	        <li><a href="/univList/init">대학관리</a></li>
	        <li><a href="/majorGroupList/init">학과관리</a></li>
	        <li><a href="/showClaimArticle">신고된글</a></li>
	        <li><a href="/insertSurvey">설문등록</a></li>
	        <li><a href="/surveyList">설문리스트</a></li>
	        <li><a href="/insertProhibitedWord">금칙어등록</a></li>
	        <li><a href="/articleFindByDate">게시글관리</a></li>
	        <li><a href="/historyList">히스토리확인</a></li>
	        <li><a href="/showQuestion">질문관리</a></li>
        </c:if>
      </ul>
      <ul class="nav navbar-nav navbar-right">

	      	<li><a href="/doLogout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>

      </ul>
    </div>
  </div>
</nav>
  