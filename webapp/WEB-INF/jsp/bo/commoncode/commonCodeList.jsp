<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.servlet.*" pageEncoding="UTF-8"%>
<%@ page import="com.devwork.common.ConstCode"%>
<%@ include file="/include/bo/common.jsp" %>

<script type="text/javascript" src="/js/paging.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		getList();
		
		//조회 
		$('#btnSearch').click(function() {
			if(fn_validation()){
				getList();
			}
		});	
		
		//그룹코드 등록 
		$('#btnRegist').click(function() {
			fn_registPage();
		});
	});
	
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
		var url = "/bo/commoncode/commonCodeList.do";
		var param = $("#form").serialize();
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
						+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].rnum +"</td>"
						+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].codeSeq +"</td>"
						+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].groupName +"</td>"
						+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].regId +"</td>"
	               		+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].regDt +"</td>"
	               		+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].modId +"</td>"
	               		+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].modDt +"</td>"
	               		+"<td onclick=\"javascript:fn_view('"+rtnList[i].codeSeq+"');\">"+ rtnList[i].useYn +"</td>"
	               		+"</tr>";
					}
				} else {
					listTxtDt += 
						"<tr>"+ 
						"<td colspan=\"9\">검색결과가 없습니다</td>"+
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
	
	
	function fn_view(codeSeq){
		document.form.codeSeq.value = codeSeq;
		document.form.action = "/bo/commoncode/commonCodeViewCall.do";
		document.form.submit();
	}
	
	function fn_registPage(){
		document.form.action = "/bo/commoncode/commonCodeRegistCall.do";
		document.form.submit();
	}
	
	function fn_validation() {
		if ($("#searchFromDate").val() == "") {
			alert('조회기간 시작일을 입력하여 주십시오.');
			return;
		}
		if ($("#searchToDate").val() == "") {
			alert('조회기간 종료일을 입력하여 주십시오.');
			return;
		}
	    if ($("#searchFromDate").val() > $("#searchToDate").val()) {
	    	alert('시작일이 종료일보다 클수 없습니다.');
	    	$("#searchFromDate").val("${commandMap.fromDate}");
	    	$("#searchToDate").val("${commandMap.toDate}");
	    	return false;
	    }
		return true;
	}	
	
</script>

<section class="wrapper adm">
	<form method="post" id="form" name="form">
		<input type="hidden" id="pageIndex"  name="pageIndex" value="${commandMap.pageIndex}"/>
		<input type="hidden" id="codeSeq"   name="codeSeq" />	
		
		<div id="wrap">
			<div id="con_right">
				<div id="right_top">
					<div id="loc"></div>
					<h1>그룹코드 목록</h1><br/>
				</div>
				<div id="right_contents"><!-- 본문 내용 -->
					
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
								<th scope="row">조회기간</th>
					          	<td align="left">
					          	<div class="mCalendar typeLayer">
										<span class="fInput">
										<input type="text" name="searchFromDate" id="searchFromDate" size="11" class="datepicker" value="${commandMap.fromDate}" readonly />
									</span> ~ 
									<span class="fInput">
										<input type="text" name="searchToDate" id="searchToDate" size="11" class="datepicker"  value="${commandMap.toDate}" readonly />
									</span>
									</div>
					          	</td>					
	
					          	<th scope="row">조회구분</td>
					          	<td align="left">
									<select class="" id="searchType" name="searchType">
										<option value="<%=ConstCode.COMMON_CODE_LIST_SEARCH_TYPE01%>">전체</option>
					          			<option value="<%=ConstCode.COMMON_CODE_LIST_SEARCH_TYPE02%>">등록자ID</option>
					          			<option value="<%=ConstCode.COMMON_CODE_LIST_SEARCH_TYPE03%>">수정자ID</option>								
									</select>	
									<input type="text" class="" id="searchTxt" name="searchTxt"  value=""/>				          		
					          	</td>					
							</tr>
						</tbody>
					</table>		
					<!-- 조회: end -->
				
					<!-- 조회 버튼: start -->				
					<div class="btn_area">
						<a class="btn act" id="btnSearch" style="float:right;">조 회</a>
						<div style="width:10px; display:inline-block;">&nbsp;</div>
						<!-- <a class="btn reset" id="btnReset">초기화</a> -->
					</div> 	
				
				
			    	<div class="margin_b10">
			    		<span id="totalRecord"></span>
			    		<span id="pageInfo" style="float:right;"></span>
			    	</div>
			    	
					<!-- 리스트 s-->
					<div id="h_scrollbox3">
						<table class="tb margin_b10" summary="">
							<colgroup>
								<col width="5%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
							</colgroup>
					    	<tr>
					    		<th>번호</th>
					    		<th>그룹코드일련번호</th>
					    		<th>그룹명</th>
					    		<th>등록자ID</th>
					    		<th>등록일시</th>
					    		<th>수정자ID</th>
					    		<th>수정일시</th>
					    		<th>사용여부</th>
					    	</tr>
					    	<tbody id="commonCodeListArea"></tbody>
					    </table>
					</div>
					<div class=" col_c_first pad_t10 pagging" id="tb_paging"></div>
					<!-- 리스트 e-->
					<div class="btn_area">
						<a class="btn act" id="btnRegist" style="float:right;">그룹코드 등록</a>
						<div style="width:10px; display:inline-block;">&nbsp;</div>
						<!-- <a class="btn reset" id="btnReset">초기화</a> -->
					</div>
				</div>
			</div>
		</div>
	</form>
</section>