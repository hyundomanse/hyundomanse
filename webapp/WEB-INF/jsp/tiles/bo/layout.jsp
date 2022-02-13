<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>devwork</title>

<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/css/bootstrap-reset.css">
<link type="text/css" rel="stylesheet" href="/css/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="/css/slidebars.css">
<link type="text/css" rel="stylesheet" href="/css/style.css">
<link type="text/css" rel="stylesheet" href="/css/adm.css">
<link type="text/css" rel="stylesheet" href="/css/bo/default.css">

<script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/js/json2.js"></script> 
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.validator.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="/js/jquery.maskedinput-1.3.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript" src="/js/jquery.number.js"></script>
<script type="text/javascript" src="/js/numericInput.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/bo/default.js"></script>

<!-- Bootstrap -->
<script type="text/javascript" src="/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap/jquery.dcjqaccordion.2.7.js" class="include"></script>
<script type="text/javascript" src="/js/bootstrap/jquery.scrollTo.min.js"></script>
<script type="text/javascript" src="/js/bootstrap/slidebars.min.js"></script>
<script type="text/javascript" src="/js/bootstrap/common-scripts.js"></script>
</head>
 
<body>
	<section id="container">
	    <!--header start-->
	    <header class="header white-bg">
	    	<tiles:insertAttribute name="header"/>
	    </header>
	    <!--header end-->
	    
	    <!--sidebar start-->
	    <aside>
	    	<tiles:insertAttribute name="left" />
	    </aside>
	    <!--sidebar end-->
	    
	    <!--main content: start-->
	    <section id="main-content">
			<tiles:insertAttribute name="content" />
		</section>
		<!--main content: end -->
		
		<!--footer start-->
		<footer class="site-footer">
		    <tiles:insertAttribute name="footer"/>
		</footer>
		<!--footer end-->		
	</section>
</html>
