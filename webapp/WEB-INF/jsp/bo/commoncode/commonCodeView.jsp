<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ page import="com.devwork.common.ConstCode"%>
<%@ include file="/include/bo/common.jsp" %>

<script type="text/javascript" src="/js/paging.js"></script>
<script type="text/javascript">
	var codeIdChkCmd = "N";

	$(document).ready(function(){
		getList();
		
		//그룹명 INPUT값이 변경 될때마다 codeIdChkCmd 값 N으로 변경
		$("#groupName").change(function(){
			 codeIdChkCmd = 'N';
			 console.log("값:"+codeIdChkCmd);
		});
		
	});
	
	//그룹코드중복체크
	function fn_codeChk(){
		
		var codeId = $('#groupName').val();
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
				}
			},
	 		error : function(request, status, error) {
 				//alert('오류가 발생하였습니다.');
	 		}, 
	 		complete : function(request, status) {
	 		}
		});
	}
	
	//공통코드 목록
	function fn_list(){
		$("#commonCodeform").attr("action", "/bo/commoncode/commonCodeListCall.do");
		$('#commonCodeform').submit();
	}
	
	//그룹코드명 수정
	function fn_modify_group(){
		if(fn_validation_group()){
			if(codeIdChkCmd == "N"){
				alert("코드ID 중복확인을 완료하세요.");
			}else if(confirm("그룹명을 수정하시겠습니까?")){
				$("#groupCodeForm").attr("action", "/bo/commoncode/groupCodeModify.do");
				$("#groupCodeForm").submit();
			}
		}
	}
	
	//그룹코드 사용여부 수정
	function fn_group_useYn(){
		if(confirm("사용여부를 수정하시겠습니까?")){
			$("#groupCodeForm").attr("action", "/bo/commoncode/groupCodeUseYn.do");
			$('#groupCodeForm').submit();
		}
	}	
	
	//그룹코드 수정 validation 
	function fn_validation_group(){
		if($('#groupName').val() == ''){
			alert("그룹명을 입력하세요.");
			return false;
		}
		return true;
	}
	
	//공통코드 등록
	function fn_regist_common(){
		if(fn_validation_common()){
			if(confirm("공통코드명을 등록하시겠습니까?")){
				$("#commonCodeform").attr("action", "/bo/commoncode/commonCodeRegist.do");
				$("#commonCodeform").submit();
			}
		}
	}
	
	//공통코드 수정 validation 
	function fn_validation_common(){
		if($('#codeName').val() == ''){
			alert("공통코드명을 입력하세요.");
			return false;
		}
		
		if($('#codeSort').val() == ''){
			alert("공통코드 순번을 입력하세요.");
			return false;
		}
		
		return true;
	}
	
	//공통코드 수정
	function fn_modify_common(codeSeq, _this){
		console.log("codeSeq:" + codeSeq);
		var thisCodeSort = $(_this).closest("tr").find("input[name=codeSort]");
		var thisCodeName = $(_this).closest("tr").find("input[name=codeName]");
		var thisCodeDesc = $(_this).closest("tr").find("input[name=codeDesc]");
		var thisUseYn = $(_this).closest("tr").find("select[name=useYn]");
		
		if(thisCodeSort.val() == ""){
			thisCodeSort.focus();
			alert("공통코드 순번을 입력해주세요");
		}else if(thisCodeName.val() == ""){
			thisCodeName.focus();
			alert("공통코드명을 입력해주세요");
		}else if(thisCodeDesc.val() == ""){
			thisCodeDesc.focus();
			alert("공통코드 설명을 입력해주세요");
		}else{
			if(confirm("그룹명을 수정하시겠습니까?")){
				$("#modCodeSort").val(thisCodeSort.val());
				$("#modCodeName").val(thisCodeName.val());
				$("#modCodeDesc").val(thisCodeDesc.val());
				$("#modUseYn").val(thisUseYn.val());
				$("#modCodeSeq").val(codeSeq);
				
				$("#commonCodeform").attr("action", "/bo/commoncode/commonCodeModify.do");
				$("#commonCodeform").submit(); 
			}
		}
		
	}
	
	
	/* ******************************************
	페이징
	******************************************* */
	function fn_linkPage(pageNo){ 
		$('#pageIndex').val(pageNo);
	    getList();
	}
	
	/* ******************************************
		공통코드 목록조회
	******************************************* */
	function getList(){
		var url = "/bo/commoncode/commonCodeDetailList.do";
		var param = $("#commonCodeform").serialize();
		var rtnList;
		var rtnMap = "";
		
		$.ajax({
			type : "POST",
		    url  : url ,
		    data : param,
		    dataType : "json",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			success : function(data) {
				rtnList		  = data.commonCodeList;
				rtnMap		  = data.commandMap;
				
				var trClass		  = "";
				var listTxtDt     = "";
				var listTxtFt	  = "";
				var listTxt		  = "";
				var totalRecord   = "";
				var pageInfo	  = "";
				var used 		  = "";
				
				$("#totalRecord").text('');
				$("#pageInfo").text('');
				$("#commonCodeListArea").text('');
				 
				if(rtnList != null && rtnList.length > 0) {
					for(var i=0; i<rtnList.length; i++) {
						if (i%2 ==0) { 
							trClass=""; 
						}else { 
							trClass="bg_f3f3f3"; 
						}
						
						if(rtnList[i].commuse == true){ 
							used = "Y"; 
						}else {
							used = "N"; 
						}
						
						listTxtDt += 
						"<tr onMouseOver=\"this.style.backgroundColor='#fff7e0';\" onMouseOut=\"this.style.backgroundColor='';\" style=\"cursor:pointer;\"  class="+trClass+">"
	               		+"<td>"
	               		+	'<input type="text" name="codeSort" value="'
	               		+ 		rtnList[i].codeSort
	               		+	'"/>'
	               		+"</td>"
	               		+"<td>"+ rtnList[i].codeSeq +"</td>"
	               		+"<td>"
	               		+	'<input type="text" name="codeName" value="'
	               		+ 		rtnList[i].codeName
	               		+	'"/>'
	               		+"</td>"
	               		+"<td>"
	               		+	'<input type="text" name="codeDesc" value="'
	               		+ 		rtnList[i].codeDesc
	               		+	'"/>'
	               		+"</td>"
						+"<td>"+ rtnList[i].regId +"</td>"
	               		+"<td>"+ rtnList[i].regDt +"</td>"
	               		+"<td>"+ rtnList[i].modId +"</td>"
	               		+"<td>"+ rtnList[i].modDt +"</td>"
	               		+"<td>"
	               		+	'<select name="useYn">'
	               		if(rtnList[i].useYn == 'Y' ){
	               			listTxtDt += '<option value="Y" selected="selected" >사용</option>'
	               			listTxtDt += '<option value="N">미사용</option>'
	               		}else{
	               			listTxtDt += '<option value="Y">사용</option>'
	               			listTxtDt += '<option value="N" selected="selected" >미사용</option>'
	               		}
	               		listTxtDt += ""
						+	'</select>'
	               		+"</td>"
	               		+"<td>"
	               		+	'<a class="btn act" onclick="javascript:fn_modify_common(\'' + rtnList[i].codeSeq + '\', this); return false;">수정</a>'
	               		+"</td>"
	               		+"</tr>";
					}
				} else {
					listTxtDt += 
						"<tr>"+ 
						"<td colspan=\"10\">검색결과가 없습니다</td>"+
						"</tr>";
				}
				
				listTxtFt += "";
	         	listTxt = listTxtDt + listTxtFt; 		
	         	pageInfo += "Total Record : "+ rtnMap.totCnt;
	         	totalRecord += "Page : "+ rtnMap.pageIndex + " / " + rtnMap.totalPageCount;
	         	
	         	$("#totalRecord").append(totalRecord);
				$("#pageInfo").append(pageInfo);
				$("#commonCodeListArea").append( listTxt );
				$("#commonCodeListArea").removeAttr("style");
				
			},
	 		error : function(request, status, error) {
					$('.close').trigger('click');
					//alert('오류가 발생하였습니다.');
	 		}, 
	 		complete : function(request, status) {
					$("#cmd").val("");
					$('#tb_paging').paging({	
						current:rtnMap.pageIndex,max:rtnMap.totalPageCount,		
					onclick:function(e,page){
						
						var pageNo = page.toString();
						
						fn_linkPage(pageNo);					
					}			
				});	
					
					$('.close').trigger('click');
	 		}
		});
	}
	
	
