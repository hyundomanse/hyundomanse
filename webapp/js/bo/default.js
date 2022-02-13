
/**
 * BO 로그인
 * @param arg
 */
function login() {
	
	if (document.loginForm.adminId.value =="") {
		alert(" 아이디를 입력해 주세요.");
		document.loginForm.adminId.focus();
		return false;
	}

	if (document.loginForm.adminPassword.value=="") {
		alert(" 비밀번호를 입력해 주세요. ");
		document.loginForm.adminPassword.focus();
		return false;
	}
	
	var param = $("#loginForm").serialize();
	var url = "/bo/login.do";
	$.ajax({
	type : "POST",
    url  : url ,
    data : param,
    dataType : "json",
	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	success : function(data) {
		var retStr = data.result;
		var retMessage = data.message;
		//결과값
		if(retStr == "F") {
			alert("아이디 또는 비밀번호가 정확하지 않습니다.");
			return;
		} else {
			location.href="/boMain.do";
		}  
		
		/* else {
			idSave();
			goMyPage();
			return;
		} */ 
	  } ,
			error : function(request, status, error) {
				//alert("code:"+request.status+ ", message: "+request.responseText+", error:"+error);
				alert('오류가 발생하였습니다.!!');
			}
	  ,complete : function(request, status) {
				
		  }
	});  
}
/**
 * BO 로그아웃
 * @param arg
 */
function logout(arg) {
	if (arg != '#') {
	  	var url = '/bo/logout.do';
	  	var param = {
		};	
	  	$.ajax({
		type : "POST",
	    url  : url ,
	    data : param,
	    dataType : "json",
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		success : function(data) {
			location.href ='/boMain.do';
		  } , error : function(request, status, error) {
 				//alert('오류가 발생하였습니다.');
 			} , 
 			complete : function(request, status) {
 			}
		});  
	 }
}

