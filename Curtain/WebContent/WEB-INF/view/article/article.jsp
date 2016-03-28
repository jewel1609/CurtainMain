<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>


<script type="text/javascript">

	$(document)
			.ready(
					function() {

						$("#imagePreview").hide();

						$(".like")
								.click(
										function() {
										alert( $(this).attr("id"));

											$.post(
															"/like",
															{
																"articleId" : $(this).attr("id")
																,"boardId" : $("#boardId").val()
															},
															function(data) {

																var jsonData = {};

																try {
																	jsonData = JSON
																			.parse(data);
																} catch (e) {
																	jsonData.result = false;
																}

																if (jsonData.result) {
																	var articleId = jsonData.articleId;
																	var result = "#"+articleId;
																	if (jsonData.doLike) {
																		$(result)
																				.attr(
																						"src",
																						"<c:url value="/resource/img/like_active_small.png"/>");
																						var count = "#likeCountlike"+jsonData.articleId;
																						$(count).text(jsonData.updateLikeCount);
																	} else {
																		$(result)
																				.attr(
																						"src",
																						"<c:url value="/resource/img/like_inactive_small.png"/>");
																						var count = "#likeCountlike"+jsonData.articleId;
																						$(count).text(jsonData.updateLikeCount);
																	}
																	
																} else {
																	alert("세션이 만료되었습니다. 다시 로그인해주세요.")
																	location.href = "/";
																}
															});
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

	<div class="w3-row">
		<div class="w3-col m7 w3-main"
			style="margin-left: 350px; margin-right: 100px; height: 705px; overflow: auto;">
			<div class="w3-row-padding">
				<div class="w3-col m12">
					<div class="w3-card-2 w3-round w3-white">

						<form id="writeArticle">
							<div class="w3-container w3-padding w3-left-align">
								<div class="form-group1">
									<input type="hidden" id="boardId" name="boardId" value="1" />
									<div>
										<div class="container col-xs-2 input-lg"
											style="padding-bottom: 10px; padding-left: 0px;">
											<select class="form-control " id="sel1" name="articleTypeId">
												<option value="1">고민</option>
												<option value="2">취업</option>
												<option value="3">연애</option>
												<option value="4">질문</option>
												<option value="5">기타</option>
											</select>
										</div>
										<div style="padding-top: 4px;">
											<input type="text" class="w3-col m8 input-lg"
												id="articleTitle" name="articleTitle" placeholder="제목" />
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

									<div style="padding-top: 4px;">
										<textarea name="articleDescription" class="w3-col m12"
											rows="5" id="comment" style="margin-bottom: 5px;"
											placeholder="무슨 생각을 하고 계신가요?"></textarea>
									</div>

									<div class="w3-col m12">
										<img id="uploadImg" src="/Curtain/resource/img/noimg.png"
											width="100" height="100">
									</div>
								</div>
								<div class="btn-group pull-right">
									<div class="btn-group">
										<button type="button" class="btn btn-primary dropdown-toggle"
											data-toggle="dropdown"
											style="background-color: #FF3300; border: 0px;">
											파일첨부 <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#">사진</a></li>
											<li><a href="#">동영상</a></li>
										</ul>
									</div>
									<button type="button" id="writeBtn" class="btn btn-primary"
										style="background-color: #FF3300; border: 0px;">게시</button>
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
								<c:if test="${article.like}">
									<input type="hidden" id="boardId" 
											value="${article.boardId}" />
									<img id="${article.articleId}"
										class="like" src="<c:url value="/resource/img/like_active_small.png"/>" />
								</c:if>
								<c:if test="${!article.like }">
									<input type="hidden" id="boardId" 
											value="${article.boardId}" />
								<img class="like" id="${article.articleId}"
										src="<c:url value="/resource/img/like_inactive_small.png"/>"  />
								</c:if>
								좋아요 <span id="likeCountlike${article.articleId}">${article.articleLikes}</span>

								<c:if test="${isExistsDislikeData}">
									<img class="dislike" id="d${article.articleId}" src="/resource/img/dislike_active_small.png" style="width:20px;">
								</c:if>
								<c:if test="${!isExistsDislikeData}">
									<img class="dislike" id="d${article.articleId}" src="/resource/img/dislike_inactive_small.png" style="width:20px;">
								</c:if>
						<%-- 	싫어요 <span id="disLikeCountlike${article.articleId}">${article.articleDislikes}</span>--%>
							</div>
						</div>
					</div>

				</div>
			</c:forEach>

		</div>
	</div>

</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>