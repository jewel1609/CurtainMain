<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resource/css/member/modifyMember.css"/>
<link rel="stylesheet" type="text/css" href="/resource/css/article/secretArticle.css" />

<script type="text/javascript">
		
	$(document).ready( function(){
		
		$(".wrapper").css("height", window.innerHeight - 130);
		
		$(window).resize(function() {
			$(".wrapper").css("height", window.innerHeight - 130);
		});
		
		$("#send").click(function(e) {
			var userNickName = $("#userNickName").val();
			$.post(
					"/duplicationCheckNickName"
					, { "userNickName" : userNickName }
					, function(data) {
						
						try{
							jsonData3 = JSON.parse(data);
						}
						catch(e) { //자바스크립트는 타입이 없기때문에 e만 적으면 된다.
						}
						
						if ( "${member.nickName}" == userNickName ) {
							alert("현재 사용중인 닉네임입니다.(사용 가능)");
							return;
						}
						if(jsonData3.isCheckId) {
							alert("이미 존재하는 닉네임입니다.");
							$("#userNickName").val("");
						}
						if(!jsonData3.isCheckId) {
							alert("사용가능한 닉네임입니다.");
						}
					  }
					);
		}); 
		
		
		
		
		$("#cancelBtn1").click(function() {
			location.href = "/myPage";
		});	
		
		$("#cancelBtn2").click(function() {
			location.href = "/myPage";
		});
		
		$("#modifyMemberBtn").click( function () {
			
			var userNickName = $("#userNickName").val();
			
			if ( confirm("수정 하시겠습니까?") == true ) {
				
				$.post(
						"/duplicationCheckNickName"
						, { "userNickName" : userNickName }
						, function(data) {
							
							try{
								jsonData3 = JSON.parse(data);
							}
							catch(e) { //자바스크립트는 타입이 없기때문에 e만 적으면 된다.
							}
							
							if(jsonData3.isCheckId) {
								alert("이미 존재하는 닉네임입니다.");
								$("#userNickName").val("");
								return;
							}
							else {
								var form = $("#modifyMember");
								alert("##안내##\n\n회원정보를 수정 했습니다..");
								form.attr("method", "post");
								form.attr("action","/modifyMemberInfo");
								form.submit();
							}
						  }
						);
			}
			else {
				return;
			}
				
		});
		
		
		$("#modifyPasswordBtn").click( function () {
			
			if ( $("#userNewPw").val().length < 6 ) {
				alert("비밀번호는 최소 6자리 입상으로 입력해 주십시오.");
				return;
			}

			if( $("#userNewPw").val() != $("#userNewRePw").val() ) {
				alert("입력한 비밀번호가 서로 같지 않습니다");
				return;
			}
			
				
			if( $("#userPw").val() == "" ){
				alert("기존 비밀번호를 입력해 주세요");
				return;
			}
			
			if ( $("#userNewPw").val() == "" || $("#userNewRePw").val() == "" ) {
				alert("비밀번호를 최소 8자리 입상으로 입력해 주십시오..");
				return;
			}
			
			if ( confirm("입력한 내용으로 비밀번호를 변경하시겠습니까?") == true ) {
				
				if ( $("#userPw").val() != "${ memberPassword }") {
					alert("기존 비밀번호가 같지 않습니다");	
					location.href = "/myPage";
					return;
				}
				
				var form = $("#modifyMemberPassword");
				form.attr("method", "post");
				form.attr("action","/modifyMemberPassword");
				form.submit();
			}
			else {
				return;
			}
		});
		
	});	
	


	
	
	

</script>


