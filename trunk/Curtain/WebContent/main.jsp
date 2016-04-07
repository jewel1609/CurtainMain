<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.ktds.curtain.history.vo.OperationHistoryVO"%>
<%@page import="com.ktds.curtain.history.vo.ActionCode"%>
<%@page import="com.ktds.curtain.history.vo.BuildDescription"%>
<%@page import="com.ktds.curtain.history.vo.Description"%>
<%@page import="com.ktds.curtain.history.biz.OperationHistoryBiz"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resource/css/article/header.css" />


<style type="text/css">

::-webkit-input-placeholder { /* WebKit, Blink, Edge */
	color: #868F84;
}

:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	color: #868F84;
	opacity: 1;
}

::-moz-placeholder { /* Mozilla Firefox 19+ */
	color: #868F84;
	opacity: 1;
}

:-ms-input-placeholder { /* Internet Explorer 10-11 */
	color: #868F84;
}
.carousel-caption {
    top: 0;
    bottom: auto;
}

</style>
<%
	OperationHistoryBiz historyBiz = new OperationHistoryBiz();
	OperationHistoryVO historyVO = new OperationHistoryVO();
	historyVO.setIp(request.getRemoteHost());
	historyVO.setUrl(request.getRequestURI());
	historyVO.setActionCode(ActionCode.MAIN);
	historyVO.setDescription( BuildDescription.get(Description.VISIT_MAIN_PAGE, request.getRemoteHost()));
	historyBiz.addHistory(historyVO);
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="<c:url value="resource/css/main/bootstrap.min.css"/>" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="resource/css/main/modern-business.css"/>" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">

<!-- Script to Activate the Carousel -->
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	
	$(document).ready(function(){
		
    $("#companyInfoBtn").click(function(){
        $("#companyInfoModal").modal();
    });
    
    $("#questionBtn").click(function(){
        $("#questionModal").modal();
    });
    
    
    $("#adQuestionBtn").click(function(){
        $("#questionModal").modal();
    });
    
    
    $("#personalBtn").click(function(){
        $("#personalModal").modal();
    });
    
    $("#termBtn").click(function(){
        $("#termModal").modal();
    });
});
	
</script>

 
	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill" style="background-image: url(/resource/img/2.png);"></div>
				<div class="carousel-caption">
				
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url(/resource/img/3.png);"></div>
				<div class="carousel-caption">
					<h2>
					</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill" style="background-image: url(/resource/img/1.png);"></div>
				<div class="carousel-caption">
					<h2></h2>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>

	<!-- Page Content -->
	<div class="container">
		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-12">
				<div style="float:left;">
				<h1 class="page-header">지금 커튼 속으로 입장하세요!</h1>
				</div>
				<div id="insertStartButton" style="float:left; padding:37px 30px 23px 30px;">
				</div>
			</div>
		</div>

		<!-- Features Section -->
		
		<div class="row">
			<div class="col-md-12" align="center" style="margin-bottom:40px;">
				<img src="/resource/img/introduction2.png">
			</div>
			
			<div class="col-md-12" align="center" style="margin-bottom:40px;">
				<img src="/resource/img/ourFeatures.png">
			</div>

			<div class="col-md-6">
				<h2 class="page-header">
					같은 전공을 가진 사람들끼리<br /> 터놓고 이야기하세요!
				</h2>
				<p>
					전국의 나와 같은 전공을 가진 사람들끼리 <br /> 학과 커튼을 열어 고민을 나누어보세요.
				</p>

			</div>

			<div class="col-md-6">
				<img class="img-responsive" src="http://placehold.it/700x450" alt="">

				<br />
				<br />
				<br />
				<br />

			</div>
			<div class="col-md-6">
				<h2 class="page-header">
					매일 달라지는 이벤트에 참여해보세요.<br />
					<br /> 회원 등급에 따라 <br /> 더 깊은 속얘기를 나눌 수 있는 <br /> 비밀 커튼 속으로 입장할수
					있습니다.
				</h2>

			</div>
			<div class="col-md-6">
				<img class="img-responsive" src="http://placehold.it/700x450" alt="">
			</div>
		</div>
		<!-- /.row -->
	</div>
	
	
	
		<!-- Footer -->
	<footer>
		<div class="w3-container w3-padding" style="background-color:#a9d039; padding-top:40px;" align="center" >
			<div class="row">
				<div>
					<img class="img-responsive" src="/resource/img/logo_white.png" style="width:150px;" alt="">
				</div>
				<div class="col-md-12"  style="margin-top:20px;">
					<p style="color:white;"><a href="#" id="companyInfoBtn">회사 소개</a> | 
					<a href="#" id="questionBtn">문의하기</a> | <a href="#" id="personalBtn">개인정보취급방침</a> | 
					<a href="#" id="termBtn">이용약관</a> | <a href="#" id="adQuestionBtn">제휴/광고</a></p>
					<p style="color:white;">@ktds University / master@curtain.com</p>
				</div>
			</div>
		</div>

	</footer>
	<!-- Modal -->
	 <div class="modal fade" id="companyInfoModal" role="dialog">
	   <div class="modal-dialog">
	   
	     <!-- Modal content-->
	     <div class="modal-content">
	       <div class="modal-header">
	         <button type="button" class="close" data-dismiss="modal">&times;</button>
	         <h4 class="modal-title"><strong>회사 소개</strong></h4>
	       </div>
	       <div class="modal-body">
	         <p>준비중입니다.</p>
	       </div>
	       <div class="modal-footer">
	         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       </div>
	     </div>
	     
	   </div>
	 </div>
	<!-- /.container -->
	
		<!-- Modal -->
	 <div class="modal fade" id="questionModal" role="dialog">
	   <div class="modal-dialog">
	   
	     <!-- Modal content-->
	     <div class="modal-content">
	       <div class="modal-header">
	         <button type="button" class="close" data-dismiss="modal">&times;</button>
	         <h4 class="modal-title"><strong>문의하기</strong></h4>
	       </div>
	       <div class="modal-body">
	         <p><input type="text" class="form-control" id="email" placeholder="이메일 주소를 입력하세요."></p>
	         <p><textarea class="form-control" rows="8" id="comment"></textarea></p>
	       </div>
	       <div class="modal-footer">
	       	<button type="button" id="sendEmail" class="btn btn-default" data-dismiss="modal">보내기</button>
	         <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	       </div>
	     </div>
	     
	   </div>
	 </div>
	<!-- /.container -->
	
		<!-- Modal -->
	 <div class="modal fade" id="personalModal" role="dialog">
	   <div class="modal-dialog">
	   
	     <!-- Modal content-->
	     <div class="modal-content">
	       <div class="modal-header">
	         <button type="button" class="close" data-dismiss="modal">&times;</button>
	         <h4 class="modal-title"><strong>개인정보취급방침</strong></h4>
	       </div>
	       <div class="modal-body">
	         <textarea class="form-control" rows="30">
