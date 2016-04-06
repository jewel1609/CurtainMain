<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resource/css/article/secretArticle.css" />

<script type="text/javascript">


	$(document).ready(function() {
		
		$("#cancelBtn1").click(function() {
			location.href = "/myPage";
		});


		$('[data-toggle="popover"]').popover();

		if ($("#isFword").val() == '1') {
			alert("비방글은 게시하실 수 없습니다.");
		}

		$("#imagePreview").hide();
		$(".claim").hide();

		$(".doClaim").click(function() {
			var root = $(this).parent().children(":eq(3)");
			root.slideToggle();
		});

		$("#movieBtn").popover({
			title : "<h5>동영상 url을 입력하세요</h5>",
			content : $("#movieUrlForm").html(),
			html : true,
			placement : "bottom",
			trigger : "click"
		}).on('click', function() {
			$('#movieUrlUploadBtn').click(function() {
				$("#movieUrl").attr("value", $("#url").val());
				alert("첨부되었습니다.");

			});
		});

		$(".doClaimBtn").click(function() {

			var root = $(this).parent().children(":eq(0)");

			$.post("/writeClaim", {
				"claimText" : root.val(),
				"articleClaim" : $(this).attr("id")
			}, function(data) {

				var jsonData = {};

				try {
					jsonData = JSON.parse(data);
				} catch (e) {
					jsonData.result = false;
				}

				if (jsonData.result) {
					$(".claim").hide();
					alert("신고가 완료되었습니다.");
				} else {
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href = "/";
				}
			})
		});

		$(".like").click(function() {

			$.post("/like", {
				"articleId" : $(this).attr("id"),
				"boardId" : $("#boardId").val()
			}, function(data) {

				var jsonData = {};

				try {
					jsonData = JSON.parse(data);
				} catch (e) {
					jsonData.result = false;
				}

				if (jsonData.result) {
					var articleId = jsonData.articleId;
					var result = "#like" + articleId;
					if (jsonData.doLike) {
						$(result).attr("style", "color:#a9d039");
						var count = "#likeCount" + jsonData.articleId;
						$(count).text(jsonData.updateLikeCount);
					} else {
						$(result).attr("style", "color:#7d7d7d");
						var count = "#likeCount" + jsonData.articleId;
						$(count).text(jsonData.updateLikeCount);
					}
				} else {
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href = "/";
				}
			})

		});

		$(".dislike").click(function() {

			$.post("/dislike", {
				"articleId" : $(this).attr("id"),
				"boardId" : $("#boardId").val()
			}, function(data) {

				var jsonData = {};

				try {
					jsonData = JSON.parse(data);
				} catch (e) {
					jsonData.result = false;
				}

				if (jsonData.result) {
					var articleId = jsonData.articleId;
					var result = "#dislike" + articleId;
					if (jsonData.isDislike) {
						$(result).attr("style", "color:#a9d039");
						var count = "#dislikeCount" + jsonData.articleId;
						$(count).text(jsonData.updateDislikeCount);
					} else {
						$(result).attr("style", "color:#7d7d7d");
						var count = "#dislikeCount" + jsonData.articleId;
						$(count).text(jsonData.updateDislikeCount);
					}
				} else {
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href = "/";
				}
			})

		});

		$(".scrab").click(function() {
			$.post("/scrab", {
				"articleId" : $(this).attr("id"),
				"boardId" : $("#boardId").val()
			}

			, function(data) {
				var jsonData = {};
				try {
					jsonData = JSON.parse(data);

				} catch (e) {
					jsonData.result = false;
				}
				if (jsonData.result) {
					var articleId = jsonData.articleId;
					var result = "#scrab" + articleId;
					if (jsonData.isScrab) {
						console.log(jsonData.isScrab);
						alert("스크랩되었습니다.");
						$(result).attr("style", "color:#a9d039");
					} else {
						alert("스크랩 해제 되었습니다.");
						$(result).attr("style", "color:#7d7d7d");
					}
				} else {
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href = "/";
				}
			})

		});

		$("#writeBtn").click(function() {

			if ($("#articleTitle").val() == "") {
				alert("제목을 입력하세요!");
				$("#articleTitle").focus();
				return; // 더이상 밑의 이벤트를 진행하지 않음.
			}

			if ($("#articleDescription").val() == "") {
				alert("내용을 입력하세요!");
				$("#articleDescription").focus();
				return; // 더이상 밑의 이벤트를 진행하지 않음.
			}

			var form = $("#writeArticle");
			form.attr("method", "post");
			form.attr("action", "/writeArticle");
			form.submit();

		});

		var searchFlag = 0;
		$(".searchEvent").hide();

		$(".btnSearch").click(function() {

			if (searchFlag == 0) {
				$(".searchEvent").show();
				$(".searchKeyword").animate({
					width : "120px"
				});
				searchFlag = 1;
			} else {
				$(".searchEvent").hide();
				$(".searchKeyword").animate({
					width : "0px"
				});
				$(".searchKeyword").val("");
				searchFlag = 0;
			}

		});

		$("#btnSearch").click(function() {
			var form = $(".searchForm");
			form.attr("method", "post");
			form.attr("action", "/studentMajorAritlce");
			form.submit();
		});

		$(".btnSearch").mouseover(function() {
			$(".btnSearch").css("cursor", "pointer");
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

<div class="w3-container w3-main">

	<div class="w3-row">
 <div class="w3-col m7 w3-main wrapper"
         style="border-right:1px solid #bababa; margin-left: 334px; margin-right: 100px; height: 905px; overflow: auto; background-color:#F3F3F3; ">
			
			<div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa; backgroud-color:white;">
		      	 <div class="w3-margin-4" style="float:left; padding-left:10px;">
		      		<h2>내가 좋아요 한 글</h2>
		      	</div>
		      	<div style="margin-left: 950px; position: relative; top:30px;">
		      	<button type="button" id="cancelBtn1" class="btn btn-default">
					뒤로가기
				</button>
				</div>
	      	</div>

			<c:forEach items="${myLikesArticle}" var="article">
         <input type="hidden" class="majorName" value="${article.majorName}"/>
            <div class="w3-row-padding w3-margin-top" >
               <div class="w3-col m12">
               
                  <div class="w3-card w3-white w3-round-large">
                     <div class="w3-container">
                        <a href="<c:url value="/hitsCount?boardId=1&articleId=${article.articleId}"/>">
                              
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
                              
                              
                             <div class="w3-col m2" align="right">
                                 <h6>조회수 ${article.hits}</h6>
                              </div>
                              
                              <div class="desc w3-col m12 w3-padding-top" style="height: 70px;">
                                 <p>${article.articleDesc}</p>
                              </div>
                        </a>
                              <div class="w3-col-m12" align="left">
                               		<h6>${article.nickName} &nbsp;&nbsp;&nbsp;&nbsp;${article.articleModifyDate}</h6>
                              </div>
                           <div class="w3-col m6 w3-padding-bottom" style="color:#7d7d7d;">
                              <div style="float:left; margin-right:10px;">
                                 <c:if test="${article.like}">
                                    <span class="like glyphicon glyphicon-thumbs-up" id="like${article.articleId}" style="color:#a9d039;"></span>
                                    <span id="likeCount${article.articleId}">${article.articleLikes}</span>
                                 </c:if>
                                 <c:if test="${!article.like}">
                                    <span class="like glyphicon glyphicon-thumbs-up" id="like${article.articleId}" style="color:#7d7d7d;"></span>
                                    <!-- 
                                    <img class="like" id="like${article.articleId}" src="/resource/img/like_inactive_small.png" style="width:20px;">
                                     -->
                                    <span id="likeCount${article.articleId}">${article.articleLikes}</span>
                                 </c:if>
                              </div>
                              <div>
                                 <c:if test="${article.dislike}">
                                    <span class="dislike glyphicon glyphicon-thumbs-down" id="dislike${article.articleId}" style="color:#a9d039;"></span>
                                    <span id="dislikeCount${article.articleId}">${article.articleDislikes}</span>
                                 </c:if>
                                 <c:if test="${!article.dislike}">
                                    <span class="dislike glyphicon glyphicon-thumbs-down" id="dislike${article.articleId}" style="color:#7d7d7d;"></span>
                                    <span id="dislikeCount${article.articleId}">${article.articleDislikes}</span>
                                 </c:if>
                              </div>
                           </div>
                           <div class="w3-col m6" align="right" style="color:#7d7d7d;">
                           <a href="<c:url value="/hitsCount?boardId=1&articleId=${article.articleId}"/>"><span class="glyphicon glyphicon-edit" style="color:#7d7d7d;"></span></a>  ${article.replyCount} &nbsp; &nbsp;
                        
                           <c:if test="${article.scrab}">
                              <span class="scrab glyphicon glyphicon glyphicon-tag" id="scrab${article.articleId}" style="color:#a9d039;"></span>  스크랩하기&nbsp; &nbsp;
                           </c:if>
                           
                           <c:if test="${!article.scrab}">
                              <span class="scrab glyphicon glyphicon glyphicon-tag" id="scrab${article.articleId}" style="color:#7d7d7d;"></span>  스크랩하기&nbsp; &nbsp;
                           </c:if>
                           
                           <span class="doClaim glyphicon glyphicon-send" style="color:#7d7d7d;"></span>  신고하기
                        
                           <div class="claim w3-col m12">
                              <form class="claimWrite">
                                 <input class="w3-input" type="text" class="claimCom" id="claimCom${article.articleId}" name="claimCom${article.articleId}"
                                             placeholder="신고 사유를 입력하세요." style="margin-bottom:5px;">
                                 <span class="doClaimBtn" id="claim${article.articleId}" style="cursor:pointer;">신고하기</span>
                              </form>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </c:forEach>

		</div>
	</div>

</div>
