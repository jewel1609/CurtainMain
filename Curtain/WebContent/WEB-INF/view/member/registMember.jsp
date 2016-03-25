<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

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

<script type="text/javascript">

	$(document).ready(function() {
		$('#registStdBtn').attr('disabled',true); // 버튼 비활성화
		$('#registCompBtn').attr('disabled',true); // 버튼 비활성화
		
		var passwordCheck = false;
		$("#inputUnivEmail").keyup(function(){
			//AJAX 준비
	         $.post(
	        		 "<c:url value="/doUnivCheck" />"//갈 서블릿url
	                  , {
	                	  "inputUnivEmail" : $("#inputUnivEmail").val()
	                	 }
	                  , function(data) { //응답내용
	                	 	 var jsonData3 = {};
		                  
		                     try {
		                        jsonData3 = JSON.parse(data);
		                     }
		                     catch(e) {
		                    	console.log(e);
		                     }
		                    console.log(jsonData3.inputUnivEmail);
		                    
	                        if(jsonData3.result){
		                    	var text = $("#inputUnivEmail").text();
		                    	if(jsonData3.isExistStdUnivEmail){
		                    		$("#stdUnivEmailCheck").text("중복된 이메일입니다.");
		                    		
		                    		$("#inputUnivEmail").focus();
		                    	}
		                    	if(!jsonData3.isExistStdUnivEmail){
		                    		$("#stdUnivEmailCheck").text("가입가능한 이메일입니다.");
		                    	}
		                  }  
	                  }
	         );
		    var text = $(this).text();
		});
		
		//기업중복되는지 AJAX
   		$("#inputCompEmail").keyup(function(){
     			//AJAX 준비
     	         $.post(
     	        		 "<c:url value="/doCompCheck" />"//갈 서블릿url
     	                  , {
     	                	  "inputCompEmail" : $("#inputCompEmail").val()
     	                	 }
     	                  , function(data) { //응답내용
     	                	 	 var jsonData3 = {};
     		                  
     		                     try {
     		                        jsonData3 = JSON.parse(data);
     		                     }
     		                     catch(e) {
     		                    	console.log(e);
     		                     }
     		                    console.log(jsonData3.inputCompEmail);
     		                    
     	                        if(jsonData3.result){
     		                    	var text = $("#inputCompEmail").text();
     		                    	if(jsonData3.isExistCompEmail){
     		                    		$("#compEmailCheck").text("중복된 이메일입니다.");
     		                    		$("#inputCompEmail").focus();
     		                    	}
     		                    	if(!jsonData3.isExistCompEmail){
     		                    		$("#compEmailCheck").text("가입가능한 이메일입니다.");
     		                    	}
     		                  }  
     	                  }
     	         );
     		    var text = $(this).text();
     		});
		
   		
		//대학 인증번호 보내기버튼 클릭시,
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
		                    		alert("메일전송이 실패하였습니다. 다시 전송해주세요.");
		                    	}
		                  } 
	                  }
	         );
	         var text = $(this).text();
	      });
		
		
   		// 회사 인증번호 보내기버튼 클릭시,
   		$("#compAuthNumSendBtn").click(function(){
   			if( $("#inputCompEmail").val() == ""){
   				alert("학교 이메일을 입력하세요");
   				return; // 더이상 밑의 이벤트를 진행하지 않음.
   				} 
   			//AJAX 준비
   	         $.post(
   	                  "<c:url value="/compAuthNumSend" />" //갈 서블릿url
   	                  , { "inputCompEmail" : $("#inputCompEmail").val() }
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
   		                    	var text = $("#inputCompEmail").text();
   		                    	if(jsonData3.isSendCheck){
   		                    		alert("메일전송이 성공하였습니다.");
   		                    	}
   		                    	if(!jsonData3.isSendCheck){
   		                    		$("#inputCompEmail").focus();
   		                    		alert("메일전송이 실패하였습니다. 다시 전송해주세요.");
   		                    	}
   		                  } 
   	                  }
   	         );
   	         var text = $(this).text();
   	      });
		                    		
		                    		
		// 대학 인증확인버튼 클릭시,
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
	                    		$('#registStdBtn').attr('disabled',false); // 버튼 활성화
	                    		
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
		
  		// 기업 인증확인버튼 클릭시,
  		$("#compAuthNumCheckBtn").click(function(){
  			if( $("#inputCompNumberCheck").val() == ""){
  				alert("인증번호를 입력하세요");
  				return; // 더이상 밑의 이벤트를 진행하지 않음.
  				} 
  			//AJAX 준비
  	         $.post(
  	        		 "<c:url value="/compAuthNumCheck" />" //갈 서블릿url
  	                  , {
  	                	  "inputCompEmail" : $("#inputCompEmail").val()  
  	                	  , "inputCompNumberCheck" : $("#inputCompNumberCheck").val() 
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
  	                    	var text = $("#inputCompNumberCheck").text();
  	                    	if(jsonData3.isCompEmail){
	                    		$('#registCompBtn').attr('disabled',false); // 버튼 활성화
  	                    		alert("인증되었습니다.");
  	                    	}
  	                    	if(!jsonData3.isCompEmail){
  	                    		$("#inputCompNumberCheck").focus();
  	                    		alert("인증이 실패되었습니다.");
  	                    	}
  	                     } 
  	                  }
  	         );
  	         var text = $(this).text();
  		});		
		
		// 대학 패스워드 확인
		$("#inputPasswordCheck").keyup(function(){
			if ( $("#inputPasswordCheck").val() == ""){
				 $("#PasswordCheckSpan").text("");
				 return;
			}
			
			if($("#inputPassword").val() == $("#inputPasswordCheck").val()){
				 $("#passwordCheckSpan").text("일치 합니다.");
				 passwordCheck = true;
				 
			}
			
			if($("#inputPassword").val() != $("#inputPasswordCheck").val()){
				 
				 $("#passwordCheckSpan").text("불일치 합니다.");
				 $("#inputPasswordCheck").focus();
			}
			
		});

		// 기업 패스워드 확인
		$("#inputCompPasswordCheck").keyup(function(){
			
			if ( $("#inputCompPasswordCheck").val() == ""){
				 $("#compPasswordCheckSpan").text("");
				 return;
			}
			if($("#inputCompPassword").val() == $("#inputCompPasswordCheck").val()){
				 $("#compPasswordCheckSpan").text("일치 합니다.");
				 passwordCheck = true;
				 
			}
			if($("#inputCompPassword").val() != $("#inputCompPasswordCheck").val()){
				 $("#compPasswordCheckSpan").text("불일치 합니다.");
				 $("#inputCompPasswordCheck").focus();
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
		
		// 학생 회원가입 버튼 누를시,
		$("#registStdBtn").click(function(){
			 // 정규식 - 이메일 유효성 검사
	        var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	        var regPassword =/^(?=\w{8,}$)(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).*/;
			// 유효성 검사 혹은 Validation Check
			 if( $("#inputUnivEmail").val() == ""){
				alert("학교 이메일을 입력하세요!");
				$("#inputUnivEmail").focus();
				return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			
			 if(!regEmail.test($("#inputUnivEmail").val())) {
	                alert('이메일 주소가 유효하지 않습니다');
	                $("#inputUnivEmail").focus();
	                return false;
	            }
			 
			 if( $("#inputNumberCheck").val() == ""){
					alert("인증번호를 입력하세요!");
					$("#inputNumberCheck").focus();
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputPassword").val() == ""){
					alert("비밀번호를 입력하세요!");
					 $("#inputPassword").focus();
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			
			 if(!regPassword.test($("#inputCompPassword"))){
				 alert("비밀번호 형식을 맞춰주세요!");
			 }
			 if( $("#inputPasswordCheck").val() == ""){
					alert("재확인 비밀번호를 입력하세요!");
					$("#inputPasswordCheck").focus();
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputUnivName").val() == ""){
					alert("대학교를 입력하세요!");
					$("#inputUnivName").focus();
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputMajorName").val() == ""){
					alert("학과를 입력하세요!");
					$("#inputMajorName").focus();
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			 if( $("#inputSecondEmail").val() == ""){
					alert("보조이메일을 입력하세요.");
					$("#inputSecondEmail").focus();
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			 if(!regEmail.test($("#inputSecondEmail").val())) {
	                alert('이메일 주소가 유효하지 않습니다');
	                $("#inputSecondEmail").focus();
	                return false;
	            }
			 
			 if($("#agree").prop('checked') == false){
				 	alert("이용약관을 체크해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 	}
			 if($("#secondAgree").prop('checked') == false){
				 	alert("이용약관을 체크해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 	}
			 if(passwordCheck == false){
				 alert("두개의 비밀번호가 다릅니다. 확인해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 }
			 
			var form = $("#registStdForm");
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doRegistStdMember" />");
			form.submit();
		
		});
		
		////////// 기업///////////
		// 기업 회원가입 버튼 누를시,
		$("#registCompBtn").click(function(){
			 var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			 // 정규식 -전화번호 유효성 검사
	         var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
			 var regPassword =/^(?=\w{8,}$)(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).*/;
			// 유효성 검사 혹은 Validation Check
			 if( $("#inputCompEmail").val() == ""){
				alert("회사 이메일을 입력하세요!");
				return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if(!regEmail.test($("#inputCompEmail").val())) {
	                alert('이메일 주소가 유효하지 않습니다');
	                $("#inputCompEmail").focus();
	                return false;
	            }
			 if( $("#inputCompNumberCheck").val() == ""){
					alert("인증번호를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputCompPassword").val() == ""){
					alert("비밀번호를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if(!regPassword.test($("#inputCompPassword"))){
				 alert("비밀번호 형식을 맞춰주세요!");
			 }
				 
			 if( $("#inputCompPasswordCheck").val() == ""){
					alert("재확인 비밀번호를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputCompName").val() == ""){
					alert("회사명을 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 if( $("#inputPhoneNum").val() == ""){
					alert("휴대폰번호를 입력하세요!");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			 if(!regPhone.test($("#inputPhoneNum").val())) {
	                alert('핸드폰 번호가 유효하지 않습니다');
	                $("#inputPhoneNum").focus();
	                return false;
	            }
			 
			 if( $("#inputCompSecondEmail").val() == ""){
					alert("보조이메일을 입력하세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			 if(!regEmail.test($("#inputCompSecondEmail").val())) {
	                alert('이메일 주소가 유효하지 않습니다');
	                $("#inputCompSecondEmail").focus();
	                return false;
	            }
			 
			 if($("#compAgree").prop('checked') == false){
				 	alert("이용약관을 체크해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 	}
			 if($("#compSecondAgree").prop('checked') == false){
				 	alert("이용약관을 체크해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 	}
			 if(passwordCheck == false){
				 alert("두개의 비밀번호가 다릅니다. 확인해주세요.");
					return; // 더이상 밑의 이벤트를 진행하지 않음.
			 }
			 
			var form = $("#registCompForm");
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doRegistCompMember" />");
			form.submit();
		
		});
		
		$("#cancleBtn").click(function(){
			window.history.back();
		});
		$("#cancleCompBtn").click(function(){
			window.history.back();
		});
		
		function w3_open() {
			document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
			document.getElementsByClassName("w3-overlay")[0].style.display = "block";
		}
		function w3_close() {
			document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
			document.getElementsByClassName("w3-overlay")[0].style.display = "none";
		}
	


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
		
		$(function(){
		    $('ul.nav-tabs a').click(function (e) {
		      e.preventDefault()
		      $(this).tab('show')
		    })
		});
	});

</script>

	<div style="width:2000px; overflow-y : auto;"> 
	<div class="w3-container w3-center w3-main"
		style="margin-top: 30px; margin-bottom: 20px;">
		
		
		<div style="width: 100%;">
			<ul class="nav nav-tabs" style="display: inline-block; margin-bottom: -8px;">
				<li class="active" data-toggle="tab" style="width: 50%;"><a href="#inputMemberInfo">개인정보 입력</a></li>
				<li data-toggle="tab" style="width: 50%;"><a href="#inputCompanyInfo">기업회원 입력</a></li>
			</ul>
		</div>

		<article class="tab-content container w3-border">
			
			<div role="tabpanel" id="inputMemberInfo" class="col-md-12 tab-pane fade active in">
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
								<input class="form-control" id="inputUnivEmail" name="inputUnivEmail" type="text" 
								placeholder="학교 이메일주소를 입력하세요. 일반이메일은 인증메일이 전송되지않습니다."/>
									<span class="input-group-btn">
										<input type="button" id="authNumSendBtn" class="btn btn-success" value="인증번호 전송">
									</span>
							</div>
							<br/>
							<span style="margin: 0 auto" class="col-sm-6" id="stdUnivEmailCheck"></span>
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
								<br/>
							<span style="margin: 0 auto" class="col-sm-6" id="passwordCheckSpan"></span>
						</div>
					</div>
						
					<hr>
					
					<!-- 대학교 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputUniv">대학교</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputUnivName" name="inputUnivName" type="text"
								placeholder="대학교를 검색하세요">
							<span class="col-sm-6" id="univSuggest"></span>
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
							개인화면 약관에 동의 <button class="btn btn-default" data-toggle="modal" data-target="#modal">
												  내용보기
											  </button>
								<input class="w3-check" id="agree" name="agree" type="checkbox" autocomplete="off">
						</div>
						
						<div class="col-sm-6" data-toggle="buttons">
							개인정보 수정 및 이용에 동의 <button class="btn btn-default" data-toggle="modal" data-target="#modal2">
														  내용보기
													  </button>
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
		
			<!-- 기업 -->
			<div role="tabpanel" id="inputCompanyInfo" class="tab-pane fade col-md-12">
				<div class="page-header">
					<h1>
						기업정보 입력
					</h1>
				</div>
			
				<form id=registCompForm class="form-horizontal">
				
					<!-- 회사 이메일 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputCompEmail">회사 이메일</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input class="form-control" id="inputCompEmail" name="inputCompEmail" type="text" 
									placeholder="회사 이메일주소를 입력하세요. 일반이메일은 인증메일이 전송되지않습니다."/>
									<span class="input-group-btn">
										<input type="button" id="compAuthNumSendBtn" class="btn btn-success" value="인증번호 전송">
									</span>
							</div>
							<br/>
							<span style="margin: 0 auto" class="col-sm-6" id="compEmailCheck"></span>
						</div>
					</div>
					
					<!-- 인증번호확인 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputNumberCheck">인증번호 확인</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input class="form-control" id="inputCompNumberCheck" name="inputCompNumberCheck" type="text" 
								placeholder="전송된 인증번호를 입력해주세요."> 
								<span class="input-group-btn">
									<input type="button" id="compAuthNumCheckBtn" class="btn btn-success" value="인증번호 확인">
								</span>
							</div>
						</div>
					</div>
					
					<!-- 비밀번호 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPassword">비밀번호</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputCompPassword" name="inputCompPassword" type="password"
								placeholder="8-20자리 영문 대소문자, 숫자를 혼합하여 사용.">
						</div>
						
						<hr>
					</div>
					
					<!-- 비밀번호 재확인 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호 재확인</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputCompPasswordCheck" name="inputCompPasswordCheck"
								type="password" placeholder="비밀번호 재확인">
								<br/>
							<span style="margin: 0 auto" class="col-sm-6" id="compPasswordCheckSpan"></span>
						</div>
					</div>
					<hr>
					
					<!-- 회사명 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputComp">회사명</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputCompName" name="inputCompName" type="text"
								placeholder="회사명을 입력하세요.">
						</div>
					</div>
					
					<!-- 개인 휴대폰 번호 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputPhoneNum">개인 휴대폰 번호</label>
						<div class="col-sm-6">
							<input class="form-control" id="inputPhoneNum" name="inputPhoneNum" type="text"
								placeholder="개인 휴대폰 번호를 입력하세요">
						</div>
					</div>
					<hr>
					
					<!-- 보조 이메일 -->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputSecondEmail">보조 이메일</label>
						<div class="col-sm-6">
								<input class="form-control" id="inputCompSecondEmail" name="inputCompSecondEmail" type="email" 
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
							개인화면 약관에 동의 <button class="btn btn-default" data-toggle="modal" data-target="#modal">
												  내용보기
											  </button>
								<input class="w3-check" id="compAgree" name="compAgree" type="checkbox" autocomplete="off">
						</div>
						
						<div class="col-sm-6" data-toggle="buttons">
							개인정보 수정 및 이용에 동의 <button class="btn btn-default" data-toggle="modal" data-target="#modal2">
														  내용보기
													  </button>
								<input class="w3-check" id="compSecondAgree" name="compSecondAgree" type="checkbox" autocomplete="off">
						</div>
					</div>
					<br>
					<!-- 가입하기 버튼 -->
					<div class="form-group">
						<div class="col-sm-12 text-center">
							<input type="button" id="registCompBtn" class="btn btn-primary" value="가입하기">
							<input type="button" id="cancleCompBtn" class="btn btn-danger" value="가입취소">
						</div>
					</div>
				</form>
				<hr>
				
			</div>
		</article>

	</div>
	</div>
	
	<!-- 개인화면 약관 Modal -->
	<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal-lg" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">개인화면 약관</h4>
	      </div>
	      <div class="modal-body" style="overflow-y: auto; height: 250px">
	    	개인화면 약관 내용
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- 개인정보 수정 및 이용 Modal2 -->
	<div class="modal fade" id="modal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal-lg" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">개인정보 수정 및 이용</h4>
	      </div>
	      <div class="modal-body" style="overflow-y: auto; height: 250px">
	      개인정보 수정 및 이용 내용
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>

