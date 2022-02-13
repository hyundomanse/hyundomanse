<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	Enumeration enums 			= request.getHeaderNames();
	String globalServletPath 	= request.getServletPath(); 
	String name					= "";
	String value				= "";
	boolean endOf 				= false;
	
	while(enums.hasMoreElements()){
		name=(String)enums.nextElement();
		Enumeration values = request.getHeaders(name);
		while(values.hasMoreElements()){
			value=(String)values.nextElement();
			if (name.toLowerCase().equals("accept-language")) {
				System.out.print("<b>"+name+"</b>"+":"+value+"<BR>");
				endOf = true;
				break;
			}
		}
		if (endOf) {
			break;
		}
	}
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>devwork</title>
<link href="/css/fo/ko/default.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<!-- 모바일 -->
<link href="/css/phone.css" rel="stylesheet" type="text/css" media="only screen and (min-width:320px) and (max-width:599px)">
<!-- 타블렛 -->
<link href="/css/tablet.css" rel="stylesheet" type="text/css" media="only screen and (min-width:600px) and (max-width:800px)">
<!-- 데스크탑 -->
<link href="/css/pc.css" rel="stylesheet" type="text/css" media="only screen and (min-width:801px)">
<SCRIPT language="javascript" src="/js/jquery-1.9.1.js" type="text/javascript"></SCRIPT>

<script type="text/javascript">
$(document).ready(function () {  
	var currentPage = parent.document.location.href; 
	
	if (currentPage.toLowerCase().indexOf("globallang=cn") > -1) {
		$("#errCnBox").attr("style","display:block");
		$("#errKoBox").attr("style","display:none");
	} else  if (currentPage.toLowerCase().indexOf("/cn") > -1) {
		$("#errCnBox").attr("style","display:block");
		$("#errKoBox").attr("style","display:none");
	}  else if (currentPage.toLowerCase().indexOf("/dutyv20/_manager") > -1) {
		$("#errKoBox").attr("style","display:block");
		$("#errCnBox").attr("style","display:none");
	} else if (currentPage.toLowerCase().indexOf("/bo/") > -1) {
		$("#errKoBox").attr("style","display:block");
		$("#errCnBox").attr("style","display:none");
	} else {
		var value = '<%=value%>';
		var langs = value.split(",");
		var lang = langs[0];
	 	lang = lang.toLowerCase();
	 	
	  	if (lang=="ko" || lang =="ko-kr") { //한국어
	  		$("#errKoBox").attr("style","display:block");
	  		$("#errCnBox").attr("style","display:none");
	    } else if (lang == 'zh' || lang=="cn" || lang=="zh-cn" || lang=="zh-hans" || lang=="zh-tw") { //중국어
	      	$("#errCnBox").attr("style","display:block");
	      	$("#errKoBox").attr("style","display:none");
	  	} else {
	  		$("#errKoBox").attr("style","display:block");
	  		$("#errCnBox").attr("style","display:none");
	  	}
	}
});	
</script>

</head>
<body>
<div class="er_box" id="errKoBox" style="display:none">
  <p class="er_title txt_red margin_b5">500 Internal(500 예기치 않은 오류 발생)</p>
  <p class="er_con margin_b10">재시도 후,정상 처리되지 않을경우, </br>시스템 관리자에게 문의 바랍니다.</p>
    <input type="button" name="" value="뒤로가기" class="btn_square_gray" onclick="history.back();"/>
</div>
<div class="er_box" id="errCnBox" style="display:none">
  <p class="er_title txt_red margin_b5">500 Internal(500 发生出乎意料的错误)</p>
  <p class="er_con margin_b10">请再试一下后，还是没有恢复正常时, </br>请联系系统管理者咨询。</p>
    <input type="button" name="" value="返回" class="btn_square_gray" onclick="history.back();"/>
</div>
</body>
</html>