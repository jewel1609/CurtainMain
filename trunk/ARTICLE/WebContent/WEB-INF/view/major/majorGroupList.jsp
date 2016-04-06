<%@page import="com.ktds.oph.member.vo.MemberVO"%>
<%@page import="com.ktds.oph.operationHistory.vo.OperationHistoryVO"%>
<%@page import="com.ktds.oph.operationHistory.vo.ActionCode"%>
<%@page import="com.ktds.oph.operationHistory.vo.BuildDescription"%>
<%@page import="com.ktds.oph.operationHistory.vo.Description"%>
<%@page import="com.ktds.oph.operationHistory.biz.OperationHistoryBiz"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function () {
		
		$("#registerNewMajorGroup").hide();
		
		$("#insertBtn").click(function () {
			
			$("#registerNewMajorGroup").slideToggle();
		});
		
		$("#insertMajorGroupBtn").click(function() {
			
			if($("#newMajorGroup").val == "" ) {
				alert("등록할 학과 그룹을 입력하세요.")
				return;
			}
			
				var form = $("#registerNewMajorGroupForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/registerNewMajorGroup" />");
				form.submit();

		});
		
		$("#initSearchBtn").click(function () {
			location.href = "<c:url value="/majorGroupList/init" />";
		});
			
		
		$("#searchBtn").click(function() {
			if($("#searchKeyword").val() == "") {
				alert("검색어를 입력하세요!");
				return;
			}
			
			<%
				MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
				String keyword = request.getParameter("searchKeyword");
				String searchType = request.getParameter("searchType");
				
				OperationHistoryBiz historyBiz = new OperationHistoryBiz();
				OperationHistoryVO historyVO = new OperationHistoryVO();
				historyVO.setIp(request.getRemoteHost());
				historyVO.setEmail(member.getEmail());
				historyVO.setUrl(request.getRequestURI());
				historyVO.setActionCode(ActionCode.DO_SEARCH);
				historyVO.setDescription( BuildDescription.get(Description.DO_SEARCH, member.getEmail()));
				historyVO.setEtc( BuildDescription.get(Description.DETAIL_DO_SEARCH, searchType, keyword ));
				System.out.println("검색시도");
				historyBiz.addHistory(historyVO);
			%>
			
			movePage('0');
		});
		
		$("#massiveSelectCheckBox").click(function () {
			// 여러가지 경우를 가져오기 위해서 prop를 사용한다.
			var isChecked = $(this).prop("checked");
			$(".majorGroupId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".majorGroupId").each(function (index, data) {
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
				form.attr("action", "<c:url value="/massiveMajorGroupDelete" />");
				form.submit();
			}
		});
		
		
		$(".updateBtn").click(function() {		
			var root = $(this).parent().children(":eq(0)");
			
			if ( root.val() == "" ) {
				alert("학과 그룹을 입력하세요.");
				return;
			}
			
			$.post(		
				"/updateMajorGroupName"
				, { "updateText" : root.val()
					, "majorGroupId" : $(this).attr("id")
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
						var majorGroupId = jsonData.majorGroupId;
						var result2 = "#majorGroup" + majorGroupId;
						var updateMajorGroupName = jsonData.updateMajorGroupName;
						URLEncoder.encode(updateMajorGroupName , "UTF-8");
						$(result2).val(updateMajorGroupName);
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
  <h2>학과 그룹 리스트</h2>
  <p>자유롭게 이용하세요</p>
  <table class="table">
    <thead>
      <tr>
      	<td style="width: 15px">
			<input type="checkbox" id="massiveSelectCheckBox" />
		</td>
        <th>학과그룹번호</th>
        <th>학과그룹명</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="massiveForm">
	    	<c:forEach items="${majors.majorGroupList}" var="major"> 
			      
			      <tr>
			     	 <td>
						<input class="majorGroupId" name="majorGroupId" value="${major.majorGroupId}" type="checkbox"/>
					</td>
			        <td><a href="/detailMajor?majorGroupId=${major.majorGroupId}">${major.majorGroupId}</a></td>  
			         <td><input type="text" id ="majorGroup${major.majorGroupId}" name="majors" value="${major.majorGroupName}"/>
			        	<span class ="updateBtn" id ="${major.majorGroupId}">수정</span>
			        </td>
			      </tr>
			    
	    	</c:forEach> 
    	</form>
    
    	<tr id="registerNewMajorGroup">
    		<td colspan=3 >
    		<form id = "registerNewMajorGroupForm">
    			학과 그룹명 : <input type="text" id ="newMajorGroup" name="newMajorGroup"/>
    			<span class ="insertMajorGroupBtn" id ="insertMajorGroupBtn">등록</span>
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
									<c:set var="name" value="학과그룹명" />
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
				<span id="insertBtn" style="cursor: pointer;">학과그룹등록</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>