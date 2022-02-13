/**
* 자바스크립트 공통함수 
* 
* 주의: 아래의 모든 메소드는 입력폼의 필드이름(myform.myfield)을
*         파라미터로 받는다. 필드의 값(myform.myfield.value)이 아님을
*         유념할 것.
*/   

//*****************************************************************************
// 모바일(스마트폰, 탭) 환경인지 Check. 모바일 환경이면 true 리턴
// by j2dev
//*****************************************************************************

function is_mobile() {
	var m_IsMobile = getSmartPhoneAgent();

	if (m_IsMobile != "etc") {
		return true; //etc가 아닐경우, 모바일 
	} else {
		return false;
	}
}

//*****************************************************************************
// Agent 환경 리턴 (return: etc, android, iphone)
// by j2dev
//*****************************************************************************
function getSmartPhoneAgent(){
	var agentKind = "etc";
	var agent = navigator.userAgent;
	
	if (agent.indexOf("AppleWebKit") != -1 || agent.indexOf("Opera") != -1) {
		if (agent.indexOf("Android") != -1 || agent.indexOf("J2ME/MIDP") != -1) {
			agentKind = "android";
		} else if (agent.indexOf("iPhone") != -1) {
			agentKind = "iphone";
		} else if (agent.indexOf("iPad") != -1) {
			agentKind = "iphone";
		}
	} else {
		agentKind = "etc";
	}
	return agentKind;
}

function fn_getBrowerVer() { 
	var IEIndex = navigator.appVersion.indexOf("MSIE");		// MSIE를 찾고 인덱스를 리턴
	var IE8Over = navigator.userAgent.indexOf("Trident");	// MS IE 8이상 버전 체크
	 
	if( IEIndex > 0 || IE8Over > 0 )  {
		var trident = navigator.userAgent.match(/Trident\/(\d.\d)/i);
		if (trident != null){
			switch (trident[1]) {
				case "7.0" :
					strVer = "11";
					break;
				case "6.0" :
					strVer = "10";
					break;
				case "5.0" :
					strVer = "9";
					break;      
				case "4.0" :
					strVer = "8";
					break;
				default : "7";
			break;
			}
		}    
		strNav = strVer;    
	}else{   
		strNav = "11";
	}
	return strNav;
}

/**
 * 입력값에 특정 문자(chars)가 있는지 체크
 * 특정 문자를 허용하지 않으려 할 때 사용
 * ex) if (containsChars(form.name,"!,*&^%$#@~;")) {
 *         alert("이름 필드에는 특수 문자를 사용할 수 없습니다.");
 *     }
 */
function containsChars(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) != -1)
           return true;
    }
    return false;
}

/**
 * 입력값이 특정 문자(chars)만으로 되어있는지 체크
 * 특정 문자만 허용하려 할 때 사용
 * ex) if (!containsCharsOnly(form.blood,"ABO")) {
 *         alert("혈액형 필드에는 A,B,O 문자만 사용할 수 있습니다.");
 *     }
 */
function containsCharsOnly(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) == -1)
           return false;
    }
    return true;
}

/**
 * 입력값이 알파벳인지 체크
 * 아래 isAlphabet() 부터 isNumComma()까지의 메소드가
 * 자주 쓰이는 경우에는 var chars 변수를 
 * global 변수로 선언하고 사용하도록 한다.
 * ex) var uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 *     var lowercase = "abcdefghijklmnopqrstuvwxyz"; 
 *     var number    = "0123456789";
 *     function isAlphaNum(input) {
 *         var chars = uppercase + lowercase + number;
 *         return containsCharsOnly(input,chars);
 *     }
 */
function isAlphabet(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    return containsCharsOnly(input,chars);
}

// 입력값이 알파벳 대문자인지 체크
function isUpperCase(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return containsCharsOnly(input,chars);
}

// 입력값이 알파벳 소문자인지 체크
function isLowerCase(input) {
    var chars = "abcdefghijklmnopqrstuvwxyz";
    return containsCharsOnly(input,chars);
}

// 입력값에 숫자만 있는지 체크
function isNumber(input) {
    var chars = "0123456789";
    return containsCharsOnly(input,chars);
}

//입력값에 숫자만 있는지 체크
function isNum(input) {
    var chars = "0123456789";
    for(var inx = 0; inx < input.value.length; inx++) {
    	if(chars.indexOf(input.value.charAt(inx)) == -1){
    		alert("숫자만 입력가능합니다.");
        	input.value = "";
        	input.focus();
        	return false;
    	}
     }
     return true;    
}

function isNumCn(input) {
    var chars = "0123456789";
    for(var inx = 0; inx < input.value.length; inx++) {
    	if(chars.indexOf(input.value.charAt(inx)) == -1){
    		alert("You can only enter numbers.");
        	input.value = "";
        	input.focus();
        	return false;
    	}
     }
     return true;    
}

// 입력값이 알파벳,숫자로 되어있는지 체크
function isAlphaNum(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    return containsCharsOnly(input,chars);
}

// 입력값이 한글로 되어있는지 체크 (추가)
function isKorean(input) {
	for(var i=0; i<input.length; i++){
		var CodeNum = input.charCodeAt(i);
		if (CodeNum < 128){
			flag = false;
		} else {
			//alert("한글 아님");
			flag = true;
		}
	}
	return flag;
}