</script>

<section class="wrapper adm">
	<form method="post" id="groupCodeForm" name="groupCodeForm">
		<!-- 공통코드일련번호 -->
		<input type="hidden" id="codeSeq" name="codeSeq" value="${commonCodeMap.codeSeq}"/>
	
		<div id="wrap">
			<div id="con_right">
				<div id="right_top">
					<div id="loc"></div>
					<h1>그룹코드 상세</h1><br/>
					<h3>그룹코드일련번호: ${commonCodeMap.codeSeq}</h3>
				</div>
				<div id="right_contents">
	
					<!-- 조회: start -->
					<table class="tbl cell">
						<caption></caption>
						<colgroup>
							<col style="width:10%;">
						</colgroup>
						<tbody>
							<tr>
								<th>그룹명</th>
								<td align="left">
									<input type="text" id="groupName" name="groupName" value="${commonCodeMap.groupName}"/>
									<div  style="float: right;">
										<a class="btn act" onclick="javascript:fn_codeChk(); return false;">중복체크</a>
										<a class="btn act" onclick="javascript:fn_modify_group(); return false;">수정</a>
									</div>
								</td>
								<th>사용여부</th>
								<td align="left">
									<select id="useYn" name="useYn">
					          			<option value="<%=ConstCode.USEYN_Y%>" <c:if test="${commonCodeMap.useYn == 'Y'}" > selected="selected"</c:if>>사용</option>
					          			<option value="<%=ConstCode.USEYN_N%>" <c:if test="${commonCodeMap.useYn == 'N'}" > selected="selected"</c:if>>미사용</option>
									</select>	
									<div  style="float: right;">
										<a class="btn act" onclick="javascript:fn_group_useYn(); return false;">저장</a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
							
					<!-- 조회: end -->
									 		
				</div>
			</div>
		</div>
	</form>

	<form method="post" id="commonCodeform" name="commonCodeform">
		<!-- 공통코드일련번호 -->
		<input type="hidden" id="codeSeq" name="codeSeq" value="${commonCodeMap.codeSeq}"/>
		
		<input type="hidden" id="pageIndex"  name="pageIndex" value="${commandMap.pageIndex}"/>
		<!-- 그룹코드-->
		<input type="hidden" id="groupCode"   name="groupCode" value="${commonCodeMap.codeSeq}"/>
		<!-- 그룹명 -->
		<input type="hidden" name="groupName" value="${commonCodeMap.groupName}"/>
		
		<!-- 공통코드 수정param -->
		<input type="hidden" id="modCodeSort"  name="modCodeSort" />
		<input type="hidden" id="modCodeName"  name="modCodeName"/>
		<input type="hidden" id="modCodeDesc"  name="modCodeDesc"/>
		<input type="hidden" id="modUseYn"  name="modUseYn"/>
		<input type="hidden" id="modCodeSeq"  name="modCodeSeq"/>		
			
		
		<div id="wrap">
			<div id="con_right">
				<div id="right_top">
					<div id="loc"></div>
					<h1>공통코드 등록</h1><br/>
				</div>
				<div id="right_contents"><!-- 본문 내용 -->
				
			    	<div class="margin_b10">
			    		<span id="totalRecord"></span>
			    		<span id="pageInfo" style="float:right;"></span>
			    	</div>
			    	
			    	<table class="tbl cell">
						<caption></caption>
						<colgroup>
							<col style="width:20%;">
						</colgroup>
						<tbody>
							<tr>
								<th>공통코드명 *10자</th>
								<td align="left">
									<input type="text" id="codeName" name="inputCodeName" maxlength="10">
								</td>
								<th>공통코드 설명 *20자</th>
								<td align="left">
									<input type="text" id="codeDesc" name="inputCodeDesc" maxlength="20">
								</td>
								<th>공통코드 순번 *숫자만입력</th>
								<td align="left">
									<input type="text" id="codeSort" name="inputCodeSort" onkeypress="onlyNum();">
								</td>
								<td align="left">
									<div  style="float: right;">
										<a class="btn act"   id="btnSearch" onclick="javascript:fn_regist_common(); return false;">공통코드등록</a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
			    	
					<!-- 리스트 s-->
					<div id="h_scrollbox3">
						<table class="tb margin_b10" summary="">
							<colgroup>
								<col width="13%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="25%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="5%"/>
								<col width="20%"/>
							</colgroup>
					    	<tr>
					    		<th>공통코드 순번</th>
					    		<th>공통코드일련번호</th>
					    		<th>공통코드명</th>
					    		<th>공통코드설명</th>
					    		<th>등록자ID</th>
					    		<th>등록일시</th>
					    		<th>수정자ID</th>
					    		<th>수정일시</th>
					    		<th>사용여부</th>
					    		<th>기타</th>
					    	</tr>
					    	<tbody id="commonCodeListArea"></tbody>
					    </table>
					</div>
					<!-- 조회 버튼: start -->
					<div  style="float: right;">
						<a class="btn reset" id="btnSearch" onclick="javascript:fn_list(); 	 return false;">목 록</a>
					</div>
					
					<div class=" col_c_first pad_t10 pagging" id="tb_paging"></div>
					<!-- 리스트 e-->
				</div>
			</div>
		</div>
	</form>

</section>

