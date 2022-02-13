<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.List"%>

<div id="sidebar" class="nav-collapse ">
	<!-- sidebar menu start-->
	<ul class="sidebar-menu" id="nav-accordion">

		<li class="sub-menu"><a href="#"> <i></i> <span>회원관리</span>
		</a>
			<ul class="sub">
				<li><a href="#">회원관리</a></li>
				<li><a href="/bo/adminAuth/adminAuthListCall.do">관리자권한관리</a></li>
			</ul></li>

		<li class="sub-menu"><a href="#"> <i></i> <span>사이트관리</span>
		</a>
			<ul class="sub">
				<li><a href="/bo/commoncode/commonCodeListCall.do">공통코드관리</a></li>
				<li><a href="#">팝업관리</a></li>
				<li><a href="#">접속카운트관리</a></li>
			</ul></li>

		<li class="sub-menu"><a href="#"> <i></i> <span>메뉴관리</span>
		</a>
			<ul class="sub">
				<li><a href="#">메뉴/프로그램관리</a></li>
				<li><a href="#">프로그램이력관리</a></li>
			</ul></li>

		<li class="sub-menu"><a href="#"> <i></i> <span>통합게시판관리</span>
		</a>
			<ul class="sub">
				<li><a href="/bo/board/boardManagerListCall.do">통합게시판마스터관리</a></li>
				<li><a href="/bo/board/boardManagerListCall.do">통합게시판게시물신고관리</a></li>
				<li><a href="/bo/board/boardManagerListCall.do">통합게시판댓글신고관리</a></li>
			</ul></li>

	</ul>
	<!-- sidebar menu end-->
</div>
