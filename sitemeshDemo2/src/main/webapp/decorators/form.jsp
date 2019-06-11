<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/include/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
	<script type="text/javascript">
	</script>
	<%@include file="/platform/include/common/_head.jsp"%>
	<sitemesh:write property='head' />
</head>
<body>
	<%@include file="/platform/include/common/header.jsp"%>
	<div class="container">
		<div class="row">
			<sitemesh:write property='body' />
		</div>
	</div>
	<hr>
	<%@include file="/platform/include/common/footer.jsp"%>
</body>
</html>