<%@page import="com.ktds.oph.operationHistory.vo.OperationHistoryVO"%>
<%@page import="com.ktds.oph.operationHistory.vo.ActionCode"%>
<%@page import="com.ktds.oph.operationHistory.vo.BuildDescription"%>
<%@page import="com.ktds.oph.operationHistory.vo.Description"%>
<%@page import="com.ktds.oph.operationHistory.biz.OperationHistoryBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="./WEB-INF/view/common/header.jsp"></jsp:include>


<%
	OperationHistoryBiz historyBiz = new OperationHistoryBiz();
	OperationHistoryVO historyVO = new OperationHistoryVO();
	historyVO.setIp(request.getRemoteHost());
	historyVO.setUrl(request.getRequestURI());
	historyVO.setActionCode(ActionCode.ADMIN_PAGE);
	historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_PAGE, request.getRemoteHost()));
	System.out.println("관리자인덱스접근");
	historyBiz.addHistory(historyVO);
%>
<script type="text/javascript">
	$(document).ready( function () {
		var errorCode = <%=request.getParameter("errorCode")%>;
		if (errorCode == 1) {
			alert("아이디 혹은 비밀번호를 확인하세요.");
		}
	});
</script>

<jsp:include page="./WEB-INF/view/common/center.jsp"></jsp:include>
<footer class="container-fluid text-center">
  <p>cocomo@cocomo</p>
</footer>

</body>
</html>