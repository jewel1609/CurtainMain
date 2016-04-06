<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="/resource/css/article/secretArticle.css">
<!-- Magnific Popup core CSS file -->
<link rel="stylesheet" href="/resource/css/article/magnific-popup.css">

<!-- Magnific Popup core JS file -->
<script src="/resource/js//jquery.magnific-popup.js"></script>

<script type="text/javascript">


$(document).ready(function () {
	
	$(document).ready(function() {
		
		$(document).ready(function() {
			$('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
				disableOn: 700,
				type: 'iframe',
				mainClass: 'mfp-fade',
				removalDelay: 160,
				preloader: false,

				fixedContentPos: false
			});
		});
		
		$('.popup-gallery').magnificPopup({
			delegate: 'a',
			type: 'image',
			tLoading: 'Loading image #%curr%...',
			mainClass: 'mfp-img-mobile',
			gallery: {
				enabled: true,
				navigateByImgClick: true,
				preload: [0,1] // Will preload 0 - before current, and 1 after the current image
			},
			image: {
				tError: '<a href="%url%">The image #%curr%</a> could not be loaded.',
				titleSrc: function(item) {
					return item.el.attr('title');
				}
			}
		});
	});
	
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
					alert("신고 완료되었습니다.");
					 $(result).attr("style", "color:#7d7d7d");
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
						$(result).attr("style", "color:#a9d039");
						var count = "#likeCount"+jsonData.replyId;
						$(count).text(jsonData.updateLikeCount);
					}
					else{
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
						$(result).attr("style", "color:#a9d039");
						var count = "#dislikeCount"+jsonData.replyId;
						$(count).text(jsonData.updateDislikeCount);
					}
					else{
						$(result).attr("style", "color:#a9d039");
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
		var table = $(this).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
		console.log(table.text);
		
		
		
		/*칼럼에 있는 정보 가져오고 싶을 때*/
		var parentReplyId = table.children(":eq(0)").children(":eq(1)").children(":eq(0)").html();
		var orderNo = table.children(":eq(0)").children(":eq(2)").children(":eq(0)").html();
		var replyId = table.children(":eq(0)").children(":eq(3)").children(":eq(0)").html();
		
		
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
		
		var formAppender = table.parent().children(":eq(1)");
		formAppender.html(form);
		formAppender.show();
		
	});
	
	$(".deleteRe").click(function () {
		var table = $(this).parent().parent().parent().parent().parent().parent().parent().parent();
		var replyId = table.children(":eq(0)").children(":eq(0)").html();
		alert(replyId);
		$("#delete").val(replyId);
		
		var form = $("#deleteReplyForm");
		form.attr("method", "post");
		form.attr("action", "/doDeleteReply");
		form.submit();
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
						<h2>홍보 게시판입니다.</h2>
					</c:if>
					<c:if test="${boardId eq 4 }">
						<h2>자유 게시판입니다.</h2>
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
            <div class="w3-col w3-card w3-white w3-round-large m12" align="left" >
	          <div class="w3-white " style="margin-top:10px; margin-bottom:10px;">
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
								<c:if test="${article.articleTypeName eq '동아리모집'}">
									<span class="label label-danger">${article.articleTypeName}</span>
								</c:if>
								<c:if test="${article.articleTypeName eq '대외활동모집'}">
									<span class="label label-warning">${article.articleTypeName}</span>
								</c:if>
								<c:if test="${article.articleTypeName eq '취업관련'}">
									<span class="label label-primary">${article.articleTypeName}</span>
								</c:if>
								<c:if test="${article.articleTypeName eq '스터디모집'}">
									<span class="label label-info">${article.articleTypeName}</span>
								</c:if>	           	 	
			           	 	</div>
							
							<div class="col-sm-11" style="padding-left:0px;">
								<span style="color: #FF3300;"><h4>${article.articleTitle}</h4></span>
							</div>
							
						</div>
													
						<div class="col-sm-12" style="margin-bottom:10px;">
							<div class="col-sm-11" style="padding-left:0px;">
								<span>
									<h6>
									${article.nickName} &nbsp;&nbsp;&nbsp;&nbsp;
									${article.articleModifyDate}
									</h6>
								</span>
							</div>
							<div class="col-sm-1" align="right"><h6>조회수 ${article.hits}</h6></div>
						</div>
						
						<div>

							<div class="w3-padding">
							${article.articleDesc}
							</div>
							
				            <br/>
				             <c:forEach items="${files}" var="file">
				             
				             	<c:if test="${file.fileType eq '1'}">	
				             	<div class="popup-gallery w3-padding">
					             	<a class="image-popup-fit-width" href="/resource/img/${file.fileName}">
										<img src="/resource/img/${file.fileName}" width="50%">
									</a>
									<!-- 여기에 이미지 추가하면댐 -->
								</div>
									
								</c:if>
								<c:if test="${file.fileType eq '2'}">
									<a class="popup-youtube w3-padding" href="${file.fileName}">
										<iframe width="560" height="315" src="${file.fileName}" ></iframe>
									</a>
								</c:if>
							</c:forEach>

						</div>
			            	<ul class="pager">
			            			<c:if test="${article.boardId eq 1}">
										 <li class="previous">
										    <a href="/studentMajorAritlce" class="btn btn-lg">
									          <span class="glyphicon glyphicon-arrow-left" style="color:#FF3300; "></span>
									        </a>
										 </li>
									</c:if>
									<c:if test="${article.boardId eq 2}">
										 <li class="previous">
										    <a href="/studentUnivArticle" class="btn btn-info btn-lg">
									          <span class="glyphicon glyphicon-arrow-left" style="color:#FF3300; "></span>
									        </a>
										 </li>
									</c:if>
									<c:if test="${article.boardId eq 3}">
										 <li class="previous">
										    <a href="/promotionArticle" class="btn btn-info btn-lg">
									          <span class="glyphicon glyphicon-arrow-left" style="color:#FF3300; "></span>
									        </a>
										 </li>
									</c:if>
									<c:if test="${article.boardId eq 4 }">
										 <li class="previous">
										    <a href="/secretArticleList" class="btn btn-info btn-lg">
									          <span class="glyphicon glyphicon-arrow-left" style="color:#FF3300; "></span>
									        </a>
										 </li>
									</c:if>
									<c:if test="${article.boardId eq 5 }">
										 <li class="previous">
										    <a href="/oneLayerCurtain" class="btn btn-info btn-lg">
									          <span class="glyphicon glyphicon-arrow-left" style="color:#FF3300; "></span>
									        </a>
										 </li>
									</c:if>		
									<c:if test="${article.boardId eq 6 }">
										 <li class="previous">
										    <a href="/twoLayerCurtain" class="btn btn-info btn-lg">
									          <span class="glyphicon glyphicon-arrow-left" style="color:#FF3300; "></span>
									        </a>
										 </li>
									</c:if>	
							   	<c:set var="loginId" value="${ sessionScope._MEMBER_.email}"/>
								<c:if test="${ loginId eq article.email }">
								<!-- ${article.boardId} -->
								<li class="next">
								<a href="<c:url value="/updateArticle?articleId=${article.articleId}&boardId=${article.boardId}"/>"  style="color:#FF3300; font-size: 10pt;">수정</a>
								</li>
								<li class="next">
								<a onClick="deleteFunction()" href="<c:url value="/deleteArticle?articleId=${article.articleId}&email=${article.email}"/>"  style="color:#FF3300; font-size: 10pt;">삭제</a>
								</li>
								</c:if>
							</ul>  
  					</div>
  				</div>
  				
  				<c:set var="nickName" value="${ sessionScope._MEMBER_.nickName }" />
  				
  				<!-- 댓글부분 -->
  				
  				<div id="formWrapper" class="w3-margin-32" >
  					<div class="w3-col m12">
					<div class="w3-margin w3-padding w3-container w3-card" style="background-color: white;">
					<form id="writeReplyForm">
						<input type="hidden" id="articleId" name="articleId" value="${article.articleId}" />
						<input type="hidden" id="parentReplyId" name="parentReplyId" value="0"/>
						<input type="hidden" id="orderNo" name="orderNo" value="0"/>
						<input type="hidden" id="replyId" name="replyId" value="0"/>
						
						<div class="w3-col m12" ><h6>${nickName}</h6></div>
						<textarea id="replyDesc" name="replyDesc" rows="3" class="form-control w3-col m10" style="margin-right:10px;"></textarea>
						<button type="button" class="btn btn-default w3-col m1 w3-padding" id="writeReplyBtn" style="color:#FF3300; height:70px;">댓글등록</button>
					
					</form>
				</div>
				</div>
				</div>
		<form id="deleteReplyForm">
			<input type="hidden" id="delete" name="delete" value=""/>
			<input type="hidden" id="articleId2" name="articleId2" value="${article.articleId}" />
		</form>
				<!-- 답글 -->
			<div style="margin-top:20px;">
				<c:forEach items="${article.replyList}" var="reply">
					<div style="margin-left:32px; margin-right:32px;" >
						<table width="100%">
							<tr>
								<td class="hide replyId">${reply.replyId}</td>
								<td>
							     	<div class="w3-row-padding">
										<div class="w3-col m12">
											<div class="w3-card w3-white">
												<div class="w3-container">
													<div>
														<span>
															<h6 style="float:left; margin-right:20px; color:#FF3300;">${reply.nickName} </h6>
															<h6 style="padding-top:10px;">${reply.replyRegisterDate}</h6>
														</span>
														
														<c:if test="${reply.parentReplyId ne reply.replyId}">
														  <div style="height:60px;"> ㄴ ${reply.replyDesc}</div>
														</c:if>
														
														<c:if test="${reply.parentReplyId eq reply.replyId}">
															<div style="height:60px;">
																${reply.replyDesc}
																<button type="button" class="btn btn-default btn-xs writeReReply ">
																ㄴ댓글쓰기
																</button>
															</div>
														</c:if>
													</div>
													
													<div class="w3-col m10 w3-padding-bottom">
														<div style="float:left; margin-right:10px; color:#7d7d7d; font-size:10pt;">
															<c:if test="${reply.like}">
																<span class="like glyphicon glyphicon-thumbs-up" id="like${reply.replyId}" style="color:#a9d039;"></span>
																<span id="likeCount${reply.replyId}">${reply.replyLikes}</span>
															</c:if>
															<c:if test="${!reply.like}">
																<span class="like glyphicon glyphicon-thumbs-up" id="like${reply.replyId}" style="color:#7d7d7d;"></span>
																<span id="likeCount${reply.replyId}">${reply.replyLikes}</span>
															</c:if>
														</div>
														<div style="color:#7d7d7d; font-size:10pt;">
															<c:if test="${reply.dislike}" >
																<span class="dislike glyphicon glyphicon-thumbs-down" id="dislike${reply.replyId}" style="color:#a9d039;"></span>
																<span id="dislikeCount${reply.replyId}">${reply.replyDislikes}</span>
															</c:if>
															<c:if test="${!reply.dislike}">
																<span class="dislike glyphicon glyphicon-thumbs-down" id="dislike${reply.replyId}" style="color:#7d7d7d;"></span>
																<span id="dislikeCount${reply.replyId}">${reply.replyDislikes}</span>
															</c:if>
														</div>
													</div>
													
													
													<div class="w3-col m1" align="right" style="color:#7d7d7d; font-size:10pt;">
														<span class="doClaim glyphicon glyphicon-send" style="color:#7d7d7d;"></span>  신고하기
													</div>
													<div class="w3-col m1" align="right" style="color:#7d7d7d; font-size:10pt;">
														<c:set var="loginId2" value="${ sessionScope._MEMBER_.email}"/>
														<c:if test="${ loginId2 eq reply.email }">
															<span class="glyphicon glyphicon-trash deleteRe" id="delete${reply.replyId}" style="color:#7d7d7d;"></span>  삭제하기
														</c:if>
													</div>
													<div class="claim w3-col m12">
														<form class="claimWrite">
															<input class="w3-input w3-col m11 " type="text" class="claimCom" id="claimCom${reply.replyId}" name="claimCom${reply.replyId}"
																			placeholder="신고 사유를 입력하세요." style="margin-bottom: 5px; float:left;">
															<span class="doClaimBtn w3-col m1 btn" id="claim${reply.replyId}" style="float:left; cursor:pointer; font-size:10pt;">신고하기</span>
														</form>
														<div style="clear: both;"></div>
													</div>
												
												</div>
											</div>
										</div>
									</div>     
								</td>
	
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
	  
	  
	
 
    	</div> 
	</div>
	

</div>
