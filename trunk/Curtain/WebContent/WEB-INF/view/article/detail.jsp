<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

<script type="text/javascript">

$(document).ready(function () {
	
	$(".wrapper").css("height", window.innerHeight - 130);
	
	$(window).resize(function() {
		$(".wrapper").css("height", window.innerHeight - 130);
	});
/* 	$("#writeReplyBtn").click(function() {
		var form = $("#writeReplyForm");
		form.attr("method", "post");
		form.attr("action", "/doWriteReply");
		form.submit();
	}); 
*/
	if($("#isFword").val() == '1'){
		alert("비방글은 게시하실 수 없습니다.");
	}
	
	$(".claim").hide();
	
	$(".doClaim").click(function () {
		
		var root = $(this).parent().parent().children(":eq(4)");
		
		root.slideToggle();
		
	});
	
	$(".doClaimBtn").click(function() {		
		
		var root = $(this).parent().children(":eq(0)");

		$.post(		
			"/writeReplyClaim"
			, { "claimText" : root.val()
				, "replyClaim" : $(this).attr("id")
			}
			, function(data) {
				
				var jsonData = {};		
				
				try {
					jsonData = JSON.parse(data);
				}
				catch(e) {
					jsonData.result = false;
				}
				
				if(jsonData.result){
					$(".claim").hide();
					alert("신고 완료");
				}
				else{
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href="/";
				}
			}
		)	
	});
		
	$(".like").click(function() {		
		
		$.post(		
			"/replyLike"
			, { "replyId" : $(this).attr("id")
				, "articleId" : $("#articleId").val()
			}
			, function(data) {
				
				var jsonData = {};		
				
				try {
					jsonData = JSON.parse(data);
				}
				catch(e) {
					jsonData.result = false;
				}
				
				if(jsonData.result){
					var replyId = jsonData.replyId;
					var result = "#like" + replyId;
					if(jsonData.doLike){
						$(result).attr("src", "/resource/img/like_active_small.png");
						var count = "#likeCount"+jsonData.replyId;
						$(count).text(jsonData.updateLikeCount);
					}
					else{
						$(result).attr("src", "/resource/img/like_inactive_small.png");
						var count = "#likeCount"+jsonData.replyId;
						$(count).text(jsonData.updateLikeCount);
					}	
				}
				else{
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href="/";
				}
			}
		)	
	});
	
	
	
	$(".dislike").click(function() {		
		
		$.post(		
			"/replyDislike"
			, { "replyId" : $(this).attr("id")
				, "articleId" : $("#articleId").val()
			}
			, function(data) {
				
				var jsonData = {};		
				
				try {
					jsonData = JSON.parse(data);
				}
				catch(e) {
					jsonData.result = false;
				}
				
				if(jsonData.result){
					var replyId = jsonData.replyId;
					var result = "#dislike" + replyId;
					if(jsonData.isDislike){
						$(result).attr("src", "/resource/img/dislike_active_small.png");
						var count = "#dislikeCount"+jsonData.replyId;
						$(count).text(jsonData.updateDislikeCount);
					}
					else{
						$(result).attr("src", "/resource/img/dislike_inactive_small.png");
						var count = "#dislikeCount"+jsonData.replyId;
						$(count).text(jsonData.updateDislikeCount);
					}	
				}
				else{
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href="/";
				}
			}
		)	
	});
	
	
	/*새로 추가된것에 접근하는 방법*/
	$("body").on ("click", "#writeReplyBtn", function() {
		
		if( $("#replyDesc").val() == "") {
			alert("내용을 입력하세요.");
			return;
		}
		
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
 
<input type="hidden" id="isFword" value="<%= request.getParameter("isFword") %>" />
  

<div class="w3-container w3-main" style="margin-top:0px;">    

	<c:set var="memberType" value="${ sessionScope._MEMBER_.memberTypeId }" />
	<c:set var="univId" value="${ sessionScope._MEMBER_.univId }" />
	<c:set var="majorGroupId" value="${ sessionScope._MEMBER_.majorGroupId }" />
	<c:set var="univName" value="${ sessionScope._UNIVNAME_}" />
	<c:set var="majorGroupName" value="${ sessionScope._MAJORGROUPNAME_ }" />
	<c:set var="boardId" value="${ sessionScope._BOARD_ID_ }" />
	


  <div class="w3-row" >
     <div class="w3-col m7 w3-main wrapper"
         style="border-right:1px solid #bababa; margin-left: 334px; margin-right: 100px; height: 905px; overflow: auto; ">
         
       		
     		<!-- 게시판 헤더 -->
     		 <div class="w3-row" style="border-bottom: 1px solid #bababa;">
				 <div class="w3-margin-4" style="float:left; padding-left:10px;">
					<c:if test="${boardId eq 1}">
						<h2>${majorGroupName} 게시판입니다.</h2>
					</c:if>
					<c:if test="${boardId eq 2}">
						<h2>${univName} 게시판입니다.</h2> 
					</c:if>
					<c:if test="${boardId eq 3}">
						<h2>홍보게시판입니다.</h2>
					</c:if>
					<c:if test="${boardId eq 4 }">
						<h2>자유게시판입니다.</h2>
					</c:if>
					<c:if test="${boardId eq 5 }">
						<h2>한겹커튼 게시판입니다.</h2>
					</c:if>		
					<c:if test="${boardId eq 6 }">
						<h2>두겹커튼 게시판입니다.</h2>
					</c:if>									
				</div> 

			</div>
			
			<!-- 게시판 body -->	
	       <div class="w3-row-padding" style="border-bottom:1px solid #BABABA; background-color:#F3F3F3;">
            <div class="w3-col m12" align="left" >
	          <div class="w3-card w3-white w3-round-large" style="margin-top:10px; margin-bottom:10px;">
	            <div class="w3-container">
	           	 	<div>
			           	 <div class="col-sm-12" style="margin-top:10px; padding-bottom: 10px; border-bottom:1px solid #bababa;">
			           	 	<div class="col-sm-1" style="padding-left:0px; padding-top:4px;">
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
													
						<div class="col-sm-12" style="margin-bottom:20px;">
							<div class="col-sm-11" style="padding-left:0px;">
								<span style="color: #FF3300;">
									<h5>
									${article.nickName} &nbsp;&nbsp;&nbsp;&nbsp;
									${article.articleModifyDate}
									</h5>
								</span>
							</div>
							<div class="col-sm-1"><h6>조회수 ${article.hits}</h6></div>
						</div>
						<div>

							<div>
							${article.articleDesc}
							</div>
							
				            <br/>
				             <c:forEach items="${files}" var="file">
				             	<c:if test="${file.fileType eq '1'}">								
									<img width="560" height="315" src="/resource/img/${file.fileName}"/>
								</c:if>
								<c:if test="${file.fileType eq '2'}">
									<iframe width="560" height="315" src="${file.fileName}" ></iframe>
								</c:if>
							</c:forEach>

						</div>
			            	<ul class="pager">
			            			<c:if test="${article.boardId eq 1}">
										 <li class="previous"><a href="/studentMajorAritlce" style="color:#FF3300; font-size: 10pt;">뒤로가기</a></li>
									</c:if>
									<c:if test="${article.boardId eq 2}">
										 <li class="previous"><a href="/studentUnivArticle" style="color:#FF3300; font-size: 10pt;">뒤로가기</a></li>
									</c:if>
									<c:if test="${article.boardId eq 3}">
										 <li class="previous"><a href="/promotionArticle" style="color:#FF3300; font-size: 10pt;">뒤로가기</a></li>
									</c:if>
									<c:if test="${article.boardId eq 4 }">
										 <li class="previous"><a href="/secretArticleList" style="color:#FF3300; font-size: 10pt;">뒤로가기</a></li>
									</c:if>
									<c:if test="${article.boardId eq 5 }">
										 <li class="previous"><a href="/oneLayerCurtain" style="color:#FF3300; font-size: 10pt;">뒤로가기</a></li>
									</c:if>		
									<c:if test="${article.boardId eq 6 }">
										 <li class="previous"><a href="/twoLayerCurtain" style="color:#FF3300; font-size: 10pt;">뒤로가기</a></li>
									</c:if>	
							   	<c:set var="loginId" value="${ sessionScope._MEMBER_.email}"/>
								<c:if test="${ loginId eq article.email }">
								${article.boardId}
								<li class="next"><a href="<c:url value="/updateArticle?articleId=${article.articleId}&boardId=${article.boardId}"/>"  style="color:#FF3300; font-size: 10pt;">수정하기</a></li>
								<li class="next"><a href="<c:url value="/deleteArticle?articleId=${article.articleId}&email=${article.email}"/>"  style="color:#FF3300; font-size: 10pt;">삭제하기</a></li>
								</c:if>
							</ul>  
  					</div>
  				</div>
            </div>

       	</div>
	  </div>
	  
	  <!-- 댓글부분 -->
	  <div id="formWrapper" class="w3-margin-32" >
			<div class="w3-col m12">
				<div class="w3-container w3-card" style="background-color: white; margin-bottom:50px;">
					<form id="writeReplyForm">
						<input type="hidden" id="articleId" name="articleId" value="${article.articleId}" />
						<input type="hidden" id="parentReplyId" name="parentReplyId" value="0"/>
						<input type="hidden" id="orderNo" name="orderNo" value="0"/>
						<input type="hidden" id="replyId" name="replyId" value="0"/>
						<textarea id="replyDesc" name="replyDesc" class="w3-col m12" style="border: none; margin-bottom:10px;"></textarea><br/>
						<button type="button" class="btn btn-default" id="writeReplyBtn" style="float:right; color:#FF3300;">댓글 등록</button>
					</form>
				</div>
			</div>
		</div>
			<div style="margin-top:30px;">
				<c:forEach items="${article.replyList}" var="reply">
					<div>
						<table width="100%">
							<tr>
								<th>
							     	<div class="w3-row-padding">
										<div class="w3-col m12">
											<div class="w3-card w3-white">
												<div class="w3-container">
												
													<div>
														<c:if test="${reply.parentReplyId ne reply.replyId}">
														  <div> ㄴ ${reply.replyDesc}</div>
														</c:if>
														
														<c:if test="${reply.parentReplyId eq reply.replyId}">
															${reply.replyDesc}
														</c:if>
													</div>
													
													<div class="w3-col m8 w3-padding-bottom">
														<div style="float:left; margin-right:10px;">
															<c:if test="${reply.like}">
																<img class="like" id="like${reply.replyId}" src="/resource/img/like_active_small.png" style="width:20px;">	
																<span id="likeCount${reply.replyId}">${reply.replyLikes}</span>
															</c:if>
															<c:if test="${!reply.like}">
																<img class="like" id="like${reply.replyId}" src="/resource/img/like_inactive_small.png" style="width:20px;">
																<span id="likeCount${reply.replyId}">${reply.replyLikes}</span>
															</c:if>
														</div>
														<div>
															<c:if test="${reply.dislike}">
																<img class="dislike" id="dislike${reply.replyId}" src="/resource/img/dislike_active_small.png" style="width:20px;">
																<span id="dislikeCount${reply.replyId}">${reply.replyDislikes}</span>
															</c:if>
															<c:if test="${!reply.dislike}">
																<img class="dislike" id="dislike${reply.replyId}" src="/resource/img/dislike_inactive_small.png" style="width:20px;">
																<span id="dislikeCount${reply.replyId}">${reply.replyDislikes}</span>
															</c:if>
														</div>
													</div>
													
													<span style="color: #FF3300;"><h5>${reply.nickName}</h5></span>
													<div class="w3-col m1">
														<span class="doClaim glyphicon glyphicon-send">신고하기</span>
													</div>
													<div class="claim w3-col m12">
														<form class="claimWrite">
															<input class="w3-input" type="text" class="claimCom" id="claimCom${reply.replyId}" name="claimCom${reply.replyId}"
																			placeholder="신고 사유를 입력하세요." style="margin-bottom: 5px;">
															<span class="doClaimBtn" id="claim${reply.replyId}">신고하기</span>
														</form>
													</div>
													
													<c:if test="${reply.parentReplyId eq reply.replyId}">
														<button type="button" class="btn btn-default btn-xs writeReReply ">
															댓글쓰기
														</button>
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
