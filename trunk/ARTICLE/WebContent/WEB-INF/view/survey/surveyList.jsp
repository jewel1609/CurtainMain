<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function () {
		$("#initSearchBtn").click(function () {
			location.href = "<c:url value="/list/init" />";
		});
			
		
		$("#searchBtn").click(function() {
			if($("#searchKeyword").val() == "") {
				alert("검색어를 입력하세요!");
				return;
			}
			
			movePage('0');
		});
		
		$("#massiveSelectCheckBox").click(function () {
			// 여러가지 경우를 가져오기 위해서 prop를 사용한다.
			var isChecked = $(this).prop("checked");
			$(".surveyId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".surveyId").each(function (index, data) {
				if(data.checked){
					isChecked = data.checked;
				}
			});
			
			if(!isChecked) {
				alert("삭제할 대상을 선택하세요.")
				return;
			}
			
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#massiveForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/surveyMassiveDelete" />");
				form.submit();
			}
		});
	});
</script>
<div class="container">
  <h2>설문 리스트</h2>
  <table class="table">
    <thead>
      <tr>
      	<td style="width: 15px">
			<input type="checkbox" id="massiveSelectCheckBox" />
		</td>
        <th>설문ID</th>
        <th>설문 제목</th>
        <th>답변1</th>
        <th>답변2</th>
		<th>답변3</th>
		<th>답변4</th>
		<th>설문날짜</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="massiveForm">
	    	<c:forEach items="${survey.surveyList}" var="survey"> 
			      
			      <tr>
			     	 <td>
						<input class="surveyId" name="surveyId" value="${survey.surveyId}" type="checkbox"/>
					</td>
			        <td><a href="/detailSurvey?surveyId=${survey.surveyId}">${survey.surveyId}</a></td>  
			        <td>${survey.surveyTitle} </td>
			        <td>${survey.surveyAnswer1}</td>
			       	<td>${survey.surveyAnswer2}</td>
			        <td>${survey.surveyAnswer3}</td>
			        <td>${survey.surveyAnswer4}</td>
			        <td>${survey.surveyDate}</td>
			       
			      </tr>
			    
	    	</c:forEach> 
    	</form>
   		<tr>
			<td colspan="8">
				<form id="searchForm">
					<div>
						${ survey.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</div>	
				</form>
				<span id="massiveDeleteBtn" style="cursor: pointer;">일괄삭제</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>