//  입력값이 숫자,대시(-)로 되어있는지 체크
function isNumDash(input) {
    var chars = "-0123456789";
    return containsCharsOnly(input,chars);
}

// 입력값이 숫자,콤마(,)로 되어있는지 체크 
function isNumComma(input) {
    var chars = ",0123456789";
    return containsCharsOnly(input,chars);
}

//입력값이 숫자,점(.)으로 되어있는지 체크 
function isNumPoint(input) {
    var chars = ".0123456789";
    return containsCharsOnly(input,chars);
}

// 입력값이 사용자가 정의한 포맷 형식인지 체크
function isValidFormat(input,format) {
    if (input.value.search(format) != -1) {
        return true; //올바른 포맷 형식
    }
    return false;
}

// 입력값이 이메일 형식인지 체크
function isValidEmail(input) {
//    var format = /^(\S+)@(\S+)\.([A-Za-z]+)$/;
    var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
    return isValidFormat(input,format);
}

// 입력값이 전화번호 형식(숫자-숫자-숫자)인지 체크
function isValidPhone(input) {
    var format = /^(\d+)-(\d+)-(\d+)$/;
    return isValidFormat(input,format);
}

function isValidDay(date) {

	var yyyy = date.substring(0,4);
    var mm 	 = date.substring(4,6);
    var dd   = date.substring(6,8);
	
	if (yyyy.length < 4) {
		return false;
	}
	
	if (mm.length < 2) {
		return false;
	}
	
	if (dd.length < 2) {
		return false;
	}
	
    var m = parseInt(mm,10) - 1;
    var d = parseInt(dd,10);

    var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
    if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
        end[1] = 29;
    }

    return (d >= 1 && d <= end[m]);
}

/**
 * 입력값의 바이트 길이를 리턴
 * ex) if (getByteLength(form.title) > 100) {
 *         alert("제목은 한글 50자(영문 100자) 이상 입력할 수 없습니다.");
 *     }
 */
function getByteLength(input) {
    var byteLength = 0;
    for (var inx = 0; inx < input.value.length; inx++) {
        var oneChar = escape(input.value.charAt(inx));
        if ( oneChar.length == 1 ) {
            byteLength ++;
        } else if (oneChar.indexOf("%u") != -1) {
            byteLength += 2;
        } else if (oneChar.indexOf("%") != -1) {
            byteLength += oneChar.length/3;
        }
    }
    return byteLength;
}

// 입력값에서 콤마 삭제
function removeComma(input) {
    return input.value.replace(/,/gi,"");
}

// replace ( ' --> '' )
function convertSingleQuote(input) {
    return input.value.replace(/"''"/gi,"'");
}

// 숫자를 통화형식으로 변환한다.
function commaNum(num) { 

	if (num < 0) { 
		num *= -1; 
		var minus = true;
	} else { 
		var minus = false;
	}
	var dotPos = (num+"").split(".");
	var dotU = dotPos[0];
	var dotD = dotPos[1];
	var commaFlag = dotU.length%3;

	if(commaFlag) {
		var out = dotU.substring(0, commaFlag);
		if (dotU.length > 3) { 
			out += ",";
		}
	}
	else var out = "";
	for (var i=commaFlag; i < dotU.length; i+=3) {
		out += dotU.substring(i, i+3); 
		if( i < dotU.length-3) {
			out += ",";
		}
	}
	if(minus) out = "-" + out;
	if(dotD) {
		return out + "." + dotD; 
	} else {
		return out; 
	}
}

               
//공백제거
String.prototype.trim = function() {
		return this.replace(/^\s*/,"").replace(/\s*$/,"");
}

// HTML 특수문자를 변환
String.prototype.htmlChars = function () {
       var str = ((this.replace('"', '&amp;')).replace('"', '&quot;')).replace('\'', '&#39;');
       return (str.replace('<', '&lt;')).replace('>', '&gt;');
}

// 왼쪽 공백없애는 함수
String.prototype.ltrim = function () { return this.replace(/^s*/g, ""); }

// 오른쪽 공백없애는 함수
String.prototype.rtrim = function () { return this.replace(/s*$/g, ""); }

// 태그만 제거
String.prototype.stripTags = function () {
       var str = this;
       var pos1 = str.indexOf('<');

    if (pos1 == -1) return str;
    else {
        var pos2 = str.indexOf('>', pos1);
        if (pos2 == -1) return str;
        return (str.substr(0, pos1) + str.substr(pos2+1)).stripTags();
    }
}

// 대소문자 구별하지 않고 단어 위치 찾기
String.prototype.ipos = function (needle, offset) {
       var str = this;
       var offset = (typeof offset == "number")?offset:0;
       var pos1 = str.toLowerCase().indexOf(needle.toLowerCase(), offset);
       var pos2 = str.toUpperCase().indexOf(needle.toUpperCase(), offset);

       if (pos1 == -1 && pos2 == -1) return false;
       if (pos1 == -1) pos1 = str.length + 1;
       if (pos2 == -1) pos2 = str.length + 1;

       return Math.min(pos1, pos2);
}