1. 개인정보의 처리 목적
 ‘http://curtain.com’ 이하 ‘커튼’ 은(는) 다음의 목적을 위하여 개인정보를 처리하고 있으며, 다음의 목적 이외의 용도로는 이용하지 않습니다.
   1.	고객 가입의사 확인, 고객에 대한 서비스 제공에 따른 본인 식별.인증, 회원자격 유지.관리, 물품 또는 서비스 공급에 따른 금액 결제, 물품 또는 서비스의 공급.배송 등

2. 개인정보처리 위탁
    1.	 '커튼' 은(는) 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.
    2.	 (http://curtain.com’이하 '커튼') 은(는) 위탁계약 체결시 개인정보 보호법 제25조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적․관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리․감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.
    3.	위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.

3. 정보주체의 권리, 의무 및 그 행사방법
이용자는 개인정보주체로서 다음과 같은 권리를 행사할 수 있습니다.
    1.	정보주체는 <기관/회사명> (‘사이트URL’이하 ‘사이트명) 에 대해 언제든지 다음 각 호의 개인정보 보호 관련 권리를 행사할 수 있습니다.
		A.	개인정보 열람요구
		B.	오류 등이 있을 경우 정정 요구
		C.	삭제요구
		D.	처리정지 요구

4. 처리하는 개인정보의 항목 작성
	1.	‘http://curtain.com’이하 '커튼' 은(는) 다음의 개인정보 항목을 처리하고 있습니다.
		A.	고객 가입의사 확인, 고객에 대한 서비스 제공에 따른 본인 식별․인증, 회원자격 유지․관리, 물품 또는 서비스 공급에 따른 금액 결제, 물품 또는 서비스의 공급․배송
			필수항목 : 비밀번호, 로그인ID, 이메일, 신용카드정보, 은행계좌정보, 결제기록, 접속 IP 정보, 쿠키, 서비스 이용 기록, 접속 로그
			선택항목 :

5. 개인정보의 파기
 '커튼' 은(는) 원칙적으로 개인정보 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 파기의 절차, 기한 및 방법은 다음과 같습니다.
	•	파기절차
		이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.
	•	파기기한	
		이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.

6. 개인정보의 안전성 확보 조치
 '커튼' 은(는) 개인정보보호법 제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.
	1.	개인정보 취급 직원의 최소화 및 교육
		개인정보를 취급하는 직원을 지정하고 담당자에 한정시켜 최소화 하여 개인정보를 관리하는 대책을 시행하고 있습니다.
	2.	정기적인 자체 감사 실시
		개인정보 취급 관련 안정성 확보를 위해 정기적(분기 1회)으로 자체 감사를 실시하고 있습니다.
	3.	내부관리계획의 수립 및 시행
		개인정보의 안전한 처리를 위하여 내부관리계획을 수립하고 시행하고 있습니다.
	4.	개인정보의 암호화
		이용자의 개인정보 및 비밀번호는 암호화 되어 저장 및 관리되고 있어 본인만이 알 수 있으며, 중요한 데이터에 대해서는 파일 및 전송 데이터를 암호화 하거나 파일 잠금 기능을 사용하는 등의 별도 보안기능을 사용하고 있습니다.
	5.	해킹 등에 대비한 기술적 대책
		' 커튼 '은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신·점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.
	6.	개인정보에 대한 접근 제한
		개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여,변경,말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.
	7.	접속기록의 보관 및 위변조 방지
		개인정보처리시스템에 접속한 기록을 최소 6개월 이상 보관, 관리하고 있으며, 접속 기록이 위변조 및 도난, 분실되지 않도록 보안기능 사용하고 있습니다.
	8.	비인가자에 대한 출입 통제
		개인정보를 보관하고 있는 물리적 보관 장소를 별도로 두고 이에 대해 출입통제 절차를 수립, 운영하고 있습니다.

7. 개인정보 보호책임자 작성
	1.	커튼(‘http://curtain.com’이하 ‘커튼) 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.
		o	개인정보 보호책임자
			성명: 안신미
			직책: 개발자
			연락처: 010-9271-6775, ahnshinmi@gmail.com
	2.	정보주체께서는 커튼(‘http://curtain.com’이하 ‘커튼) 의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 커튼(‘http://curtain.com’이하 ‘커튼) 은(는) 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.

8. 개인정보 처리방침 변경
	1.	이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의 추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다.


	         
	         </textarea>
	       </div>
	       <div class="modal-footer">
	         <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	       </div>
	     </div>
	     
	   </div>
	 </div>
	<!-- /.container -->
		<!-- Modal -->
	 <div class="modal fade" id="termModal" role="dialog">
	   <div class="modal-dialog">
	   
	     <!-- Modal content-->
	     <div class="modal-content">
	       <div class="modal-header">
	         <button type="button" class="close" data-dismiss="modal">&times;</button>
	         <h4 class="modal-title"><strong>이용약관</strong></h4>
	       </div>
	       <div class="modal-body">
	         <textarea class="form-control" rows="30">
커튼에 방문하신 것을 환영합니다. 본 약관은 커튼 회원 및 비회원의 활동에 적용됩니다. 커튼을 이용하면 본 약관에 동의하는 것으로 간주됩니다.

제1장 총칙
제1조 (목적)
1.	커튼 서비스 약관(이하 "본 약관")은 커튼(이하 "커튼")이 제공하는 커튼 및 커튼 관련 모든 서비스와 관련하여 커튼과 회원의 권리⋅의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.

제2조 (정의)
1.	본 약관의 용어 정의는 다음 각 호와 같습니다.
A.	서비스: 구현되는 단말기(PC, TV, 휴대형 단말기 등 각종 유무선 장치를 포함)와 상관없이 "이용자"가 커튼 플랫폼에 접속하여 커튼 및 커튼이 제공하는 일체의 용역. 자료 DB화, 컴퓨터 기타 이와 유사한 정보통신처리장치를 이용한 일체의 행위를 말합니다.
B.	이용자: 커튼에 접속하여 본 약관에 따라 서비스를 받는 회원 및 비회원.
C.	비회원: 비회원이라 함은 회원에 가입하지 않고 커튼이 제공하는 서비스를 이용하는 자를 말합니다.
D.	회원: 커튼에 개인정보를 제공하여 회원등록을 한 자로서, 커튼의 정보를 제공받으며, 서비스를 이용할 수 있는 사람을 말합니다.
E.	회원ID: 회원 식별과 서비스 이용을 위하여 커튼 회원임을 확인하고 회원 자신의 비밀보호를 위해 회원자신이 선정하고 커튼이 승인하는 문자와 숫자의 조합.
F.	비밀번호: 커튼 회원이 부여 받은 회원ID와 일치된 회원임을 확인하고 자신의 비밀 보호를 위해 회원이 설정한 문자와 숫자의 조합.
G.	개인정보: 생존하는 개인에 관한 정보로서 해당 정보에 포함되어 있는 학교, 학과 등의 사항에 의하여 해당 개인을 식별할 수 있는 정보(해당 정보만으로는 특정 개인을 식별할 수 없더라도 다른 정보와 용이하게 결합하여 식별할 수 있는 것을 포함)
H.	유료서비스 : 커튼이 유료로 제공하는 각종 컨텐츠 및 제반 서비스
I.	서비스이용요금(또는 서비스요금 또는 이용요금) : 커튼이 제공하는 서비스를 사용하기 위해서 지불하는 대가.
J.	플랫폼 : 커튼이 서비스를 제공하기 위해 온라인에 구축한 API(application programming interface), DB, 기타 이와 유사한 정보통신 처리 장치로서 앱 개발자와 웹 사이트 운영자를 비롯한 타인은 위 정보통신처리장치에 API, DB 등을 제공할 수도 있고, 데이터를 검색할 수도 있습니다.
K.	어플리케이션 : 플랫폼에 접속하여 커튼이 제공하는 서비스를 이용할 수 있도록 하는 웹, 모바일 웹, 스마트폰 앱, 기타 이와 유사한 정보통신 처리장치
L.	컨텐츠 : 커튼이 생성, 수집, 기타 일체의 방법으로 플랫폼에 제공한 컨텐츠
M.	사용자 컨텐츠 : "회원"이 "서비스"를 이용함에 있어 "서비스상"에 게시한 부호, 문자, 음성, 음향, 화상, 동영상 등의 정보, 각종 파일과 링크(어플리케이션 이용 등의 방식으로 게시한 것도 포함).
N.	데이터 : 커튼이 서비스를 제공하는 과정에서 수집하고 축적한 각종 정보, 자료 및 이용의 컨텐츠.

제3조 (약관적용과 약관 외 준칙)
1.	커튼의 서비스 이용에 관하여 본 약관을 적용하며, 본 약관에 명시되지 아니한 사항과 이 약관의 해석 및 약관에 규용되지 아니한 사항에 관하여는 전기통신기본법, 전기통신사업법, 정보통신망 이용촉진 등에 관한 법률, 전자거래기본법, 신용정보의 이용 및 보호에 관한 법률, 기타 관련 법령의 규정, 또는 상관례에 따릅니다.

제4조 (약관의 공시, 준용, 효력 및 변경)
1.	본 약관은 회원 가입 시 이용자에게 공시되며, 이용자는 '동의' 또는 '동의하지 않음' 버튼을 클릭함으로써 동의 여부를 선택할 수 있습니다.
2.	약관의 내용은 서비스 약관페이지에 게시하여 공지합니다. 커튼은 사정변경의 경우와 영업상 중요한 사유가 있을 경우 관련법령을 위반하지 않는 범위에서 약관을 변경할 수 있으며, 변경된 약관은 이용자에게 적용일자를 명시하여 서비스 이용 약관 페이지와 공지사항, 로그인 시 동의창 등에 변경 기준일로부터 최소7일 전에 공지하는 것을 원칙으로 합니다. 단, 변경 내용이 귀하에 불리한 영향을 미칠 것으로 판단되는 사항에 대해서는 최소30일 이상의 유예 기간을 두어 고지합니다. 또한 동 기간 동안 개정된 약관의 내용을 약관이 게시되는 서비스 화면에 함께 게시하며, 위의 유예기간 이후 변경 약관의 효력을 발생합니다.
3.	커튼의 약관을 개정할 경우 그 개정 약관은 적용일자 이후에 체결되는 계약에만 적용됩니다. 커튼이 전항에 따라 회원에게 통지하면서 공지⋅고지일로부터 개정약관 시행일 7일 후까지 거부의사를 표시하지 아니하면 승인한 것으로 본다는 뜻을 명확하게 고지하였음에도 의사표시가 없는 경우에는 변경된 약관을 승인한 것으로 봅니다 변경된 약관에 동의하지 않을 경우 고객지원센터에 회원 탈퇴를 요청할 수 있으며, 변경된 약관의 효력 발생일 이후에도 서비스를 계속 사용할 경우 약관의 변경 사항에 동의한 것으로 간주됩니다. 다만, 기존 약관을 적용할 수 없는 특별한 사정이 있는 경우에는 커튼은 이용계약을 해지할 수 있습니다.
4.	제2조의 회원 및 본 조 제1항 내지 제3항의 규정과는 별도로 법령에 의해 개별적인 통보 및 동의가 필요한 경우에는 그 절차에 따릅니다.

제2장 서비스 이용계약
제5조 (이용 계약의 성립)
1.	커튼 회원으로 가입하여 커튼이 제공하는 서비스를 받고자 하는 자는 회원가입절차를 거쳐 가입하여야 합니다. 회원 가입 희망자가 이 약관의 내용을 숙지하고 동의 여부를 선택할 수 있으며, '동의' 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다. 약관 변경 시에는, 공지된 변경 약관에 동의하지 않을 경우 고객지원센터 문의하시면 회원등록 취소가 가능합니다.
2.	이용계약은 서비스 이용 희망자의 이용약관 동의 후 이용 신청에 대하여 커튼이 신청절차 상에서 승낙을 표시함으로써 성립합니다.
3.	커튼은 "회원"에 대해 등급별로 구분하여 이용시간, 이용횟수, 서비스 메뉴 등을 세분하여 이용에 차등을 둘 수 있습니다.
4.	커튼은 전문기관을 통한 실명확인 및 본인인증을 요청할 수 있습니다.

제6조 (이용신청)
1.	회원으로 가입하여 서비스를 이용하기를 희망하는 자는 커튼이 요청하는 소정의 가입 신청 양식에서 요구하는 사항을 기록하여 신청합니다.
2.	온라인 가입신청 양식에 기재하는 모든 회원 정보는 실제 데이터인 것으로 간주하므로 실명이나 실제 정보를 입력하지 않은 서비스 사용의 제한을 받으시거나 법적 보호를 받지 못 하실 수 있습니다.

제7조 (이용 신청의 승낙)
1.	커튼은 제6조에 따른 이용자의 이용신청에 대하여 신용정보의 이용 및 보호에 관한 법률이 정하는 신용불량자로 등록되어 있는 등의 특별한 사정이 없는 한 접수 순서대로 이용 신청을 승낙합니다.
2.	커튼은 다음 각 호에 해당하는 경우 이용신청에 대한 승낙을 제한하거나, 그 사유가 해소될 때까지 승낙을 유보할 수 있으며, 사후에라도 이용계약을 해지할 수 있습니다
A.	서비스 관련 설비에 여유가 없는 경우.
B.	기술상 지장이 있는 경우.
C.	기타 커튼의 사정상 필요하다고 인정되는 경우.
3.	커튼은 다음 각 호에 해당하는 이용계약 신청에 대하여는 이를 승낙하지 않을 수 있습니다.
A.	본인의 실명으로 신청하지 않은 경우.
B.	다른 사람의 명의를 사용하여 신청한 경우.
C.	이용 신청 시 허위의 정보를 기재하거나, 허위서류를 첨부하는 경우, 커튼이 제시하는 내용을 기재하지 않은 경우
D.	사회의 안녕과 질서 혹은 미풍양속을 저해할 목적으로 신청한 경우.
E.	이용자의 귀책사유로 인하여 승인이 불가능하거나 기타 규정한 제반 사항을 위반하며 신청하는 경우
F.	기타 커튼이 정한 이용 신청 요건이 미흡한 경우.
4.	커튼은 관계법령에서 규정하는 미성년자일 경우에 서비스별 안내에서 정하는 바에 따라 이용계약 신청의 승낙을 보류할 수 있습니다. 커튼은 "회원"에 대해 "영화및비디오물의진흥에관한법률" 및 "청소년보호법"등에 따른 등급 및 연령 준수를 위해 이용제한이나 등급별 제한을 할 수 있습니다.

제8조 (개인정보의 보호)
1.	커튼은 회원의 개인정보 보호를 위해 최선을 다하고 있습니다. 회원의 개인정보 보호 및 회원이 커튼을 이용하여 회원의 컨텐츠를 타인과 공유하고 커튼이 이를 수집 및 이용하는 방법에 대해 중요한 사항은 개인정보취급방침[k1]이 적용됩니다. 개인정보취급방침을 읽고 올바른 판단을 내리는 데 활용하시기 바랍니다.

제9조 (계약 사항의 변경)
1.	회원은 개인정보관리를 통해 언제든지 본인의 개인정보를 열람하고 수정할 수 있습니다. 다만, 서비스 관리를 위해 필요한 아이디 등은 수정이 불가능합니다.
2.	회원은 이용신청 시 기재한 사항이 변경되었을 경우 온라인 수정 및 전자우편으로 커튼에 그 변경사항을 알려야 합니다. 회원정보의 변경의 미통보로 인하여 발생한 손해의 책임은 회원에게 있습니다.

제3장 계약 당사자의 의무
제10조 (커튼의 의무)
1.	커튼은 본 약관에서 정한 바에 따라 계속적이고 안정적인 서비스의 제공을 위하여 지속적으로 노력하며, 설비에 장애가 생기거나 멸실 된 때에는 지체 없이 이를 수리 복구합니다. 다만, 천재지변, 비상사태 또는 그밖에 부득이한 경우에는 그 서비스를 일시 중단하거나 중지할 수 있으며, 이 경우에 대한 손해에 대해서는 커튼이 책임지지 않습니다. 또한 서비스 운영상, 기술상 필요하고 상당한 이유가 있는 경우 서비스의 전부, 또는 일부를 변경할 수 있습니다. 서비스의 전부, 또는 일부를 변경할 경우 다음 각 호에서 정한 바에 따릅니다.
2.	서비스의 내용, 이용방법, 이용시간에 대하여 변경이 있는 경우에는 변경사유, 변경될 서비스의 내용 및 제공일자 등을 그 변경 전에 사이트에 게시합니다
A.	무료로 제공되는 서비스의 일부 또는 전부를 수정, 중단, 변경할 경우 관련법에 특별한 규정이 없는 한 회원에게 별도의 보상을 하지 않습니다.
3.	커튼은 회원으로부터 소정의 절차에 의해 제기되는 의견이나 불만이 정당하다고 인정할 경우에는 적절한 절차를 거처 처리합니다. 처리 시 일정 기간이 소요될 경우 회원에게 게시판, 전자우편, 기타 수단 등을 이용하여 그 사유와 처리 일정을 알려드립니다.
4.	커튼은 이용계약의 체결, 계약사항의 변경 및 해지 등 이용고객과의 계약 관련 절차 및 내용 등에 있어 이용고객에게 편의를 제공하도록 노력합니다.
5.	커튼은 정보통신망 이용촉진 등에 관한 법률, 통신비밀보호법, 전기통신기본법 등 서비스의 운영, 유지와 관련 있는 법규를 준수합니다.

제11조 (회원의 의무)
1.	유료 서비스를 사용하는 커튼 회원은 서비스 이용에 대한 대가로 커튼이 책정한 서비스 요금 등을 커튼에 지불하여야 합니다. 유료서비스 이용에 따른 요금 지급 등은 커튼유료서비스 이용 및 결제약관이 적용됩니다.
2.	회원은 이 약관에서 규정하는 사항과 서비스 이용안내 또는 주의사항 등 커튼이 공지 혹은 통지하는 사항을 준수하여야 하며, 기타 커튼 운영에 방해되는 행위를 하여서는 안 됩니다. 본 약관의 규정이나 정신을 위반하거나 커튼에 위험이나 법적 피해를 초래한다면 커튼서비스 전체나 일부의 제공을 중단할 수 있습니다. 이와 같은 사실은 이메일을 통해 또는 추후 계정 접속을 시도할 때 알려 드립니다. 회원은 언제든지 본인의 계정을 삭제할 수 있습니다.
3.	회원은 서비스 이용을 위한 회원 ID, 비밀번호의 관리에 대한 책임, 본인 ID의 제3자에 의한 부정사용 등 회원의 고의⋅과실로 인해 발생하는 모든 불이익에 대한 책임을 부담합니다. 단, 이것이 커튼의 고의⋅과실로 인하여 야기된 경우는 커튼이 책임을 부담합니다. 커튼은 다음 각 호의 경우 해당 아이디의 이용을 제한할 수 있습니다.
A.	개인정보 유출 우려가 있는 경우
B.	반사회적 또는 미풍양속에 어긋나는 경우
4.	회원은 내용 별로 커튼이 서비스 공지사항에 게시하거나 전자우편 등 별도로 공지한 이용제한 사항을 준수하여야 합니다.
5.	회원은 커튼의 명시적인 동의가 없는 한 서비스의 이용권한, 기타 이용계약 상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.
6.	커튼을 안전하게 유지하기 위해 최선을 다하고 있지만 회원이 다음 각 호의 행위를 하는 경우에는 안전을 보장할 수 없습니다. 안전을 위해서 회원은 서비스 이용과 관련하여 다음 각 호에 해당되는 행위를 하여서는 아니 됩니다.
A.	다른 회원의 ID와 비밀번호 등을 도용하는 행위.
B.	본 서비스를 통하여 얻은 정보를 커튼의 사전 승낙 없이 회원의 이용 이외 목적으로 복제하거나 이를 출판 및 방송 등에 사용하거나 제3자에게 제공하는 행위.
C.	타인의 특허, 상표, 영업비밀, 저작권 기타 지적재산권을 침해하는 내용을 게시, 전자메일 또는 기타의 방법으로 타인에게 유포하는 행위.
D.	공공질서 및 미풍양속에 위반되는 저속, 음란, 증오, 폭력적인 내용의 정보, 문장, 도형 등을 전송, 게시, 전자메일 또는 기타의 방법으로 타인에게 유포하는 행위.
E.	모욕적이거나 위협적이어서 타인의 프라이버시를 침해할 수 있는 내용을 전송, 게시, 전자메일 또는 기타의 방법으로 타인에게 유포하는 행위.
F.	커튼이 제공하는 서비스를 이용하여 불법적이거나 오해의 소지가 있거나 악의적이거나 차별적인 활동을 하는 행위
G.	관련 법령에 의하여 그 전송 또는 게시가 금지되는 정보(컴퓨터 프로그램 등)를 전송 또는 게시하는 행위
H.	서비스 거부 공격과 같이 서비스의 적절한 작동을 무력하게 만들거나 과도한 부담을 주거나 저해할 수 있는 행위
I.	커튼의 허가없이 자동화된 수단(예, 수집로봇, 스파이더, 스크래퍼)을 이용하여 사용자의 컨텐츠나 정보를 수집하거나 다른 방식을 통해 접근하는 행위
J.	컴퓨터 소프트웨어, 하드웨어, 전기통신 장비의 정상적인 가동을 방해, 파괴할 목적으로 고안된 소프트웨어 바이러스, 기타 다른 컴퓨터 코드, 파일, 프로그램을 포함하고 있는 자료를 게시하거나 전자우편으로 발송하는 행위
K.	불특정 다수의 자를 대상으로 하여 광고 또는 선전을 게시하거나 스팸메일을 전송하는 등의 방법으로 커튼의 서비스를 이용하여 영리목적의 활동을 하는 행위
L.	정크메일(junk mail), 행운의 편지(chain letters), 피라미드 조직에 가입할 것을 권유하는 메일을 보내거나 기타 공서양속에 반하는 정보를 공개 또는 게시하는 행위
M.	커튼의 사전 서면 동의 없이 경연이나 경품 등(이하 "프로모션"라 함)을 커튼 서비스 내에서 제공하는 행위. 커튼이 동의하는 경우에도 홍보에 대한 책임은 전적으로 회원에게 있으며, 커튼의 프로모션가이드라인 및 관련 법규를 모두 준수해야 합니다.
N.	커튼의 직원이나 관리자를 가장하거나 사칭하여 내용물을 게시, 등록, 변경]하거나 메일을 발송하는 행위.
O.	범죄와 결부된다고 객관적으로 판단되는 행위.
P.	커튼의 승인을 받지 않고 다른 사용자의 개인정보를 수집 또는 저장하는 행위.
Q.	본 약관의 위반을 조장하거나 권장하는 행위
R.	기타 관계법령에 위배되는 행위.
7.	커튼 및 회원은 타인의 권리를 보호하기 위해 다음 각 호에서 정한 사항을 이행합니다.
A.	커튼은 타인의 권리를 존중하며, 회원도 이를 존중해야 합니다.
B.	회원은 타인의 권리를 침해하여서는 아니되며 법률을 위반하는 컨텐츠를 게시하거나 그러한 조치를 취할 수 없습니다. 이에 위반하는 컨텐츠나 정보를 커튼이 임의로 제거할 수 있습니다.
C.	상습적으로 타인의 지적 재산권을 침해하는 경우, 회원 계정이 비활성화될 수 있습니다.
D.	커튼의 서면 허가 없이 커튼의 저작권이나 상표권 및 커튼과 혼동되는 유사 상표를 사용할 수 없습니다.
E.	사용자로부터 정보를 수집하는 경우에는 사용자의 동의를 구하고 정보를 수집하는 주체는 회원(커튼이 아니라)임을 분명히 밝히고 회원이 수집하는 정보와 이용 방법을 설명하는 개인정보 취급방침을 게시해야 합니다.
F.	타인의 신분 증명 문서나 민감한 금융 정보는 게시할 수 없습니다.
G.	동의 없이 사용자를 태그하거나 비사용자에게 이메일 초대장을 전송할 수 없습니다.
8.	커튼의 모바일 서비스는 다음 각 호에서 정한 바에 따라 제공됩니다.
A.	커튼은 현재 모바일 서비스를 무료로 제공하지만, 문자 메시지 수수료와 같이 이동통신사의 정규 요금 및 수수료는 적용됩니다.
B.	회원은 커튼에 보이는 기본 정보 및 사용자 컨텐츠를 동기화(어플리케이션을 통한 동기화 포함)하는 데 필요한 모든 권리를 제공합니다.
C.	모바일에서 등록한 컨텐츠, 프로필, 개인정보는 웹과 동일한 약관이 적용됩니다.

제4장 서비스 이용
제12조 (정보의 제공)
1.	이용자는 커튼의 자체 서비스 및 공지내용을 이메일, 휴대폰 문자메시지로 받을 수 있으며, 광고 정보의 경우 수신 여부를 선택 할 수 있고, 수신에 동의한 회원에 한하여 광고 정보를 받게 됩니다. 다만, 회원의 거래관련 정보 및 고객문의 등에 대한 회신에 있어서는 제외됩니다.

제13조 (회원의 컨텐츠)
1.	커튼의 각 게시판에 이용자가 글을 작성할 경우 글쓴이의 IP는 비공개를 원칙으로 합니다. 또한 커튼은 회원이 게시하거나 등록하는 서비스내의 내용물이 다음 각 호에 해당한다고 판단되는 경우에 게시판 이용 금지 및 강제 회원탈퇴 등 이용 상 제한을 할 수 있습니다.
A.	다른 회원 또는 제3자를 비방하거나 중상모략으로 명예를 손상시키는 내용인 경우.
B.	공공질서 및 미풍양속에 위반되는 내용인 경우(예. 욕설, 음란 등).
C.	범죄적 행위에 결부된다고 인정되는 내용일 경우 (예. 자살 모의, 도박, 절도 등).
D.	커튼 및 다른 회원 혹은 제3자의 저작권을 포함한 지적재산권 등 기타 권리를 침해하는 내용인 경우.
E.	서비스에 위해를 가하거나 바이러스 감염자료 게재 등으로 서비스의 건전한 운영을 저해하는 경우(예. 게시판 도배 행위, 해킹 등).
F.	기타 관계법령에 위반되거나, 본 약관 제11조 제7항에 해당한다고 판단되는 경우.

제14조 (컨텐츠 관리)
1.	커튼은 건전한 통신문화정착과 효율적인 서비스 운영을 위하여 이용자가 게시하거나 제공하는 자료가 다음에 해당한다고 판단되는 경우에 임의로 삭제, 적합한 위치로의 자료이동, 등록거부를 할 수 있습니다.
A.	커튼에서 규정한 게시기간을 초과한 경우.
B.	게시판의 성격에 부합하지 않는 게시물의 경우.
C.	제3자 또는 커튼의 명예, 신용을 훼손하거나 오용하는 내용 또는 영업을 방해하는 내용인 경우.
D.	범죄적 행위와 결부된다고 객관적으로 인정되는 내용인 경우.
E.	제3자 또는 커튼의 지적재산권 등 권리를 침해하는 내용인 경우.
F.	제11조 제8항에 해당하는 경우
G.	"정보통신망법" 및 "저작권법"등 관계법령 및 커튼의 게시물 관리 규정에 위배된다고 판단되는 경우.
2.	정보통신망 이용촉진 및 정보보호 등에 관한 법률(이하 "정보통신망법"이라 합니다)의 규정에 의해 다른 회원의 공개된 게시물 등이 본인의 사생활을 침해하거나 명예를 훼손하는 등 권리를 침해 받은 회원 또는 제3자(이하 "삭제 등 신청인"이라 합니다)는 그 침해사실을 소명하여 커튼에 해당 게시물 등의 삭제 또는 반박 내용의 게재를 요청할 수 있습니다. 이 경우 커튼은 해당 게시물 등의 권리 침해 여부를 판단할 수 없거나 당사자 간의 다툼이 예상되는 경우 해당 게시물 등에 대한 접근을 임시적으로 차단하는 조치(이하 "임시조치"라 합니다)를 최장 30일까지 취합니다.
3.	제2항에 의해 본인의 게시물 등이 임시조치 된 회원(이하 "게시자"라 합니다)은 임시조치기간 중 커튼에 해당 게시물 등을 복원해 줄 것을 요청(이하 "재게시 청구"라 합니다)할 수 있으며, 커튼은 임시조치된 게시물의 명예훼손 등 판단에 대한 방송통신심의위원회 심의 요청에 대한 게시자 및 삭제 등 신청인의 동의가 있는 경우 게시자 및 삭제 등 신청인을 대리하여 이를 요청하고 동의가 없는 경우 커튼이 이를 판단하여 게시물 등의 복원 여부를 결정합니다. 게시자의 재게시 청구가 있는 경우 임시조치 기간 내에 방송통신심의위원회 또는 커튼의 결정이 있으면 그 결정에 따르고 그 결정이 임시조치 기간 내에 있지 않는 경우 해당 게시물 등은 임시조치 만료일 이후 복원됩니다. 재게시 청구가 없는 경우 해당 게시물 등은 임시조치 기간 만료 이후 삭제됩니다.
4.	커튼은 서비스 내에 게시된 게시물 등이 사생활 침해 또는 명예훼손 등 제3자의 권리를 침해한다고 인정하는 경우 제2항에 따른 회원 또는 제3자의 신고가 없는 경우에도 임시조치(이하 "임의의 임시조치"라 합니다)를 취할 수 있습니다. 임의의 임시조치된 게시물의 처리 절차는 제2항 후단 및 제3항의 규정에 따릅니다.
5.	회원의 게시물 등으로 인한 법률상 이익 침해를 근거로, 다른 회원 또는 제3자가 회원 또는 커튼을 대상으로 하여 민형사상의 법적 조치(예: 형사고소, 가처분 신청⋅손해배상청구 등 민사소송의 제기)를 취하는 경우, 커튼은 동 법적 조치의 결과인 법원의 확정판결이 있을 때까지 관련 게시물 등에 대한 접근을 잠정적으로 제한할 수 있습니다. 게시물 등의 접근 제한과 관련한 법적 조치의 소명, 법원의 확정 판결에 대한 소명 책임은 게시물 등에 대한 조치를 요청하는 자가 부담합니다.

제15조 (게시물의 저작권 및 권리의 귀속과 컨텐츠의 공유)
1.	서비스에 게재된 자료에 대한 권리는 다음 각 호의 내용과 같습니다.
A.	커튼이 작성한 저작물에 대한 저작권 기타 지적재산권은 커튼에 귀속합니다. 단, "회원"의 "게시물" 및 제휴계약에 따라 제공된 저작물 등은 제외합니다.
B.	"커튼"는 서비스와 관련하여 "회원"에게 "커튼"이 정한 이용조건에 따라 계정, "아이디", 컨텐츠, 등을 이용할 수 있는 이용권만을 부여하며, 회원은 서비스를 이용함으로써 얻은 정보를 커튼의 사전 승낙 없이 복제, 송신, 출판, 전송, 배포, 방송 기타 방법에 의하여 영리목적으로 이용하거나 판매, 담보제공 혹은 제3자에게 이용하게 하여서는 안됩니다.
C.	회원이 게시하는 컨텐츠와 정보의 모든 소유권은 회원에게 있습니다.
D.	회원은 자신이 창작, 등록한 사진이나 동영상과 같은 지적 재산권이 적용되는 컨텐츠(이하 "사용자 컨텐츠"라 함)에 대해 다음의 각 목에서 정한 바에 따라 커튼에게 사용권한을 부여합니다. 회원은 커튼에 대한 사용권한 부여를 회원의 개인 정보 설정 및 어플리케이션 설정에서 결정할 수 있습니다.
i.	회원은 회원의 사용자 컨텐츠를 서비스 개선 목적으로 분석할 수 있는 권한을 커튼에게 부여합니다. 단, 커튼에 부여된 권한은 전 세계를 대상으로 하며, 비독점적이고, 양도 또는 재면허를 부여할 수 있고, 그에 대한 사용료, 기타 반대급부는 무상으로 합니다.
ii.	회원이 커튼에 부여한 권한은 회원이 회원의 사용자 컨텐츠나 계정을 삭제할 때 종료됩니다. 단, 회원이 컨텐츠를 타인과 공유하고 타인이 이를 삭제하지 않았을 경우에는 그러하지 아니합니다. 외부 API등 오픈플랫폼을 제공하는 서비스에 저장되는 경우 컨텐츠 유실 등의 책임은 커튼이 부담하지 않습니다.
iii.	사용자 컨텐츠를 삭제하면 컴퓨터의 휴지통을 비우는 것과 유사한 방식으로 컨텐츠가 삭제됩니다. 단, 제거된 컨텐츠는 일정 기간 동안 백업 사본으로 존속될 수 있으며, 백업 사본으로 존속되는 동안에도 타인에게 제공되지는 않습니다.
iv.	어플리케이션을 이용할 경우 회원의 컨텐츠나 정보는 어플리케이션과 공유됩니다. 해당 어플리케이션과 회원의 계약은 어플리케이션이 해당 컨텐츠나 정보를 이용, 저장, 전송하는 방법에 적용됩니다.
E.	커튼에 대한 의견을 포함한 제안은 언제든지 환영하지만, (회원에게 제안 의무가 없는 것처럼) 커튼도 보상 의무 없이 이를 활용할 수 있습니다
F.	커튼은 제1호 이외의 방법으로 회원의 사용자 컨텐츠를 상업적으로 이용하고자 하는 경우, 전화, 팩스, 전자우편 등의 방법을 통해 사전에 회원의 동의를 얻어야 합니다. 단, 회원정보에 등록된 연락처가 사실과 다르거나 회원이 커튼의 연락에 응하지 않아 사전에 동의를 구하지 못한 경우, 커튼은 사후에 동의 절차를 구할 수 있습니다. 커튼이 본 호에 따라 회원의 사용자 컨텐츠를 상업적으로 이용할 경우 커튼은 별도의 보상제도를 운영할 수 있습니다.
2.	커튼 웹사이트에 링크 공유 버튼을 포함시킨다면 다음 각 호에서 정한 사항이 추가 적용됩니다.
A.	링크 공유 버튼을 이용할 권한을 부여하는 것은 이용자가 회원의 웹사이트에서 커튼으로 링크하거나 컨텐츠를 게시할 수 있도록 하는 것입니다.
B.	회원은 해당 링크나 컨텐츠를 커튼이 이용할 권한을 커튼과 타인에게 부여합니다.
C.	커튼에 게시되면 본 정책을 위반할 수 있는 컨텐츠가 담긴 페이지에는 링크 공유 버튼을 게시할 수 없습니다.

제16조 (광고게재 및 광고주와의 거래)
1.	커튼은 본 서비스 상에 게재된 광고주의 판촉활동 및 광고주와 회원간의 교신 또는 거래의 결과로서 발생하는 모든 손실 또는 손해에 대해 책임을 지지 않습니다.

제17조 (서비스 이용시간)
1.	서비스의 이용은 커튼의 업무상 또는 기술상 특별한 지장이 없는 한, 연중무휴1일 24시간 가능함을 원칙으로 합니다. 다만 정기 점검 등의 필요로 커튼이 정한 기간은 예외입니다.
2.	커튼은 서비스를 일정범위로 분할하여 각 범위 별로 이용가능 시간을 별도로 정할 수 있습니다. 이 경우 사전에 공지를 통해 그 내용을 알립니다.

제18조 (서비스 제공의 중지 등)
1.	커튼은 다음에 해당하는 경우 서비스 제공을 중지할 수 있습니다.
A.	정보통신설비의 보수점검⋅교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 부득이하게 서비스의 제공을 일시적으로 중단할 수 있습니다.
B.	전기통신사업법에 규정된 기간통신사업자가 전기통신 서비스를 중지했을 경우.
C.	기타 불가항력적 사유가 있는 경우
2.	커튼은 국가비상사태, 정전, 서비스 설비의 장애 또는 서비스 이용의 폭주 등으로 정상적인 서비스 이용에 지장이 있는 때에는 서비스의 전부 또는 일부를 제한하거나 중지할 수 있습니다.
3.	커튼은 제1항 및 제2항의 규정에 의하여 서비스의 이용을 제한하거나 중지한 때에는 그 사유 및 제한기간 등을 커튼을 통하여 공지하거나 전자우편 기타의 방법으로 지체 없이 회원에게 알려야 합니다. 다만, 사전에 통지할 수 없는 부득이한 사유가 있는 경우 사후에 통지할 수 있습니다.

제5장 계약해지 및 이용제한
제19조 (계약 해지 및 이용 제한)
1.	회원이 이용 계약을 해지하고자 하는 경우에는 온라인 상에서 즉시 회원탈퇴를 실행 하시거나 회원탈퇴 문의 메일을 온라인을 통해 보내주시면 됩니다. 문의 메일을 통해 회원탈퇴를 진행하실 경우에는 최대 24시간의 처리 기간을 갖고 있습니다. 회원은 언제든지 서비스초기화면의 고객센터 또는 내 정보 관리 메뉴 등을 통하여 이용계약 해지 신청을 할 수 있으며, 커튼은 관련법 등이 정하는 바에 따라 이를 즉시 처리합니다.
2.	회원이 계약을 해지할 경우, 관련법 및 개인정보보호정책에 따라 커튼이 회원정보를 보유하는 경우를 제외하고는 해지 즉시 회원의 모든 데이터는 소멸됩니다.
3.	"회원"이 계약을 해지하는 경우, 본인 계정에 등록된 게시물 일체는 삭제됩니다. 다만, 타인에 의해 담기, 스크랩 등이 되어 재 게시되거나, 공용게시판에 등록된 "게시물" 등은 삭제되지 않으며 삭제를 원할 경우 사전에 삭제 후 탈퇴하여야 합니다.
4.	커튼은 회원이 본 약관 제11조의 의무를 위반하거나, 기타 본 약관에 따른 의무를 위반하는 행위를 하였을 경우 이용계약을 해지하거나 또는 서비스 이용 중지, 기타 서비스 이용을 제한할 수 있습니다.

제6장 손해배상 및 기타 사항
제20조 (손해배상 및 면책)
1.	천재지변 또는 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 커튼의 서비스 제공에 관한 책임이 면제됩니다.
2.	커튼은 회원의 귀책사유로 인한 서비스 이용의 장애에 대하여 책임을 지지 않습니다.
3.	커튼은 회원이 서비스를 이용하여 기대하는 수익을 상실한 것이나 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 않습니다.
4.	커튼은 제휴회사가 제공하거나 회원이 서비스에 게재한 정보, 자료 등의 사실 유무 신뢰도, 정확성 등에 관하여는 책임을 지지 않습니다.
5.	커튼은 서비스 이용과 관련하여 가입자에게 발생한 손해 가운데 가입자의 고의, 과실에 의한 손해에 대하여 책임을 지지 않습니다.

제21조 (이용자 보호)
1.	커튼은 이용자의 권익을 보호하기 위해, 상호명, 대표자 성명, 영업소 소재지와 전화번호, E-mail, 사업자등록번호 등 커튼의 신원정보를 홈페이지를 통해 표시하고 있습니다.
2.	커튼은 서비스 이용약관을 공시하여 이용자의 피해를 최소화하기 위해 노력합니다.

제22조 (분쟁 해결)
1.	커튼은 이용자로부터 제기되는 불만사항 및 의견을 우선적으로 처리합니다. 다만, 신속한 처리가 곤란한 경우에는 이용자에게 그 사유와 처리 일정을 즉시 통보합니다.
2.	커튼과 회원은 서비스와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한 모든 노력을 합니다.
3.	회원의 커튼에서의 활동이나 컨텐츠, 정보와 관련하여 타인이 커튼을 상대로 클레임을 제기하면, 회원은 해당 클레임과 관련된 손해나 손실, 경비(합리적인 변호사 수임료와 비용 포함)로부터 커튼을 면책 및 보호해야 합니다.
4.	커튼은 제3자의 활동, 컨텐츠, 정보, 데이터에 대해 책임지지 않으며, 인지 여부에 관계 없이 해당 제3자를 상대로 제기하는 클레임으로 인하거나 그와 관련하여 발생하는 클레임과 손해로부터 커튼과 커튼의 이사, 임직원을 면책합니다. 본 조항이나 커튼으로 인하여 발생하는 채무 총액은 지난 일년간 커튼에 납부한 금액을 초과할 수 없습니다.
5.	제1항 내지 제4항과 관련하여 발생하는 법률분쟁은 서울중앙지방법원을 배타적인 제1심 전속관할 법원으로 하여 해결합니다.

부칙
제1조 (약관의 효력 및 변경)
1.	이 약관은 2016년 4월 9일부터 시행하며, 이 약관이 제정되기 전에 가입한 회원에게도 적용됩니다.

</textarea>
	       </div>
	       <div class="modal-footer">
	         <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	       </div>
	     </div>
	     
	   </div>
	 </div>
	<!-- /.container -->	
	
</body>

</html>
