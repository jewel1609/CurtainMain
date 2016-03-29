<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<script type="text/javascript">

$(document).ready(function () {

	$(this).click(function() {		
		
		$.post(		
			"/deleteFile"
			, { "fileName" : $(this).attr("id")
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
					var result = "#" + fileId;
					$(result).hide();
				}
				else{
					alert("세션이 만료되었습니다. 다시 로그인해주세요.");
					location.href="/";
				}
			}
		)	
	});
	
	$(updateArticle).click(function () {
		
		
		
		
		
	});
	
});

</script>
   
<div class="w3-container w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left: 350px; margin-right: 100px; height: 705px; overflow: auto;">
     
     <div class="w3-row-padding">
				<div class="w3-col m12">
					<div class="w3-card-2 w3-round w3-white">

						<form id="writeArticle" enctype="multipart/form-data">
							<div class="w3-container w3-padding w3-left-align">
								<div class="form-group1">
								<div>
									<input type="hidden" id="boardId" name="boardId" value="1" />
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
											<input type="hidden" id="articleId" value="${article.articleId}"/>
											<input type="text" class="w3-col m10 input-lg"
												id="articleTitle" name="articleTitle"  value="${article.articleTitle}"/>
										</div>
										
										<div class="col-sm-2">

											<input type="file" id="file" name="imgFile" style="display: none;"
												onchange="readURL(this);">
											<button type="button" class="btn btn-default btn-sm"
												onclick="document.all.imgFile.click();">
												<span class="glyphicon glyphicon-picture"></span>
											</button>


											<input type="file" id="file" name="movieFile" style="display: none;">
											<button type="button" class="btn btn-default btn-sm"
												onclick="document.all.movieFile.click();">
												<span class="glyphicon glyphicon-facetime-video"></span>
											</button>
										</div>
										<div style="padding-top: 4px;">
										<textarea name="articleDescription" class="w3-col m12"
											rows="5" id="comment" style="margin-bottom: 5px;">${article.articleDesc}</textarea>
									</div>
									
									<div class="w3-col m12">
										<c:forEach items="${files}" var="file">
											<div id="${file.fileId}">${file.fileName}<span id="${file.fileName}" class="glyphicon glyphicon-trash"></span></div>
										</c:forEach>
									</div>
								</div>
							</div>
									
			            	<ul class="pager">
							 	 <li class="previous"><a href="<c:url value="/showDetail?articleId=${article.articleId}"/>">뒤로가기</a></li>
								<li id="updateArticle" class="next"><a href="">수정완료</a></li>
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
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
