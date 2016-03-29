<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="w3-sidenav w3-light-grey w3-collapse w3-white" style="z-index:3; width:250px; height:800px; margin-top:105px; left: 100px; border-right:1px solid #bababa; border-left:1px solid #bababa;">
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-hide-large w3-closenav w3-large">메뉴 닫기</a>
  <div class="w3-accordion">

    <a onclick="myAccordion('demo')" href="javascript:void(0)">학생 게시판  <i class="fa fa-caret-down"></i></a>
    <div id="demo" class="w3-accordion-content w3-animate-left w3-padding">
      <a href="<c:url value="/studentMajorAritlce"/>">컴퓨터공학</a>
      <a href="<c:url value="/studentUnivArticle"/>">밀당대학교</a>
    </div>
  </div>
    <div class="w3-accordion">
    <a onclick="myAccordion('demo1')" href="javascript:void(0)">비밀게시판  <i class="fa fa-caret-down"></i></a>
    <div id="demo1" class="w3-accordion-content w3-animate-left w3-padding">
      <a href="<c:url value="/secretArticleList"/>">한겹커튼</a>
      <a href="<c:url value="/studentUnivArticle"/>">두겹커튼</a>
       <a href="<c:url value="/studentUnivArticle"/>">세겹커튼</a>
    </div>
  </div>	
  <a href="#">홍보게시판</a>	
  <a href="/myPage">마이페이지</a>		
   <a href="<c:url value="/questionAndAnswer" />">FAQ / Q&A</a>		
</nav>
