<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>


<script type="text/javascript">
	$(document).ready(function() {
		
		$("#initSearchBtn").click(function() {
			location.href = "<c:url value="/showQuestion/init" />";
		});
		
		$("#searchKeyword").keydown( function (event) {
			if (event.keyCode == 13) {
				return false;
			}
		});

		$("#searchBtn").click(function() {
			if ($("#searchKeyword").val() == "") {
				alert("검색어를 입력하세요!");
				return;
			}
			
			var searchKeyword = $("#searchKeyword").val();
			
			if ($("#searchType").val() == 3 && !(searchKeyword == "y" || searchKeyword == "n" 
					|| searchKeyword == "Y" || searchKeyword == "N" )) {
				alert("검색어를 y(Y) 또는 n(N)으로 입력하세요!");
				return;
			}
			
			if (searchKeyword == "y") {
				$("#searchKeyword").val("Y");
			}
			
			if (searchKeyword == "n") {
				$("#searchKeyword").val("N");
			}

			movePage('0');
		});

		$(document).on("mouseover", ".btnDetailQuestion", function () {
			$(".btnDetailQuestion").css("cursor","pointer");
		});
		
		$(document).on("click", ".btnDetailQuestion", function () {
			var td = $(this);
			var form = td.parent().children(":eq(0)").children(":eq(0)");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/detailQuestion" />");
			form.submit();
		});

	});
</script>
<div class="container">
	<h2>질문 리스트</h2>
	<p>질문에 대한 답변을 해주세요.</p>
	<table class="table">
		<thead>
			<tr>
				<th style="width: 100px; text-align: center;">질문 일</th>
				<th style="width: 200px; text-align: center;">질문 제목</th>
				<th style="width: 300px; text-align: center;">질문 내용 (40 글자)</th>
				<th style="width: 300px; text-align: center;">답변 내용 (40 글자)</th>
				<th style="width: 100px; text-align: center;">답변 일</th>
				<th style="width: 100px; text-align: center;">질문자 EMAIL</th>
				<th style="width: 100px; text-align: center;">확인 여부</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${questionList.questions}" var="question">
				<tr>
					<td class="btnDetailQuestion" style="text-align: center;">
						<form class="massiveForm">
							<input name="questionId" type="hidden" value="${question.questionId}" />
						</form>
						<c:set var="questionDate" value="${question.questionDate}" />
						${fn:substring(questionDate, 0, 10)}
					</td>
					<td class="btnDetailQuestion" style="text-align: center;">
						${question.questionTitle}
					</td>
					<td class="btnDetailQuestion" style="text-align: center;">
						<c:set var="questionDescription" value="${question.questionDescription}" />
						<c:set var="isContainByQuestion" value="${fn:contains(questionDescription, \"<br />\")}" />
						<c:if test="${isContainByQuestion}">
							${fn:substringBefore(questionDescription, "<br />")}...
						</c:if>
						<c:if test="${!isContainByQuestion}">
							${fn:substring(questionDescription, 0, 40)}...
						</c:if>
					</td>
					<td class="btnDetailQuestion" style="text-align: center;">
						<c:set var="answerDescription" value="${question.answerDescription}" />
						<c:set var="isContainByAnswer" value="${fn:contains(answerDescription, \"<br />\")}" />
						<c:if test="${isContainByAnswer}">
							${fn:substringBefore(answerDescription, "<br />")}
						</c:if>
						<c:if test="${!isContainByAnswer}">
							${fn:substring(answerDescription, 0, 40)}
						</c:if>
					</td>
					<td class="btnDetailQuestion" style="text-align: center;">
						<c:set var="answerDate" value="${question.answerDate}" />
						${fn:substring(answerDate, 0, 10)}
					</td>
					<td class="btnDetailQuestion" style="text-align: center;">
						${question.email}
					</td>
					<td class="btnDetailQuestion" style="text-align: center;">
						${question.isChecked}
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7">
					<form id="searchForm">
						<div>
							${ questionList.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
						</div>
						<div style="text-align: right;">
							<select id="searchType" name="searchType">
								<c:forEach begin="1" end="3" step="1" var="i">
									<c:set var="selected" value="" />
									<c:if test="${ i eq searchVO.searchType }">
										<c:set var="selected" value="selected='selected'" />
									</c:if>
									<c:if test="${i eq 1}">
										<c:set var="name" value="제목 + 내용" />
									</c:if>
									<c:if test="${i eq 2}">
										<c:set var="name" value="작성자" />
									</c:if>
									<c:if test="${i eq 3}">
										<c:set var="name" value="확인여부" />
									</c:if>
									<option value="${i}" ${selected}>${name}</option>
								</c:forEach>
							</select> 
							<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" /> 
							<input type="button" id="searchBtn" value="검색" /> <input type="button" id="initSearchBtn" value="검색 초기화" />
						</div>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>