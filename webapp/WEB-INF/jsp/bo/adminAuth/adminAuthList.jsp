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
	});
	
	/* ******************************************
		페이징
	******************************************* */
	function fn_linkPage(pageNo){ 
	    document.form.pageIndex.value = pageNo;
	    getList();
	}
	
	/* ******************************************
		관리자권한 목록조회
	******************************************* */
	function getList(){
		var url = "/bo/adminAuth/adminAuthList.do";
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
				rtnList		  = data.adminAuthList;
				rtnMap		  = data.commandMap;
				
				console.log('data check :: ', data);
				
				var trClass		  = "";
				var listTxtDt     = "";
				var listTxtFt	  = "";
				var listTxt		  = "";
				var totalRecord   = "";
				var pageInfo	  = "";
				var used 		  = "";
				
				$("#totalRecord").text('');
				$("#pageInfo").text('');
				$("#listArea").text('');
				 
				if(rtnList != null && rtnList.length > 0) {
					for(var i=0; i<rtnList.length; i++) {
						if (i%2 ==0) { 
							trClass=""; 
						}else { 
							trClass="bg_f3f3f3"; 
						}
						
						listTxtDt += 
						"<tr onMouseOver=\"this.style.backgroundColor='#fff7e0';\" onMouseOut=\"this.style.backgroundColor='';\" style=\"cursor:pointer;\"  onclick=\"javascript:fn_viewCall('"+rtnList[i].adminId+"'); return false;\" class="+trClass+">"
						+"<td>"+rtnList[i].rnum+"</td>"
						+"<td>"+rtnList[i].adminId+"</td>"
						+"<td>"+rtnList[i].adminAuth+"</td>"
	               		+"<td>"+rtnList[i].adminName+"</td>"
	               		+"<td>"+rtnList[i].adminDept+"</td>"
	               		+"<td>"+rtnList[i].adminEmail+"</td>"
	               		+"<td>"+rtnList[i].adminTel+"</td>"
	               		+"<td>"+rtnList[i].adminMobile+"</td>"
	               		+"<td>"+rtnList[i].useYn+"</td>"
	               		+"<td>"+rtnList[i].regId+"</td>"
	               		+"<td>"+rtnList[i].regDt+"</td>"
	               		+"<td>"+rtnList[i].modId+"</td>"
	               		+"<td>"+rtnList[i].modDt+"</td>"	               		
	               		+"</tr>";
					}
				} else {
					listTxtDt += 
						"<tr>"+ 
						"<td colspan=\"13\">검색결과가 없습니다</td>"+
						"</tr>";
				}
				
				listTxtFt += "";
	         	listTxt = listTxtDt + listTxtFt; 		
	         	pageInfo += "Total Record : "+ rtnMap.totCnt;
	         	totalRecord += "Page : "+ rtnMap.pageIndex + " / " + rtnMap.totalPageCount;
	         	
	         	$("#totalRecord").append(totalRecord);
				$("#pageInfo").append(pageInfo);
				$("#listArea").append( listTxt );
				$("#listArea").removeAttr("style");
				
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
	
	function fn_viewCall(adminId){
		$("#searchAdminId").val(adminId);
		$("#form").attr("action", "/bo/adminAuth/adminAuthViewCall.do").submit();
	}
	
	function fn_registCall(){
		$("#form").attr("action", "/bo/adminAuth/adminAuthRegistCall.do").submit();		
	}
	
	function fn_delete(adminId){
		if(confirm("해당 관리자를 삭제하시겠습니까?")){
			$("#searchAdminId").val(adminId);
			$("#form").attr("action", "/bo/adminAuth/adminAuthDelete.do").submit();
		}
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
	<input type="hidden" id="pageIndex" name="pageIndex" value="${commandMap.pageIndex}"/>
	<input type="hidden" id="searchAdminId"	name="searchAdminId" />		

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
									<option value="<%=ConstCode.ADMIN_AUTH_LIST_SEARCH_TYPE01%>">전체</option>
				          			<option value="<%=ConstCode.ADMIN_AUTH_LIST_SEARCH_TYPE02%>">아이디</option>
				          			<option value="<%=ConstCode.ADMIN_AUTH_LIST_SEARCH_TYPE03%>">이름</option>								
								</select>	
								<input type="text" class="" id="searchTxt" name="searchTxt"  value=""/>				          		
				          	</td>					
						</tr>
					</tbody>
				</table>		
				<!-- 조회: end -->
			
				<!-- 버튼: start -->				
				<div class="btn_area">
					<a class="btn act" id="btnSearch" style="float:right;">조 회</a>
					<div style="width:10px; display:inline-block;">&nbsp;</div>
					<!-- <a class="btn reset" id="btnReset">초기화</a> -->
				</div>
				<!-- 버튼: end -->

				<!-- 리스트: start -->
				<div id="h_scrollbox3">
					<table class="tb margin_b10" summary="">
						<colgroup>
							<col width="6%"/>
							<col width="8%"/>
							<col width="6%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
							<col width="8%"/>
						</colgroup>
				    	<tr>
				    		<th>번호</th>
				    		<th>아이디</th>
				    		<th>권한</th>
				    		<th>이름</th>
				    		<th>소속</th>
				    		<th>이메일</th>
				    		<th>전화</th>
				    		<th>모바일</th>
				    		<th>사용여부</th>
				    		<th>등록자</th>
				    		<th>등록일자</th>
				    		<th>수정자</th>
				    		<th>수정일자</th>
				    	</tr>
				    	<tbody id="listArea"></tbody>
				    </table>
				</div>
				<!-- 리스트: end -->
				
				<!-- 등록 버튼: start -->				
				<div class="btn_area">
					<a class="btn btn-default" id="btnSearch" style="float:right;" onclick="javascript:fn_registCall();">등 록</a>
					<div style="width:10px; display:inline-block;">&nbsp;</div>
				</div> 		
				<!-- 등록 버튼: end -->
				
				<!-- 페이징: start -->			
				<div class=" col_c_first pad_t10 pagging" id="tb_paging"></div>
				<!-- 페이징: end -->
				
			</div>
		</div>
	</div>
</form>
</section>

	
