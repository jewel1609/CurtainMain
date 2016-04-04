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
			$(".deleteMemberEmail").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".deleteMemberEmail").each(function (index, data) {
				if(data.checked){
					isChecked = data.checked;
				}
			});
			
			if(!isChecked) {
				alert("삭제할 대상을 선택하세요.")
				return;
			}
			
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#massiveDeleteForm");
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
    	<form id="massiveDeleteForm">
	    	<c:forEach items="${members.memberList}" var="member"> 
			      <tr>
			     	 <td>
						<input class="deleteMemberEmail" name="deleteMemberEmail" value="${member.email}" type="checkbox"/>
					</td>
			        <td>${member.email}</td>
			        <td>${member.memberTypeId}</td>
			        <td>${member.univId}</td>
			       	<td>${member.majorId}</td>
			        <td>${member.signupDate}</td>
			        <td>${member.nickName}</td>
			        <td>${member.secondEmail}</td>
			        <td>${member.point}</td>
			        <td>${member.rankModifyDate}</td>
			        <td>${member.password}</td>
			        <td>${member.phoneNumber}</td>
			        <td>${member.companyName}</td>
			        <td>${member.majorGroupId}</td>
			      </tr>
	    	</c:forEach> 
    	</form>
   		<tr>
			<td colspan="6">
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
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>