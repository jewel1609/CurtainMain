<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<nav class="w3-sidenav w3-collapse w3-white w3-card" 
			style="z-index: 3; width: 250px; height: 100%; 
			margin-top: 0px; border:0px; border-left:1px solid #bababa; 
			border-right:1px solid #bababa;  left: 100px;" >
			
		<div style="padding: 40px; text-align: center; border-bottom:1px solid #BABABA;">
			<strong><mark>${ sessionScope._MEMBER_.nickName }</mark></strong>님<br/> 환영합니다.
			
		</div>
			
	<a href="javascript:void(0)" onclick="w3_close()" class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>

	<c:set var="memberType" value="${ sessionScope._MEMBER_.memberTypeId }" />
	<c:set var="univId" value="${ sessionScope._MEMBER_.univId }" />
	<c:set var="majorGroupId" value="${ sessionScope._MEMBER_.majorGroupId }" />
	<c:set var="univName" value="${ sessionScope._UNIVNAME_}" />
	<c:set var="majorGroupName" value="${ sessionScope._MAJORGROUPNAME_ }" />
	<c:set var="nickName" value="${ sessionScope._MEMBER_.nickName }" />
	<c:set var="companyName" value="${ sessionScope._MEMBER_.companyName }" />
	
	<c:if test="${ companyName eq null}">
		<div class="w3-accordion">
			<a onclick="myAccordion('demo')" href="javascript:void(0)">학생게시판<i class="fa fa-caret-down"></i></a>
			<div id="demo" class="w3-accordion-content w3-animate-left w3-padding">
			<c:if test="${memberType eq 1 || memberType eq 2 || memberType eq 3}">
				<div id="majorGroup">
					<a href="<c:url value="/studentMajorAritlce"/> ">&nbsp;&nbsp;&nbsp;${majorGroupName}</a>
				</div> 
				<div id="univFill">
					<a href="<c:url value="/studentUnivArticle"/> ">&nbsp;&nbsp;&nbsp;${univName}</a>
				</div>
			</c:if>
			</div>
		</div>

		<div class="w3-accordion">
			<a onclick="myAccordion('demo1')" href="javascript:void(0)">비밀게시판<i class="fa fa-caret-down"></i></a>
			<div id="demo1" class="w3-accordion-content w3-animate-left w3-padding">
				<c:if test="${memberType eq 2 || memberType eq 3}">
					<div id="oneLayerCurtain">
						<a href="<c:url value="/oneLayerCurtain"/>">한겹커튼</a> 
					</div>
					<c:if test="${memberType eq 3 }">
						<div id="twoLayerCurtain">
							<a href="<c:url value="/twoLayerCurtain"/>">두겹커튼</a>
						</div>
					</c:if>
				</c:if>
			</div>
		</div>
		<div id="freeArticle">
			<a href="<c:url value="/secretArticleList"/>">전국 대학생 게시판</a> 
		</div>
	</c:if>
		
		
	<c:if test="${memberType eq 1 || memberType eq 2 || memberType eq 3 || memberType eq 4 || memberType eq 5}">
		<div id="promotion">
			<a href="<c:url value="/promotionArticle"/>">홍보게시판</a>
		</div>
		<div id="myPage">
			<a href="/myPage">마이페이지</a>
		</div>
		<div id="qna">
		<a href="<c:url value="/questionAndAnswer" />">FAQ / Q&A</a>
		</div>
	</c:if>

	
	</nav>
	
