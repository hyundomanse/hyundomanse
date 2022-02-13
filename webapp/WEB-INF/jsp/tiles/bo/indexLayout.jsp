<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>devwork</title>
<link type="text/css" rel="stylesheet" href="/css/bo/default.css">
<link type="text/css" rel="stylesheet" href="/css/jquery-ui.css">
<script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/js/fo/default.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>

</head>
<body>
<!-- 헤더 -->
	<div id="contents"><tiles:insertAttribute name="content" /></div>
</body>
</html>