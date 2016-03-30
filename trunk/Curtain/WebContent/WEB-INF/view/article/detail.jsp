<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

<script type="text/javascript">

$(document).ready(function () {

/* 	$("#writeReplyBtn").click(function() {
		var form = $("#writeReplyForm");
		form.attr("method", "post");
		form.attr("action", "/doWriteReply");
		form.submit();
	}); 
*/
	
	/*새로 추가된것에 접근하는 방법*/
	$("body").on ("click", "#writeReplyBtn", function() {
		var form = $("#writeReplyForm");
		form.attr("method", "post");
		form.attr("action", "/doWriteReply");
		form.submit();
	});
	
	$(".writeReReply").click (function () {
		var table = $(this).parent().parent().parent().parent().parent().parent().parent();
		console.log(table.text);
		/*칼럼에 있는 정보 가져오고 싶을 때*/
		var parentReplyId = table.children(":eq(1)").children(":eq(0)").html();
		var orderNo = table.children(":eq(2)").children(":eq(0)").html();
		var replyId = table.children(":eq(3)").children(":eq(0)").html();
		
		
		$("#parentReplyId").val(replyId);
		$("#orderNo").val(orderNo);
		$("#replyId").val(replyId);
		
		var form = $("#formWrapper").html();
		$("#formWrapper").detach();
		
		if ( form == undefined ) {
			$(".formAppender").each(function (index, data) {
				if (data.innerHTML != "" ) {
					form = data.innerHTML;
				}
			});
			$(".formAppender").html("");
		}
		
		var formAppender = table.parent().parent().children(":eq(1)");
		formAppender.html(form);
		formAppender.show();
		
	});

});

</script>
   
<div class="w3-container w3-main" style="margin-top:0px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="border-right:1px solid #bababa;
    background-color: #F3F3F3; margin-left: 334px; margin-right: 100px; height:100%; overflow: auto;">
     		
     		<!-- 게시판 헤더 -->
     		<div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa;">
				<div class="w3-margin-4" style="float:left;">
					<h2> 무슨무슨 게시판입니다. </h2> 
				</div> 
				<div style="float:left; padding-top:20px;">
					<h5>   참여인원 명</h5> 
				</div>
			</div>
			
			<!-- 게시판 body -->	
	      <div class="w3-row-padding">
	        <div>
	          <div class="w3-card w3-white w3-round-large" style="margin-top:10px; margin-bottom:10px;">
	            <div class="w3-container">
	           	 	<div>
			           	 <div class="col-sm-12" style="margin-top:10px; padding-bottom: 10px; border-bottom:1px solid #bababa;">
			           	 	<div class="col-sm-1" style="padding-left:0px;">
								<c:if test="${article.articleTypeName eq '연애'}">
								<span class="label label-danger">${article.articleTypeName}</span>
								</c:if>
								<c:if test="${article.articleTypeName eq '고민'}">
									<span class="label label-warning">${article.articleTypeName}</span>
								</c:if>
								<c:if test="${article.articleTypeName eq '질문'}">
									<span class="label label-primary">${article.articleTypeName}</span>
								</c:if>
								<c:if test="${article.articleTypeName eq '취업'}">
									<span class="label label-info">${article.articleTypeName}</span>
								</c:if>
								<c:if test="${article.articleTypeName eq '기타'}">
									<span class="label label-default">${article.articleTypeName}</span>
								</c:if>		           	 	
			           	 	</div>
							
							<div class="col-sm-11" style="padding-left:0px;">
								<span style="color: #FF3300;"><h4>${article.articleTitle}</h4></span>
							</div>
							
						</div>
						<div>${article.articleDesc}
			            
				             <c:forEach items="${files}" var="file">
				             	<c:if test="${file.fileType eq '1'}">								
									<img src="/resource/img/${file.fileName}"/>
								</c:if>
								<c:if test="${file.fileType eq '2'}">
									<iframe width="560" height="315" src="${file.fileName}" ></iframe>
								</c:if>
							</c:forEach>

						</div>
			             <div>${article.articleModifyDate}  ${article.nickName}</div>
			            	<ul class="pager">
							  <li class="previous"><a href="/studentMajorAritlce">뒤로가기</a></li>
							  	<c:set var="loginId" value="${ sessionScope._MEMBER_.email}"/>
								<c:if test="${ loginId eq article.email }">
								<li class="next"><a href="<c:url value="/updateArticle?articleId=${article.articleId}"/>">수정하기</a></li>
								<li class="next"><a href="<c:url value="/deleteArticle?articleId=${article.articleId}&email=${article.email}"/>">삭제하기</a></li>
								</c:if>
							</ul>  
							
							<div class="col-sm-12">
						<h6>조회수 ${article.hits}</h6>
						</div>
							
						
			        
  					</div>
  				</div>
            </div>

       	</div>
	        </div>
	        <div class="w3-row-padding w3-margin-top">
				<div class="w3-col m12 w3-round-large">
					<div id="formWrapper" class="w3-container">
						<form id="writeReplyForm">
							<input type="hidden" id="articleId" name="articleId" value="${article.articleId}" />
							<input type="hidden" id="parentReplyId" name="parentReplyId" value="0"/>
							<input type="hidden" id="orderNo" name="orderNo" value="0"/>
							<input type="hidden" id="replyId" name="replyId" value="0"/>
							<textarea id="replyDesc" name="replyDesc" class="w3-col m12"></textarea><br/>
							<input type="button" id="writeReplyBtn" value="댓글 등록" />
						</form>
					</div>
				</div>
			</div>
			<div>
				<c:forEach items="${article.replyList}" var="reply">
					<div >
						<table width="100%">
							<tr>
								<th>
							     	<div class="w3-row-padding w3-margin-top">
										<div class="w3-col m12">
											<div class="w3-card-2 w3-white w3-round-large">
												<div class="w3-container">
													<c:if test="${reply.parentReplyId ne reply.replyId}">
													  <div> ==> ${reply.replyDesc}</div>
													</c:if>
													<c:if test="${reply.parentReplyId eq reply.replyId}">
													${reply.replyDesc}
													</c:if>
													${reply.nickName}
													<c:if test="${reply.parentReplyId eq reply.replyId}">
													<span class="writeReReply" style="cursor:pointer;">댓글 달기</span>
													</c:if>
												</div>
											</div>
										</div>
									</div>     
								</th>
	
							</tr>
							<tr>
								<td class="hide parentReplyId">${reply.parentReplyId}</td>
							</tr>
							<tr>
								<td class="hide orderNo">${reply.replyOrder}</td>
							</tr>
							<tr >
								<td colspan="2" class="hide replyId">${reply.replyId}</td>
							</tr>
						</table>
						<div class="formAppender"></div>
					</div>
				</c:forEach>
			</div>
	
 
    	</div> 
	</div>
	

</div>
