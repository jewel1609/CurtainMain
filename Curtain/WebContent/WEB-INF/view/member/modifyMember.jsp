<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resource/css/member/modifyMember.css"/>

<script type="text/javascript">
		
	$(document).ready( function(){
		
		$("#cancelBtn1").click(function() {
			location.href = "/myPage";
		})	
		
		$("#cancelBtn2").click(function() {
			location.href = "/myPage";
		})
		
		$("#modifyMemberBtn").click( function () {
			
			if ( confirm("수정 하시겠습니까?") == true ) {
				var form = $("#modifyMember");
				form.attr("method", "post");
				form.attr("action","/modifyMemberInfo");
				form.submit();
			}
			else {
				return;
			}
				
		});
		
		$("#modifyPasswordBtn").click( function () {
			
			if ( confirm("입력한 내용으로 비밀번호를 변경하시겠습니까?") == true ) {
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


<div class="w3-container w3-center w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px; margin-right: 100px; height: 750px; overflow: auto;">
      
      <div class="w3-myfont w3-left-align">
      	<div class="w3-col" style="width:30%">
      		<h1>회원정보 변경</h1>
      	</div>
      	<div class="w3-col" style="width:70%; margin-top:35px;">
      		<p>닉네임과 이메일 주소는 변경 시 꼭 회원  정보를 변경하여 주세요!</p>
      	</div>
      </div>
      <form id="modifyMember">
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
            <div class="w3-container w3-padding w3-left-align">
          		<div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>대학교 이메일</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>jewel1609@smu.ac.kr</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
            	<div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>일반 이메일</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<input type="text" tabindex="0" id="userEmail" name="userEmail" placeholder="jewel1609@nate.com" />
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
            	<div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>닉네임</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<input type="text" tabindex="1" id="userNickName" name="userNickName" placeholder="갓피스" />
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>회원 등급</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>한겹 커튼</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>학교 이름</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>00 대학교</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>학과 소계열명</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>ICT 융합</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>학과 이름</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>미디어소프트웨어</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>   
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>회원가입 날짜</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>2016/03/22</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>   
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>보유 포인트</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>000 point</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>   
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>활동량</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>000</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>  
      </form> 
     	
     <div class="w3-center-align">	
		<div class="w3-btn-group">
			<button type="button" id="modifyMemberBtn" class="btn btn-primary" tabindex="2" style="background-color:#FF3300; border:0px;">
				회원 정보 변경하기
			</button>
			<button type="button" id="cancelBtn1" class="btn btn-primary" tabindex="3" style="background-color:#c0c0c0; border:0px;">
				<div style="margin-left:50px; margin-right:50px;">취소</div>
			</button>
		</div>
	</div>
	
	<div class="w3-myfont w3-left-align">
      	<div class="w3-col" style="width:30%">
      		<h1>비밀번호 변경</h1>
      	</div>
      	<div class="w3-col" style="width:90%; margin-left:20px; margin-bottom:20px;">
      		<h>비밀번호는 연속번호나 생년월일 등 타인이 알기 쉬운 숫자를 피해 공백 없이 8자 이상의 영문/숫자 조합으로 입력하여 주시기 바랍니다.</h>
      	</div>
      </div>
	
      <form id="modifyMemberPassword">
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>기존 비밀 번호</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<input type="password"  tabindex="4" id="userPw" name="userPw"/>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>새로운 비밀번호</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<input type="password"  tabindex="5" id="userNewPw" name="userPw"/>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>새로운 비밀번호 확인</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<input type="password"  tabindex="6" id="userNewRePw" name="userPw"/>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
	  </form>	
		      
      <div class="w3-center-align">	
		<div class="w3-btn-group">
			<button type="button" id="modifyPasswordBtn" class="btn btn-primary " tabindex="7" style="background-color:#FF3300; border:0px;">
				비밀번호 변경하기
			</button>
			<button type="button" id="cancelBtn2" class="btn btn-primary" tabindex="8" style="background-color:#c0c0c0; border:0px;">
				<div style="margin-left:50px; margin-right:50px;">취소</div>
			</button>
		</div>
	</div>   
      
        
    </div>
  
</div>

</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>