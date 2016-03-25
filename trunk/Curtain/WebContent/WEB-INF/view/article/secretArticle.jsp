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
						var result = "#" + articleId;
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


	<div class="w3-container w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">

		<div class="w3-row">
			<div class="w3-col m7 w3-main"
				style="margin-left: 350px; margin-right: 100px; height: 750px; overflow: auto;">
				<div style="float:left;"><h2>한겹커튼게시판입니다.</h2></div> 
				<div style="margin-top:25px; position:relative; left: 20px ">참여인원 명</div>
				<div class="w3-row-padding">
					<div class="w3-col m12" align="left">
						<div class="w3-card-2 w3-round w3-white">

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
										<div id="imagePreview"><img id="uploadImg" src="#" width="300px;"></div>
									</div>
								</div>
							</form>
						</div>


					</div>

				</div>

				<c:forEach items="${secretArticles}" var="article">
					<div class="w3-row-padding w3-margin-top">
						<div class="w3-col m12">
							<div class="w3-card-2 w3-white w3-round-large">
								<div class="w3-container">
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
										
									</div>
									<div>
										<input type="hidden" id="articleId" name="articleId" value="${article.articleId}" />
										<input type="hidden" id="boardId" name="boardId" value="${article.boardId}" />
										<strong>${article.articleId}</strong>
										<strong>${article.boardId}</strong>
										
									</div>
									<div>
										<strong>${article.articleTitle}</strong>
									</div>
									
									<div>
										${article.articleDesc}
									</div>
									
									<div>
										${article.articleModifyDate} | ${article.nickName}
									</div>
									<div style="width: 780px; float:left; padding:5px;">
										<c:if test="${article.like}">
											
											<img id="" src="/resource/img/like_active_small.png" style="width:20px;">
											좋아요 갯수 
										</c:if>
										<c:if test="${!article.like}">
											<img id="likeBtn" src="/resource/img/like_inactive_small.png" style="width:20px;">
											좋아요 갯수
										</c:if>
										
										<c:if test="${article.dislike}">
											<img class="dislike" id="${article.articleId}" src="/resource/img/dislike_active_small.png" style="width:20px;">
											싫어요 갯수
											<span id="dislikeCount${article.articleId}">${article.articleDislikes}</span>
										</c:if>
										<c:if test="${!article.dislike}">
											<img class="dislike" id="${article.articleId}" src="/resource/img/dislike_inactive_small.png" style="width:20px;">
											싫어요 갯수
											<span id="dislikeCount${article.articleId}">${article.articleDislikes}</span>
										</c:if>
									</div>
									<div style="float:left;">
									<img src="/resource/img/reply_small.png" style="width:20px;">
									댓글 수<img src="/resource/img/scrap_inactive_small.png" style="width:20px;">
									스크랩하기
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