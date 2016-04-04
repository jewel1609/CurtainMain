<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

<script type="text/javascript">

$(document).ready(function () {

	if($("#isFword").val() == '1'){
		alert("비방글은 게시하실 수 없습니다.");
	}
	
	$("#imagePreview").hide();
	
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
	
	$(".glyphicon-trash").click(function() {		

		$.post(		
			"/deleteFile"
			, { "fileId" : $(this).attr("id") }
			, function(data) {
				
				var jsonData = {};		
				
				try {
					jsonData = JSON.parse(data);
				}
				catch(e) {
					jsonData.result = false;
				}
				
				if(jsonData.result){
					var result = "#" + jsonData.fileId;
					$(result).hide();
				}
				else{
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href="/";
				}
			}
		)	
	});
	

	
	$(updateArticleBtn).click(function () {
		
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
		var form = $(updateArticle);
		form.attr("method", "post");
		form.attr("action", "/doUpdateArticle");
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
<input type="hidden" id="isFword" value="<%= request.getParameter("isFword") %>" /> 
<div class="w3-container w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left: 350px; margin-right: 100px; height: 705px; overflow: auto;">
     
     <div class="w3-row-padding">
				<div class="w3-col m12">
					<div class="w3-card-2 w3-round w3-white">

						<form id="updateArticle" enctype="multipart/form-data">
							<div class="w3-container w3-padding w3-left-align">
								<div class="form-group1">
								<div>
									<input type="hidden" id="boardId" name="boardId" value="${article.boardId}" />
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
											 <img id="${article.articleId}"
											class="like" src="<c:url value="/resource/img/like_active_small.png"/>" />${article.articleLikes}
										</div>
										<div style="padding-top: 4px;">
											<input type="hidden" id="articleId" name="articleId" value="${article.articleId}"/>
											<input type="text" class="w3-col m10 input-lg"
												id="articleTitle" name="articleTitle"  value="${article.articleTitle}"/>
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
										</div>
										
										<div style="padding-top: 4px;">
										<textarea name="articleDescription" class="w3-col m12"
											rows="5" id="comment" style="margin-bottom: 5px;">${article.articleDesc}</textarea>
									</div>
									<div class="col-sm-9"  id="imagePreview"><img id="uploadImg" src="" width="100px;"></div>
									<div class="w3-col m12">
										<c:forEach items="${files}" var="file">
											<span id="${file.fileId}" class="glyphicon glyphicon-trash col-sm-9">${file.fileName}</span>
										</c:forEach>
									</div>
								</div>
							</div>
									
			            	<ul class="pager">
							 	 <li class="previous col-sm-2"><a href="<c:url value="/showDetail?articleId=${article.articleId}&boardId=${article.boardId}"/>">뒤로가기</a></li>
								<li id="updateArticleBtn" class="col-sm-2">수정완료</li>
							</ul>  
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
	     

	
 
    	</div> 
	</div>
	

</div>
