<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>


<script type="text/javascript"
	src="<c:url value="/resource/js/jquery-1.12.1.js" />"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#doWrite").click(function() {

			var form = $("#writeForm");
			form.attr("method", "post");
			form.attr("action", "/secretWriteArticle");
			form.submit();

		});

	});

	function readURL(input) {

		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#uploadImg').attr("src", e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

<body>

	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px; min-height: 100%">

		<div class="w3-row">
			<div class="w3-col m7 w3-main"
				style="margin-left: 350px; margin-right: 100px; padding-bottom: 1em;">

				<div class="w3-row-padding">

					<div class="w3-col m12" align="left">
						한겹커튼게시판입니다. 참여인원 명 참여대학교
						<div class="w3-card-2 w3-round w3-white">
							<div class="w3-container w3-padding w3-left-align">
								<form id="writeForm" enctype="multipart/form-data">
									<div class="col-sm-2">
										<select name="articleTypeId">
											<option value="1">고민</option>
											<option value="2">질문</option>
											<option value="3">기타</option>
										</select>
									</div>
									<div class="col-sm-8" style="background-color: lavenderblush;">
										<input class="w3-input" type="text" name="articleTitle" 
											placeholder="제목을 입력하세요." style="margin-bottom: 5px;">

									</div>
									<div class="col-sm-2">

										<input type="file" name="imgFile" style="display: none;"
											onchange="readURL(this);">
										<button type="button" class="btn btn-default btn-sm"
											onclick="document.all.imgFile.click();">
											<span class="glyphicon glyphicon-picture"
												onclick="document.all.imgFile.click();"></span>
										</button>


										<input type="file" name="movieFile" style="display: none;">
										<button type="button" class="btn btn-default btn-sm"
											onclick="document.all.movieFile.click();">
											<span class="glyphicon glyphicon-facetime-video"></span>
										</button>
									</div>
									
									<div class="form-group" style="margin-top:30px;">

										<textarea class="w3-col m12" rows="5" id="comment"
											name="articleDescription"
											style="margin-bottom: 5px; overflow: visible;"
											placeholder="무슨 생각을 하고 계신가요?"></textarea>
									</div>

									<input type="hidden" name="boardId" value="4">
									<div>
										<img id="uploadImg" src="/Curtain/resource/img/noimg.png" width="100"
											height="100">
									</div>
								</form>
								<br />


							</div>


							<br />
							<button type="button" class="btn btn-default" id="doWrite"
								style="border-color: #FF3300; color: #FF3300;">게시</button>

						</div>

					</div>
				</div>
			</div>
		</div>

		<div class="w3-row-padding w3-margin-top">
			<div class="w3-col m12">
				<div class="w3-card-2 w3-white w3-round-large">
					<div class="w3-container">
						<p>TITLE</p>
						<p>안녕하세요. 커튼입니다.</p>
					</div>
				</div>
			</div>
		</div>

		<div class="w3-row-padding w3-margin-top">
			<div class="w3-col m12">
				<div class="w3-card-2 w3-white w3-round-large">
					<div class="w3-container">
						<p>Just Forgot that I had to mention something about someone
							to someone about how I forgot something, but now I forgot it.
							Ahh, forget it! Or wait. I remember.... no I don't.</p>
					</div>
				</div>
			</div>
		</div>

		<div class="w3-row-padding w3-margin-top">
			<div class="w3-col m12">
				<div class="w3-card-2 w3-white w3-round-large">
					<div class="w3-container">
						<p>Just Forgot that I had to mention something about someone
							to someone about how I forgot something, but now I forgot it.
							Ahh, forget it! Or wait. I remember.... no I don't.</p>
					</div>
				</div>
			</div>
		</div>

		<div class="w3-row-padding w3-margin-top w3-margin-bottom">
			<div class="w3-col m12">
				<div class="w3-card-2 w3-white w3-round-large">
					<div class="w3-container">
						<p>Just Forgot that I had to mention something about someone
							to someone about how I forgot something, but now I forgot it.
							Ahh, forget it! Or wait. I remember.... no I don't.</p>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>