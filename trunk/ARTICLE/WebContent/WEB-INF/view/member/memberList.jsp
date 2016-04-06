<%@page import="com.ktds.oph.member.vo.MemberVO"%>
<%@page import="com.ktds.oph.operationHistory.vo.OperationHistoryVO"%>
<%@page import="com.ktds.oph.operationHistory.vo.ActionCode"%>
<%@page import="com.ktds.oph.operationHistory.vo.BuildDescription"%>
<%@page import="com.ktds.oph.operationHistory.vo.Description"%>
<%@page import="com.ktds.oph.operationHistory.biz.OperationHistoryBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function () {
		$("#initSearchBtn").click(function () {
			location.href = "<c:url value="/showMember/init" />";
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
				historyBiz.addHistory(historyVO);
			%>
			
			movePage('0');
		});
		
		$("#massiveSelectCheckBox").click(function () {
			// 여러가지 경우를 가져오기 위해서 prop를 사용한다.
			var isChecked = $(this).prop("checked");
			$(".memberEmail").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".memberEmail").each(function (index, data) {
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
				form.attr("action", "<c:url value="/massiveDelete" />");
				form.submit();
			}
		});
		
	});
</script>
<div class="container">
  <h2>회원리스트</h2>
  <p>자유롭게 이용하세요</p>
  <table class="table">
    <thead>
      <tr>
      	<td style="width: 15px">
			<input type="checkbox" id="massiveSelectCheckBox" />
		</td>
        <th>EMAIL</th>
        <th>멤버등급</th>
        <th>대학ID</th>
        <th>학과ID</th>
		<th>등록일</th>
		<th>닉네임</th>
		<th>보조EMAIL</th>
		<th>포인트</th>
		<th>등급수정일</th>
		<th>비밀번호</th>
		<th>핸드폰번호</th>
		<th>기업이름</th>
		<th>소계열ID</th>
      </tr>
    </thead>
    
    <tbody>
    	<form id="massiveForm">
	    	<c:forEach items="${members.memberList}" var="member"> 
			      
			      <tr>
			     	 <td>
						<input class="memberEmail" name="memberEmail" value="${member.email}" type="checkbox"/>
					</td>
			        <td><a href="/detailMember?memberEmail=${member.email}">${member.email}</a></td>  
			        <td>${member.memberTypeId} </td>
			        <td>${member.univId}</td>
			       	<td>${member.majorId}</td>
			       	<td>
			       		<c:set var="signupDate" value="${member.signupDate}" />
			       		 ${fn:substring(signupDate, 0, 11)}
			        </td>
			        <td>${member.nickName}</td>
			        <td>${member.secondEmail}</td>
			        <td>${member.point}</td>
			        <td>
			        <c:set var="rankModifyDate" value="${member.rankModifyDate}" />
			       		 ${fn:substring(rankModifyDate, 0, 11)}
			        </td>
			        <td>${member.password}</td>
			        <td>${member.phoneNumber}</td>
			        <td>${member.companyName}</td>
			        <td>${member.majorGroupId}</td>
			      </tr>
	    	</c:forEach> 
    	</form>
   		<tr>
			<td colspan="14">
				<form id="searchForm">
					<div>
						${ members.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</div>	
					<div style= "text-align: right;">
							<select name="searchType">
							<c:forEach begin="1" end="3" step="1" var="i">
								<c:set var="selected" value="" />
								<c:if test="${ i eq searchVO.searchType }">
									<c:set var="selected" value="selected='selected'" />
								</c:if>
								<c:if test="${i eq 1}" >
									<c:set var="name" value="EMAIL" />
								</c:if>
								<c:if test="${i eq 2}" >
									<c:set var="name" value="NICK_NAME" />
								</c:if>
								<c:if test="${i eq 3}" >
									<c:set var="name" value="COMPANY_NAME" />
								</c:if>
								<option value="${i}" ${selected}> ${name} </option>
							</c:forEach>
						</select> 
						<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="initSearchBtn" value="검색 초기화" />
					</div>
				<span id="massiveDeleteBtn" style="cursor: pointer;">일괄삭제</span>
				</form>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>