// 대소문자 구별하지 않고 뒤에서부터 단어위치 찾기
String.prototype.ripos = function (needle, offset) {
       var offset = (typeof offset == "number")?offset:0;
       var pos1 = str.toLowerCase().lastIndexOf(needle.toLowerCase(), offset);
       var pos2 = str.toUpperCase().lastIndexOf(needle.toUpperCase(), offset);

       if (pos1 == -1 && pos2 == -1) return false;
       return Math.max(pos1, pos2);
}

// 문자열을 배열로
String.prototype.toArray = function () {
       var len = this.length;
       var arr = new Array;
       for (var i=0; i<len; i++) arr[i] = this.charAt(i);
       return arr;
}


//*****************************************************************************
//쿠키 추출
//*****************************************************************************
function getCookie( name ){
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length ){
		var y = (x+nameOfCookie.length);
		if ( document.cookie.substring( x, y ) == nameOfCookie ) {
			  if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
			  endOfCookie = document.cookie.length;
			  return unescape( document.cookie.substring( y, endOfCookie ) );
		}
		x = document.cookie.indexOf( " ", x ) + 1;
		if ( x == 0 )
		break;
	}
	return "";
}

//*****************************************************************************
//쿠키 생성
//*****************************************************************************
/*
function setCookie (name, value, expires) {
	  document.cookie = name + "=" + escape (value) +
		"; path=/; expires=" + expires.toGMTString();
}
*/
function setCookie( name, value, expiredays ){ 
	var todayDate = new Date(); 
	todayDate.setDate( todayDate.getDate() + expiredays ); 
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
} 	




//*****************************************************************************
// 이메일 주소 특수문자 체크
//*****************************************************************************
function checkChar(str){
   len=str.value.length;
   for(i=0;i<len;i++){
      if(str.value.charAt(i)=="" || str.value.charAt(i)=="%" || str.value.charAt(i)=="\"" || str.value.charAt(i)=="&"){
          alert("e-Mail주소를 올바르게 입력하세요");
    	  str.value="";
          str.focus();
          return false;
      }
   }
   return true;
}

//*****************************************************************************
//이메일 주소 입력구분 체크
//*****************************************************************************
function chkMail(eml){
   if(checkChar(eml)==false) return false;
   comIndex=eml.value.indexOf(",");
   aIndex=eml.value.indexOf("@");
   dotIndex=eml.value.indexOf(".");
   len=eml.value.length;
   if(len==0) return true;
   else if(comIndex=="-1" && aIndex>1 && dotIndex>3 && aIndex<(dotIndex-1)){
      return true;
   }
   else{
      alert("e-Mail주소를 올바르게 입력하세요");
      eml.value="";
      eml.focus();
      return false;
   }
   return true;
}

//*****************************************************************************
//숫자인지 입력글자의 타입체크
//*****************************************************************************
function chkInteger(Form1){
	for(i=0 ; i < Form1.value.length ; i++){
		if((Form1.value.charAt(i)<"0") || (Form1.value.charAt(i)>"9")){
			alert("숫자만 입력가능합니다.");
			Form1.value=""
			Form1.focus();
	        return false;
		}

    } // end for
	return true;
}

//*****************************************************************************
//아이디 입력 체크
//*****************************************************************************
function idcheck(Form1){
   str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for(i=0;i<Form1.length;i++){
      sw = false;
      for(j=0;j<str.length;j++){
         if(Form1.charAt(i)==str.charAt(j)){
            sw=true;
            break;
         }
      }
      if(sw) {
       continue;
      }else{
       alert("아이디는 영문자 숫자만 입력가능합니다");
		Form1="";
      return(false);
      }

   }
   return(true);
}

//*****************************************************************************
// 년도 확인 함수
//*****************************************************************************
function chkYear(Form1){
	chkInteger(Form1);
	today=new Date();
        year1=today.getYear();
	if ((eval(Form1.value)< (year1-10)) || (eval(Form1.value)>(year1+10))){
		alert("년도가 허용범위를 넘었습니다.");
		Form1.value="";
		Form1.focus();
		return false;
	}
}

//*****************************************************************************
// 달 확인함수
//*****************************************************************************
function chkMonth(Form1){
	chkInteger(Form1);
	if ((eval(Form1.value)<1) || (eval(Form1.value)>12)){
		alert("입력한 월이 잘못 되었습니다.");
		Form1.value="";
		Form1.focus();
		return false;
	}
}

//*****************************************************************************
// 일자 확인 함수
//*****************************************************************************
function chkDay(Form1){
	chkInteger(Form1);
	if((eval(Form1.value)<1) || (eval(Form1.value)>31)){
		alert("입력한 일이 잘못 되었습니다.");
		Form1.value="";
		Form1.focus();
		return false;
	}
}

//*****************************************************************************
// 시간 확인함수
//*****************************************************************************
function chkClock(Form1){
	chkInteger(Form1);

	if ((eval(Form1.value)<0) || (eval(Form1.value)>23)){
		alert("입력한 시간이 잘못 되었습니다.");
		Form1.value="";
		Form1.focus();
		return false;
	}
}


//*****************************************************************************
// 분 확인함수
//*****************************************************************************
function chkMin(Form1){
	chkInteger(Form1);

	if ((eval(Form1.value)<0) || (eval(Form1.value)>=60)){
		alert("입력한 시간이 잘못되었습니다.");
		Form1.value="";
		Form1.focus();
		return false;
	}
}

