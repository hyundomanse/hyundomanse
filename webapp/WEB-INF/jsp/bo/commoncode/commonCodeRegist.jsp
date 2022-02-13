<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ include file="/include/bo/common.jsp" %>

<script type="text/javascript">
	var codeIdChkCmd = 'N';
	
	$(document).ready(function(){
		
		//그룹명 INPUT값이 변경 될때마다 codeIdChkCmd 값 N으로 변경
		$("#groupName").change(function(){
			 codeIdChkCmd = 'N';
			 console.log("값:"+codeIdChkCmd);
		});

	});
	
	//그룹코드중복체크
	function fn_codeChk(){
		
		var codeId = form.groupName.value;
		var url = "/bo/commoncode/groupCodeDupCheck.do";
		var param = {"groupName" : codeId};
		var rtnMap = "";
		
		$.ajax({
			type : "POST",
		    url  : url ,
		    data : param,
		    dataType : "json",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			success : function(data) {
				rtnMap = data.commandMap;
				
				if(rtnMap.chkCnt == 0){
					alert("사용가능한 그룹명 입니다.");
					codeIdChkCmd = "Y";
				}else{
					alert("사용중이거나 할당된 그룹명입니다. \n 다른 ID를 입력하세요.");
					$("#groupName").focus();
					$("#groupName").val("");
				}
			},
	 		error : function(request, status, error) {
 				//alert('오류가 발생하였습니다.');
	 		}, 
	 		complete : function(request, status) {
	 		}
		});
	}
	
	//그룹코드등록
	function fn_regist(){
		//validation
		if(fn_validation()){
			if(codeIdChkCmd == "N"){
				alert("코드ID 중복확인을 완료하세요.");
			}else{
				if(confirm("공통코드를 등록하시겠습니까?")){
					document.form.action = "/bo/commoncode/groupCodeRegist.do";
					document.form.submit();
				}
			}
		}
	}
	
	//validation 
	function fn_validation(){
		var form = document.form;
		if(form.groupName.value == ''){
			alert("그룹명을 입력하세요.");
			return false;
		}
		return true;
	}
	
	//목록으로가기
	function fn_list(){
		if(confirm("공통코드 등록을 취소하시겠습니까?")){
			document.form.action = "/bo/commoncode/commonCodeListCall.do";
			document.form.submit();
		}
	}
	
</script>

<section class="wrapper adm">
	<form method="post" id="form" name="form">
		<div id="wrap">
			<div id="con_right">
				<div id="right_top">
					<div id="loc"></div>
					<h1>그룹코드 등록</h1><br/>
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
								<th>그룹명  *10자</th>
								<td align="left">
									<input type="text" id="groupName" name="groupName"  maxlength="10"/>
									<a class="btn act"   id="" onclick="javascript:fn_codeChk(); return false;">중복체크</a>
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
