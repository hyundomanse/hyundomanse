<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ page import="com.devwork.common.ConstCode"%>
<%@ include file="/include/bo/common.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
	});
	
	function fn_list(){
		$("#form").attr("action", "/bo/adminAuth/adminAuthListCall.do").submit();
	}
	
	function fn_regist(){
		if(fn_validation()){
			if(confirm("관리자정보를 등록하시겠습니까?")){
				dupCheck();
			}
		}
	}
	
	function fn_validation(){
		if($("#searchAdminId").val() == ""){
			alert("아이디를 입력하세요.");
			$("#searchAdminId").focus();
			return false;
		}		
		if($("#searchAdminAuth").val() == ""){
			alert("권한을 선택하세요.");
			$("#searchAdminAuth").focus();
			return false;
		}		
		if($("#searchAdminPassword").val() == ""){
			alert("비밀번호를 입력하세요.");
			$("#searchAdminPassword").focus();
			return false;
		}
		if($("#searchAdminEmail").val() == ""){			
			alert("이메일을 입력하세요.");
			$("#searchAdminEmail").focus();
			return false;
		}
		if($("#searchAdminDept").val() == ""){
			alert("소속을 입력하세요.");
			$("#searchAdminDept").focus();
			return false;
		}
		if($("#searchAdminName").val() == ""){			
			alert("이름을 입력하세요.");
			$("#searchAdminName").focus();
			return false;
		}
		if($("#searchAdminTel").val() == ""){
			alert("전화를 입력하세요.");
			$("#searchAdminTel").focus();
			return false;
		}
		if($("#searchAdminMobile").val() == ""){
			alert("모바일을 입력하세요.");
			$("#searchAdminMobile").focus();
			return false;
		}		
		return true;
	}
	
	/*******************************************
	관리자권한 목록조회
	******************************************* */
	function dupCheck(){
		var url = "/bo/adminAuth/adminAuthDupCheck.do";
		var param = $("#form").serialize();
		var rtnMap = "";
		
		$.ajax({
			type : "POST",
		    url  : url ,
		    data : param,
		    dataType : "json",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			success : function(data) {
				rtnMap	= data.commandMap;
				if(rtnMap.chkCnt == 1){
					$("#searchAdminId").val("");
					alert("이미 사용중인 아이디입니다.");
				}else{
					$("#form").attr("action", "/bo/adminAuth/adminAuthRegist.do").submit();
				}
			},
	 		error : function(request, status, error) {
				alert('오류가 발생하였습니다.');
	 		}, 
	 		complete : function(request, status) {
	 		}
		});
	}	
</script>

<section class="wrapper adm">
<form method="post" id="form" name="form">
	<div id="wrap">
		<div id="con_right">
			<div id="right_top">
				<div id="loc"></div>
				<h1>관리자권한관리</h1><br/>
			</div>
			<div id="right_contents">

				<!-- 조회: start -->
				<table class="tbl cell">
					<caption></caption>
					<colgroup>
						<col style="width:10%;">
						<col style="width:40%;">
						<col style="width:10%;">
						<col style="width:40%">
					</colgroup>
					<tbody>
						<tr>
							<th>아이디</th>
							<td align="left">
								<input type="text" id="searchAdminId" name="searchAdminId"  maxlength="25"/>
							</td>
							
							<th>권한</th>
							<td align="left">
								<select id="searchAdminAuth" name="searchAdminAuth">
				          			<option value="<%=ConstCode.ADMIN_AUTH_01%>" <c:if test="${adminAuthMap.adminAuth == '01'}" > selected="selected"</c:if>>슈퍼관리자</option>
				          			<option value="<%=ConstCode.ADMIN_AUTH_02%>" <c:if test="${adminAuthMap.adminAuth == '02'}" > selected="selected"</c:if>>서브관리자</option>
								</select>								
							</td>							
						</tr>
						
						<tr>
							<th>비밀번호</th>
							<td align="left">
								<input type="password" id="searchAdminPassword" name="searchAdminPassword" maxlength="20"/>
							</td>
							
							<th>이메일</th>
							<td align="left">
								<input type="text" id="searchAdminEmail" name="searchAdminEmail" maxlength="30" onchange="chkMail(this);"/>
							</td>							
						</tr>
						
						<tr>
							<th>소속</th>
							<td align="left">
								<input type="text" id="searchAdminDept" name="searchAdminDept" maxlength="20" onkeypress="specialChar();" style="ime-mode:disabled"/>
							</td>
							
							<th>이름</th>
							<td align="left">
								<input type="text" id="searchAdminName" name="searchAdminName" maxlength="20" onkeypress="specialChar();" style="ime-mode:disabled"/>
							</td>
						</tr>						
						<tr>
							<th>전화</th>
							<td align="left">
								<input type="text" id="searchAdminTel" name="searchAdminTel" maxlength="12" onkeypress="onlyNum();" style="ime-mode:disabled"/>
							</td>
							
							<th>모바일</th>
							<td align="left">
								<input type="text" id="searchAdminMobile" name="searchAdminMobile" maxlength="12" onkeypress="onlyNum();" style="ime-mode:disabled"/>
							</td>							
						</tr>												
					</tbody>
				</table>		
				<!-- 조회: end -->
			
				<!-- 버튼: start -->
				<div  style="float: right;">
					<a class="btn act"   id="btnSearch" onclick="javascript:fn_regist(); return false;">등 록</a>
					<!-- <a class="btn act"   id="btnSearch" onclick="javascript:fn_delete(); return false;">삭 제</a> -->
					<a class="btn reset" id="btnSearch" onclick="javascript:fn_list(); 	 return false;">목 록</a>
				</div>
				<!-- 버튼: end -->
								 		
			</div>
		</div>
	</div>
</form>
</section>
