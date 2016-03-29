<%@page import="com.ktds.curtain.survey.vo.SurveyVO"%>
<%@page import="com.ktds.curtain.survey.biz.SurveyBiz"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>


<%
	SurveyBiz surveyBiz = new SurveyBiz();

	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
	Date currentTime = new Date ( );
	String mTime = mSimpleDateFormat.format ( currentTime );
	System.out.println ( mTime );
	
	
	SurveyVO survey = surveyBiz.showTodaySurvey(mTime);
	String surveys[] = { survey.getFirstAnswer(), survey.getSecondAnswer(), survey.getThirdAnswer(), survey.getFourthAnswer() }; 
	
	request.setAttribute("mTime", mTime);
	request.setAttribute("survey", survey);
	request.setAttribute("surveys", surveys);
	
%>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		$("#doSurvey").click( function () {
			
			var selectRadio = $(".todaySurvey:checked").val();
			selectRadio = $.trim(selectRadio);
			if ( selectRadio == "" ) {
				alert("오늘의 투표를 선택하세요.");
				return;
			}
			
			if ( confirm("투표 하시겠습니까?") == true ) {
				
				var form = $("#todaySurveyForm");
				alert("##안내##\n\n투표했습니다!");
				form.attr("method", "post");
				form.attr("action","/doTodaySurvey");
				form.submit();
				
			}
			else {
				return;
			}
			
		});
		
	});
	

</script>

<nav class="w3-sidenav w3-collapse w3-white w3-card" style="z-index:3; width:250px; height:800px; margin-top:110px; right: 0px;">
  <a href="javascript:void(0)" onclick="w3_close()" 
  class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>	
 <div class="w3-padding-medium">
	 <div>
	 	<div class="w3-card w3-round w3-white" style="padding:4px;">
	 		<strong>게시판의 관심사</strong><br/>
	 	</div>

		  1.<br/>
		  2.<br/>
		  3.<br/>
		  4.<br/>
		  5.<br/>
		  6.<br/>
		   
	</div>	   
	
	<div>
	
	  <div class="w3-card w3-round w3-white" style="margin-top:50px; padding:4px;">
	  	<strong>오늘의 투표</strong><br/>  ${ mTime }
	  </div>
		<br/>
		<p> ${ survey.surveyTitle }</p>
		<form id="todaySurveyForm">
			<c:forEach items="${surveys}" var="surveyList">
				<c:if test="${ surveyList ne null }">
				<input type="radio" class="todaySurvey" name="todaySurvey" value="${surveyList}"> ${ surveyList }<br/><br/>
				</c:if>
			</c:forEach>
				<input type="hidden" name="survey1" value="${ survey.firstAnswer }"/>
				<input type="hidden" name="survey2" value="${ survey.secondAnswer }"/>
				<input type="hidden" name="survey3" value="${ survey.thirdAnswer }"/>
				<input type="hidden" name="survey4" value="${ survey.fourthAnswer }"/>
				<input type="hidden" name="surveyId" value="${ survey.surveyId }"/>
		</form>
		<div class="w3-col" style="width:60%">
			<button id = "doSurvey" type="button" class="btn btn-primary btn-sm">투표하기</button>
		</div>
		<div class="w3-col" style="width:30%;">
			<button id = "showSurveyResult" type="button" class="btn btn-primary btn-sm">결과보기</button>
		</div>
	  	
	 
	</div>
  </div>
</nav>