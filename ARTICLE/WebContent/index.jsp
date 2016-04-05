<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="./WEB-INF/view/common/header.jsp"></jsp:include>

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