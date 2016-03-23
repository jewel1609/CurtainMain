<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

<script type="text/javascript">
	
	$(document).ready( function(){
		$("#modifyBtn").click(function() {
			location.href = "/Curtain/myPage/modifyMember";
		})	
		
	});
		
	

</script>


<div class="w3-container w3-center w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px;">
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
            <div class="w3-container w3-padding w3-left-align">
          		<div class="w3-col" style="width:90%">
          			<h3>닉네임 000 님의 마이페이지 입니다.</h3>
          		</div>
          		<div class="w3-col" style="width:10%">
    				<button type="button" id="modifyBtn" class="btn btn-primary " style="background-color:#FF3300; border:0px;">회원정보 변경</button>
    			</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container w3-padding w3-left-align">
            	<div class="w3-container w3-third">
    				<h2>등급</h2>
    				<p> 한겹커튼 </p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h2>보유 포인트</h2>
    				<p>000 point</p> 
  				</div>
  				<div class="w3-container w3-third">
    				<h2>활동량</h2>
    				<p> 000 (활동량에 따라 등급이 결정됩니다.) </p>
  				</div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
           		<a href="/Curtain/myPage/myLikesArticle">
           		<h4>
					내가 좋아요 한 글            		
           		</h4>
           		</a>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <a href="/Curtain/myPage/myWriteArticle">
              <h4>
              	내가 쓴 글
              </h4>
              </a>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <a href="/Curtain/myPage/myScrabArticle">
              <h4>
              	내가 스크랩 한 글
              </h4>
              </a>
            </div>
          </div>
        </div>
      </div>     
    </div>
  
</div>

</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>