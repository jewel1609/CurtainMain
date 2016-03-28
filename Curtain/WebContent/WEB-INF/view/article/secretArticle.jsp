<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<link rel="stylesheet" type="text/css"
	href="/resource/css/article/secretArticle.css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/jquery-1.12.1.js" />"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
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

		$("#doWrite").click(function() {

			var form = $("#writeForm");
			form.attr("method", "post");
			form.attr("action", "/secretWriteArticle");
			form.submit();

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


	<div class="w3-container w3-main" style="margin-bottom: 20px;">

		<div class="w3-row">
			<div class="w3-col m7 w3-main" style="margin-left: 350px; border:1px solid; margin-right: 100px; height: 820px; overflow: auto; ">
				<div style="float:left;">
					<!-- 아이콘 -->
					<h2>한겹커튼게시판입니다. </h2> 
				</div> 
				<div style="float:left; padding:20px;">
					<h6>참여인원 명</h6> 
				</div>
				<div class="w3-row-padding" style="border:1px solid;">
					<div class="w3-col m12" align="left">
						<div class="w3-card w3-round w3-white">

							<form id="writeForm" enctype="multipart/form-data">
								<div class="w3-container w3-padding w3-left-align">
									<div class="form-group1">

										<div>
											<div class="col-sm-1">
												<select name="articleTypeId">
													<option value="1">고민</option>
													<option value="2">취업</option>
													<option value="3">연애</option>
													<option value="4">질문</option>
													<option value="5">기타</option>
												</select>
											</div>
											<div class="col-sm-9"
												style="background-color: lavenderblush;">
												<input class="w3-input" type="text" name="articleTitle"
													placeholder="제목을 입력하세요." style="margin-bottom: 5px;">

											</div>
											<div class="col-sm-2">

												<input type="file" name="imgFile" style="display: none;"
													onchange="readURL(this);">
												<button type="button" class="btn btn-default btn-sm"
													onclick="document.all.imgFile.click();">
													<span class="glyphicon glyphicon-picture"></span>
												</button>


												<input type="file" name="movieFile" style="display: none;">
												<button type="button" class="btn btn-default btn-sm"
													onclick="document.all.movieFile.click();">
													<span class="glyphicon glyphicon-facetime-video"></span>
												</button>
											</div>


										</div>
										<div class="form-group" style="margin-top: 30px;">

											<textarea class="w3-col m12" rows="5" id="comment"
												name="articleDescription"
												style="margin-bottom: 5px; overflow: visible;"
												
												placeholder="무슨 생각을 하고 계신가요?"></textarea>
										</div>

										<input type="hidden" name="boardId" value="4">
										<div style="margin-top: 20px;">
											
										</div>
										<button type="button" class="btn btn-default" id="doWrite"
											style="border-color: #FF3300; color: #FF3300;">게시</button>
										
										<div id="imagePreview"><img id="uploadImg" src="#" width="100px;"></div>
									</div>
								</div>
							</form>
						</div>


					</div>

				</div>
				
				
				<!-- 가장 조회수 많은 게시글 시작-->
				<div class="w3-row-padding w3-margin-top">
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
									조회수 ${topArticle.hits}
									<input type="hidden" id="articleId" name="articleId" value="${topArticle.articleId}" />
									<input type="hidden" id="boardId" name="boardId" value="${topArticle.boardId}" />
								</div>
								<div class="w3-col m12 w3-padding-top" style="height: 60px;">
									${topArticle.articleDesc}
								</div>
								
								<div>
									${topArticle.articleModifyDate} 
								</div>
								<div class="w3-col m10 w3-padding-bottom">
									<div style="float:left; margin-right:10px;">
										<c:if test="${topArticle.like}">
											<img class="like" id="like${topArticle.articleId}" src="/resource/img/like_active_small.png" style="width:20px;">	
											<span id="likeCount${topArticle.articleId}">${topArticle.articleLikes}</span>
										</c:if>
										<c:if test="${!topArticle.like}">
											<img class="like" id="like${topArticle.articleId}" src="/resource/img/like_inactive_small.png" style="width:20px;">
											<span id="likeCount${topArticle.articleId}">${topArticle.articleLikes}</span>
										</c:if>
									</div>
									<div>
										<c:if test="${article.dislike}">
											<img class="dislike" id="dislike${topArticle.articleId}" src="/resource/img/dislike_active_small.png" style="width:20px;">
											<span id="dislikeCount${topArticle.articleId}">${topArticle.articleDislikes}</span>
										</c:if>
										<c:if test="${!article.dislike}">
											<img class="dislike" id="dislike${topArticle.articleId}" src="/resource/img/dislike_inactive_small.png" style="width:20px;">
											<span id="dislikeCount${topArticle.articleId}">${topArticle.articleDislikes}</span>
										</c:if>
									</div>
								</div>
								<div class="w3-col m2">
									<img src="/resource/img/reply_small.png" style="width:20px;">댓글 수 
								
									<c:if test="${topArticle.scrab}">
										<img class="scrab" id="scrab${topArticle.articleId}" src="/resource/img/scrap_active_small.png" style="width:20px;">스크랩하기
									</c:if>
									
									<c:if test="${!topArticle.scrab}">
										<img class="scrab" id="scrab${topArticle.articleId}" src="/resource/img/scrap_inactive_small.png" style="width:20px;">스크랩하기
									</c:if>
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
									<div class="w3-col m12 w3-padding-top" style="height: 60px;">
										${article.articleDesc}
									</div>
									
									<div>
										${article.articleModifyDate} 
									</div>
									
									<div class="w3-col m10 w3-padding-bottom">
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
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>