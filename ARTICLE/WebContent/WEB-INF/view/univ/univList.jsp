 <%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function () {
		
		$("#registerNewUniv").hide();
		
		$("#insertBtn").click(function () {
			
			$("#registerNewUniv").slideToggle();
		});
		
		$("#insertUnivBtn").click(function() {
			
			if($("#newUniv").val == "" ) {
				alert("등록할 대학명을 입력하세요.")
				return;
			}
			
				var form = $("#registerNewUnivForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/registerNewUniv" />");
				form.submit();

		});
		
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
			$(".univId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".univId").each(function (index, data) {
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
				form.attr("action", "<c:url value="/massiveUnivDelete" />");
				form.submit();
			}
		});
		
		
		$(".updateBtn").click(function() {		
			var root = $(this).parent().children(":eq(0)");
			
			if ( root.val() == "" ) {
				alert("대학명을 입력하세요.");
				return;
			}
			
			$.post(		
				"/updateUnivName"
				, { "updateText" : root.val()
					, "univId" : $(this).attr("id")
				}
				, function(data) {
					var jsonData = {};		
					
					try {
						jsonData = JSON.parse(data);
					}
					catch(e) {
						jsonData.result = false;
					}
					if(jsonData.result){
						var univId = jsonData.univId;
						var result2 = "#univ" + univId;
						var updateUnivName = jsonData.updateUnivName;
						URLEncoder.encode(updateUnivName , "UTF-8");
						$(result2).val(updateUnivName);
					}
					else{
						alert("세션이 만료되었습니다. 다시 로그인해주세요.");
						location.href="/";
					}
				}
			)	
		});
			
	});
</script>
<div class="container">
  <h2>대학리스트</h2>
  <p>자유롭게 이용하세요</p>
  <table class="table">
    <thead>
      <tr>
      	<td style="width: 15px">
			<input type="checkbox" id="massiveSelectCheckBox" />
		</td>
        <th>대학번호</th>
        <th>대학명</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="massiveForm">
	    	<c:forEach items="${univs.univList}" var="univ"> 
			      
			      <tr>
			     	 <td>
						<input class="univId" name="univId" value="${univ.univId}" type="checkbox"/>
					</td>
			        <td>${univ.univId}</td>  
			        <td><input type="text" id ="univ${univ.univId}" name="univs" value="${univ.univName}"/>
			        	<span class ="updateBtn" id ="${univ.univId}">수정</span>
			        </td>
			      </tr>
			    
	    	</c:forEach> 
    	</form>
    
    	<tr id="registerNewUniv">
    		<td colspan=3 >
    		<form id = "registerNewUnivForm">
    			대학명 : <input type="text" id ="newUniv" name="newUniv"/>
    			<span class ="insertUnivBtn" id ="insertUnivBtn">등록</span>
    		</form>
    		</td>
    	</tr>
    	
   		<tr>
			<td colspan="6">
				<form id="searchForm">
					<div>
						${ univs.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</div>	
					<div style= "text-align: right;">
							<select name="searchType">
							<c:forEach begin="1" end="3" step="1" var="i">
								<c:set var="selected" value="" />
								<c:if test="${ i eq searchVO.searchType }">
									<c:set var="selected" value="selected='selected'" />
								</c:if>
								<c:if test="${i eq 1}" >
									<c:set var="name" value="제목+내용" />
								</c:if>
								<c:if test="${i eq 2}" >
									<c:set var="name" value="작성자ID" />
								</c:if>
								<c:if test="${i eq 3}" >
									<c:set var="name" value="작성자명" />
								</c:if>
								<option value="${i}" ${selected}> ${name} </option>
							</c:forEach>
						</select> 
						<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="initSearchBtn" value="검색 초기화" />
					</div>
				</form>
				<span id="massiveDeleteBtn" style="cursor: pointer;">일괄삭제</span>
				<span id="insertBtn" style="cursor: pointer;">대학등록</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>