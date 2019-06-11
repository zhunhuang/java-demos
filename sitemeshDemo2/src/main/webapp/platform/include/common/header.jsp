<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/platform/include/common/taglibs.jsp" %>

<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <ul class="nav navbar-nav">
                <li>
                    <a class="navbar-brand" href="javascript:void(0)">会员权益</a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_OTHER">
                    <li name="memberCard/"><a href="${ctx}/memberCard/">会员卡</a></li>
                    <li name="privilege/"><a href="${ctx}/privilege/">权益</a></li>
                    <%--<li name="data/"><a href="${ctx}/data/">数据</a></li>--%>
                </security:authorize>
                <li class="dropdown subMenu">
                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">售卖信息<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li name="saleInfo/trend"><a href="${ctx}/saleInfo/trend">售卖走势</a></li>
                        <li name="saleInfo/"><a href="${ctx}/saleInfo/">售卖信息</a></li>
                        <li name="saleInfo/hourly"><a href="${ctx}/saleInfo/hourly">按小时对比</a></li>
                        <li name="saleInfo/source"><a href="${ctx}/saleInfo/source">分来源统计</a></li>
                    </ul>
                </li>
                <security:authorize ifAnyGranted="ROLE_ADMIN">
                    <li class="dropdown subMenu">
                        <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">新建功能<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li name="newCardType"><a href="${ctx}/memberCard/newCardType">新建会员类型</a></li>
                            <li name="newCard"><a href="${ctx}/memberCard/newCard">新建会员卡</a></li>
                            <li name="new"><a href="${ctx}/privilege/new">新建权益</a></li>
                        </ul>
                    </li>
                    <li name="user/"><a href="${ctx}/user/">用户管理</a></li>
                </security:authorize>
            </ul>
        </div>
        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <li>
                    <a class="navbar-brand" href="javascript:void(0)">${currentUser}</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
