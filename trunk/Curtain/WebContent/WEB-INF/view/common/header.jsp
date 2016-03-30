<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>커튼</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="/resource/css/article/theme.css">
<link href='https://fonts.googleapis.com/css?family=RobotoDraft' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="/resource/css/article/header.css" />

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/resource/js/json2.js"></script>
<%
   Cookie[] cookies = request.getCookies();
   String userId = "";
   String userPassword = "";
   String autoLogin = "";
   
   if ( cookies != null ) {
      for ( Cookie cookie : cookies ) {
         if ( cookie.getName().equals("userId")) {
            userId = cookie.getValue();
         }
         else if ( cookie.getName().equals("userPassword")) {
            userPassword = cookie.getValue();
         }
         else if ( cookie.getName().equals("autoLogin")) {
            autoLogin = cookie.getValue();
         }
      }
   }
%>

<script type="text/javascript">

   $(document).ready( function () {
      
      var autoLogin = "<%=autoLogin%>";
      if (autoLogin == "true") {
         $("#userId").val("<%=userId%>");
         $("#userPassword").val("<%=userPassword%>");
         $("#autoLoginCheckBox").click();
         
         var div = $("#insertStartButton");
         div.html("<button type=\"button\" id=\"btnStart\" class=\"btn btn-primary btn-lg\">시작하기</button>");
         div.show();
         
      }
      else {
         var div = $("#insertStartButton");
         div.html("<button type=\"button\" id=\"btnRegister\" class=\"btn btn-primary btn-lg\">가입하기</button>");
         div.show();
      }
      
      $(document).on("click","#btnRegister",function () {
         var form = $("#btnForm");
         form.attr("method", "get");
         form.attr("action", "<c:url value="/registStdMember"/> ");
         form.submit();
      });
      
      $(document).on("click","#btnStart",function () {
         var form = $("#btnForm");
         form.attr("method", "post");
         form.attr("action", "<c:url value="/doLogin"/> ");
         form.submit();
      });
      
      $("#closeModal").click(function() {
         $("#loginModal").modal('hide');
      });
      
      $("#btnDoLogin").click(function() {
         var userId = $("#userId").val();
         userId = $.trim(userId);
         if (userId == "") {
            $("#userId").focus();
            return;
         }

         var userPassword = $("#userPassword").val();
         userPassword = $.trim(userPassword);
         if (userPassword == "") {
            $("#userPassword").focus();
            return;
         }

         var form = $("#btnForm");
         form.attr("method", "post");
         form.attr("action", "<c:url value="/doLogin"/> ");
         form.submit();
      });
      
      $("#userPassword").keyup(function(e) {
         if (e.keyCode == 13) {
            //Enter 입력했다면..
            $("#btnDoLogin").click();
         }
      });

   });
</script>


<body style="padding-top: 0;">
   
<%--
   
   <!-- Navigation -->
   <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
         <!-- Brand and toggle get grouped for better mobile display -->
         <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
               <span class="sr-only">Toggle navigation</span> 
               <span class="icon-bar"></span> 
               <span class="icon-bar"></span> 
               <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.jsp">커튼</a>
         </div>
         <!-- Collect the nav links, forms, and other content for toggling -->
         <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
               
            </ul>
         </div>
         <!-- /.navbar-collapse -->
      </div>
      <!-- /.container -->
   </nav>
--%>

   <%-- 헤더 --%>
   <div class="w3-main">
   
      <header class="w3-container" style="background-color: blue; border-bottom: 1px solid #bababa; height:105px;">
         <form id="homeForm">
            <div id="btnHome" style="float: left; width: 200px;">
               <img src="/resource/img/title4.png" style="width:85%;">
            </div>
         </form>

            <div style="float: right; width: 100px; padding-top: 25px;">
               <c:if test="${sessionScope._MEMBER_ eq null}">
                  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#loginModal" style="border-color: #FF3300; color: #FF3300;" >로그인</button>
               </c:if>
               <c:if test="${sessionScope._MEMBER_ ne null}">
                  <form action="<c:url value="/doLogout" /> " method="post">
                  	<button type="submit" class="btn btn-default" style="border-color: #FF3300; color: #FF3300;" >로그아웃</button>
              	 </form>
               </c:if>
               
            </div>
      </header>


   </div>
   

   <!-- login Modal -->
   <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 250px;">
      <div class="modal-dialog">
         <div class="modal-content">
            <div class="modal-header">
               <a href="/registStdMember" class="btn btn-primary btn-lg" style="width: 49%;">
                  회원가입
               </a>
               <button id="closeModal" type="button" class="btn btn-primary btn-lg"
                  data-dismiss="modal" aria-hidden="true" style="width: 49%;">닫기</button>
            </div>
            <form id="btnForm">
               <div class="arrow_box" style="width: 100%;">
                  <div class="modal-body" style="overflow-y: auto; height: 265px;">
                     아이디<input id="userId" name="userId" class="w3-input" type="text" placeholder="이메일을 입력하세요."/>
                     비밀번호<input id="userPassword" name="userPassword" class="w3-input" type="password" placeholder="비밀번호를 입력하세요"/>
                     <div style="width: 140px; float:left;">
                        자동로그인 하기 <input id="autoLoginCheckBox" name="autoLoginCheckBox" type="checkbox" class="w3-check" value="1"/>
                     </div>
                     <div id="btnDoLogin" class="btn btn-primary btn-lg" style="margin-top: 20px; margin-bottom:5px; width: 100%; text-align: center;">시작하기</div>
                     <a href="/findPassword" style=""> 비밀번호를 잊으셨나요? </a>
                  </div>
               </div>
               
               <div style="clear: both; height: 10px;"></div>
            </form>
         </div>
      </div>
   </div>
   