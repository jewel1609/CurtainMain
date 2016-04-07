<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	
	$(document).ready( function(){
		
		$(".wrapper").css("height", window.innerHeight - 130);
		
		$(window).resize(function() {
			$(".wrapper").css("height", window.innerHeight - 130);
		});
	
		$("#secedeBtn").click(function() {
			
			if ( $("#secedeDesc").val() == "" ) {
				alert("탈퇴사유를 적어주세요. 서비스 향상을 위해 노력하겠습니다.");
			}	
			else if ( confirm("사용가능한 포인트가 ${ member.point } 남아있습니다. 탈퇴하시겠습니까?") == true ) {
				
				var form = $("#doSecedeMemberForm");
				alert("##안내##\n\n탈퇴에 성공하셨습니다.");
				form.attr("method", "post");
				form.attr("action","/doSecedeMember");
				form.submit();
			}
			
			
		});
			
		
		$("#cancelSecedeBtn").click(function() {
			
			location.href = "/myPage";
			
		});
		
		
		
		
	});
		
	

</script>
<div class="w3-container w3-center" style="margin-top:0px;">    
  <div class="w3-row" >
    <div class="w3-col m7 wrapper" style="border-right:1px solid #bababa;
    background-color: #F3F3F3; margin-left:334px; margin-right: 100px; height: 885px; overflow: auto;">
      
<form id="doSecedeMemberForm" >
      <div class="w3-myfont w3-left-align" >
      	<div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa;">
	      	<div class="w3-margin-4" style="float:left;">
	      		<h2>회원 탈퇴</h2>
			   <input type="hidden" id="memberEmail" name="memberEmail" value="${ member.email }" />
	      	</div>
      	</div>
      </div>
   
      <div class="w3-myfont w3-center-align" >
      	<div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa;">
	      	<div class="w3-margin-4" >
	      		<p>고객님께서 회원 탈퇴를 결정 하신 것은 저희 커튼의 서비스에 대해 부족한 점을 느끼셨기 때문일 것입니다.</p> 
				<p>고객님께서 느끼신 불편 사항이나 아쉬웠던 점을 알려주시면 고객님의 불편을 해결하고 더 좋은 모습으로 발전하도록 노력하겠습니다.</p> 
				<p>불편을 드린 점 진심으로 사과 드리며, 커튼은 앞으로 더 좋은 서비스로 다가갈 수 있도록 노력하겠습니다.</p> 
				<b> 그 동안 커튼을 이용해주셔서 감사합니다.</b> 
	      	</div>
      	</div>
      </div>
      
      <br/>
      <div class="w3-myfont w3-left-align" >
      <p>회원 탈퇴 후  포인트는 자동 소멸되며 등급 혜택도 소멸됩니다. 탈퇴 고객님의 재가입은 탈퇴 1개월 후 가능합니다.</p>
      </div>
      
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card w3-white w3-round-large" style="margin-top:10px; margin-bottom:10px;">
            <div class="w3-container w3-padding w3-center-align">
            	<div class="w3-container w3-third">
    				<h3>회원 등급</h3>
    				<c:if test="${ member.memberTypeId eq 1 }">
    				<img src="/resource/img/window.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px">
    				</c:if>
    				<c:if test="${ member.memberTypeId eq 2 }">
    				<img src="/resource/img/oneLayerCurtain.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px">
    				</c:if>
    				<c:if test="${ member.memberTypeId eq 3 }">
    				<img src="/resource/img/twoLayerCurtain.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px">
    				</c:if>
    				<c:if test="${ member.memberTypeId eq 4 }">
    				<img src="/resource/img/company.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px">
    				</c:if>
    				<c:if test="${ member.memberTypeId eq 5 }">
    				<img src="/resource/img/company2.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px">
    				</c:if>
    				<p>회원님의 등급은 ' ${ memberRank } '입니다.</p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h3>포인트</h3>
    				<img src="/resource/img/point.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px;">
    				<p style="position:relative; top:10px;">${ member.point } 포인트</p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h3>활동량</h3>
    				<div class="w3-container w3-third" style="padding: 0px; margin: 0px;">
    					<div>
	    					<img src="/resource/img/article.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px;">
    					</div>
    					<p>게시글</p>
    					<p>${ countArticle } 개</p>
    				</div>
    				<div class="w3-container w3-third" style="padding: 0px; margin: 0px;">
    					<div>
	    					<img src="/resource/img/reply.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px;">
    					</div>
    					<p>댓글</p>
    					<p>${ countReply } 개</p>
    				</div>
    				<div class="w3-container w3-third" style="padding: 0px; margin: 0px;">
    					<div>
	    					<img src="/resource/img/vote.png" class="w3-center w3-margin-bottom" style="width:100px; height: 100px;">
    					</div>
    					<p>투표참여</p>
    					<p>${ countSurvey } 번</p>
    				</div>
  				</div>
            </div>
          </div>
        </div>
      </div>
      <div class="w3-myfont w3-left-align" >
      	<div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa;">
	      	<div class="w3-margin-4" style="float:left;">
	      		<h4>회원 탈퇴 신청하기</h4>
			      <div class="w3-myfont w3-left-align" >
			      <p>커튼을 이용하시면서 가장 불편했던 점을 적어주시기 바랍니다. 서비스 향상을 위해 활용하도록 하겠습니다.</p>
	    		  </div>
	      	</div>
      	</div>
      </div>
      
      
      <div id="formWrapper" class="w3-margin-32" >
			<div class="w3-col m12">
				<div class="w3-container w3-card" style="background-color: white; margin-bottom:25px;">
						<textarea id="secedeDesc" name="secedeDesc" class="w3-col m12" rows="4" style="border: none; margin-bottom:70;"></textarea><br/>
				</div>
			</div>
		</div>
 </form>
		
      <div class="w3-center-align">	
		<div class="w3-btn-group">
			<button type="button" id="secedeBtn" class="btn btn-primary" style="background-color:#FF3300; border:0px;">
				탈퇴
			</button>
			<button type="button" id="cancelSecedeBtn" class="btn btn-primary" style="background-color:#c0c0c0; border:0px;">
				취소
			</button>
		</div>
	</div>
      
        
    </div>
</div>

</div>
