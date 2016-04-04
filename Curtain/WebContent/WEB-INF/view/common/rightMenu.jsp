<%@page import="com.ktds.curtain.survey.vo.SurveyStatsVO"%>
<%@page import="com.ktds.curtain.survey.vo.SurveyVO"%>
<%@page import="com.ktds.curtain.survey.biz.SurveyBiz"%>
<%@page import="javax.servlet.http.HttpSession" %>
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
	SurveyStatsVO surveyStats = surveyBiz.getSurveyStats(mTime);
	
	int one = surveyStats.getFirstStats();
	int two = surveyStats.getSecondStats();
	int three = surveyStats.getThirdStats();
	int four = surveyStats.getFourthStats();
	int sumStats = one+two+three+four;
	
	double first2 = (double) one / sumStats * 100;
	int first = (int) first2;
	double second2 = (double) two / sumStats * 100;
	int second = (int) second2;
	double third2 = (double) three / sumStats * 100;
	int third = (int)third2;
	double fourth2 = (double) four / sumStats * 100;
	int fourth = (int)fourth2;
	int surveyCount[] = { one, two, three, four };
	String surveys[] = { survey.getFirstAnswer(), survey.getSecondAnswer(), survey.getThirdAnswer(), survey.getFourthAnswer() }; 
	request.setAttribute("mTime", mTime);
	request.setAttribute("survey", survey);
	request.setAttribute("surveys", surveys);
	request.setAttribute("first", first); 
	request.setAttribute("second", second); 
	request.setAttribute("third", third); 
	request.setAttribute("fourth", fourth); 
	request.setAttribute("one", one);
	request.setAttribute("two", two);
	request.setAttribute("three", three);
	request.setAttribute("four", four);
	