//*****************************************************************************
// 좌측 공백 제거 함수
//*****************************************************************************
function Ltrim(strValue){
    while (strValue.length>0){
       if(strValue.charAt(0)==' '){
           strValue=strValue.substring(1,strValue.length);
	   }
       else
          return strValue;
    }
	return strValue;
}

//*****************************************************************************
// 우측 공백 제거 함수
//*****************************************************************************
function Rtrim(strValue){
    while (strValue.length>0){
       if(strValue.charAt(strValue.length-1)==' '){
           strValue=strValue.substring(0,strValue.length-1);
	   }
       else
           return strValue;
   }
   return strValue;
}

//*****************************************************************************
// 양쪽 공백 제거 함수
//*****************************************************************************
function Trim(strValue){
   strValue = Ltrim(strValue);
   strValue = Rtrim(strValue);
   return strValue;
}

//*****************************************************************************
//파일 폴더 이름 생성 체크
//*****************************************************************************
function MakenameCheck(chkValue){
	str = '\\/:*?"<>|';
    for(i=0;i<chkValue.length;i++){
    	for(j=0;j<str.length;j++){
    		if(chkValue.charAt(i)==str.charAt(j)){
    			return false;
    			break;
    		}
    	}
    }
    return true;
}

//*****************************************************************************
// 년도, 달, 일자 체크 함수 ( chk 0 = 년월 or 년 chk 1 = 년월만, 2 = 년월일)
// 2009.08.19 보완수정 bk.yoon
// 2009.11.24 보완수정 bk.yoon
//*****************************************************************************
function chkDate(Form1,chk) {
	chkInteger(Form1);
	yyyy = Form1.value.substring(0,4);
	mm = Form1.value.substring(4,6);
	l_val = Form1.value.length ;
	if(chk==1) {
		if(l_val >=1 && l_val <=5) {
			alert("연월 입력이 올바르지 않습니다.\n예) 200501(년/월)");
			Form1.value = "";
			Form1.focus();
			return false;
		}
    } else if(chk==4) {
		if(l_val >=1 && l_val <=3) {
			alert("연월 입력이 올바르지 않습니다.\n예) 0501(년/월)");
			Form1.value = "";
			Form1.focus();
			return false;
		}

    } else if(chk==0){

      if(l_val == 6){

		if(l_val >=1 && l_val <=5) {
			alert("연월 입력이 올바르지 않습니다.\n예) 200501(년/월)");
			data = "";
			return false;
		}

      } 

	} else {
		if(l_val >=1 && l_val <=7) {
			alert("연월일 입력이 올바르지 않습니다.\n예) 20050101(년/월/일)");
			Form1.value = "";
			Form1.focus();
			return false;
		}
	}

	today=new Date();
	year1=today.getYear();
	if ((eval(yyyy)< (year1-10)) || (eval(yyyy)>(year1+10)))
	{
		alert("년도가 허용범위를 넘었습니다.");
		Form1.value="";
		Form1.focus();
		return false;
	}

	if ((eval(mm)<1) || (eval(mm)>12))
	{
		alert("입력한 월이 잘못 되었습니다.");
		Form1.value="";
		Form1.focus();
		return false;
	}

	if(chk==2) {
		dd = Form1.value.substring(6,8);
		if ((eval(dd)<1) || (eval(dd)>31))
		{
			alert("입력한 일이 잘못 되었습니다.");
			Form1.value="";
			Form1.focus();
			return false;
		}
	}
}

//*****************************************************************************
//숫자와 백스페이스, 딜리트, 탭, 방향키만 입력가능
//*****************************************************************************
function onlyNumber(){
	if ((event.keyCode>=48 && event.keyCode<=57 ) || (event.keyCode>=96 && event.keyCode<=105) || event.keyCode==46 || event.keyCode==8 || event.keyCode==9 || (event.keyCode>=35 && event.keyCode <=40) || event.keyCode==13 || event.keyCode==189 || event.keyCode==109 || event.keyCode==17 || event.keyCode==18 || event.keyCode==144 || (event.ctrlKey && event.keyCode==67) || (event.ctrlKey && event.keyCode==86) ) {
		//숫자키, 백스페이스, 딜리트, 탭, 방향키, home, end, 엔터
	} else {
		//alert("숫자만 입력 가능합니다.");
		event.returnValue=false;
	}
}


/*****************************************************************************
 * 입력된값이 숫자만 입력가능하게 하는 함수 (. 허용)
 *****************************************************************************/
function onlyDouble(){
	if (( event.keyCode>=48 && event.keyCode<=57 ) || (event.keyCode>=96 && event.keyCode<=105) || event.keyCode==188 || event.keyCode==46 || event.keyCode==8 || event.keyCode==9 || (event.keyCode>=35 && event.keyCode <=40) ||event.keyCode==190 || event.keyCode==110 || event.keyCode==13 || event.keyCode==17 || event.keyCode==18 || event.keyCode==189 || event.keyCode==109 || event.keyCode==190 || event.keyCode==144 || (event.ctrlKey && event.keyCode==67) || (event.ctrlKey && event.keyCode==86)) {
		//숫자키, 백스페이스, 딜리트, 탭, 방향키
	} 
	else {
		alert("숫자만 입력 가능합니다.");
		event.returnValue=false;
	}
}

