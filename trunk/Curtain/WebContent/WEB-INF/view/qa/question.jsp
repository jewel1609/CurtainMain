<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">
		<div class="w3-row">
			<div class="w3-col m7 w3-main" style="margin-left: 350px;">
				<div class="w3-row-padding">
					<div class="w3-col m12">
						<div class="w3-card-2 w3-round w3-white">
							<div class="w3-container w3-padding w3-left-align">
								<div align="center" style="text-align: center;">
									<div style="display: inline-block; margin: 0px 0px 0px 0px">
										<div style="float: left; margin-top: 7px;">
											<h3
												style="display: inline; border-right: thin; border-right-style: solid; border-right-color: #DDDDDD; padding-right: 20px;">
												문의하기</h3>
										</div>
										<div style="float: left; margin-top: 10px;">
											<p style="padding-left: 20px; margin-bottom: 0px;">
												이용하시면서 불편한 사항이나 개선점이 있다면 언제든지 알려주세요.
											</p>
											<div style="clear: both;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="w3-row-padding w3-margin-top">
					<div class="w3-col m12">
						<div class="w3-card-2 w3-white w3-round-large" style="padding: 10px; height: 500px;">
							<table class="w3-table w3-border w3-bordered">
								<tr>
									<td width="200px" style="padding-left: 20px; padding-top: 15px;">제목</td>
									<td>
										<input class="w3-input" type="text" placeholder="포인트가 갑자기 사라졌어요.">
									</td>
								</tr>
								<tr>
									<td style="padding-left: 20px; padding-top: 15px;">문의 내용</td>
									<td>
										<textarea class="w3-col m12" rows="5" id="comment" style=" margin-bottom:5px;"
											 placeholder="일요일 오전 10시경 포인트가 갑자기 사라졌어요.">
										</textarea>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>