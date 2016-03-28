<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<script type="text/javascript">
	
	$(document).ready( function(){
		$("#modifyBtn").click(function() {
			location.href = "/myPage/modifyMember";
		});
		
		$('[data-toggle="tooltip"]').tooltip();
		
		$("#alarmBtn").click(function () {
			location.href = "/myQuestion";
		});
		
	});
		
	

</script>


<div class="w3-container w3-center w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px; height: 750px; overflow: auto;">
      
      <div class="w3-myfont w3-left-align">
      	<div class="w3-col" style="width:90%">
      		<h1>마이페이지</h1>
      	</div>
      	<div class="w3-col" style="width:10%; margin-top:30px;">
      		<a href="#" data-toggle="tooltip" data-placement="top" class="red-tooltip" title="문의하신 답변이 왔습니다!" >
      			<button type="button" id="alarmBtn" class="btn btn-primary" style="background-color:#FF3300; border:0px;">알림!</button>
      		</a>
      	</div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-center-align">
            	<div class="w3-container w3-third">
    				<h2>회원 등급</h2>
    				<img src="/resource/img/curtainGradeOne.png" class="w3-center w3-circle w3-margin-bottom" style="width:100px; height: 100px">
    				<p>회원님의 등급은 ' ${ memberRank } '입니다.</p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h2>포인트</h2>
    				<img src="/resource/img/point.png" class="w3-center w3-circle w3-margin-bottom" style="width:100px; height: 90px;">
    				<p style="position:relative; top:10px;">${ member.point } 포인트</p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h2>활동량</h2>
    				<div class="w3-container w3-third">
    					<div>
	    					<img src="/resource/img/circle.png" class="w3-center w3-circle w3-margin-bottom w3-margin-right" style="width:110px; height: 90px;
	    					position: relative; left:-13px;">
	    					<p style="position:relative; top:-70px; left:0px;">test</p>
    					</div>
    					<p style="position:relative; top:-22px;">게시글</p>
    				</div>
    				<div class="w3-container w3-third">
    					<div>
	    					<img src="/resource/img/circle.png" class="w3-center w3-circle w3-margin-bottom" style="width:110px; height: 90px;
	    					position: relative; left:-13px;">
	    					<p style="position:relative; top:-70px; left:0px;">test</p>
    					</div>
    					<p style="position:relative; top:-22px;">댓글</p>
    				</div>
    				<div class="w3-container w3-third">
    					<div>
	    					<img src="/resource/img/circle.png" class="w3-center w3-circle w3-margin-bottom" style="width:110px; height: 90px;
	    					position: relative; left:-13px;">
	    					<p style="position:relative; top:-70px; left:0px;">test</p>
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
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-left-align">
              <a href="/myPage/myWriteArticle">
              <h4>
              	내가 쓴 게시글/댓글 보기
              </h4>
              </a>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-left-align">
           		<a href="/myPage/myLikesArticle">
           		<h4>
					내가 좋아요 한 글  보기       		
           		</h4>
           		</a>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-left-align">
              <a href="/myPage/myScrabArticle">
              <h4>
              	내가 스크랩 한 글 보기
              </h4>
              </a>
            </div>
          </div>
        </div>
      </div>   
      
      <div class="w3-myfont w3-left-align">
      	<div class="w3-col" style="width:90%">
      		<h1>회원정보</h1>
      	</div>
      	<div class="w3-col" style="width:10%; margin-top:30px;">
      		<button type="button" id="modifyBtn" class="btn btn-primary " style="background-color:#FF3300; border:0px;">회원정보 변경</button>
      	</div>
      </div>
      
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
            <div class="w3-container w3-padding w3-left-align">
          		<div class="w3-col" style="width:90%">
          			<div class="w3-col" style="width:20%">
          				<b>이메일</b>
          			</div>
          			<div class="w3-col" style="width:80%">
    					<p>${ member.secondEmail }</p>
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
    					<p>${ univName }</p>
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
    					<p>${ majorName}</p>
    				</div>
          		</div>
            </div>
          </div>
        </div>
      </div>   
      
      
      
        
    </div>
</div>

</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>