//문자열 변환
function replace(str, original, replacement){
	var result;
	result = "";
	while(str.indexOf(original) != -1){
		if (str.indexOf(original) > 0)
			result = result + str.substring(0, str.indexOf(original)) + replacement;
		else
			result = result + replacement;
			str = str.substring(str.indexOf(original) + original.length, str.length);
	}
	return result + str;
}


function lpad(s, n, c) {    
    if (! s || ! c || s.length >= n) {
        return s;
    } 
    var max = (n - s.length)/c.length;
    for (var i = 0; i < max; i++) {
        s = c + s;
    }
    return s;
}

function round(num,ja) { 
    ja=Math.pow(10,ja) 
    return Math.round(num * ja) / ja; 
} 


function Comma(number) {
	number = '' + number;
	if (number.length > 3) {

	var mod = number.length % 3;
	var output = (mod > 0 ? (number.substring(0,mod)) : '');
		for (i=0 ; i < Math.floor(number.length / 3); i++) {
			if ((mod == 0) && (i == 0))
				output += number.substring(mod+ 3 * i, mod + 3 * i + 3);
			else
				output+= ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
		}
	return (output);
	}
	else return number;
}

function checkDecimals(fieldValue) {
	if (fieldValue == "") return fieldValue;
    var argMinu = false;
    var argTxt = fieldValue.toString();
      
    if (argTxt.indexOf('-') == -1) {
    } else {
    	argTxt = argTxt.replace("-","");
    	argMinu = true;
    }
      
    if (argTxt.indexOf('.') == -1) {
    	if (argMinu == true) {
    		return '-' + Comma(argTxt);
    	} else {
    	 	return Comma(argTxt);
    	}
    } else {
    	var dectext = argTxt.substring(argTxt.indexOf('.')+1, argTxt.length);
	    dectext = parseInt(dectext);
	    dectext = round(dectext,2);
	    var intText =  argTxt.substring(0, argTxt.indexOf('.'));
	    if (argMinu == true) {
	    	return '-' + Comma(intText) +'.' +dectext;
	    } else {
	    	return Comma(intText) +'.' +dectext;
	    }
    }
} 
    
/**
 * 즐겨찾기
 */
function fn_favorite() {
	var title= "devwork";
	var url = "http://www.naver.com/boMain.do";
	
	// Internet Explorer
	if(document.all){
		window.external.AddFavorite(url, title); 
	}else if(window.chrome){ // Google Chrome
		alert("Ctrl+D키를 누르시면 즐겨찾기에 추가하실 수 있습니다.");
	}else if (window.sidebar && window.sidebar.addPanel) { // Firefox 
		window.sidebar.addPanel(title, url, ""); 
	}else if(window.opera && window.print){  // Opera 작동 안함
		alert("Ctrl+D키를 누르시면 즐겨찾기에 추가하실 수 있습니다.");
	}else{
		alert("해당브라우저는 즐겨찾기 추가기능이 지원되지 않습니다.\n\n수동으로 즐겨찾기에 추가해주세요.");
	}
}

//*****************************************************************************
// 특정문자열로 왼쪽을 채우는 fucntion
// by j2dev
//*****************************************************************************
function lpad2(src, len, padStr){
	var retStr = "";
	var padCnt = Number(len) - String(src).length;
	for(var i=0;i<padCnt;i++) retStr += String(padStr);
	return retStr+src;
}

//*****************************************************************************
// 숫자 입력만 허용하는 fucntion
// by j2dev
//*****************************************************************************
function onlyNum(){
	if(event.keyCode < 48 || event.keyCode > 57){
		if(navigator.appName=='Netscape'){
			event.preventDefault();		
		}else{
			event.returnValue=false;		
		}
	}
}

//*****************************************************************************
// 한글 입력만 허용하는 fucntion
// by j2dev
//*****************************************************************************
function hangul(){
	if(event.keyCode < 12592 || event.keyCode > 12687){
		if(navigator.appName=='Netscape'){
			event.preventDefault();		
		}else{
			event.returnValue=false;		
		}
	}
}

//*****************************************************************************
// 특수문자만 입력 불 허용하는 fucntion
// by j2dev
//*****************************************************************************
function specialChar(){
	if(event.keyCode > 32 && event.keyCode < 48 || event.keyCode > 57 && event.keyCode < 65 || event.keyCode > 90 && event.keyCode < 97){
		if(navigator.appName=='Netscape'){
			event.preventDefault();		
		}else{
			event.returnValue=false;		
		}
	}
}

function enCode(sText) {
	var arrData = new Array();
	var arrCode = new Array();
	var sCode = "ozdtestkey";
	cntData = (sText.length) -1;
	cntCode = (sCode.length) -1;
	
	for(var i =0 ; i <= cntData ; i++){
		arrData[i] = sText.substring(i , i+1);
	}
	
	for(var i =0 ; i <= cntCode ; i++){
		arrCode[i] = sCode.substring(i , i+1);
	}

	var flag = 0;
	var strResult = "";
	 
	for(var i =0 ; i <= cntData ; i++){
		strResult += arrData[i].charCodeAt(0)^arrCode[flag].charCodeAt(0);
		strResult += "@";
		if (flag == cntCode) {
			flag = 0;
		}else{
			flag = flag + 1;
		}
	}
	return strResult;
}	
	
