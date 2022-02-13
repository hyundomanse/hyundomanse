<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.devwork.common.util.StringUtil"%>
<%@ page import="com.devwork.common.LoginVO"%>
<%@ page import="com.devwork.common.util.SessionCookieUtil"%>

<%
	HttpSession Session = request.getSession(true);
	com.devwork.common.LoginVO loginVo = (com.devwork.common.LoginVO)Session.getValue("loginVO");
	
	/* 	
	HttpSession Session = request.getSession(true);
	
	String SESSION_USERID 		= "";
	String SESSION_USERNAME		= "";
	String SESSION_USERDEPT		= "";
	String SESSION_USERGRADE	= "";
	String SESSION_USERSNSTYPE	= "";
	
	com.devwork.common.LoginVO loginVo = (com.devwork.common.LoginVO)Session.getValue("loginVO");	

	if (loginVo != null) {
		SESSION_USERID 		= StringUtil.nullConvert(loginVo.getUserId());
		SESSION_USERNAME	= StringUtil.nullConvert(loginVo.getUserName());
		SESSION_USERDEPT	= StringUtil.nullConvert(loginVo.getUserDept());
		SESSION_USERGRADE	= StringUtil.nullConvert(loginVo.getUserGrade());
		SESSION_USERSNSTYPE	= StringUtil.nullConvert(loginVo.getUserSnsType());
	}
	*/
%>
