<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="w3-sidenav w3-collapse w3-white w3-card"
	style="z-index: 3; width: 250px; height: 800px; margin-top: 110px; left: 100px;">
	<a href="javascript:void(0)" onclick="w3_close()"
		class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>

	<c:set var="memberType" value="${ sessionScope._MEMBER_.memberTypeId }" />
	<c:set var="univId" value="${ sessionScope._MEMBER_.univId }" />
	<c:set var="majorGroupId" value="${ sessionScope._MEMBER_.majorGroupId }" />
	<c:set var="univName" value="${ sessionScope._UNIVNAME_}" />
	<c:set var="majorGroupName" value="${ sessionScope._MAJORGROUPNAME_ }" />

	<c:if test="${memberType eq 1 || memberType eq 2 || memberType eq 3}">
		<div class="w3-accordion">
			<a onclick="myAccordion('demo')" href="javascript:void(0)">
				학생게시판<i class="fa fa-caret-down"></i>
			</a>
			<div id="demo" class="w3-accordion-content w3-animate-left w3-padding">
				<a href="<c:url value="/studentMajorAritlce?majorGroupId="/>${majorGroupId}">${majorGroupName}</a> 
				<a href="<c:url value="/studentUnivArticle?univId="/>${univId}">${univName}</a>
			</div>
		</div>

		<div class="w3-accordion">
			<a onclick="myAccordion('demo1')" href="javascript:void(0)">비밀게시판<i class="fa fa-caret-down"></i></a>
			<div id="demo1" class="w3-accordion-content w3-animate-left w3-padding">
				<a href="<c:url value="/secretArticleList"/>">자유게시판</a> 
				<c:if test="${memberType eq 2 || memberType eq 3}">
					<a href="<c:url value="/studentUnivArticle"/>">한겹</a> 
					<c:if test="${memberType eq 3 }">
						<a href="<c:url value="/studentUnivArticle"/>">두겹</a>
					</c:if>
				</c:if>
			</div>
		</div>
	</c:if>

	<c:if test="${memberType eq 1 || memberType eq 2 || memberType eq 3 || memberType eq 4 || memberType eq 5}">
		<a href="#">홍보게시판</a>

		<a href="/myPage">마이페이지</a>

		<a href="<c:url value="/questionAndAnswer" />">FAQ / Q&A</a>
	</c:if>
	
</nav>
