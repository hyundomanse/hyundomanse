<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="ko" lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>devwork</title>
<script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
<script language="javascript" src="/js/bo/default.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="/css/bo/default.css">
<script type="text/javascript">
</script>
</head>

<body class="bg_login">
<form name="loginForm" id="loginForm">
<div class="logbox">
  <div id="intabdiv">
    <h1>로그인</h1>
    <div class="txt_ip"> </div>
    <p>devwork 관리자 로그인입니다.</p>
    <div class="l_part">
      <dl class="width64 float_l">
        <dt><strong>아이디</strong></dt>
        <dd>
          <input type="text" id="adminId" name="adminId" class="width60 bg_fff margin_b5">
        </dd>
        <dt><strong>비밀번호</strong></dt>
        <dd>
          <input type="password" id="adminPassword" name="adminPassword" class="width60 bg_fff"  OnKeyPress="JavaScript:if (event.keyCode == 13) login();" >
        </dd>
      </dl>
      <input type="button" id="loginBtn" value="로그인" class="btn_2row float_r" onclick="javascript:login(); return false;"/>
    </div>
  </div>
</div>
</form>
</body>
</html>
