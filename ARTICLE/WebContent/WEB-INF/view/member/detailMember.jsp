<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function () {
		
		//수정
		$("#modifyMemberInfoBtn").click(function() {
			if($("#memberPoint").val() == ""){
				alert("포인트를 입력해주세요!");
				return;
			}
			if($("#memberPassword").val() == ""){
				alert("패스워드를 입력해주세요!");
				return;
			}
			
			if (confirm("정말 수정하시겠습니까?")) {
				var form = $("#modifyForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/memberModify" />");
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
    	<form id="modifyForm">
			      <tr>
			      	<input type="hidden" name="memberEmail" value="${member.email}"/>
			        <td>${member.email}</td>
			        <td>
			        	<select id="memberTypeId" name="memberTypeId">
			        		<option value="${member.memberTypeId}">${member.memberTypeId}</option>
			        		<option value="1">창문</option>
			        		<option value="2">한겹커튼</option>
			        		<option value="3">두겹커튼</option>
			        		<option value="4">홍보담당자</option>
			        		<option value="5">홍보담당자(글)</option>
			        		<option value="6">관리자</option>
			        	</select>
			        </td>
			        <td>${member.univId}</td>
			       	<td>${member.majorId}</td>
			        <td>${member.signupDate}</td>
			        <td>${member.nickName}</td>
			        <td>${member.secondEmail}</td>
			        <td>
			        	<input id="memberPoint" name="memberPoint" value="${member.point}" type="text"/>
			        </td>
			        <td>${member.rankModifyDate}</td>
			        <td>
			        	<input id="memberPassword" name="memberPassword" value="${member.password}" type="text"/>
			        </td>
			        <td>${member.phoneNumber}</td>
			        <td>${member.companyName}</td>
			        <td>${member.majorGroupId}</td>
			      </tr>
    	</form>
   		<tr>
			<td colspan="6">
				<span id="modifyMemberInfoBtn" style="cursor: pointer;">수정하기</span>
			</td>
		</tr>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>