<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/jquery-1.9.1.js"></script>

<h1>파일 업로드 sample</h1>
<form action="/fileupload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadfile" placeholder="파일 선택" /><br/>
    <input type="submit" value="업로드">
</form>
<form action="/deleteFile.do" method="post">
	 <input type="submit" value="파일삭제기능샘플">
</form>