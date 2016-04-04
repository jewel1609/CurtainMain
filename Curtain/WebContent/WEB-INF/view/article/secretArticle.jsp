<%@page import="com.ktds.curtain.util.MultipartHttpServletRequest"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<link rel="stylesheet" type="text/css" href="/resource/css/article/secretArticle.css" />
<script type="text/javascript" src="<c:url value="/resource/js/jquery-1.12.1.js" />"></script>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

<script type="text/javascript">

	$(document).ready(function() {
		
		$(".wrapper").css("height", window.innerHeight - 130);
		
		$(window).resize(function() {
			$(".wrapper").css("height", window.innerHeight - 130);
		});
	      
		
		$("#freeArticle").mouseleave(function(){
			$("#freeArticle").css('background-color', '#a9d039');
			$("#freeArticle").css('color', '#ffffff');
		});
		$("#freeArticle").css('background-color', '#a9d039');
		$("#freeArticle").css('color', '#ffffff');
		
		if($("#isFword").val() == '1'){
			alert("비방글은 게시하실 수 없습니다.");
		}
		
		$("#movieBtn").popover({
					title: "<h5>동영상 url을 입력하세요</h5>"
					, content: $("#movieUrlForm").html()
					, html: true
					, placement: "bottom"	
					, trigger: "click"
		}).on('click', function(){
			$('#movieUrlUploadBtn').click(function(){
				$("#movieUrl").attr("value",$("#url").val());
				alert("첨부되었습니다.");
				
			});			
		});
		
		
		$(".doClaimBtn").click(function() {		
			
			var root = $(this).parent().children(":eq(0)");

			$.post(		
				"/writeClaim"
				, { "claimText" : root.val()
					, "articleClaim" : $(this).attr("id")
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
				"/like"
				, { "articleId" : $(this).attr("id")
					, "boardId" : $("#boardId").val()
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
						var articleId = jsonData.articleId;
						var result = "#like" + articleId;
						if(jsonData.doLike){
							$(result).attr("src", "/resource/img/like_active_small.png");
							var count = "#likeCount"+jsonData.articleId;
							$(count).text(jsonData.updateLikeCount);
						}
						else{
							$(result).attr("src", "/resource/img/like_inactive_small.png");
							var count = "#likeCount"+jsonData.articleId;
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
				"/dislike"
				, { "articleId" : $(this).attr("id")
					, "boardId" : $("#boardId").val()
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
						var articleId = jsonData.articleId;
						var result = "#dislike" + articleId;
						if(jsonData.isDislike){
							$(result).attr("src", "/resource/img/dislike_active_small.png");
							var count = "#dislikeCount"+jsonData.articleId;
							$(count).text(jsonData.updateDislikeCount);
						}
						else{
							$(result).attr("src", "/resource/img/dislike_inactive_small.png");
							var count = "#dislikeCount"+jsonData.articleId;
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
		
		$(".scrab").click(function(){
			$.post(
				"/scrab"
				, { "articleId" : $(this).attr("id")
					, "boardId" : $("#boardId").val()	
				}
				, function(data){
					var jsonData = {};
					try{
						jsonData = JSON.parse(data);
						
					}
					catch(e){
						jsonData.result = false;
					}
					if(jsonData.result){
						var articleId = jsonData.articleId;
						var result = "#scrab" + articleId;
						if(jsonData.isScrab){
							alert("스크랩되었습니다.");
							$(result).attr("src", "/resource/img/scrap_active_small.png");
						}
						else{
							alert("스크랩 해제 되었습니다.");
							$(result).attr("src", "/resource/img/scrap_inactive_small.png");
						}
					}
					else{
						alert("세션이 만료되었습니다. 다시 로그인해주세요.");
						location.href="/";
					}
				}
			)
		});
		
		$("#imagePreview").hide();
		$(".claim").hide();
		
		$(".doClaim").click(function () {
			var root = $(this).parent().parent().children(":eq(5)");
			root.slideToggle();
		});

		$("#doWrite").click(function() {
		
			
			 if( $("#articleTitle").val() == ""){
				alert("제목을 입력하세요!");
				$("#articleTitle").focus();
				return; // 더이상 밑의 이벤트를 진행하지 않음.
			} 
			 
			 if( $("#articleDescription").val() == ""){
				alert("내용을 입력하세요!");
				$("#articleDescription").focus();
				return; // 더이상 밑의 이벤트를 진행하지 않음.
			} 

			var form = $("#writeForm");
			form.attr("method", "post");
			form.attr("action", "/secretWriteArticle");
			form.submit();

		});
		
		
		var searchFlag = 0;
		$(".searchEvent").hide();
		
		$(".btnSearch").click(function () {
			
			if (searchFlag == 0) {
				$(".searchEvent").show();
				$(".searchKeyword").animate({ width : "120px"});
				searchFlag = 1;
			}
			else {
				$(".searchEvent").hide();
				$(".searchKeyword").animate({ width : "0px"});
				$(".searchKeyword").val("");
				searchFlag = 0;
			}
			
		});
		
		$("#btnSearch").click(function () {
			var form = $(".searchForm");
			form.attr("method", "post");
			form.attr("action", "/secretArticleList");
			form.submit();
		});
		
		$(".btnSearch").mouseover( function () {
			$(".btnSearch").css("cursor","pointer");
		});
		

	});

	function readURL(input) {

		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#imagePreview").show();
				$('#uploadImg').attr("src", e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

<input type="hidden" id="isFword" value="<%= request.getParameter("isFword") %>" />

	<div class="w3-container w3-main" style="margin-top: 30px; margin-bottom: 20px;">
	
		<c:set var="freeBoardMemberCount" value="${ sessionScope._MEMBER_.freeBoardMemberCount }" />
		
		
		<div class="w3-row" >
			<div class="w3-col m7 w3-main wrapper" style="border-right:1px solid #bababa;
    			background-color: #F3F3F3; margin-left: 334px; margin-right: 100px; height: 885px; overflow: auto; ">
				
				<!-- 게시판 헤더 -->
				
				<div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa;">
					<div class="w3-margin-4" style="float:left;">
						<h2>자유게시판입니다.</h2> 
					</div> 
					<div style="float:left; padding:20px;">
						<h5>참여인원 ${freeBoardMemberCount}명</h5> 
					</div>
					
					<div style="width: 400px; float: right; margin: 20px 20px 0 0; text-align: right;">
						<form class="searchForm">
							<div class="btnSearch material-icons w3-xlarge" style="float:right; padding-top: 5px;">search</div>
							<div style="float:right; padding-bottom: 5px;">
								<input id="btnSearch" class="searchEvent btn btn-default" style="float: right; border-color: #FF3300; color: #FF3300;" type="button" value="검색"/>
							</div>
							<div style="float:right; padding-bottom: 5px;">
								<input name="searchKeyword" class="searchEvent searchKeyword w3-input" type="text" style="width: 0px;"/>
							</div>
							<select class="searchEvent form-control" id="sel1" name="articleTypeId" style="float: right; margin-right: 5px; width: 80px;">
								<option value="1">제목</option>
								<option value="2">내용</option>
							</select>
							<div style="width: 100px; float: right; margin: 7px 10px 0 0;">
								<a href="/secretArticleList">검색초기화</a>
							</div>
						</form>
					</div>
					
				</div>
				<!-- 게시판 타이틀 끝 -->
				
				<!-- 글쓰기 시작 -->
				<div class="w3-row-padding" style="border-bottom:1px solid #BABABA;">
					<div class="w3-col m12" align="left">
						<div class="w3-card w3-round-large w3-white" style="margin-top:10px; margin-bottom:10px;">

							<form id="writeForm" enctype="multipart/form-data">
								<div class="w3-container w3-padding w3-left-align">
									<div class="form-group">
										<div style="padding:10px;">
											<div class="col-sm-2 input" style="padding-left:0px;">
												 <select class="form-control " id="sel1" name="articleTypeId">
													<option value="1">고민</option>
													<option value="2">취업</option>
													<option value="3">연애</option>
													<option value="4">질문</option>
													<option value="5">기타</option>
												</select>
											</div>
											<div class="col-sm-8">
												<input class="w3-input" type="text" id="articleTitle" name="articleTitle"
													placeholder="제목을 입력하세요."/>
											</div>
											<div class="col-sm-2" align="right" style="padding-right:0px;">					
												<input type="file" name="imgFile" style="display: none;" onchange="readURL(this);">
												<button type="button" class="btn btn-default btn-sm" onclick="document.all.imgFile.click();">
													<span class="glyphicon glyphicon-picture"></span>
												</button>
												<button type="button" id="movieBtn" class="btn btn-default btn-sm">
													<span class="glyphicon glyphicon-facetime-video"></span>
												</button>
												
												<div id="movieUrlForm" class="hide">
													<div>
														<label for="about">url</label>
															<textarea rows="3" name="url" id="url" class="form-control input-md"></textarea>
															<button type="button" id="movieUrlUploadBtn" class="btn btn-default">
																<em class="icon-ok">첨부</em>
															</button>
													</div>
												</div>
												<input type="hidden" id="movieUrl" name="movieUrl" />
												<input type="hidden" name="boardId" value="4">
											</div>
										</div>
										
										<div class="form-group" style="margin-top: 30px; padding:10px;">
											<textarea class="form-control w3-col m12" rows="7" id="articleDescription" name="articleDescription" style="margin-bottom: 5px; overflow: visible;"
												placeholder="무슨 생각을 하고 계신가요?"></textarea>
										</div>
										<div class="col-sm-12" id="imagePreview">
											<img id="uploadImg" src="" width="100px;">
										</div>
										<div class="col-sm-12" style="margin-top:10px;">
											<button type="button" class="btn btn-default" id="doWrite" style="float: right; border-color: #FF3300; color: #FF3300;">게시</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				
				<!-- 가장 조회수 많은 게시글 시작-->
				<div style="background-color: white;">
				<div class="w3-row-padding w3-margin-top" >
					<div class="w3-col m12">
						<div class="w3-card w3-round-large" style="border-color: #a9d039;" >
							<div class="w3-container">
							
								<div class="w3-col m10 w3-padding-top">
										<span class="label label-default">HOT</span>
										<c:if test="${topArticle.articleTypeName eq '연애'}">
											<span class="label label-danger">${topArticle.articleTypeName}</span>
										</c:if>
										<c:if test="${topArticle.articleTypeName eq '고민'}">
											<span class="label label-warning">${topArticle.articleTypeName}</span>
										</c:if>
										<c:if test="${topArticle.articleTypeName eq '질문'}">
											<span class="label label-primary">${topArticle.articleTypeName}</span>
										</c:if>
										<c:if test="${topArticle.articleTypeName eq '취업'}">
											<span class="label label-info">${topArticle.articleTypeName}</span>
										</c:if>
										<c:if test="${topArticle.articleTypeName eq '기타'}">
											<span class="label label-default">${topArticle.articleTypeName}</span>
										</c:if>
										<strong>${topArticle.articleTitle}</strong>
										
								</div>
								<div class="w3-col m1 w3-padding-top">
									${topArticle.nickName}
								</div>
								<div class="w3-col m1 w3-padding-top">
									<h6>조회수 ${topArticle.hits}</h6>
									<input type="hidden" id="articleId" name="articleId" value="${topArticle.articleId}" />
									<input type="hidden" id="boardId" name="boardId" value="${topArticle.boardId}" />
								</div>
								<a href="<c:url value="/hitsCount?boardId=4&articleId=${topArticle.articleId}"/>">
								<div class="w3-col m12 w3-padding-top desc" id="desc${topArticle.articleId}" style="height: 60px;">
									${topArticle.articleDesc}
								</div>
								</a>
								<div>
									${topArticle.articleModifyDate} 
								</div>
								<div class="w3-col m10 w3-padding-bottom">
									<div style="float:left; margin-right:10px;">
										<c:if test="${topArticle.like}">
											<span class="like" id="like${topArticle.articleId}" src="/resource/img/like_active_small.png" style="width:20px;">	
											<span id="likeCount${topArticle.articleId}">${topArticle.articleLikes}</span>
										</c:if>
										<c:if test="${!topArticle.like}">
											<img class="like" id="like${topArticle.articleId}" src="/resource/img/like_inactive_small.png" style="width:20px;">
											<span id="likeCount${topArticle.articleId}">${topArticle.articleLikes}</span>
										</c:if>
									</div>
									<div>
										<c:if test="${topArticle.dislike}">
											<img class="dislike" id="dislike${topArticle.articleId}" src="/resource/img/dislike_active_small.png" style="width:20px;">
											<span id="dislikeCount${topArticle.articleId}">${topArticle.articleDislikes}</span>
										</c:if>
										<c:if test="${!topArticle.dislike}">
											<img class="dislike" id="dislike${topArticle.articleId}" src="/resource/img/dislike_inactive_small.png" style="width:20px;">
											<span id="dislikeCount${topArticle.articleId}">${topArticle.articleDislikes}</span>
										</c:if>
									</div>
								</div>
								<div class="w3-col m2">
									<img src="/resource/img/reply_small.png" style="width:20px;">댓글 수 
								
									<c:if test="${topArticle.scrab}">
										<img class="scrab" id="scrab${topArticle.articleId}" src="/resource/img/scrap_active_small.png" style="width:20px;">
									</c:if>
									
									<c:if test="${!topArticle.scrab}">
										<img class="scrab" id="scrab${topArticle.articleId}" src="/resource/img/scrap_inactive_small.png" style="width:20px;">
									</c:if>
								</div>
								<div class="w3-col m1">
									<span class="doClaim glyphicon glyphicon-send"><h6>신고하기</h6></span>
								</div>
								<div class="claim w3-col m12">
									<form class="claimWrite">
										<input class="w3-input" type="text" class="claimCom" id="claimCom${topArticle.articleId}" name="claimCom${topArticle.articleId}"
														placeholder="신고 사유를 입력하세요." style="margin-bottom: 5px;">
										<span class="doClaimBtn" id="claim${topArticle.articleId}">신고하기</span>
									</form>
								</div>									
							</div>
						</div>
					</div>
				</div>	
				<!-- 조회수 많은 게시글 끝 -->
				
				
				
				
				<!-- 최신순 리스트 시작 -->
				
				<c:forEach items="${secretArticles}" var="article">
					<div class="w3-row-padding w3-margin-top">
						<div class="w3-col m12">
							
								<div class="w3-card w3-white w3-round-large">
									<div class="w3-container">
									<a href="<c:url value="/hitsCount?boardId=4&articleId=${article.articleId}"/>">
										<div class="w3-col m10 w3-padding-top">
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
											<strong>${article.articleTitle}</strong>
											
										</div>
										<div class="w3-col m1 w3-padding-top">
											${article.nickName}
										</div>
										<div class="w3-col m1 w3-padding-top">
											조회수 ${article.hits}
											<input type="hidden" id="articleId" name="articleId" value="${article.articleId}" />
											<input type="hidden" id="boardId" name="boardId" value="${article.boardId}" />
										</div>
										<div class="w3-col m12 w3-padding-top desc" style="height: 60px;">
											${article.articleDesc}
										</div>	
									</a>						
										<div>
											${article.articleModifyDate} 
										</div>
										<div class="w3-col m w3-padding-bottom">
											<div style="float:left; margin-right:10px;">
												<c:if test="${article.like}">
													<img class="like" id="like${article.articleId}" src="/resource/img/like_active_small.png" style="width:20px;">	
													<span id="likeCount${article.articleId}">${article.articleLikes}</span>
												</c:if>
												<c:if test="${!article.like}">
													<img class="like" id="like${article.articleId}" src="/resource/img/like_inactive_small.png" style="width:20px;">
													<span id="likeCount${article.articleId}">${article.articleLikes}</span>
												</c:if>
											</div>
											<div>
												<c:if test="${article.dislike}">
													<img class="dislike" id="dislike${article.articleId}" src="/resource/img/dislike_active_small.png" style="width:20px;">
													<span id="dislikeCount${article.articleId}">${article.articleDislikes}</span>
												</c:if>
												<c:if test="${!article.dislike}">
													<img class="dislike" id="dislike${article.articleId}" src="/resource/img/dislike_inactive_small.png" style="width:20px;">
													<span id="dislikeCount${article.articleId}">${article.articleDislikes}</span>
												</c:if>
											</div>
										</div>
										<div class="w3-col m2">
											<img src="/resource/img/reply_small.png" style="width:20px;">댓글 수 
										
											<c:if test="${article.scrab}">
												<img class="scrab" id="scrab${article.articleId}" src="/resource/img/scrap_active_small.png" style="width:20px;">
											</c:if>
											
											<c:if test="${!article.scrab}">
												<img class="scrab" id="scrab${article.articleId}" src="/resource/img/scrap_inactive_small.png" style="width:20px;">
											</c:if>
										</div>
										<div class="w3-col m1">
										<span class="doClaim glyphicon glyphicon-send" style="cursor:pointer;"><h6>신고하기</h6></span>
										</div>
										<div class="claim w3-col m12">
											<form class="claimWrite">
												<input class="w3-input" type="text" class="claimCom" id="claimCom${article.articleId}" name="claimCom${article.articleId}"
																placeholder="신고 사유를 입력하세요." style="margin-bottom: 5px;">
												<span class="doClaimBtn" id="claim${article.articleId}" style="cursor:pointer;">신고하기</span>
											</form>
										</div>	
									</div>
									
								</div>
							
						</div>
					</div>
				</c:forEach>
				
				</div>

			</div>
		</div>
	</div>