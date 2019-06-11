<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--装饰模板, 用来规定网站的模板样式--%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/include/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
    <script type="text/javascript">
    </script>
    <%@include file="/platform/include/common/_head.jsp"%>
    <script src="${ctx}/include/js/simple_dim.js"></script>
</head>
<body>
<%@include file="/platform/include/common/header.jsp"%>
<hr>
<decorator:body />
<%@include file="/platform/include/common/footer.jsp"%>
</body>
</html>