<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>
<%@ taglib uri="/WEB-INF/tlds/security.tld" prefix="security" %>

<c:set var="ctx" value="${pageContext.request.contextPath}/platform"/>
<c:set var="systemName" value="nolan后台管理系统"/>

<script type="text/javascript">
    CTX = '${ctx}';
</script>