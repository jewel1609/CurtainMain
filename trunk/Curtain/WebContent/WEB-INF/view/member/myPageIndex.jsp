<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	
	$(document).ready( function(){
		$("#myPage").mouseleave(function(){
			$("#myPage").css('background-color', '#a9d039');
			$("#myPage").css('color', '#ffffff');
		});
		$("#myPage").css('background-color', '#a9d039');
		$("#myPage").css('color', '#ffffff');
		
		$('[data-toggle="tooltip"]').tooltip();
		
		$("#modifyBtn").click(function() {
			location.href = "/myPage/modifyMember";
		});
		
		
		$("#alarmBtn").click(function () {
			location.href = "/myQuestion";
		});
		
		$("#secedeBtn").click(function() {
			location.href= "/myPage/secedeMember"
		});
		
		
	});
		
	

</script>
<div class="w3-container w3-center w3-main" style="margin-top:0px;">    
<c:set var="companyCheck" value="${ sessionScope._COMPANY_ }" />
  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="border-right:1px solid #bababa;
    background-color: #F3F3F3; margin-left:334px; margin-right: 100px; height: 885px; overflow: auto;">
      <div class="w3-myfont w3-left-align" >
      	<div class="w3-row" style="background-color: white; border-bottom: 1px solid #bababa;">
	      	<div class="w3-margin-4" style="float:left;">
	      		<h2>마이페이지</h2>
	      	</div>
	      	<div class="w3-col" style="width:10%; margin-top:30px;">
	      		<a href="#" data-toggle="tooltip" data-placement="top" class="red-tooltip" title="문의하신 답변이 왔습니다!" >
	      			<button type="button" id="alarmBtn" class="btn btn-primary" style="background-color:#FF3300; border:0px;">알림!</button>
	      		</a>
	      	</div>
	      	<div class="w3-col" style="width:10%; margin-top:30px;">
      			<c:if test="${ companyCheck ne null }">
      			<button type="button" id="alarmBtn" class="btn btn-primary" style="background-color:#FF3300; border:0px;"
      			data-toggle="modal" data-target="#myModal" >포인트 결제</button>
      			</c:if>
	      	</div>
      	</div>
      </div>
      
      <!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">포인트 결제 방법</h4>
	        </div>
	        <div class="modal-body">
	          <p>1000point 기준 : 일만원</p> <br/>
	          <p>국민은행 : 471202-04-004907</p> <br/>
	          <p>'밀당컴퍼니' 로 입금하시면 됩니다.</p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	    </div>
	  </div>
      
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card w3-white w3-round-large" style="margin-top:10px; margin-bottom:10px;">
            <div class="w3-container w3-padding w3-center-align">
            	<div class="w3-container w3-third">
    				<h3>회원 등급</h3>
    				<img src="/resource/img/curtainGradeOne.png" class="w3-center w3-circle w3-margin-bottom" style="width:100px; height: 100px">
    				<p>회원님의 등급은 ' ${ memberRank } '입니다.</p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h3>포인트</h3>
    				<img src="/resource/img/point.png" class="w3-center w3-circle w3-margin-bottom" style="width:100px; height: 90px;">
    				<p style="position:relative; top:10px;">${ member.point } 포인트</p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h3>활동량</h3>
    				<div class="w3-container w3-third">
    					<div>
	    					<img src="/resource/img/circle.png" class="w3-center w3-circle w3-margin-bottom w3-margin-right" style="width:110px; height: 90px;
	    					position: relative; left:-13px;">
	    					<p style="position:relative; top:-70px; left:0px;">${ countArticle }</p>
    					</div>
    					<p style="position:relative; top:-22px;">게시글</p>
    				</div>
    				<div class="w3-container w3-third">
    					<div>
	    					<img src="/resource/img/circle.png" class="w3-center w3-circle w3-margin-bottom" style="width:110px; height: 90px;
	    					position: relative; left:-13px;">
	    					<p style="position:relative; top:-70px; left:0px;">${ countReply }</p>
    					</div>
    					<p style="position:relative; top:-22px;">댓글</p>
    				</div>
    				<div class="w3-container w3-third">
    					<div>
	    					<img src="/resource/img/circle.png" class="w3-center w3-circle w3-margin-bottom" style="width:110px; height: 90px;
	    					position: relative; left:-13px;">
	    					<p style="position:relative; top:-70px; left:0px;">${ countSurvey }</p>
    					</div>
    					<p style="position:relative; top:-22px;">투표참여</p>
    				</div>
  				</div>
            </div>
          </div>
        </div>
      </div>
      
        <div class="w3-row-padding w3-margin-top">
       		 <div class="w3-col m12">
            	<ul class="list-group w3-left-align">
            		<li class="list-group-item"><a href="/myPage/myWriteArticle">내가 쓴 게시글/댓글 보기</a></li>
            		<li class="list-group-item"><a href="/myPage/myLikesArticle">내가 좋아요 한 글  보기</a></li>
            		<li class="list-group-item"><a href="/myPage/myScrabArticle">내가 스크랩 한 글 보기</a></li>
            	</ul>
       		 </div>
      	</div>

      
      <div class="w3-myfont w3-left-align">
	      <div class="w3-row" style="background-color: white; border-top: 1px solid #bababa; border-bottom: 1px solid #bababa;">
		        <div class="w3-margin-4" style="float:left;">
		      		<h2>회원정보</h2>
		      	</div>
		      	<div class="w3-col" style="width:10%; margin-top:30px;">
		      		<button type="button" id="modifyBtn" class="btn btn-primary " style="background-color:#FF3300; border:0px;">회원정보 변경</button>
		      	</div>
		      </div>
	      </div>

      <c:if test="${companyCheck eq null}"> 
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card w3-round-large w3-white" style="margin-top:10px; margin-bottom:10px;">
            <div class="w3-container w3-padding w3-left-align">
          		<div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>대학교 이메일</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>${ member.email }</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      
       <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>학교 이름</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>${ univName }</p>
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
          				<b>학과 이름</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>${ majorName}</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>   
      </c:if>
      
      <c:if test="${companyCheck ne null}">
		<div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card w3-round-large w3-white" style="margin-top:10px; margin-bottom:10px;">
            <div class="w3-container w3-padding w3-left-align">
          		<div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>기업 이메일</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>${ member.email }</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>
      
      
       <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
              <div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>기업 이름</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>${ member.companyName }</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
       </div>
       <br/>
      </c:if> 
      
      <button type="button" id="secedeBtn" class="btn btn-primary " style="background-color:#FF3300; border:0px;">회원 탈퇴</button>
      
        
    </div>
</div>

</div>
