<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>


<script type="text/javascript">

	$(document)
			.ready(
					function() {

						$("#imagePreview").hide();

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
											console.log(jsonData.isScrab);
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
						
						$("#writeBtn").click(function() {
							var form = $("#writeArticle");
							form.attr("method", "post");
							form.attr("action", "/writeArticle");
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

<div class="w3-container w3-main"
	style="margin-top: 30px; margin-bottom: 20px;">

	<div>
		<div class="w3-col m7 w3-main"
			style="margin-left: 350px; margin-right: 100px; height: 705px; overflow: auto;">
			<div style="float:left;">
					<h2>학과 게시판입니다. </h2> 
				</div> 
				<div style="float:left; padding:20px;">
					<h6>참여인원 명</h6> 
				</div>
			
			<div class="w3-row-padding">
				<div class="w3-col m12" align="left" >
					<div class="w3-card-2 w3-round w3-white">

						<form id="writeArticle" enctype="multipart/form-data">
							<div class="w3-container w3-padding w3-left-align">
								<div class="form-group1">
									<input type="hidden" id="boardId" name="boardId" value="1" />
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
										<div class="col-sm-9">
												<input class="w3-input" type="text" id="articleTitle" name="articleTitle"
													placeholder="제목을 입력하세요." style="margin-bottom: 5px;">
										</div>
											<div class="col-sm-2">					
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

									<div class="form-group" style="margin-top: 30px;">
											<textarea class="w3-col m12" rows="5" id="articleDescription" name="articleDescription" style="margin-bottom: 5px; overflow: visible;"
												placeholder="무슨 생각을 하고 계신가요?"></textarea>
										</div>

										<div class="col-sm-9" style="margin-top: 20px;">
											<button type="button" class="btn btn-default" id="writeBtn" style="border-color: #FF3300; color: #FF3300;">게시</button>
											
										</div>
										
										<div id="imagePreview"><img id="uploadImg" src="#" width="100px;"></div>
									</div>
								</div>
						</form>

					</div>
				</div>
			</div>

			<c:forEach items="${majorArticles}" var="article">
				<div class="w3-row-padding w3-margin-top">
					<div class="w3-col m12">
						<div class="w3-card-2 w3-white w3-round-large">
							<div class="w3-container">
								<a
									href="<c:url value="/hitsCount?boardId=1&articleId=${article.articleId}"/>">
									<div>
										<input type="hidden" id="${article.articleId}" value="${article.articleId}"/>
										<div>
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


										<p>${article.articleDesc}</p>
										<p>${article.articleModifyDate}${article.nickName}</p>
									</div>
								</a>
								<p>조회수 ${article.hits}</p>
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
