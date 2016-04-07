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
	
	$(".badge").click(function() {	
		
		var r = confirm("삭제하시겠습니까?");
	    if (r == true) {
	    
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
	    } else {
	       
	    }
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
	 else {
		 alert("수정되었습니다.");
		 var form = $(updateArticle);
			form.attr("method", "post");
			form.attr("action", "/doUpdateArticle");
			form.submit();
	 }
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



<div class="w3-container" style="margin-top:0px; ">    

  <div class="w3-row" style="border-bottom: 1px solid #bababa;">
    <div class="w3-col m7 wrapper w3-padding"
         style="border-right:1px solid #bababa; margin-left: 334px; margin-right: 100px; height: 905px; overflow: auto; ">
         
        <div class="w3-row-padding">
				<div class="w3-col m12">
					<div class="w3-card w3-white w3-round-large" style="margin-top:10px; margin-bottom:10px;">
						<div class="w3-container">
						
							<div class="w3-container">
							<form id="updateArticle" enctype="multipart/form-data">
								<input type="hidden" id="boardId" name="boardId" value="${article.boardId}" />
								
								<div>
									<div class="col-sm-12" style="margin-top:10px; padding-bottom: 10px; border-bottom:1px solid #bababa;">
										<div class="col-sm-1 input" style="padding-left:0px; padding-top:8px;" align="left">
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
										
										<div class="col-sm-9" align="left" style="padding-left:0px;">
										<input type="hidden" id="articleId" name="articleId" value="${article.articleId}"/>
											<input type="text" class="w3-input w3-col m10 input-lg"
												id="articleTitle" name="articleTitle"  value="${article.articleTitle}"/>
										</div>
										<div class="col-sm-2" align="right" style="padding-right:0px; padding-top:8px;">					
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
									</div>
								
								<div class="col-sm-12" style="margin-bottom:10px; margin-top:10px;">
									<textarea name="articleDescription" class="w3-col m12 w3-padding"
											rows="10" id="comment" style="margin-bottom: 5px;">${article.articleDesc}</textarea>
								</div>
								<div class="col-sm-9"  id="imagePreview"><img id="uploadImg" src="" width="100px;"></div>
								<div class="w3-col m12 w3-margin-top">
									<c:forEach items="${files}" var="file">
										  	<span id="${file.fileId}" class="badge" style="cursor:pointer;">${file.fileName}&nbsp;&nbsp; X</span>
									</c:forEach>
								</div>
								</div>
							</div>
									
			            	<ul class="pager">

							 	 <li class="previous col-sm-2">
							 	 <a href="<c:url value="/showDetail?articleId=${article.articleId}&boardId=${article.boardId}"/>">
							 	 	<span class="glyphicon glyphicon-arrow-left" style="color:#FF3300; "></span>
							 	 </a>
							 	 </li>
							 	 <li class="next btn w3-round-xxlarge">
									<a id="updateArticleBtn" style="color:#FF3300;">수정</a>
								</li>
								
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
