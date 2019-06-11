<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/platform/include/common/taglibs.jsp" %>
<html>
<head>
	<script type="text/javascript" src="${ctx}/include/js/jslib/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function () {
			$("#securityForm").submit();
		});
	</script>
</head>
<body>
	<form id="securityForm" action="/platform/securityLogin" method="post">
		<input type="hidden" name="username" value="${username}" />
		<input type="hidden" name="password" value="000000" />
		<input type="hidden" name="_spring_security_remember_me" value="true" />
	</form>
</body>
</html>

