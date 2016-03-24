<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>커튼</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3-theme-teal.css">
<link href='https://fonts.googleapis.com/css?family=RobotoDraft' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="/resource/css/article/header.css"/>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/json2.js"></script>

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

<body>
<%-- 왼쪽 메뉴--%>
<jsp:include page="/WEB-INF/view/common/leftMenu.jsp"></jsp:include>
<%-- 오른쪽 메뉴--%>
<jsp:include page="/WEB-INF/view/common/rightMenu.jsp"></jsp:include>

<%-- 헤더 --%>
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer"></div>

<div class="w3-main" >

<div id="myTop" class="w3-top w3-container w3-padding-8"  style="background-color:#A9D039; border:0px;" >
  <i class="fa fa-bars w3-opennav w3-hide-large w3-xlarge w3-margin-left w3-margin-right" onclick="w3_open()"></i>
</div>

<header class="w3-container w3-padding-15 " style="background-color:#A9D039; border:0px; padding: 20px;">
  <h1 class="w3-xxxlarge w3-padding-5" style="margin-left:10px;">커튼</h1>
</header>
</div>

