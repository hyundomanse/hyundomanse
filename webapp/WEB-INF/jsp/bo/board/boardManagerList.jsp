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
		게시판관리 목록조회
	******************************************* */
	function getList(){
		var url = "/bo/board/boardManagerList.do";
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
				rtnList		  = data.boardMgrList;
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
				$("#listArea").text('');
				 
				if(rtnList != null && rtnList.length > 0) {
					for(var i=0; i<rtnList.length; i++) {
						if (i%2 ==0) { 
							trClass=""; 
						}else { 
							trClass="bg_f3f3f3"; 
						}
						listTxtDt += 
						"<tr onMouseOver=\"this.style.backgroundColor='#fff7e0';\" onMouseOut=\"this.style.backgroundColor='';\" style=\"cursor:pointer;\"  onclick=\"javascript:fn_viewCall('"+rtnList[i].boardId+"'); return false;\" class="+trClass+">"
						+"<td>"+rtnList[i].rnum+"</td>"
						+"<td>"+rtnList[i].boardId+"</td>";
						
	               		// rtnList[i].boardType
	               		if(rtnList[i].boardType == "01"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardType'"+[i]+"'\" name=\"boardType'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardType');\">"
		               		+"<option value='<%=ConstCode.BOARD_TYPE01%>' "+"selected"+">텍스트</option>"
		               		+"<option value='<%=ConstCode.BOARD_TYPE02%>' "+""+">이미지</option>"
		               		+"<option value='<%=ConstCode.BOARD_TYPE03%>' "+""+">동영상</option>"
	               			+"</select></td>";
	               		}else if(rtnList[i].boardType == "02"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardType'"+[i]+"'\" name=\"boardType'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardType');\">"
		               		+"<option value='<%=ConstCode.BOARD_TYPE01%>' "+""+">텍스트</option>"
		               		+"<option value='<%=ConstCode.BOARD_TYPE02%>' "+"selected"+">이미지</option>"
		               		+"<option value='<%=ConstCode.BOARD_TYPE03%>' "+""+">동영상</option>"
	               			+"</select></td>";
	               		}else if(rtnList[i].boardType == "03"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardType'"+[i]+"'\" name=\"boardType'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardType');\">"
		               		+"<option value='<%=ConstCode.BOARD_TYPE01%>' "+""+">텍스트</option>"
		               		+"<option value='<%=ConstCode.BOARD_TYPE02%>' "+""+">이미지</option>"
		               		+"<option value='<%=ConstCode.BOARD_TYPE03%>' "+"selected"+">동영상</option>"
	               			+"</select></td>";	               			
	               		}												
  
	               		// rtnList[i].boardName
						listTxtDt += ""
						+"<td><input type=\"text\" size=\"30\" name=\"boardName"+[i]+"\" id=\"boardName"+[i]+"\" value=\""+rtnList[i].boardName+"\" />&nbsp;"
						+"<input type=\"button\"  onClick=\" javascript:fn_boardManagerModify('"+rtnList[i].boardId+"','boardName'+"+[i]+", 'boardName');\" value=\"수정\" /></td>";
						
	               		
	               		// rtnList[i].boardRead
	               		if(rtnList[i].boardRead == "Y"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardRead'"+[i]+"'\" name=\"boardRead'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardRead');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+"selected"+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+""+">미사용</option>"	        				
	               			+"</select></td>";
	               		}else{
	               			listTxtDt += ""
	               			+"<td><select id=\"boardRead'"+[i]+"'\" name=\"boardRead'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardRead');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+""+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+"selected"+">미사용</option>"
	               			+"</select></td>";
	               		}	               		
	               		
	               		// rtnList[i].boardWrite
	               		if(rtnList[i].boardWrite == "Y"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardWrite'"+[i]+"'\" name=\"boardWrite'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardWrite');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+"selected"+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+""+">미사용</option>"	        				
	               			+"</select></td>";
	               		}else{
	               			listTxtDt += ""
	               			+"<td><select id=\"boardWrite'"+[i]+"'\" name=\"boardWrite'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardWrite');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+""+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+"selected"+">미사용</option>"
	               			+"</select></td>";
	               		}	               		
	               		
	               		// rtnList[i].boardDelete
	               		if(rtnList[i].boardDelete == "Y"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardDelete'"+[i]+"'\" name=\"boardDelete'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardDelete');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+"selected"+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+""+">미사용</option>"	        				
	               			+"</select></td>";
	               		}else{
	               			listTxtDt += ""
	               			+"<td><select id=\"boardDelete'"+[i]+"'\" name=\"boardDelete'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardDelete');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+""+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+"selected"+">미사용</option>"
	               			+"</select></td>";
	               		}	               		
	               		
	               		// rtnList[i].boardReply
	               		if(rtnList[i].boardReply == "Y"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardReply'"+[i]+"'\" name=\"boardReply'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardReply');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+"selected"+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+""+">미사용</option>"	        				
	               			+"</select></td>";
	               		}else{
	               			listTxtDt += ""
	               			+"<td><select id=\"boardReply'"+[i]+"'\" name=\"boardReply'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardReply');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+""+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+"selected"+">미사용</option>"
	               			+"</select></td>";
	               		}
	               		
	               		// rtnList[i].boardAttach
	               		if(rtnList[i].boardAttach == "Y"){
	               			listTxtDt += ""
	               			+"<td><select id=\"boardAttach'"+[i]+"'\" name=\"boardAttach'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardAttach');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+"selected"+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+""+">미사용</option>"	        				
	               			+"</select></td>";
	               		}else{
	               			listTxtDt += ""
	               			+"<td><select id=\"boardAttach'"+[i]+"'\" name=\"boardAttach'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'boardAttach');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+""+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+"selected"+">미사용</option>"
	               			+"</select></td>";
	               		}
	               		
	               		// rtnList[i].useYn
	               		if(rtnList[i].useYn == "Y"){
	               			listTxtDt += ""
	               			+"<td><select id=\"useYn'"+[i]+"'\" name=\"useYn'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'useYn');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+"selected"+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+""+">미사용</option>"	        				
	               			+"</select></td>";
	               		}else{
	               			listTxtDt += ""
	               			+"<td><select id=\"useYn'"+[i]+"'\" name=\"useYn'"+[i]+"'\" onchange=\"javascript:fn_boardManagerModify('"+rtnList[i].boardId+"',this.value, 'useYn');\">"
		               		+"<option value='<%=ConstCode.USEYN_Y%>' "+""+">사용</option>"
		               		+"<option value='<%=ConstCode.USEYN_N%>' "+"selected"+">미사용</option>"
	               			+"</select></td>";
	               		}
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
	
	function fn_registCall(){
		$("#form").attr("action", "/bo/adminAuth/adminAuthRegistCall.do").submit();		
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
	
	
	function fn_viewCall(boardId){
	}
	
	function fn_boardManagerModify(boardId, value, entity){
		
		$("#searchBoardId").val(boardId);	// 게시판아이디
		$("#searchEntity").val(entity);		// 수정대상필드
		
		// 수정입력값 (수정대상에 따라 조회방식이 다름) 
		if(entity == "boardName"){
			$("#searchValue").val($("#"+value).val());
		}else if(entity == "boardType"){
			$("#searchValue").val(value);
		}else if(entity == "boardRead"){
			$("#searchValue").val(value);
		}else if(entity == "boardWrite"){
			$("#searchValue").val(value);
		}else if(entity == "boardDelete"){
			$("#searchValue").val(value);
		}else if(entity == "boardReply"){
			$("#searchValue").val(value);
		}else if(entity == "boardAttach"){
			$("#searchValue").val(value);
		}else if(entity == "useYn"){
			$("#searchValue").val(value);
		}

		// 게시판관리 수정처리
		var url = "/bo/board/boardManagerModify.do";
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
	<input type="hidden" id="pageIndex" name="pageIndex" value="${commandMap.pageIndex}"/>
	<input type="hidden" id="searchAdminId"	name="searchAdminId" />		
	<input type="hidden" id="searchBoardId"	name="searchBoardId" />
	<input type="hidden" id="searchValue"	name="searchValue" />
	<input type="hidden" id="searchEntity"	name="searchEntity" />

	<div id="wrap">
		<div id="con_right">
			<div id="right_top">
				<div id="loc"></div>
				<h1>통합게시판관리</h1><br/>
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
				          			<option value="<%=ConstCode.ADMIN_AUTH_LIST_SEARCH_TYPE02%>">게시판명</option>
				          			<option value="<%=ConstCode.ADMIN_AUTH_LIST_SEARCH_TYPE03%>">등록자</option>								
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
							<col width="8%"/>
							<col width="9%"/> 
							<col width="4%"/>
							<col width="25%"/>
							<col width="4%"/>
							<col width="4%"/>
							<col width="4%"/>
							<col width="4%"/>
							<col width="4%"/>
							<col width="4%"/>
							
						</colgroup>
				    	<tr>
				    		<th>번호</th>
				    		<th>게시판ID</th>
				    		<th>게시판종류</th>
				    		<th>게시판명</th>
				    		<th>읽기권한</th>
				    		<th>쓰기권한</th>
				    		<th>삭제권한</th>
				    		<th>덧글권한</th>
				    		<th>파일권한</th>
				    		<th>사용여부</th>
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

	
