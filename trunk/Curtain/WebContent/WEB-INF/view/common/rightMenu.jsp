<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	  	<strong>오늘의 투표 ${ survey.surveyDate }</strong><br/>
	  </div>
		<br/>
		<p> ${ survey.surveyTitle }</p>
		<input type="radio" name="todayVote" value="01"> ${ survey.firstAnswer}<br/><br/>
		<input type="radio" name="todayVote" value="02"> ${ survey.secondAnswer } <br/><br/>
		<input type="radio" name="todayVote" value="03"> ${ survey.thirdAnswer } <br/><br/>
		<input type="radio" name="todayVote" value="04"> ${ survey.fourthAnswer } <br/><br/>
		<div class="w3-col" style="width:60%">
			<button id = "doVote" type="button" class="btn btn-primary btn-sm">투표하기</button>
		</div>
		<div class="w3-col" style="width:30%;">
			<button id = "showResult" type="button" class="btn btn-primary btn-sm">결과보기</button>
		</div>
			
			
	  	
	 
	</div>
  </div>
</nav>