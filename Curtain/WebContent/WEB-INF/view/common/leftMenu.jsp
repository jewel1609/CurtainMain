<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="w3-sidenav w3-collapse w3-white w3-animate-left w3-card-2" style="z-index:3;width:250px; height:400px; margin-top:185px;">
  <a href="#" class="w3-border-bottom w3-large"><img src="http://www.w3schools.com/images/w3schools.png" style="width:80%;"></a>
  <a href="javascript:void(0)" onclick="w3_close()" 
  class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>
  <div class="w3-accordion">
    <a onclick="myAccordion('demo')" href="javascript:void(0)">학생 게시판<i class="fa fa-caret-down"></i></a>
    <div id="demo" class="w3-accordion-content w3-animate-left w3-padding">
      <a href="<c:url value="/studentMajorAritlce"/>">컴퓨터공학</a>
      <a href="#">밀당대학교</a>
    </div>
  </div>	
  <a href="#">홍보게시판</a>	
  <a href="/Curtain/myPage">마이페이지</a>		
   <a href="<c:url value="/questionAndAnswer" />">Q & A</a>		
</nav>