%>
<script type="text/javascript">
	
	var check = null;
	
	$(document).ready(function() {
		
		$('[data-toggle="tooltip"]').tooltip();
		
		var first = $("#first").val();
		
		
		$("#doSurvey").click(function(e) {
			var selectRadio = $(".todaySurvey:checked").val();
			var survey1 = $("#survey1").val();
			var survey2 = $("#survey2").val();
			var survey3 = $("#survey3").val();
			var survey4 = $("#survey4").val();
			var surveyId = $("#surveyId").val();
			
			$.post(
					"/doSurvey"
					, { "selectRadio" : selectRadio
						, "survey1" : survey1
						, "survey2" : survey2
						, "survey3" : survey3
						, "survey4" : survey4
						, "surveyId" : surveyId
						}
					, function(data) {
						
							try{
								jsonData3 = JSON.parse(data);
							}
							catch(e) { //자바스크립트는 타입이 없기때문에 e만 적으면 된다.
							}
							
							if ( selectRadio == "" ) {
								alert("오늘의 투표를 선택하세요.");
								return;
							} 
							if(!jsonData3.isCheckId) {
								alert("오늘의 투표를 선택하세요.");
								return;
							}
							if(jsonData3.isCheckId) {
								alert("##안내##\n\n투표했습니다!");
								location.reload(true);
								return;
							}
				  		}
			);
		}); 
		
		
		
		
		/* $("#doSurvey").click( function () {
			
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
			
		}); */
		
		
		setInterval(function(e) {
			$.post(
					"/showKeyword"
					, {
						"boardId" : <%=request.getParameter("boardId")%>
					}
					, function(data) {
						var jsonData = {};
						
						try{
							jsonData = JSON.parse(data);
						}
						catch(e) { 
						}
						
						if ( jsonData.keyword0 != null ) {
							$("#keyword1").html("1. <span class=\"topKeyword\">" + jsonData.keyword0 + "</span>");
						}
						else {
							$("#keyword1").html("1. ");
						}
						
						if ( jsonData.keyword1 != null ) {
							$("#keyword2").html("2. <span class=\"topKeyword\">" + jsonData.keyword1 + "</span>");
						}
						else {
							$("#keyword2").html("2. ");
						}
						
						if ( jsonData.keyword2 != null ) {
							$("#keyword3").html("3. <span class=\"topKeyword\">" + jsonData.keyword2 + "</span>");
						}
						else {
							$("#keyword3").html("3. ");
						}
						
						if (jsonData.keyword3 != null ) {
							$("#keyword4").html("4. <span class=\"topKeyword\">" + jsonData.keyword3 + "</span>");
						}
						else {
							$("#keyword4").html("4. ");
						}
						
						if (jsonData.keyword4 != null ) {
							$("#keyword5").html("5. <span class=\"topKeyword\">" + jsonData.keyword4 + "</span>");
						}
						else {
							$("#keyword5").html("5. ");
						}
						
						if (jsonData.keyword5 != null ) {
							$("#keyword6").html("6. <span class=\"topKeyword\">" + jsonData.keyword5 + "</span>");
						}
						else {
							$("#keyword6").html("6. ");
						}
						
						if (jsonData.keyword6 != null ) {
							$("#keyword7").html("7. <span class=\"topKeyword\">" + jsonData.keyword6 + "</span>");
						}
						else {
							$("#keyword7").html("7. ");
						}
			  		}
			);
		}, 1000*60*5); 
		
		$.post(
				"/showKeyword"
				, { 
					"boardId" : <%=request.getParameter("boardId")%>
				}
				, function(data) {
					var jsonData = {};
					
					try{
						jsonData = JSON.parse(data);
					}
					catch(e) { 
					}
					
					if ( jsonData.keyword0 != null ) {
						$("#keyword1").html("1. <span class=\"topKeyword\">" + jsonData.keyword0 + "</span>");
					}
					else {
						$("#keyword1").html("1. ");
					}
					
					if ( jsonData.keyword1 != null ) {
						$("#keyword2").html("2. <span class=\"topKeyword\">" + jsonData.keyword1 + "</span>");
					}
					else {
						$("#keyword2").html("2. ");
					}
					
					if ( jsonData.keyword2 != null ) {
						$("#keyword3").html("3. <span class=\"topKeyword\">" + jsonData.keyword2 + "</span>");
					}
					else {
						$("#keyword3").html("3. ");
					}
					
					if (jsonData.keyword3 != null ) {
						$("#keyword4").html("4. <span class=\"topKeyword\">" + jsonData.keyword3 + "</span>");
					}
					else {
						$("#keyword4").html("4. ");
					}
					
					if (jsonData.keyword4 != null ) {
						$("#keyword5").html("5. <span class=\"topKeyword\">" + jsonData.keyword4 + "</span>");
					}
					else {
						$("#keyword5").html("5. ");
					}
					
					if (jsonData.keyword5 != null ) {
						$("#keyword6").html("6. <span class=\"topKeyword\">" + jsonData.keyword5 + "</span>");
					}
					else {
						$("#keyword6").html("6. ");
					}
					
					if (jsonData.keyword6 != null ) {
						$("#keyword7").html("7. <span class=\"topKeyword\">" + jsonData.keyword6 + "</span>");
					}
					else {
						$("#keyword7").html("7. ");
					}
		  		}
		);
		
		
		$("body").on ("click", ".topKeyword", function() {
			var form = $("#topKeywordForm");
			form.attr("method", "post");
			var boardId = $(".boardId").val();
			
			$(".articleTypeId").val("1");
			$(".searchKeyword").val($(this).text());
			
			if(boardId == "1") {
				form.attr("action", "/studentMajorAritlce");
			}
			else if ( boardId == "2" ) {
				form.attr("action", "/studentUnivArticle");
			}
			else if ( boardId == "3" ) {
				form.attr("action", "/promotionArticle");
			}
			else if ( boardId == "4" ) {
				form.attr("action", "/secretArticleList");
			}
			else if ( boardId == "5" ) {
				form.attr("action", "/oneLayerCurtain");
			}
			else if ( boardId == "6" ) {
				form.attr("action", "/twoLayerCurtain");
			}
			
			form.submit();
		});
		
		$("body").on ("mouseover", ".topKeyword", function() {
			$(".topKeyword").css("cursor","pointer");
		});
	});