function deCode(sText) {
	var arrCode = new Array();
	var strResult = "";
	var arrData = new Array();
	var i;
	var sCode =  "ozdtestkey";
	
	arrData = sText.split("@");
	var cntData = arrData.length-1;
	var cntCode = sCode.length -1;
	
	for(i=0 ; i <= cntCode ; i++){
		arrCode[i] = sCode.substring(i, i+1);
	}
	
	flag = 0;
	strResult = "";
	
	for(i = 0 ; i < cntData ; i++){
		strResult += String.fromCharCode(arrData[i]^arrCode[flag].charCodeAt(0));
		if(flag == cntCode){
			flag = 0;
		}else{
			flag = flag + 1;
		}
	}
	return strResult;
}

function checkByte (strId, strName, maxLength){ 
	var tcount = 0;
    var str = $('#'+strId).val();
    var length = 0;
    length = str.length;
    if (length > 0) {
    	for(var i = 0; i < length; i++){
    		var byteStr = str.charAt(i);
    		if(escape(byteStr).length > 4){
    			tcount += 3;
    		}else{
    			tcount += 1;
    		}
	    }
    }    
    if(tcount > maxLength){
    	alert(strName + "(" + maxLength + "byte) 이내로 작성해주세요.");
    	$('#'+strId).focus();
    	return false;
    }else{
    	return true;
    }
}


/**
 * jquery Dialog popup (경고,에러)
 * @param errMsg
 * @param obj
 * @param func
 * @param style
 */
function showErrPop(errMsg, obj, func, style)
{
	var		objTmp, w;	
	if(style !=null) w = style.width;

	jQuery('body').append('<div id="valDiv" style="display:none; z-index:3000;">'+errMsg+"<br>"+'</div>');
	jQuery('#valDiv').dialog({
		bgiframe: true
		, modal: true
		, title: '확인해 주세요.'
		, open: function(){
			objTmp	= obj;
		}
		, close: function(event, ui){
			jQuery('#valDiv').remove();
			$(objTmp).focus();
			if(func !=null)
				func();
		}
		,width : w
		, buttons: {
			'닫기': function() {
				$(this).dialog('close');
				$(this).remove();
			}
		}			
	});
}

function popSelfClose(){
	self.close();
}

required = function(element){	
	var length; // 선택 혹은 입력 여부를 판단하기 위한 변수
	switch(element.tagName){
	case 'input':
		switch(element.tagType){
		case 'checkbox':
			length = element.obj.filter(":checked").length;
			break;
		case 'radio':
			length = element.obj.filter(":checked").length;
			break;						
		default:
			length = element.obj.val().length;						
			break;
		}
		break;
	case 'select':					
		length = element.obj.find('option:selected:first').val().length;
		break;				
	case 'textarea':					
		length = element.obj.val().length;
		break;
	default:
		length = element.val().length;
	}
	
	if(length > 0){
		return true;
	}else{
		return false;
	}
}

number= function(element){
	var result = true;

	switch(element.tagName){
	case 'input':
		switch(element.tagType){
		case 'checkbox':
			//TODO 처리기준 확정
			break;
		case 'radio':
			//TODO 처리기준 확정
			break;						
		default:
			if(element.obj.val().length > 0){
				result = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(element.obj.val());
			}
			
			break;
		}
		break;
	case 'select':	
		//TODO 처리기준 확정
		break;	
	case 'textarea':
		if(element.obj.val().length > 0){
			result = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(element.obj.val());
		}					
		break;
	default:
		if(element.val().length > 0){
			result = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(element.val());
		}
	}
	return result;
}

//내림 ##################################################
//num: 대상 숫자, p: 대상 자리수
function setFloor(num, p) {
	if (!p) p = 0;
	return Math.floor(num * Math.pow(10, p)) / Math.pow(10, p);
}

//반올림 ##################################################
//num: 대상 숫자, p: 대상 자리수
function setRound(num, p) {
	if (!p) p = 0;
	return Math.round(num * Math.pow(10, p)) / Math.pow(10, p);
}

//올림 ##################################################
//num: 대상 숫자, p: 대상 자리수
function setCeil(num, p) {
	if (!p) p = 0;
	return Math.ceil(num * Math.pow(10, p)) / Math.pow(10, p);
}

//공백 확인 ##################################################
function isEmpty(obj) {
	return (obj.value.stripspace()=='' ? true : false);
}

//Radio(CheckBox) 설정값 가져오기 ##################################################
function getRadio(obj) {
	var value = '';

	if (obj) {
		if (typeof(obj.length) == 'undefined') {
			if (obj.checked) {
				value = obj.value;
			}
		}
		else {
			for (var i=0; i<obj.length; i++) {
				if (obj[i].checked) {
					value = obj[i].value;
					break;
				}
			}
		}
	}
	return value;
}

//콤마찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
 
//콤마풀기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}
 
//input시 콤마찍기
function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

