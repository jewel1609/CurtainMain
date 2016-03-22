<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3-theme-teal.css">
<link href='https://fonts.googleapis.com/css?family=RobotoDraft' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "RobotoDraft","Roboto",sans-serif;}
.w3-sidenav a {padding:16px;font-weight:bold}
</style>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<body>

<nav class="w3-sidenav w3-collapse w3-white w3-animate-left w3-card-2" style="z-index:3;width:250px; height:400px; margin-top:185px;">
  <a href="#" class="w3-border-bottom w3-large"><img src="http://www.w3schools.com/images/w3schools.png" style="width:80%;"></a>
  <a href="javascript:void(0)" onclick="w3_close()" 
  class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>
  <div class="w3-accordion">
    <a onclick="myAccordion('demo')" href="javascript:void(0)">학생 게시판<i class="fa fa-caret-down"></i></a>
    <div id="demo" class="w3-accordion-content w3-animate-left w3-padding">
      <a href="#">컴퓨터공학</a>
      <a href="#">밀당대학교</a>
    </div>
  </div>	
  <a href="#">홍보게시판</a>	
  <a href="#">마이페이지</a>		
   <a href="#">Q & A</a>		
</nav>

<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer"></div>

<div class="w3-main" >

<div id="myTop" class="w3-top w3-container w3-padding-8"  style="background-color:#A9D039; border:0px;" >
  <i class="fa fa-bars w3-opennav w3-hide-large w3-xlarge w3-margin-left w3-margin-right" onclick="w3_open()"></i>
</div>

<header class="w3-container w3-padding-15 " style="background-color:#A9D039; border:0px; padding: 20px;">
  <h1 class="w3-xxxlarge w3-padding-5" style="margin-left:10px;">커튼</h1>
</header>
</div>


<div class="w3-container w3-center w3-main" style="margin-top:30px; margin-bottom:20px;">    

  <div class="w3-row" >
    <div class="w3-col m7 w3-main" style="margin-left:350px; margin-right: 100px;">
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
            <div class="w3-container w3-padding w3-left-align">
              <div class="form-group">
				  <textarea class="w3-col m12" rows="5" id="comment" style=" margin-bottom:5px;" placeholder="무슨 생각을 하고 계신가요?"></textarea>
				</div>
               <div class="btn-group">
					    <div class="btn-group">
					      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="background-color:#FF3300; border:0px;">
					      파일첨부 <span class="caret"></span></button>
					      <ul class="dropdown-menu" role="menu">
					        <li><a href="#">사진</a></li>
					        <li><a href="#">동영상</a></li>
		    				</ul>
    					</div>
    				<button type="button" class="btn btn-primary" style="background-color:#FF3300; border:0px;">게시</button>
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
              <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
            </div>
          </div>
        </div>
      </div>     
    </div>
    

<div id="rigth-menu" >
    <div class="w3-col m2" style="float: right;">
      <div class="w3-card-2 w3-round w3-white">
        <div class="w3-container">
          <p>실시간 키워드</p>
        </div>
      </div>
      <br>
      <div class="w3-card-2 w3-round w3-white w3-padding-16">
        <p>설문 조사</p>
      </div>
    </div>
    
 </div>
  
</div>


</div>

<footer class="w3-container" style="background-color:#A9D039; border:0px;">
  <h5>Footer</h5>
  <p>Footer information goes here</p>
</footer>
     


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
window.onscroll = function() {myFunction()};

function myFunction() {
    if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
        document.getElementById("myTop").classList.add("w3-card-4");
    } else {
        document.getElementById("myTop").classList.remove("w3-card-4");
    }
}

function myAccordion(id) {
    document.getElementById(id).classList.toggle("w3-show");
    document.getElementById(id).previousElementSibling.classList.toggle("w3-theme");
}
</script>
     
</body>
</html> 