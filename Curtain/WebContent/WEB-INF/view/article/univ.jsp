<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

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


<div class="w3-container w3-main" style="margin-top:0px;">
	<c:set var="univName" value="${ sessionScope._UNIVNAME_}" />
	<c:set var="univMemberCount" value="${ sessionScope._MEMBER_.univMemberCount }" />
	
	<div class="w3-row" >
		<div class="w3-col m7 w3-main" style="border-right:1px solid #bababa;
    			background-color: #F3F3F3; margin-left: 334px; margin-right: 100px; height: 885px; overflow: auto; ">
				
		<!-- 게시판 헤더 -->
	     <div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa;">
			<div class="w3-margin-4" style="float:left;">
	    		<h2>${univName} 게시판입니다.</h2>
	   		</div>
			<div style="float:left; padding:20px;">
				<h5>참여인원 ${univMemberCount}명</h5> 
			</div> 	
		</div>
		<!-- 게시판 타이틀 끝 -->
		
		
		<!-- 글쓰기 시작 -->
        <div class="w3-row-padding" style="border-bottom:1px solid #BABABA;">
          <div class="w3-col m12" align="left">
          	<div class="w3-card w3-round-large w3-white" style="margin-top:10px; margin-bottom:10px;">

          
         	 <form id="writeArticle" enctype="multipart/form-data">
	            <div class="w3-container w3-padding w3-left-align">
	              <div class="form-group">
		              	  <input type="hidden" id="boardId" name="boardId" value="2"/>
		              	<div>
		              		<div class="container col-xs-2 input-lg" style="padding-bottom:10px; padding-left:0px;">
						      <select class="form-control " id="sel1" name="articleTypeId">
						        <option value="1">고민</option>
						        <option value="2">취업</option>
						        <option value="3">연애</option>
						        <option value="4">질문</option>
								<option value="5">기타</option>
						      </select>
							</div>
							<div class="col-sm-8">
							    <input class="w3-input" type="text" id="articleTitle" name="articleTitle" 
							    		placeholder="제목을 입력하세요."  style="margin-bottom: 5px;"/>
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
								<input type="hidden" name="boardId" value="4">
							</div>
							
						</div>
						
						<div class="form-group" style="margin-top: 30px;">
							<textarea class="w3-col m12" rows="5" id="articleDescription" name="articleDescription" style="margin-bottom: 5px; overflow: visible;"
								placeholder="무슨 생각을 하고 계신가요?"></textarea>
						</div>
					</div>
					<div class="col-sm-12" id="imagePreview">
						<img id="uploadImg" src="#" width="100px;">
					</div>
					<div class="col-sm-12" style="margin-top:10px;">
						<button type="button" class="btn btn-default" id="writeBtn" style="float: right; border-color: #FF3300; color: #FF3300;">게시</button>
					</div>

	            </div>
            </form>
            
          </div>
        </div>
      </div>
      
      
      <c:forEach items="${univArticles}" var="article">
	      <div class="w3-row-padding w3-margin-top">
	        <a href="<c:url value="/hitsCount?boardId=2&articleId=${article.articleId}"/>">
	        <div class="w3-col m12">
	          <div class="w3-card-2 w3-white w3-round-large">
	            <div class="w3-container">
	           	 <div>
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
