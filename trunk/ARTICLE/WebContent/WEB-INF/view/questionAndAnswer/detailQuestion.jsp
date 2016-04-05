<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	});
</script>

<div class="container">
	<h2>신고게시글 상세페이지</h2>
	<table class="table">
		<thead>
			<tr>
				<th style="text-align: center;">질문 번호 : </th>
				<th style="text-align: center;">질문 일 : </th>
				<th style="text-align: center;">질문자 EMAIL :</th>
				<th style="text-align: center;">확인 여부 :</th>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center;">
					<b>질문 제목 : </b>
				</td>
				<td colspan="3" style="text-align: center;"></td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center;">
					<b>질문 내용 : </b>
				</td>
				<td colspan="3" style="text-align: center;"></td>
			</tr>
		</thead>

		<tbody>
			<form id="claimArticleForm">
				<tr>
					<input type="hidden" name="articleId"
						value="${claimReplyInfo.replyId}" />
					<td>${claimReplyInfo.replyId}</td>
					<td>${claimReplyInfo.replyDesc}</td>
					<td>${claimReplyInfo.replyRegisterDate}</td>
					<td>${claimReplyInfo.replyModifyDate}</td>
				</tr>
			</form>
			<tr>
				<td colspan="14"><span id="deleteClaimArticleBtn"
					style="cursor: pointer;">댓글 삭제하기</span></td>
			</tr>
		</tbody>
	</table>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>