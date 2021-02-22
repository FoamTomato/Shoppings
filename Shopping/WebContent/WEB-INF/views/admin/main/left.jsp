<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="${pageContext.request.contextPath}/resources/admin/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/vendor/metisMenu/dist/metisMenu.min.js"></script>
<!-- Navigation -->
<aside id="menu" style="position: fixed;">
    <div id="navigation">
        <div class="profile-picture">
            <a href="#">
                <img style="width: 150px" src="${pageContext.request.contextPath}/resources/admin/images/logo.png" class="img-circle m-b" alt="logo">
            </a>
        </div>
        <ul class="nav" id="side-menu">
        		<c:if test="${fn:contains(jurisdiction.jurisdiction,'classifys_product')}">
	            <li id="leftMenuProduct" class="sidebar-search">
	                <a href="#"><span class="nav-label">商品管理</span><span class="fa arrow"></span> </a>
	                <ul class="nav nav-second-level">
	                    	<li><a href="${pageContext.request.contextPath}/admin/productCategoryList">商品分类</a></li>
	                    	<li><a href="${pageContext.request.contextPath}/admin/productList">商品列表</a></li>
	                   <%-- <li><a href="${pageContext.request.contextPath}/admin/optionInfo">属性管理</a></li> --%>
	                </ul>
	            </li> 
            	</c:if>
        		<c:if test="${fn:contains(jurisdiction.jurisdiction,'orders')}">
	            <li id="leftMenuProduct" >
	                <a href="#"><span class="nav-label">订单管理</span><span class="fa arrow"></span> </a>
	                <ul class="nav nav-second-level" >
	                    <li><a href="${pageContext.request.contextPath}/order/select">订单列表</a></li>
	                    <li><a href="${pageContext.request.contextPath}/order/Outof">集拼列表</a></li>
	                    <li><a href="${pageContext.request.contextPath}/preview/main">运费预览</a></li>
	                    <li><a href="${pageContext.request.contextPath}/Logistics/tail">查看跟踪</a></li>
	                    <li><a href="${pageContext.request.contextPath}/Logistics/channel/add">渠道更改</a></li>
	                    <li><a href="${pageContext.request.contextPath}/Logistics/country/transform">国家更改</a></li>
	                    <li><a href="${pageContext.request.contextPath}/statistics/skip">订单统计</a></li>
	                </ul>
	            </li>
	            </c:if>
        		<c:if test="${fn:contains(jurisdiction.jurisdiction,'Users')}">
				 <li id="leftMenuArticle">
	                <a href="#"><span class="nav-label">用户管理</span><span class="fa arrow"></span> </a>
	                <ul class="nav nav-second-level">
		                    <li><a href="${pageContext.request.contextPath}/admin/role">权限列表</a></li>
		                   <li> <a href="${pageContext.request.contextPath}/admin/user"><span class="nav-label">用户列表</span></a></li>
	                </ul>
	            </li>
	            </c:if>
	            
        		<c:if test="${fn:contains(jurisdiction.jurisdiction,'qx')}">
	            <li id="leftMenuArticle">
	                <a href="#"><span class="nav-label">邮件管理</span><span class="fa arrow"></span> </a>
	                <ul class="nav nav-second-level">
		                    <li><a href="${pageContext.request.contextPath}/email/postEmail">发件箱</a></li>
		                    <li><a href="${pageContext.request.contextPath}/email/giveEmail">收件箱</a></li>
		                    <li><a href="${pageContext.request.contextPath}/email/getEmail">客服邮箱</a></li>
	                </ul>
            	</li>
	            <li id="leftMenuArticle">
	                <a href="#"><span class="nav-label">亚马逊</span><span class="fa arrow"></span> </a>
	                <ul class="nav nav-second-level">
		                    <li><a href="${pageContext.request.contextPath}/amazon/postProduct">上传产品</a></li>
		                    <li><a href="${pageContext.request.contextPath}/amazon/TransferAccountAuthorization">账户授权</a></li>
	                </ul>
            	</li>
            	</c:if>
            	<c:if test="${fn:contains(jurisdiction.jurisdiction,'lazada')}">
	            <li id="leftMenuArticle">
	                <a href="#"><span class="nav-label">Lazada</span><span class="fa arrow"></span> </a>
	                <ul class="nav nav-second-level">
		                    <li><a href="${pageContext.request.contextPath}/lazada/account">账户授权</a></li>
		                    <%-- <li><a href="${pageContext.request.contextPath}/lazada/postProduct">上传产品</a></li> --%>
		                    <li><a href="${pageContext.request.contextPath}/lazada/postProduct2/1">上传产品</a></li>
		                    <li><a href="${pageContext.request.contextPath}/lazada/orderList">订单列表</a></li>
		                    <li><a href="${pageContext.request.contextPath}/lazada/productList">商品列表</a></li>
	                </ul>
            	</li>
            	</c:if>
            	<c:if test="${fn:contains(jurisdiction.jurisdiction,'case')}">
	            <li id="leftMenuArticle">
	                <a href="#"><span class="nav-label">手机壳</span><span class="fa arrow"></span> </a>
	                <ul class="nav nav-second-level">
		                    <li><a href="${pageContext.request.contextPath}/phoneCases/classAngles">角度管理</a></li>
		                    <li><a href="${pageContext.request.contextPath}/phoneCases/classification">品类管理</a></li>
		                    <li><a href="${pageContext.request.contextPath}/phoneCases/customization">定制壳</a></li>
		                    <li><a href="${pageContext.request.contextPath}/phoneCases/historyCustomization">历史定制</a></li>
	                </ul>
            	</li>
            	</c:if>
            <%--<li>
                <a href="${pageContext.request.contextPath}/admin/comments"><span class="nav-label">关键词替换</span></a>
            </li>
            <li id="leftMenuArticle">
                <a href="#"><span class="nav-label">产品批量导入</span><span class="fa arrow"></span> </a>
                <ul class="nav nav-second-level">
                    <li><a href="${pageContext.request.contextPath}/admin/articlecategory">分类管理</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/article">文章管理</a></li>
                </ul>
            </li> --%>
        </ul>
    </div>
</aside>
<script type="text/javascript">
$(function() {
    $('#side-menu').metisMenu(); // ul.nav#side-menu
})
</script>