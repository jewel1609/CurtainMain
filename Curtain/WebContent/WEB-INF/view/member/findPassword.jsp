<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="/resource/css/member/modifyMember.css"/>
<script type="text/javascript">

	$(document).ready(function() {
		
		$(".wrapper").css("height", window.innerHeight - 130);
		
		$(window).resize(function() {
			$(".wrapper").css("height", window.innerHeight - 130);
		});
		
		//비밀번호 전송버튼 누를시
		$("#sendPasswordBtn").click(function(){
			 
			 if( $("#email").val() == ""){
				alert("이메일을 입력 해주세요");
				return;
				// 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			 if( $("#secondEmail").val() == ""){
				alert("보조 이메일을 입력해주세요.");
				return; // 더이상 밑의 이벤트를 진행하지 않음.
				} 
			 
			var form = $("#sendPasswordForm");
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doSendPassword" />");
			form.submit();
		
		});
		
		$("#cancleBtn").click(function(){
			window.history.back();
		});
		
	});

</script>

<div class="w3-container w3-center" style="margin-top:30px; margin-bottom:20px;">    

  	<div class="w3-row" >
		<div class="w3-col m7 wrapper" style="margin-left:350px; margin-right: 100px; margin-right: 100px; overflow: auto;">
			<form id="sendPasswordForm">
				<div class="w3-row-padding w3-margin-top w3-margin-bottom">
				        <div class="w3-col m12">
				          <div class="w3-card w3-white w3-round-large">
				            <div class="w3-container w3-padding w3-left-align">
				              <div class="w3-col" style="width:90%">
				          			<div class="w3-col" style="width:20%">
				          				<b>이메일 입력</b>
				          			</div>
				          			<div class="w3-col" style="width:80%">
				    					<input class="form-control" type="text" id="email" name="email" placeholder="가입시 입력한 이메일을 입력해주세요."/>
				    				</div>
				          		</div>
				            </div>
				          </div>
				        </div>
				     
				 </div>
				<div class="w3-row-padding w3-margin-top w3-margin-bottom">
			        <div class="w3-col m12">
			          <div class="w3-card w3-white w3-round-large">
			            <div class="w3-container w3-padding w3-left-align">
			              <div class="w3-col" style="width:90%">
			          			<div class="w3-col" style="width:20%">
				          				<b>보조이메일 입력</b>
				          		</div>
				          		<div class="w3-col" style="width:80%">
				    					<input class="form-control" type="text"  id="secondEmail" name="secondEmail" placeholder="가입시 입력한 보조이메일을 입력해주세요."/>
				    			</div>
			          		</div>
			            </div>
			          </div>
			        </div>
			      </div>	
			      <div class="w3-center-align">	
			      	<div class="w3-col"> 보조 이메일로 임시 비밀번호가 전송됩니다.</div>
			      </div>
			 </form>
			 <br/>	      
		      <div class="w3-center-align">	
				<div class="w3-btn-group">
					<input type="button" id="sendPasswordBtn" class="btn btn-primary" value="비밀번호 전송" style="background-color:#FF3300; border:0px;"/>
					<a href="/main.jsp"><input type="button" id="cancelBtn" class="btn btn-primary" value="취소" style="background-color:#c0c0c0; border:0px;"/></a>
				</div>
			  </div>   
		</div>
	</div>

</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>