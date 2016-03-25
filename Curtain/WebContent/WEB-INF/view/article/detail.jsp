<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<script type="text/javascript">


</script>
   
<div class="w3-container w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px;">
     
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
					 ${article.hits}
					 <img id="${article.articleId}"
										class="like" src="<c:url value="/resource/img/like_active_small.png"/>" />${article.articleLikes}
					<strong class="w3-center"><h2>${article.articleTitle}</h2></strong>
				</div>

	              
	              <p>${article.articleDesc}</p>
	              <p>${article.articleModifyDate}  ${article.nickName}</p>
	              
  				</div>
  				</div>
	            </div>

	        	</div>
	        </div>
      
    </div> 
</div>

</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
