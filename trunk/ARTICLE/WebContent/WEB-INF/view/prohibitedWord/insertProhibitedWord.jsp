<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
		
		//비밀번호 전송버튼 누를시
		$("#prohibitedWordBtn").click(function(){
			 
			 if( $("#prohibitedWordName").val() == ""){
				alert("욕설 및 금칙어를 입력해주세요!");
				return;
				// 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			var form = $("#prohibitedWordForm");
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doInsertProhibitedWord" />");
			form.submit();
		
		});
		
		$("#cancleBtn").click(function(){
			window.history.back();
		});
		
	});
</script>


<div class="container">
  <h2>욕설 등록</h2>
   <form id="prohibitedWordForm">         
	  <table class="table">
	    <thead>
	      <tr>
	        <th>욕설, 금칙어</th>
	      </tr>
	    </thead>
	    <tbody>
	    	
			    <tr>
						<td><input type="text" id="prohibitedWordName" name="prohibitedWordName" placeholder="욕설 및 금칙어 입력"></td>
				</tr>
			
	    </tbody>
	  </table>
  </form>
</div>
<div class="container">
  	<span id="prohibitedWordBtn" style="cursor: pointer;">욕설,금칙어 등록</span>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<span id="cancleBtn" style="cursor: pointer;">취소</span>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>