//Form Disabled 전체 설정하기 ##################################################
function setRadioDisabledAll(obj, disabled) {
	var i;

	if (obj) {
		if (typeof(obj.length) == 'undefined') {
			obj.disabled = disabled;
		}
		else {
			for (var i=0; i<obj.length; i++) {
				obj[i].disabled = disabled;
			}
		}
	}
}

//Select 설정값 가져오기 ##################################################
function getSelect(obj) {
	var value = '';
	var idx = obj.selectedIndex;

	if (idx >= 0){
		value = obj.options[idx].value;
	}

	return value;
}

//Select Index 가져오기 ##################################################
function getSelectIndex(obj, value) {
	var index = -1;

	var nodes = obj.childNodes;
	for (var i=0; i<nodes.length; i++) {
		if (nodes[i].value.toString() == value.toString()) {
			index = i;
			break;
		}
	}

	return index;
}

//이메일 확인 ##################################################
function checkEmail(email) {
	if (email.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
		return true;
	}
	else {
		return false;
	}
}


/**
 * 팝업화면생성
 * @param config
 *         url  : 팝업URL
 *         width : 팝업 width, default : 760
 *         height : 팝업 height, default : 760
 *         windowId : 팝업 window ID, default : popup
 *         ex>{ "url" : "/abc.do"}
 *         ex>{ "url" : "/abc.do", "height" : 200}
 *         ex>{ "url" : "/abc.do", "width" : 200, "height" : 200}
 *         ex>{ "url" : "/abc.do", "width" : 200, "height" : 200, "windowId" : "openPop"  }
 * @return
 */
