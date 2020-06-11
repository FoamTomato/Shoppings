<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   
    <title>产品库后台</title>
	<jsp:include page="../commonCss.jsp"/> 
</head>
<body class="blank" onload='document.loginForm.username.focus();' >
<div class="color-line"></div>
<div class="login-container">
    <div class="row">
        <div class="col-md-12">
            <div class="text-center m-b-md">
                <h3>产品库后台</h3>
                <small></small>
            </div>
		    <c:if test="${not empty error}">
				<div class="error" style='color:red' >${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg" style='color:red'>${msg}</div>
			</c:if>
            <div class="hpanel">
                <div class="panel-body">
                        <form class="form-signin" name='loginForm' action="<c:url value='/auth/login_check?targetUrl=${targetUrl}' />" method='POST'>
						     <div class="form-group">
                                <label class="control-label" for="username">用户名</label>
                                <input type="text" placeholder="用户名" title="请输入用户名" required="" value="" name="username" id="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="password">密码</label>
                                <input type="password" title="请输入用户名" placeholder="******" required="" value="" name="password" id="password" class="form-control">
                            </div>
                            <button class="btn btn-success btn-block" type="submit">登录</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../commonJs.jsp"/> 
</body>
</html>