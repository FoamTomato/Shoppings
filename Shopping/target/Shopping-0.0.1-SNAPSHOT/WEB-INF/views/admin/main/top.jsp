<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<div id="header">
    <div class="color-line">
    </div>
    <div id="logo" class="light-version">
        <span>
          	产品库后台
        </span>
    </div>
    <nav role="navigation"><!-- 
        <div class="header-link hide-menu"><i class="fa fa-bars"></i></div> -->
        <div class="navbar-right">
            <ul class="nav navbar-nav no-borders">
                <li class="dropdown">
                    <nobr id="username" style="line-height: 55px;"><security:authentication property="principal.username"></security:authentication></nobr>
                </li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/logout">
                        <i class="pe-7s-upload pe-rotate-90"></i>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>