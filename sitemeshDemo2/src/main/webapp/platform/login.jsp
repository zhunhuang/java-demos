<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/platform"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <style>
        body{background:#F0F0F0;}
        .qsso{position:absolute;top:48%;left:45%;width:480px;height:200px;padding:10px;margin:-150px 0 0 -200px;background:#effbff;border:1px solid #b9e0f0;text-align: center;}
        .qsso p{margin:30px 0;line-height: 50px; font-family:"Microsoft Yahei";font-size: 32px; text-shadow:1px 1px 1px;color:#00A0D1;}
        .qsso img{padding:2px;margin:0 20px 0 0;vertical-align:middle;border:1px solid #b9e0f0;}
        .qsso button {
            background:#00A0D1;
            background:-moz-linear-gradient(top,#00A0D1 0%,#008DB8 100%);
            background:-webkit-gradient(linear,left top,left bottom,color-stop(0%,#00A0D1),color-stop(100%,#008DB8));
            background:-webkit-linear-gradient(top,#00A0D1 0%,#008DB8 100%);
            background:-o-linear-gradient(top,#00A0D1 0%,#008DB8 100%);
            background:-ms-linear-gradient(top,#00A0D1 0%,#008DB8 100%);
            background:linear-gradient(top,#00A0D1 0%,#008DB8 100%);
            _filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#00A0D1',endColorstr='#008DB8',GradientType=0);
            padding:8px 20px;
            color:#cfebf3;
            font-family:'Helvetica Neue',sans-serif;
            font-size:13px;
            border-radius:40px;
            -moz-border-radius:40px;
            -webkit-border-radius:40px;
            border:1px solid #095B7E;
            font-size: 29px;
            cursor: pointer;
        }
        .contact{position:absolute;top:40%;left:45%;width:480px;height:50px;padding:10px;margin:-150px 0 0 -200px;text-align: center;}
    </style>
</head>
<body>
<div class="qsso">
    <p>
        <img src="https://baidu.com/img/logo.png" border="0" alt=""/>
        管理平台
    </p>
    <form action="/login.do" method="post">
        <p>userName: <input type="text" name="username" /></p>
        <p>password: <input type="password" name="password" /></p>
        <input type="submit" value="Submit" />
    </form>
</div>
<c:if test="${param.error eq '1'}">
    <div class="contact">
        <font color="red">无平台权限</font>
    </div>
</c:if>
</body>
</html>