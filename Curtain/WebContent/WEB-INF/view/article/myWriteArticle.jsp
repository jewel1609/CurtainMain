<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<script type="text/javascript">

	$(document).ready( function () {
	});

</script>

<div class="w3-container w3-center w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px;">
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
          
            
          </div>
        </div>
      </div>
      
      <c:forEach items="${likesArticles}" var="article">
	      <div class="w3-row-padding w3-margin-top">
	        <div class="w3-col m12">
	          <div class="w3-card-2 w3-white w3-round-large">
	            <div class="w3-container">
	           	 <div>
	           		${article.articleTitle}
	           	 </div>
	              
	              <p>${article.articleDesc}</p>
	              <p>${article.articleModifyDate}  ${article.nickName}  ${article.hits}</p>
	            </div>
	          </div>
	        </div>
	      </div>
      </c:forEach>
      
    </div> 
</div>

</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>