 <%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function () {
		
		$("#registerNewMajor").hide();
		
		$("#insertBtn").click(function () {
			
			$("#registerNewMajor").slideToggle();
		});
		
		$("#insertMajorBtn").click(function() {
			
			if($("#newMajor").val == "" ) {
				alert("등록할 학과명을 입력하세요.")
				return;
			}
				var majorGroupId = $(".majorGroupId").val();
				$(".majorGroupId1").val(majorGroupId);
				
				var form = $("#registerNewMajorForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/registerNewMajor" />");
				form.submit();

		});
		
		$("#initSearchBtn").click(function () {
			location.href = "<c:url value="/major/init" />";
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
			$(".majorId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".majorId").each(function (index, data) {
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
				form.attr("action", "<c:url value="/massiveMajorDelete" />");
				form.submit();
			}
		});
		
		
		$(".updateBtn").click(function() {		
			var root = $(this).parent().children(":eq(0)");
			
			if ( root.val() == "" ) {
				alert("학과명을 입력하세요.");
				return;
			}
			
			$.post(		
				"/updateMajorName"
				, { "updateText" : root.val()
					, "majorId" : $(this).attr("id")
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
						var majorId = jsonData.majorId;
						var result2 = "#major" + majorId;
						var updateMajorName = jsonData.updateMajorName;
						URLEncoder.encode(updateMajorName , "UTF-8");
						$(result2).val(updateMajorName);
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
	    	<c:forEach items="${majors.majorList}" var="major"> 
			      
			      <tr>
			     	 <td>
						<input class="majorId" name="majorId" value="${major.majorId}" type="checkbox"/>
					</td>
			        <td>${major.majorId}</td>  
			        <td><input type="text" id ="major${major.majorId}" value="${major.majorName}"/>
			        	<span class ="updateBtn" id ="${major.majorId}">수정</span>
			        </td>
    				<td><input type="hidden" class="majorGroupId" name="majorGroupId" value="${major.majorGroupId}"/></td>
			      </tr>
			    
	    	</c:forEach> 
    	</form>
    
    	<tr id="registerNewMajor">
    		<td colspan=3 >
    		<form id = "registerNewMajorForm">
    		<input type="hidden" class="majorGroupId1" name="majorGroupId1" value=""/>
    			학과명 : <input type="text" id ="newMajor" name="newMajor"/>
    			<span class ="insertMajorBtn" id ="insertMajorBtn">등록</span>
    		</form>
    		</td>
    	</tr>
    	
   		<tr>
			<td colspan="6">
				<form id="searchForm">
					<div>
						${ majors.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</div>	
					<div style= "text-align: right;">
							<select name="searchType">
							<c:forEach begin="1" end="2" step="1" var="i">
								<c:set var="selected" value="" />
								<c:if test="${ i eq majorSearchVO.searchType }">
									<c:set var="selected" value="selected='selected'" />
								</c:if>
								<c:if test="${i eq 1}" >
									<c:set var="name" value="선택" />
								</c:if>
								<c:if test="${i eq 2}" >
									<c:set var="name" value="학과명" />
								</c:if>
								<option value="${i}" ${selected}> ${name} </option>
							</c:forEach>
						</select> 
						<input type="text" id="searchKeyword" name="searchKeyword" value="${majorSearchVO.searchKeyword}" />
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="initSearchBtn" value="검색 초기화" />
					</div>
				</form>
				<span id="massiveDeleteBtn" style="cursor: pointer;">일괄삭제</span>
				<span id="insertBtn" style="cursor: pointer;">학과등록</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>