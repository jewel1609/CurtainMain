<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<script type="text/javascript">

$(document).ready(function () {

	$(updateArticle).click(function () {
		
		
		
		
		
	});
	
});

</script>
   
<div class="w3-container w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left: 350px; margin-right: 100px; height: 705px; overflow: auto;">
     
	      <div class="w3-row-padding w3-margin-top">
	        <div class="w3-col m12">
	          <div class="w3-card-2 w3-white w3-round-large">
	            <div class="w3-container">
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
						<div>
						<label><h4>제목 : </h4></label>
						<input type="text" class="w3-col m12" value="${article.articleTitle}"/>
						</div>
						<div>
						<label><h4>내용 : </h4></label>
			             <textarea class="w3-col m12" >${article.articleDesc}</textarea>
			             </div>
			             <div>${article.articleModifyDate}  ${article.nickName}</div>
			             <c:forEach items="${article.fileList}" var="file">
							<td colspan="7">
								<a href="/deleteFile?fileId=${file.fileId}&articleId=${article.articleId}">
									${file.fileName}
								</a>
							</td>
						</c:forEach>
			            	<ul class="pager">
							 	 <li class="previous"><a href="<c:url value="/showDetail?articleId=${article.articleId}"/>">뒤로가기</a></li>
								<li id="updateArticle" class="next">수정완료</li>
							</ul>  
  					</div>
  				</div>
            </div>

       	</div>
	 </div>

	
 
    	</div> 
	</div>
	

</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
