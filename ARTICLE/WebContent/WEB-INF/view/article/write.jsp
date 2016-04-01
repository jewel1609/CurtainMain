<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">

	$(document).ready( function () {
		
		<c:choose>
		 	<c:when test="${ article.articleId eq null }">
				$("#btnWrite").click( function () {
					var form = $("#doWriteForm");
					form.attr("method", "post");
					form.attr("action", "/doWrite");
					form.submit();
				});
	 		</c:when>
	 		<c:otherwise>
				$("#btnUpdate").click( function () {
					var form = $("#doWriteForm");
					form.attr("method", "post");
					form.attr("action", "/doUpdate");
					form.submit();
				});
				$("#btnUpdateReset").click( function () {
					location.reload();
				});
	 		</c:otherwise>
		 </c:choose>
	});

</script>
<br/>
<br/>
<div class="container">
  <form role="form" id="doWriteForm" enctype="multipart/form-data">
    <div class="form-group">
      <label for="usr"><span style="font-size:20px;">제목 :</span></label>
      <input type="text" class="form-control" id="title" name="title" value="${article.title}"/>
    </div>
    <div class="form-group">
      <label for="comment"><span style="font-size:20px;">글 내용 :</span></label>
      <textarea class="form-control" rows="5" id="descript" name="descript">${article.descript}</textarea>
    </div>
    <div>
    	<c:choose>
	 	<c:when test="${ article.articleId eq null }">
		   <label for="usr"><span style="font-size:20px;">파일 업로드</span></label>
           <input type="file" id="file" name="file"/>
	 	</c:when>
	 	<c:otherwise>
		    <c:forEach items="${article.fileList}" var="file">
						<td colspan="6">
								${file.fileName}
						</td>
		    </c:forEach> 	
	 	</c:otherwise>
	    </c:choose>


	</div>
	<br/>
	<br/>
	<!-- 
	<c:if test="${ article.articleId eq null }">
	<button type="button" class="btn btn-default" id="btnWrite">등록</button>
	<button type="reset" class="btn btn-default" id="btnWrite">다시쓰기</button>
	</c:if>
	<c:if test="${ article.articleId ne null }">
	<button type="button" class="btn btn-default" id="btnWrite">수정</button>
	<button type="button" class="btn btn-default" id="btnWrite">다시쓰기</button>
	</c:if>
	 -->
	 <c:choose>
	 	<c:when test="${ article.articleId eq null }">
	 		<button type="button" class="btn btn-default" id="btnWrite">등록</button>
			<button type="reset" class="btn btn-default" id="reset">다시쓰기</button>
	 	</c:when>
	 	<c:otherwise>
	 		<input type="hidden" name="articleId" value="${article.articleId}" />
			<button type="button" class="btn btn-default" id="btnUpdate">수정</button>
			<button type="button" class="btn btn-default" id="btnUpdateReset">다시쓰기</button>	 	
	 	</c:otherwise>
	 </c:choose>
  </form>
</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