function openWindow(config){
    var url = config.url;
    var width = config.width ? config.width : "790";
    var height = config.height ? config.height : "760";
    var windowId = config.windowId ? config.windowId : "popup";
    var left= (screen.width-width)/2;
	var top = (screen.height-height)/2;
    return window.open(url, windowId, 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=' + width + ',height=' + height+", left="+left+", top="+top);
}

/**
 * 실수 입력 제한 체크
 * 사용예)  <input type='text'  onkeyup='javascript:isDecimalKey(this, 100, 4)' />
 * @param obj 오브젝트
 * @param max 최대 값 설정 (0~max)까지의 범위 숫자만 입력값으로 제한할 입력값 (입력값 자연수 또는 양의 실수)
 * @param number 소수 자리수 제한
 */

function isDecimalKey(obj, max, number) {
	var num = obj.value;
	var pattern = /[^0-9|^\.]/gi;
	if( pattern.test(num)==true){
		alert("소수나 숫자만 입력 가능합니다.");
		obj.value = num.replace(pattern, "");
		obj.focus();
	}

	//잘못된 형식의 소수 방지
	if(num[0]=='.'){
		alert("소수의 처음부터 '.'이 올수 없습니다.");
		obj.value = "";
		obj.focus();
	}
	
	//소수점이 두개이상 나오는것 방지
	var findCharCount = 0;
	for(var i=0; i < num.length; i++){
		if(num[i]=='.'){
			findCharCount++;
			if(findCharCount>1){
				alert("소수점이 두개이상 올수 없습니다.");
				obj.value = num.substring(0, num.length-1);
				obj.focus();
				break;
			}
		}
	}

	//소수점 자리수 제한
	var dotIndex = 0;
	for(var i=0; i < num.length; i++){
		if(num[i]=='.'){
			dotIndex = i;
			break;
		}
	}

	if(dotIndex>0){
		var subNum = num.substring(dotIndex+1, num.length);
		if(subNum.length > number){
			alert("소수점이하 "+number+"째 자리까지만 입력 가능합니다.");
			obj.value = num.substring(0, num.length-1);
			obj.focus();
		}
	}

	//설정한 범위안의 숫자만 입력가능하도록 설정
	if(max != undefined){
		if(num > max){
			alert("0~"+max+"범위 안의 숫자만 입력 가능합니다.");
			obj.value = max;
			obj.focus();
		}
	}
}
/**
 * 정수 입력 제한 체크
 * 사용예)  <input type='text'  onkeyup='javascript:onlyNumCheck(this, 100)' />
 * @param obj 오브젝트
 * @param max 최대 값 설정 (0~max)까지의 범위 숫자만 입력값으로 제한할 입력값 (입력값 자연수 또는 양의 실수)
 * @param number 소수 자리수 제한
 */
function onlyNumCheck(obj,max){
	
	var num = obj.value;
	var pattern = /[^0-9]/gi;
	if( pattern.test(num)==true){
		alert("정수만 입력 가능합니다.");
		obj.value = num.replace(pattern, "");
		obj.focus();
	}
	if (num[0]=='0'){
		alert("정수의 처음부터 '0'이 올수 없습니다.");
		obj.value = "";
		obj.focus();
	}
	//설정한 범위안의 숫자만 입력가능하도록 설정
	if(max != undefined){
		if(num > max){
			alert("0~"+max+"범위 안의 숫자만 입력 가능합니다.");
			obj.value = max;
			obj.focus();
		}
	}
}
/**
 * 숫자 입력 제한 체크
 * 사용예)  <input type='text'  onkeyup='javascript:onlyNumCheck(this)' />
 * @param obj 오브젝트
 */
function onlyNumberCheck(obj){
	var num = obj.value;
	var pattern = /[^0-9]/gi;
	if( pattern.test(num)==true){
		alert("숫자만 입력 가능합니다.");
		obj.value = num.replace(pattern, "");
		obj.focus();
	}
}

/**
 * 파라미터를 전화번호 형식으로 변경
 */
function phoneFormatter(val) {
	if ( typeof val == 'undefined' || val == null ) return '';
	
	var formatNum = '';
	if ( val.length == 7 ) {
		formatNum = val.replace(/(\d{3})(\d{4})/,'$1-$2');
	} else if ( val.length == 8 ) {
		formatNum = val.replace(/(\d{4})(\d{4})/,'$1-$2');
	} else if ( val.length == 10 ) {
		if ( val.indexOf('02') == 0 ) {
			formatNum = val.replace(/(\d{2})(\d{4})(\d{4})/,'$1-$2-$3');
		} else {
			formatNum = val.replace(/(\d{3})(\d{3})(\d{4})/,'$1-$2-$3');
		}
	} else if ( val.length == 11 ) {
		formatNum = val.replace(/(\d{3})(\d{4})(\d{4})/,'$1-$2-$3');
	} else {
		formatNum = val;
	}
	return formatNum;
}

/*
 * 사업자번호 유효성체크
 */
function bizNo_validate(value){
	var sumMod = 0;
	sumMod += parseInt(value.substring(0,1));
	sumMod += parseInt(value.substring(1,2)) * 3 % 10;
	sumMod += parseInt(value.substring(2,3)) * 7 % 10;
	sumMod += parseInt(value.substring(3,4)) * 1 % 10;
	sumMod += parseInt(value.substring(4,5)) * 3 % 10;
	sumMod += parseInt(value.substring(5,6)) * 7 % 10;
	sumMod += parseInt(value.substring(6,7)) * 1 % 10;
	sumMod += parseInt(value.substring(7,8)) * 3 % 10;
	sumMod += Math.floor(parseInt(value.substring(8,9)) * 5 / 10);
	sumMod += parseInt(value.substring(8,9)) * 5 % 10;
	sumMod += parseInt(value.substring(9,10));
	
	if(sumMod % 10 != 0){
		alert("사업자등록번호가 잘못되었습니다.");
		return false;
	}
	return true;
}

/*
 * 주민번호 유효성체크
 */
function idNo_validate(value){
	temp = value;
	var temp1 = temp.substring(0,6);
  	var temp2 = temp.substring(6,13);

  	if(temp.length < 13){
   		alert("주민등록번호가 유효하지 않습니다. 다시 입력하세요");
   		return false;
  	}
  
	//내국인
  	if(temp2.charAt(0) <= "4"){
   		for(i=0,sum=0; i<12; i++){
    		sum += (((i%8) + 2) * (temp.charAt(i) - "0"));
   		}
   
   		sum = 11 - (sum % 11);
   		sum = sum % 10;

   		if(sum != temp.charAt(12)){
    		alert("주민등록번호가 유효하지 않습니다. 다시 입력하세요.");
    		return false;
   		}
   	  	return true;   		
  	}
  	
	//외국인  	
  	else{
		if (fgn_no_chksum(temp) == false){
			alert("주민등록번호가 유효하지 않습니다. 다시 입력하세요.");
    		return false;
		}
		return true; 
	}
  	
  	return true;
}

/*
 * 외국인 주민등록번호 체크
 */
function fgn_no_chksum(reg_no) {
	var sum = 0;
  	var odd = 0;
  	var buf = new Array(13);
  
  	for (i = 0; i < 13; i++)
   		buf[i] = parseInt(reg_no.charAt(i));
  		odd = buf[7]*10 + buf[8];    
  
  		if (odd%2 != 0) 
   			return false;
  		if ((buf[11] != 6)&&(buf[11] != 7)&&(buf[11] != 8)&&(buf[11] != 9))
   	return false;
   
  	var multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
  
  	for (i = 0, sum = 0; i < 12; i++) 
   		sum += (buf[i] *= multipliers[i]);
  		sum=11-(sum%11);    
  	if (sum>=10) sum-=10;
  		sum += 2;
  	if (sum>=10) sum-=10;
  
	if ( sum != buf[12]) 
		return false;
	else  
		return true;
}

/*
 * 법인등록번호 유효성 체크
 */
function isCorpNo(Sect) {
	var szChkDgt = new Array(1,2,1,2,1,2,1,2,1,2,1,2);
	var szCoNo = Sect;
	var lV1 = 0;
    var nV2 = 0;
    var nV3 = 0;

    for(var i = 0; i < 12; i++) {
    	lV1 = parseInt(szCoNo.substring(i,i+1)) * szChkDgt[i];

		if(lV1 >= 10) {
		     nV2 += lV1 % 10;
		} else {
		    nV2 += lV1;
		}
    }
    nV3 = nV2 % 10;
   
    if(nV3 > 0) {
	   nV3 = 10 - nV3;
    } else {
	   nV3 = 0;
    }

    if(szCoNo.length != 13){
    	alert("법인등록번호가 유효하지 않습니다. 다시 입력하세요.");
    	return false;    	
    }
    
    if(szCoNo.substring(12,13) != nV3) {
    	alert("법인등록번호가 유효하지 않습니다. 다시 입력하세요.");
    	return false;
    }
    
    return true;
}