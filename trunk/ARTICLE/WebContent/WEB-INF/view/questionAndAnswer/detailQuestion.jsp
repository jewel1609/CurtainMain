<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/view/common/headerLogin.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		//수정
		$("#deleteClaimArticleBtn").click(function() {

			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#claimArticleForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/deleteClaimReply" />");
				form.submit();
			}
		});
		
		$("#btnAddAnswer").click( function () {
			
			var answerDescription = $("#answerDescription").text();
			
			if (answerDescription == "") {
				alert("내용을 입력하세요!");
				return;
			}
			
			var form = $("#addAnswerForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/addNewAnswer" />");
			form.submit();
		});

	});
</script>

<div class="container">
	<h2>질문 상세페이지</h2>
	<table class="table">
		<thead>
			<tr>
				<th style="text-align: center;">질문 번호 : ${question.questionId} </th>
				<th style="text-align: center;">
					<c:set var="questionDate" value="${question.questionDate}" />
					질문 일 : ${fn:substring(questionDate, 0, 10)}
				</th>
				<th style="text-align: center;">질문자 EMAIL : ${question.email}</th>
				<th style="text-align: center;">확인 여부 : ${question.isChecked}</th>
			</tr>
			<tr>
				<td style="width: 150px; text-align: center;">
					<b>질문 제목 : </b>
				</td>
				<td colspan="3">
					${question.questionTitle}
				</td>
			</tr>
			<tr>
				<td style="width: 150px; text-align: center;">
					<b>질문 내용 : </b>
				</td>
				<td colspan="3">
					${question.questionDescription}
				</td>
			</tr>
		</thead>

		<thead>
			<tr><td colspan="4" style="height: 50px;"></td></tr>
		</thead>
		
		<tbody>
			<c:if test="${question.answerDescription ne null}">
				<tr>
					<td>
						<c:set var="answerDate" value="${question.answerDate}" />
						<b>답변 일 : ${fn:substring(answerDate, 0, 10)} </b>
					</td>
					<td style="width: 150px;">
						<b>답변 내용 : </b>
					</td>
					<td colspan="2">
						${question.answerDescription}
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center;">
						수정 하기
					</td>
				</tr>
			</c:if>
			
			<c:if test="${question.answerDescription eq null}">
				<tr>
					<td colspan="4" style="text-align: center;">
						<form id="addAnswerForm">
							<input id="btnAddAnswer" type="button" value="답변 하기" />
							<input id="questionId" name="questionId" type="hidden" value="${question.questionId}" />
							<div style="clear: both;"></div>
							<textarea id="answerDescription" name="answerDescription"></textarea>
						</form>
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>