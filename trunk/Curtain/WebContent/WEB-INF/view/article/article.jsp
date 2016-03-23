<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<div class="w3-container w3-center w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px;">
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
            <div class="w3-container w3-padding w3-left-align">
              <div class="form-group">
				  <textarea class="w3-col m12" rows="5" id="comment" style=" margin-bottom:5px;" placeholder="무슨 생각을 하고 계신가요?"></textarea>
				</div>
               <div class="btn-group pull-right">
					    <div class="btn-group">
					      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="background-color:#FF3300; border:0px;">
					      파일첨부 <span class="caret"></span></button>
					      <ul class="dropdown-menu" role="menu">
					        <li><a href="#">사진</a></li>
					        <li><a href="#">동영상</a></li>
		    				</ul>
    					</div>
    				<button type="button" class="btn btn-primary " style="background-color:#FF3300; border:0px;">게시</button>
    			</div>

            </div>
          </div>
        </div>
      </div>
      
      <c:forEach items="${majorArticles}" var="article">
	      <div class="w3-row-padding w3-margin-top">
	        <div class="w3-col m12">
	          <div class="w3-card-2 w3-white w3-round-large">
	            <div class="w3-container">
	              <p>${article.articleTitle}</p>
	              <p>${article.articleDesc}</p>
	              <p>${article.articleModifyDate}  ${article.nickName}</p>
	            </div>
	          </div>
	        </div>
	      </div>
      </c:forEach>
      
    </div> 
</div>

</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>