</script>
<nav class="w3-sidenav w3-collapse w3-white w3-card" style="z-index:3; width:250px; height:100%; margin-top:0px; border:0px; border-left:1px solid #bababa; right: 0px;">
  <a href="javascript:void(0)" onclick="w3_close()" 
  class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>	
	
 	<c:set var="voteCheck" value="${ sessionScope._VOTE_ }" />
 	<input type="hidden" id="first" value="${ first }" />
 

 <div class="w3-padding-medium">
	 <c:if test="${ sessionScope._BOARD_ID_ ne 0 }">
	 <div>
	 	<div class="w3-card w3-round w3-white" style="padding:4px;">
	 		<strong>게시판의 관심사</strong><br/>
	 	</div>
		<div id="wrapperKeywordList">
			<form id="topKeywordForm">
				<input type="hidden" name="searchKeyword" class="searchKeyword" value="" />
				<input type="hidden" name="articleTypeId" class="articleTypeId" value="" />
				<input type="hidden" name="boardId" class="boardId" value="${ sessionScope._BOARD_ID_ }" />
				<p id="keyword1"></p>
				<p id="keyword2"></p>
				<p id="keyword3"></p>
				<p id="keyword4"></p>
				<p id="keyword5"></p>
				<p id="keyword6"></p>
				<p id="keyword7"></p>
			</form>
		</div>
	</div>	
	</c:if>   
	
	<div>
		
	  <input type="hidden" id="isVoteCheck" name="isVoteCheck" value= false />	
	  <div class="w3-card w3-round w3-white" style="margin-top:50px; padding:4px;">
	  	<strong>오늘의 투표</strong><br/>  ${ mTime }
	  </div>
		<br/>
		<input type="hidden" id="todaySurveyCheck" name="todaySurveyCheck" value= "0" />
		
		
		<p> ${ survey.surveyTitle }</p>
		<c:if test="${voteCheck eq null }">
		<form id="todaySurveyForm" name="todaySurveyForm">	
			<c:forEach items="${surveys}" var="surveyList">
				<c:if test="${ surveyList ne null }">
				<input type="radio" class="todaySurvey" name="todaySurvey" value="${surveyList}" /> ${ surveyList }<br/><br/>
				</c:if>
			</c:forEach>
				<input type="hidden" id = "survey1" name="survey1" value="${ survey.firstAnswer }" />
				<input type="hidden" id = "survey2" name="survey2" value="${ survey.secondAnswer }" />
				<input type="hidden" id = "survey3" name="survey3" value="${ survey.thirdAnswer }" />
				<input type="hidden" id = "survey4" name="survey4" value="${ survey.fourthAnswer }" />
				<input type="hidden" id = "surveyId" name="surveyId" value="${ survey.surveyId }" />
		<div class="w3-container w3-center-align" style="margin-left: 45px;">
			<button id = "doSurvey" type="button" class="btn btn-primary btn-sm">투표하기</button>
		</div>
		</form>
		</c:if>
		
		<c:if test="${voteCheck ne null}">
		<form id="surveyResultForm">
			결과보기
			<c:if test="${ survey.firstAnswer ne null }">
				<div class="progress">
		    		<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:${first}%">
		    			<a href="#" data-toggle="tooltip" data-placement="top" class="red-tooltip" title="${ first }%, ${ one }표" >
				    	</a>
		    		</div>
		      			<h>${ survey.firstAnswer }</h> 
	  			</div>
  			</c:if>
  			<c:if test="${ survey.secondAnswer ne null }">
	  			<div class="progress">
				    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:${second}%">
	  				<a href="#" data-toggle="tooltip" data-placement="top" class="red-tooltip" title="${ second }%, ${ two }표" >
				    </a>
				    </div>
				      	<h>${ survey.secondAnswer }</h> 
			  	</div>
		  	</c:if>
		  	<c:if test="${ survey.thirdAnswer ne null }">
			  	<div class="progress">
			    	<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:${third}%">
						<a href="#" data-toggle="tooltip" data-placement="top" class="red-tooltip" title="${ third }%, ${ three }표" >
				    	</a>
			    	</div>
			      		<h>${ survey.thirdAnswer }</h>
			 	</div>
		 	</c:if>
		 	<c:if test="${ survey.fourthAnswer ne null }">
			  	<div class="progress">
				    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:${fourth}%">
						<a href="#" data-toggle="tooltip" data-placement="top" class="red-tooltip" title="${ fourth }%, ${ four }표" >
				    	</a>
				    </div>
				      <p>${ survey.fourthAnswer }</p>
			  	</div>
		  	</c:if>
		</form>
		</c:if>
	  	
	 
	</div>
  </div>
</nav>