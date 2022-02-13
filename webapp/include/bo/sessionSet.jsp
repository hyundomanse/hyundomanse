<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.devwork.common.util.StringUtil"%>
<%@ page import="com.devwork.common.LoginAdminVO"%>
<%@ page import="com.devwork.common.util.SessionCookieUtil"%>

<%
	//HttpSession Session = request.getSession(true);
	//com.devwork.common.LoginAdminVO loginVo = (com.devwork.common.LoginAdminVO)Session.getValue("loginAdminVO");
 	
	HttpSession Session = request.getSession(true);
	
	String SESSION_ADMINID 		= "";
	String SESSION_ADMINAUTH	= "";
	String SESSION_ADMINNAME	= "";
	String SESSION_ADMINDEPT	= "";
	
	com.devwork.common.LoginAdminVO loginVo = (com.devwork.common.LoginAdminVO)Session.getValue("loginAdminVO");
	
	if (loginVo != null) {
		SESSION_ADMINID 	= StringUtil.nullConvert(loginVo.getAdminId());
		SESSION_ADMINNAME	= StringUtil.nullConvert(loginVo.getAdminName());
		SESSION_ADMINDEPT	= StringUtil.nullConvert(loginVo.getAdminDept());
		SESSION_ADMINAUTH	= StringUtil.nullConvert(loginVo.getAdminAuth());

	}
%>