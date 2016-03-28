<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>


<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#imagePreview").hide();

						$("#like")
								.click(
										function() {

											$.post(
															"/like",
															{
																"articleId" : $("#articleId").val()
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
																	if (jsonData.doLike) {
																		$(
																				"#like")
																				.attr(
																						"src",
																						"<c:url value="/resource/img/like_active_small.png"/>");
																	} else {
																		$(
																				"#like")
																				.attr(
																						"src",
																						"<c:url value="/resource/img/like_inactive_small.png"/>");
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
			<c:forEach items="${myWriteArticles}" var="article">
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
								<c:if test="${article.like }">
									<input type="hidden" id="articleId" name="articleId"
											value="${article.articleId}" />
									<input type="hidden" id="boardId" name="boardId"
											value="${article.boardId}" />
									<img id="like"
										src="<c:url value="/resource/img/like_active_small.png"/>" />
								</c:if>
								<c:if test="${!article.like }">
									<input type="hidden" id="articleId" name="articleId"
											value="${article.articleId}" />
									<input type="hidden" id="boardId" name="boardId"
											value="${article.boardId}" />
									<img id="like"
										src="<c:url value="/resource/img/like_inactive_small.png"/>" />
								</c:if>
								<div>
									좋아요 <span id="likeCount">${article.articleLikes}</span>
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