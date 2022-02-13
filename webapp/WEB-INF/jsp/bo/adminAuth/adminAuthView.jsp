<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ page import="com.devwork.common.ConstCode"%>
<%@ include file="/include/bo/common.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
	});
	
	function fn_list(){
		$("#form").attr("action", "/bo/adminAuth/adminAuthListCall.do").submit();
	}
	
	function fn_modify(){
		if(fn_validation()){
			if(confirm("관리자정보를 수정하시겠습니까?")){
				$("#form").attr("action", "/bo/adminAuth/adminAuthModify.do").submit();
			}
		}
	}
	
	function fn_validation(){
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
</script>

<section class="wrapper adm">
<form method="post" id="form" name="form">
	<input type="hidden" id="searchAdminId"	name="searchAdminId" value="${adminAuthMap.adminId}"/>
	
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
								<input type="text" id="adminId" name="adminId"  value="${adminAuthMap.adminId}" disabled="disabled"/>
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
								<input type="password" id="searchAdminPassword" name="searchAdminPassword" value="${adminAuthMap.adminPassword}" maxlength="20"/>
							</td>
							
							<th>이메일</th>
							<td align="left">
								<input type="text" id="searchAdminEmail" name="searchAdminEmail" value="${adminAuthMap.adminEmail}" maxlength="30" onchange="chkMail(this);"/>
							</td>							
						</tr>
						
						<tr>
							<th>소속</th>
							<td align="left">
								<input type="text" id="searchAdminDept" name="searchAdminDept" value="${adminAuthMap.adminDept}" maxlength="20" onkeypress="specialChar();" style="ime-mode:disabled"/>
							</td>
							
							<th>이름</th>
							<td align="left">
								<input type="text" id="searchAdminName" name="searchAdminName" value="${adminAuthMap.adminName}" maxlength="20" onkeypress="specialChar();" style="ime-mode:disabled"/>
							</td>
						</tr>						
						<tr>
							<th>전화</th>
							<td align="left">
								<input type="text" id="searchAdminTel" name="searchAdminTel" value="${adminAuthMap.adminTel}" maxlength="12" onkeypress="onlyNum();" style="ime-mode:disabled"/>
							</td>
							
							<th>모바일</th>
							<td align="left">
								<input type="text" id="searchAdminMobile" name="searchAdminMobile" value="${adminAuthMap.adminMobile}" maxlength="12" onkeypress="onlyNum();" style="ime-mode:disabled"/>
							</td>							
						</tr>												
						
						<tr>
							<th>등록자</th>
							<td align="left">
								<input type="text" id="searchRegId" name="searchRegId" value="${adminAuthMap.regId}" disabled="disabled"/>
							</td>
							
							<th>등록일자</th>
							<td align="left">
								<input type="text" id="searchRegDt" name="searchRegDt" value="${adminAuthMap.regDt}" disabled="disabled"/>
							</td>							
						</tr>

						<tr>
							<th>수정자</th>
							<td align="left">
								<input type="text" id="searchRegId" name="searchRegId" value="${adminAuthMap.regId}" disabled="disabled"/>
							</td>
							
							<th>수정일자</th>
							<td align="left">
								<input type="text" id="searchRegDt" name="searchRegDt" value="${adminAuthMap.regDt}" disabled="disabled"/>
							</td>							
						</tr>

						<tr>
							<th>사용여부</th>
							<td align="left" colspan="3">
								<select id="searchUseYn" name="searchUseYn">
				          			<option value="<%=ConstCode.USEYN_Y%>" <c:if test="${adminAuthMap.useYn == 'Y'}" > selected="selected"</c:if>>사용</option>
				          			<option value="<%=ConstCode.USEYN_N%>" <c:if test="${adminAuthMap.useYn == 'N'}" > selected="selected"</c:if>>미사용</option>
								</select>								
							</td>
						</tr>													
												
					</tbody>
				</table>		
				<!-- 조회: end -->
			
				<!-- 버튼: start -->
				<div  style="float: right;">
					<a class="btn act"   id="btnSearch" onclick="javascript:fn_modify(); return false;">수 정</a>
					<!-- <a class="btn act"   id="btnSearch" onclick="javascript:fn_delete(); return false;">삭 제</a> -->
					<a class="btn reset" id="btnSearch" onclick="javascript:fn_list(); 	 return false;">목 록</a>
				</div>
				<!-- 버튼: end -->
								 		
			</div>
		</div>
	</div>
</form>
</section>
