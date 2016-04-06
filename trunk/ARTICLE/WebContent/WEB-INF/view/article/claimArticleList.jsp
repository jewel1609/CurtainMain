<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function () {
		$("#initSearchBtn").click(function () {
			location.href = "<c:url value="/claimArticle/init" />";
		});
			
		
		$("#searchBtn").click(function() {
			if($("#searchKeyword").val() == "") {
				alert("검색어를 입력하세요!");
				return;
			}
			
			movePage('0');
		});
		
	});
</script>
<div class="container">
  <h2>신고된 글 리스트</h2>
  <table class="table">
    <thead>
      <tr>
        <th>순서</th>
        <th>EMAIL</th>
        <th>글ID</th>
        <th>댓글ID</th>
		<th>신고날짜</th>
		<th>신고내용</th>
		<th>신고된 횟수</th>
      </tr>
    </thead>
    
    <tbody>
    	<c:forEach items="${claimArticles.claimArticleList}" var="claimArticle"> 
		      
		      <tr>
		      	<td>${claimArticle.articleClaimId}</td>
		        <td>${claimArticle.email} </td>
		        
		        <c:if test="${claimArticle.articleId != 0}">
		       		<td><a href="/detailClaimArticle?articleId=${claimArticle.articleId}">${claimArticle.articleId}</a></td>
		        </c:if>
		        <c:if test="${claimArticle.articleId == 0}">
		        	<td>${claimArticle.articleId}</td>
		        </c:if>
		        
		        <c:if test="${claimArticle.replyId != 0}">
		       		<td><a href="/detailClaimReply?replyId=${claimArticle.replyId}">${claimArticle.replyId}</a></td>
		       	</c:if>
		       	<c:if test="${claimArticle.replyId == 0}">
		       		<td>${claimArticle.replyId}</td>
		       	</c:if>
		       	
		        <td>${claimArticle.claimDate}</td>
		        <td>${claimArticle.claimText}</td>
		        
		        <c:if test="${claimArticle.articleId != 0}">
		       		<td>${claimArticle.claimCount}</td>
		       	</c:if>
		       	
		       	<c:if test="${claimArticle.articleId == 0}">
		       		<td>${claimArticle.claimReplyCount}</td>
		       	</c:if>
		      </tr>
		    
    	</c:forEach> 
   		<tr>
			<td colspan="7">
				<form id="searchForm">
					<div>
						${ claimArticles.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</div>	
					<div style= "text-align: right;">
							<select name="searchType">
							<c:forEach begin="1" end="3" step="1" var="i">
								<c:set var="selected" value="" />
								<c:if test="${ i eq searchVO.searchType }">
									<c:set var="selected" value="selected='selected'" />
								</c:if>
								<c:if test="${i eq 1}" >
									<c:set var="name" value="글ID" />
								</c:if>
								<c:if test="${i eq 2}" >
									<c:set var="name" value="댓글ID" />
								</c:if>
								<c:if test="${i eq 3}" >
									<c:set var="name" value="EMAIL" />
								</c:if>
								<option value="${i}" ${selected}> ${name} </option>
							</c:forEach>
						</select> 
						<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="initSearchBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>