<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<script type="text/javascript">

	$(document).ready( function () {
		$("#writeBtn").click( function () {
				var form = $("#writeArticle");
				form.attr("method", "post");
				form.attr("action","/writeArticle");
				form.submit();
		});
	});

</script>

<div class="w3-container w3-center w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px;">
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
          
          <form id="writeArticle">
	            <div class="w3-container w3-padding w3-left-align">
	              <div class="form-group1">
		              	  <input type="hidden" id="boardId" name="boardId" value="1"/>
		              	<div>
		              	<div class="container col-xs-2 input-lg" style="padding-bottom:10px; padding-left:0px;">
						      <select class="form-control " id="sel1" name="articleTypeId">
						        <option value="1">고민</option>
						        <option value="2">취업</option>
						        <option value="3">연애</option>
						      </select>
						</div>
							<div style="padding-top:4px;">
							    <input type="text"  class="w3-col m10 input-lg" id="articleTitle" name="articleTitle" placeholder="제목"  />
							</div>
						</div>
						<div style="padding-top:4px;">
						  <textarea name="articleDescription" class="w3-col m12" rows="5" id="comment" style=" margin-bottom:5px;" placeholder="무슨 생각을 하고 계신가요?"></textarea>
						</div>
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
	    				<button type="button" id="writeBtn" class="btn btn-primary" style="background-color:#FF3300; border:0px;">게시</button>
	    			</div>
	            </div>
            </form>
            
          </div>
        </div>
      </div>
      
      <c:forEach items="${majorArticles}" var="article">
	      <div class="w3-row-padding w3-margin-top">
	      <a href="<c:url value="/hitsCount?boardId=1&articleId=${article.articleId}"/>">
	        <div class="w3-col m12">
	          <div class="w3-card-2 w3-white w3-round-large">
	            <div class="w3-container">
	           	 <div>
	           	  <input type="hidden" id="articleId" name="articleId" value="${article.articleId}"/>
		           	 <div>${article.articleTypeName}</div>
	           		${article.articleTitle}
	           	 </div>
	              
	              <p>${article.articleDesc}</p>
	              <p>${article.articleModifyDate}  ${article.nickName}</p>
	              <p>조회수  ${article.hits}</p>
	            </div>
	          </div>
	        </div>
	        </a>
	      </div>
      </c:forEach>
      
    </div> 
</div>

</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>