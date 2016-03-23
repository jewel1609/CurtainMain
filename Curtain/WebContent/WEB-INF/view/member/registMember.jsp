<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="http://www.w3schools.com/lib/w3-theme-teal.css">
<link href='https://fonts.googleapis.com/css?family=RobotoDraft'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "RobotoDraft", "Roboto", sans-serif;
}

.w3-sidenav a {
	padding: 16px;
	font-weight: bold
}

.univList{
	cursor: pointer;
}

.majorList{
	cursor: pointer;
}
</style>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery-1.12.1.js"></script>
<script type="text/javascript" src="/resource/js/json2.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		//인증번호 보내기버튼 클릭시,
		$("#authNumSendBtn").click(function(){
			if( $("#inputUnivEmail").val() == ""){
				alert("학교 이메일을 입력하세요");
				return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			//AJAX 준비
	         $.post(
	                  "<c:url value="/authNumSend" />" //갈 서블릿url
	                  , { "inputUnivEmail" : $("#inputUnivEmail").val() }
	                  , function(data) { //응답내용
	                	  
	                     var jsonData3 = {};
	                  
	                     try {
	                        jsonData3 = JSON.parse(data);
	                     }
	                     catch(e) {
	                    	console.log(e);
	                        jsonData3.result = false;
	                     }
	                  
	                     console.log(jsonData3);
	                     if(jsonData3.result){
		                    	var text = $("#inputUnivEmail").text();
		                    	if(jsonData3.isSendCheck){
		                    		alert("메일전송이 성공하였습니다.");
		                    	}
		                    	if(!jsonData3.isSendCheck){
		                    		$("#inputUnivEmail").focus();
		                    		alert("메일전송이 실패하였습니다.");
		                    	}
		                     } 
	                  }
	         );
	         var text = $(this).text();
	      });
		
		//인증확인버튼 클릭시,
		$("#authNumCheckBtn").click(function(){
			if( $("#inputNumberCheck").val() == ""){
				alert("인증번호를 입력하세요");
				return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			//AJAX 준비
	         $.post(
	        		 "<c:url value="/authNumCheck" />" //갈 서블릿url
	                  , {
	                	  "inputUnivEmail" : $("#inputUnivEmail").val()  
	                	  , "inputNumberCheck" : $("#inputNumberCheck").val() 
	                	 }
	                  , function(data) { //응답내용
	                	  
	                     var jsonData3 = {};
	                  
	                     try {
	                        jsonData3 = JSON.parse(data);
	                     }
	                     catch(e) {
	                    	console.log(e);
	                        jsonData3.result = false;
	                     }
	                  
	                     console.log(jsonData3);
	                     if(jsonData3.result){
	                    	var text = $("#inputNumberCheck").text();
	                    	if(jsonData3.isUnivEmail){
	                    		alert("인증되었습니다.");
	                    	}
	                    	if(!jsonData3.isUnivEmail){
	                    		$("#inputNumberCheck").focus();
	                    		alert("인증이 실패되었습니다.");
	                    	}
	                     } 
	                  }
	         );
	         var text = $(this).text();
		});
		
		//패스워드 확인
		$("#inputPasswordCheck").keyup(function(){
			
			if ( $("#inputPasswordCheck").val() == ""){
				 $("#passwordCheckSpan").text("");
				 return;
			}
			if($("#inputPassword").val() == $("#inputPasswordCheck").val()){
				 $("#passwordCheckSpan").text("재확인 비밀번호가 같습니다.");
			}
			if($("#inputPassword").val() != $("#inputPasswordCheck").val()){
				 $("#passwordCheckSpan").text("재확인 비밀번호가 다릅니다.");
				 $("#inputPasswordCheck").focus();
			}
			
		});
		
		//대학이름 검색
		$("#inputUnivName").keyup(function(){
			//AJAX 준비
	         $.post(
	        		 "<c:url value="/doUnivSearch" />"//갈 서블릿url
	                  , {
	                	  "inputUnivName" : $("#inputUnivName").val()
	                	 }
	                  , function(data) { //응답내용
	                	 	 var jsonData3 = {};
		                  
		                     try {
		                        jsonData3 = JSON.parse(data);
		                     }
		                     catch(e) {
		                    	console.log(e);
		                     }
		                    console.log(jsonData3.checkUnivName);
		                    
		                    jsonData3.checkUnivName = $.trim(jsonData3.checkUnivName);
		                    
		                    var result = jsonData3.checkUnivName.split(',');
		        			var univList = "";
		        			for(var i = 0; i<result.length; i++){
		        				univList += "<div class=\"univList\">" + result[i] +"</div>";
		        			}
		        			$("#univSuggest").html(univList).show();
	                  }
	         );
		});
		
		//새로HTML이 추가되면 해줘야하는것 
		$("body").on("click", ".univList",function(){
			alert($(this).text());
			$("#inputUnivName").val($(this).text());
			$("#univSuggest").hide("slow");
		});
		
		//과 이름 검색
		$("#inputMajorName").keyup(function(){
			//AJAX 준비
	         $.post(
	        		 "<c:url value="/doMajorSearch" />" //갈 서블릿url
	                  , {
	                	  "inputMajorName" : $("#inputMajorName").val()
	                	 }
	                  , function(data) { //응답내용
	                	 	 var jsonData3 = {};
		                  
		                     try {
		                        jsonData3 = JSON.parse(data);
		                     }
		                     catch(e) {
		                    	console.log(e);
		                     }
		                    console.log(jsonData3.checkMajorNameList);
		                    
		                    jsonData3.checkMajorNameList = $.trim(jsonData3.checkMajorNameList);
		                    
		                    var result = jsonData3.checkMajorNameList.split(',');
		        			var univList = "";
		        			for(var i = 0; i<result.length; i++){
		        				univList += "<div class=\"majorList\">" + result[i] +"</div>";
		        			}
		        			$("#majorSuggest").html(univList).show();
	                  }
	         );
		});
		
		//$("#inputMajorName").keyup 에서 새로HTML이 추가되기 때문에
		//on으로 이어주기 위해 해줘야하는것 
		$("body").on("click", ".majorList",function(){
			$("#inputMajorName").val($(this).text());
			$("#majorSuggest").hide("slow");
		});
		
		//회원가입 버튼 누를시,
		$("#registStdBtn").click(function(){
			
			// 유효성 검사 혹은 Validation Check
			 if( $("#inputUnivEmail").val() == ""){
				alert("학교 이메일을 입력하세요!");
				return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputNumberCheck").val() == ""){
					alert("인증번호를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputPassword").val() == ""){
					alert("비밀번호를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputPasswordCheck").val() == ""){
					alert("재확인 비밀번호를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputUnivName").val() == ""){
					alert("대학교를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputMajorName").val() == ""){
					alert("학과를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputSecondEmail").val() == ""){
					alert("보조이메일을 입력하세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if($("#agree").prop('checked') == false){
				 	alert("이용약관을 체크해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 	}
			 if($("#secondAgree").prop('checked') == false){
				 	alert("이용약관을 체크해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 	}
			 
			var form = $("#registStdForm");
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doRegistStdMember" />");
			form.submit();
		
		});
		
		$("#cancleBtn").click(function(){
			window.history.back();
		});
		
	});

</script>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">

		<article class="container">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						개인정보 입력
					</h1>
				</div>
			
				<form id=registStdForm class="form-horizontal">
				
					<!-- 대학교 이메일 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputUnivEmail">대학교 이메일</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input class="form-control" id="inputUnivEmail" name="inputUnivEmail" type="email" 
								placeholder="학교 이메일주소를 입력하세요. 일반이메일은 인증메일이 전송되지않습니다."/>
									<span class="input-group-btn">
										<input type="button" id="authNumSendBtn" class="btn btn-success" value="인증번호 전송">
									</span>
							</div>
						</div>
					</div>
					
					<!-- 인증번호확인 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputNumberCheck">인증번호 확인</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input class="form-control" id="inputNumberCheck" name="inputNumberCheck" type="text" 
								placeholder="전송된 인증번호를 입력해주세요."> 
								<span class="input-group-btn">
									<input type="button" id="authNumCheckBtn" class="btn btn-success" value="인증번호 확인">
								</span>
							</div>
						</div>
					</div>
					
					<!-- 비밀번호 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPassword">비밀번호</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputPassword" name="inputPassword" type="password"
								placeholder="8-20자리 영문 대소문자, 숫자를 혼합하여 사용.">
						</div>
					</div>
					
					<!-- 비밀번호 재확인 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호 재확인</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputPasswordCheck" name="inputPasswordCheck"
								type="password" placeholder="비밀번호 재확인">
						</div>
						<span id="passwordCheckSpan"></span>
					</div>
					<hr>
					
					<!-- 대학교 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputUniv">대학교</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputUnivName" name="inputUnivName" type="text"
								placeholder="대학교를 검색하세요">
							<div class="col-sm-6" id="univSuggest"></div>
						</div>
					</div>
					
					<!-- 학과 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputMajor">학과</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputMajorName" name="inputMajorName" type="text"
								placeholder="학과를 검색하세요.">
							<div class="col-sm-6" id="majorSuggest"></div>
						</div>
					</div>
					<hr>
					
					<!-- 보조 이메일 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputSecondEmail">보조 이메일</label>
						<div class="col-sm-6">
								<input class="form-control" id="inputSecondEmail" name="inputSecondEmail" type="email" 
								placeholder="비밀번호 분실 시 활용됩니다.">
						</div>
					</div>
					<br/>
					
					<!-- 약관 동의 -->
					<h1>
						약관 동의
					</h1><hr>
					<div class="form-group">
						<div class="col-sm-6" data-toggle="buttons">
							개인화면 약관에 동의 <a href="#">내용보기</a>
								<input class="w3-check" id="agree" name="agree" type="checkbox" autocomplete="off">
						</div>
						
						<div class="col-sm-6" data-toggle="buttons">
							개인정보 수정 및 이용에 동의 <a href="#">내용보기</a>
								<input class="w3-check" id="secondAgree" name="secondAgree" type="checkbox" autocomplete="off">
						</div>
					</div>
					<br>
					<!-- 가입하기 버튼 -->
					<div class="form-group">
						<div class="col-sm-12 text-center">
							<input type="button" id="registStdBtn" class="btn btn-primary" value="가입하기">
							<input type="button" id="cancleBtn" class="btn btn-danger" value="가입취소">
						</div>
					</div>
				</form>
				<hr>
				
			</div>
		</article>

	</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
	<script>
		function w3_open() {
			document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
			document.getElementsByClassName("w3-overlay")[0].style.display = "block";
		}
		function w3_close() {
			document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
			document.getElementsByClassName("w3-overlay")[0].style.display = "none";
		}
	</script>

	<script>
		window.onscroll = function() {
			myFunction()
		};

		function myFunction() {
			if (document.body.scrollTop > 80
					|| document.documentElement.scrollTop > 80) {
				document.getElementById("myTop").classList.add("w3-card-4");
			} else {
				document.getElementById("myTop").classList.remove("w3-card-4");
			}
		}

		function myAccordion(id) {
			document.getElementById(id).classList.toggle("w3-show");
			document.getElementById(id).previousElementSibling.classList
					.toggle("w3-theme");
		}
	</script>

</html>