<div class="w3-container wrapper" style="margin-top:0px;">
<c:set var="companyCheck" value="${ sessionScope._COMPANY_ }" />

  <div class="w3-row" >
     <div class="w3-col m7 wrapper"
         style="border-right:1px solid #bababa; margin-left: 334px; margin-right: 100px; height: 905px; overflow: auto; background-color:#f3f3f3;">
       
         <div class="w3-row" style="border-bottom: 1px solid #bababa;">
            <div class="w3-margin-4" style="float:left; padding-left:10px;">
      		<h2>회원정보 변경</h2>
      	</div>
            <div style="float:left; padding-top:30px;">
      		<p style="padding-left:10px;">비밀번호를 찾을 수 있는 일반 이메일주소와 닉네임을 변경하실 수 있습니다.</p>
      	</div>
      </div>
      
      
      <form id="modifyMember">
      
      <c:if test="${ companyCheck eq null }">
        <div class="w3-padding" style="border-bottom:1px solid #BABABA; background-color:#F3F3F3;">
      
        <div class="w3-col m12" align="left" >
              <ul class="list-group">
			    <li class="list-group-item">          			
			    <div style="float:left; width:20%">
          				<b>대학교 이메일</b>
          			</div>
          			<div style="width:80%">
    					<p>${ member.email }</p>
    				</div></li>
			  </ul>
        </div>
      </div>
      </c:if>
      
      <c:if test="${ companyCheck ne null }">
       <div class="w3-padding" align="left">
        <div class="w3-col m12">
             <ul class="list-group">
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>기업 이메일</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ member.email }</p>
    				</div>
    			</li>
			  </ul>
        </div>
      </div>
      </c:if>
      
      
      <div class="w3-row-padding w3-margin-top" align="left">
        <div class="w3-col m12">
               <ul class="list-group">
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>일반 이메일</b>
          			</div>
          			<div style="width:80%; padding-top:5px;">
    					<input type="text" class="form-control" 
    					style="width:250px;" tabindex="1" id="userEmail" name="userEmail" placeholder="${ member.secondEmail }" /> 
    				</div>
    			</li>
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>닉네임</b>
          			</div>
          			<div style="width:80%; padding-top:5px;">
    					<input type="text" class="form-control" tabindex="2" id="userNickName" 
    					style="width:150px; float:left; margin-right:10px;" name="userNickName" placeholder="${ member.nickName }" />
						<button type="button" id="send" class="btn btn-default" tabindex="3">
							중복 체크
						</button>	
    				</div>
    			</li>  
    			<li class="list-group-item">          			
           			<div style="float:left; width:20%; padding-top:7px;">
          				<b>회원 등급</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ memberRank }</p>
    				</div>
    			</li>    			
			  </ul>      

        </div>
      </div>
 
      
      <c:if test="${ companyCheck eq null }">
      <div class="w3-row-padding w3-margin-top" align="left">
        <div class="w3-col m12">
             <ul class="list-group">
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>학교 이름</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ univName }</p>
    				</div>
    			</li>
			  </ul>
        </div>
      </div>
      </c:if>
      
      <c:if test="${ companyCheck ne null }">
      <div class="w3-row-padding w3-margin-top" align="left">
        <div class="w3-col m12">
             <ul class="list-group">
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>기업 이름</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ member.companyName }</p>
    				</div>
    			</li>
			  </ul>
        </div>
      </div>
      </c:if>
      
      <c:if test="${ companyCheck eq null }">
      <div class="w3-row-padding w3-margin-top w3-margin-bottom" align="left">
        <div class="w3-col m12">
             <ul class="list-group">
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>학과 소계열명</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ majorGroupName }</p>
    				</div>
    			</li>
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>학과 이름</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ majorName }</p>
    				</div>
    			</li>
			  </ul>        
        </div>
      </div>   
      </c:if>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom" align="left">
        <div class="w3-col m12">
              <ul class="list-group">
			    <li class="list-group-item">          			
          				<div style="float:left; width:20%; padding-top:7px;">
          				<b>회원가입 날짜</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ member.signupDate }</p>
    				</div>
    			</li>
			    <li class="list-group-item">          			
          				<div style="float:left; width:20%; padding-top:7px;">
          				<b>보유 포인트</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
    					<p>${ member.point }</p>
    				</div>
    			</li>
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>활동량</b>
          			</div>
          			<div style="width:80%; padding-top:7px;">
          				<p>게시글 수 : ${ countArticle }, 댓글 수 : ${ countReply }, 투표 참여 수 : ${ countSurvey }</p>
    				</div>
    			</li>    			
			  </ul>        

        </div>
      </div>   
      </form> 
     	
     <div class="w3-padding-bottom" align="center">	
		<div class="w3-btn-group" >
			<button type="button" id="modifyMemberBtn" class="btn btn-default" tabindex="3">
				회원 정보 변경하기
			</button>
			<button type="button" id="cancelBtn1" class="btn btn-default" tabindex="4" style="border-color:#7b7b7b;">
				<div style="margin-left:50px; margin-right:50px;">취소</div>
			</button>
		</div>
	</div>
	
	
	
	<div class="w3-row" style="border-bottom: 1px solid #bababa; border-top: 1px solid #bababa;">
      	 <div class="w3-margin-4" style="float:left; padding-left:10px;">
      		<h2>비밀번호 변경</h2>
      	</div>
      </div>
	
      <form id="modifyMemberPassword">
      <div class="w3-row-padding w3-margin-top w3-margin-bottom" align="left">
        <div class="w3-col m12">
             <ul class="list-group">
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>기존 비밀번호</b>
          			</div>
          			<div style="width:80%; padding-top:5px;">
    					<input type="password" class="form-control" style="width:150px;" tabindex="5" id="userPw" name="userPw"/>
    				</div>
    			</li>
			    <li class="list-group-item">          			
          			<div style="float:left; width:20%; padding-top:7px;">
          				<b>새로운 비밀번호</b>
          			</div>
          			<div style="width:80%; padding-top:5px;">
    					<input type="password"  class="form-control" style="width:150px; float:left; margin-right:10px;" tabindex="6" id="userNewPw" name="userNewPw"/>
          				<h6>연속번호나 생년월일 등 타인이 알기 쉬운 숫자를 피해 공백 없이 8자 이상의 영문/숫자 조합으로 입력하여 주시기 바랍니다.</h6>
    				</div>
    			</li>
			    <li class="list-group-item">          			
          		<div style="float:left; width:20%; padding-top:7px;">
          				<b>새로운 비밀번호 확인</b>
          			</div>
          			<div style="width:80%; padding-top:5px;">
    					<input type="password"  class="form-control" style="width:150px;" tabindex="7" id="userNewRePw" name="userNewRePw"/>
    				</div>
    			</li>    			
			  </ul>          

        </div>
      </div>

	  </form>	
		      
      <div class="w3-padding-bottom" align="center">	
		<div class="w3-btn-group">
			<button type="button" id="modifyPasswordBtn" class="btn btn-default " tabindex="8" >
				비밀번호 변경하기
			</button>
			<button type="button" id="cancelBtn2" class="btn btn-default" tabindex="9" style="border-color:#7b7b7b;">
				<div style="margin-left:50px; margin-right:50px;">취소</div>
			</button>
		</div>
	</div>   
      
        
    </div>
  
</div>

</div>


