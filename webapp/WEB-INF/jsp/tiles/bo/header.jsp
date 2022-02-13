<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/include/bo/common.jsp" %>

<script language="javascript" src="/js/bo/default.js" type="text/javascript"></script>
<script type="text/javascript">
</script>

<div class="sidebar-toggle-box">
	<div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
</div>
<!--logo start-->
<a href="/boMain.do" class="logo">devwork <span>관리자</span></a>
<!--logo end-->

<div class="nav notify-row" id="top_menu">
	<!--  notification start -->
	<ul class="nav top-menu">
    <!-- settings start -->
	<!-- header gnb 필요 시 추가: start -->
	<!-- <li>header 추가영역</li> -->
	<!-- header gnb 필요 시 추가: end -->
    <!-- notification dropdown end -->
   </ul>
	<!--  notification end -->
</div>

<div class="top-nav ">
	<!--search & user info start-->
	<ul class="nav pull-right top-menu">
		<!-- user login dropdown start-->
		<li class="dropdown">
			<a data-toggle="dropdown" class="dropdown-toggle" href="#">
				<img alt="" src="/images/bootstrap/no-profile-img.gif">
				<span class="username"><font color=FF0000><%=SESSION_ADMINID %></font>님 로그인 중입니다. <input type="button" value="로그아웃" class="btn_square_red" onclick="javascript:logout(); return false;" /></span>
           	</a>
       	</li>
       <!-- user login dropdown end -->
	</ul>
	<!--search & user info end-->
</div>
        
        
