<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		function subStringFromDate() {
			
		};
	});
</script>

	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">
		<div class="w3-row">
			<div class="w3-col m7 w3-main" style="margin-left: 350px;">
				<div class="w3-row-padding w3-margin-top">
					<div class="w3-col m12">
						<div class="w3-card-2 w3-white w3-round-large" style="padding: 10px; height: 500px;">
							<div style="display: inline-block; margin: 0px 0px 20px 0px">
								<div style="float: left; margin-top: 7px;">
									<h3
										style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
										내가 한 질문들</h3>
								</div>
								<div style="float: left;">
									<p style="padding-left: 20px; margin-top: 7px;">
										커튼의 서비스에 대한 회원님의 질문입니다.</p>
								</div>
								<div style="clear: both;"></div>
							</div>
							
							<c:forEach items="${questions}" var="question">
								<div class="w3-container w3-card-2" style="margin-bottom: 10px; padding: 10px 10px 10px 10px;" >
									
									<table class="w3-table">
										<tr>
											<td>
												${question.questionDate}
												<c:if test="${question.checkedQuestion eq 'N'}">
													<div style="float: right;">
														답변완료
													</div>
												</c:if>
											</td>
											<td>
												
											</td>
										</tr>
									</table>
									
									<div style="float: left; margin-left: 20px;">
										<h4 style="display: inline;">
											문의 제목: ${question.questionTitle}
											<br />
											문의 내용 : ${question.questionDescription}
										</h4>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>