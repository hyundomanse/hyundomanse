// JavaScript Document

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function setPng24(obj) {    
        obj.width=obj.height=1;    
        obj.className=obj.className.replace(/\bpng24\b/i,'');    
        obj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+obj.src+"',sizingMethod='image');"   
        obj.src='';    
        return '';    
    }

function MM_showHideLayers() { //v9.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) 
  with (document) if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}

function showReview(){
	//btn_review.style.display="none";
	  document.getElementById("btn_review").style.display="none";
	  re_reg.style.display="block";
}
function closeReview(){
      //btn_review.style.display="block";
	  document.getElementById("btn_review").style.display="block";
	  re_reg.style.display="none";
}


function show_dcPop(){
      dc_pop.style.display="block";
}
function close_dcPop(){
      dc_pop.style.display="none";
}
function show_tiPop(){
    od_time.style.display="block";
}
function close_tiPop(){
    od_time.style.display="none";
}
function showSearch(br_num){
	if(br_num == 1){
      search_box.style.display="block";
	  btn_search_img_off.style.display="none";
	  btn_search_img_on.style.display="block";
	}else{
	  search_box.style.display="none";
	  btn_search_img_off.style.display="block";
	  btn_search_img_on.style.display="none";
	}

}

function brendShow(br_num){
	if(br_num == 1){
	      view_br.style.display="block";
		  br_on.style.display="none";
		  br_off.style.display="block";
		}else{
		  view_br.style.display="none";
		  br_on.style.display="block";
		  br_off.style.display="none";
		}

}
function cateShow(br_num){
	if(br_num == 1){
      view_ca.style.display="none";
	  ca_on.style.display="none";
	  ca_off.style.display="block";
	}else{
	  view_ca.style.display="block";
	  ca_on.style.display="block";
	  ca_off.style.display="none";
	}

}
function mobileShow(mo_num){
	if(mo_num == 1){
	  mo_ca_off1.style.display="none";
	  mo_ca_off2.style.display="block";
	  mo_ca_off3.style.display="block";
	  mo_ca_off4.style.display="block";
	  mo_ca_on1.style.display="block";
	  mo_ca_on2.style.display="none";
	  mo_ca_on3.style.display="none";
	  mo_ca_on4.style.display="none";
      mobileS1.style.display="block";
	  mobileS2.style.display="none";
	  mobileS3.style.display="none";
	  mobileS4.style.display="none";
	}else if(mo_num == 2){
	  mo_ca_off1.style.display="block";
	  mo_ca_on1.style.display="none";
      mobileS1.style.display="none";
	}
	else if(mo_num == 3){
	  mo_ca_off1.style.display="block";
	  mo_ca_off2.style.display="none";
	  mo_ca_off3.style.display="block";
	  mo_ca_off4.style.display="block";
	  mo_ca_on1.style.display="none";
	  mo_ca_on2.style.display="block";
	  mo_ca_on3.style.display="none";
	  mo_ca_on4.style.display="none";
      mobileS1.style.display="none";
	  mobileS2.style.display="block";
	  mobileS3.style.display="none";
	  mobileS4.style.display="none";
	}else if(mo_num == 4){
	  mo_ca_off2.style.display="block";
	  mo_ca_on2.style.display="none";
      mobileS2.style.display="none";
	}else if(mo_num == 5){
	  mo_ca_off1.style.display="block";
	  mo_ca_off2.style.display="block";
	  mo_ca_off3.style.display="none";
	  mo_ca_off4.style.display="block";
	  mo_ca_on1.style.display="none";
	  mo_ca_on2.style.display="none";
	  mo_ca_on3.style.display="block";
	  mo_ca_on4.style.display="none";
      mobileS1.style.display="none";
	  mobileS2.style.display="none";
	  mobileS3.style.display="block";
	  mobileS4.style.display="none";
	}else if(mo_num == 6){
	  mo_ca_off3.style.display="block";
	  mo_ca_on3.style.display="none";
      mobileS3.style.display="none";
	}else if(mo_num == 7){
	  mo_ca_off1.style.display="block";
	  mo_ca_off2.style.display="block";
	  mo_ca_off3.style.display="block";
	  mo_ca_off4.style.display="none";
	  mo_ca_on1.style.display="none";
	  mo_ca_on2.style.display="none";
	  mo_ca_on3.style.display="none";
	  mo_ca_on4.style.display="block";
      mobileS1.style.display="none";
	  mobileS2.style.display="none";
	  mobileS3.style.display="none";
	  mobileS4.style.display="block";
	}else if(mo_num == 8){
	  mo_ca_off4.style.display="block";
	  mo_ca_on4.style.display="none";
      mobileS4.style.display="none";
	}

}
function iconOver(icon_num){
	if(icon_num == 1){
      img_detail_off.style.display="none";
	  img_detail_on.style.display="block";
	}else if(icon_num == 2){
	  img_wish_off.style.display="none";
	  img_wish_on.style.display="block";
	}else if(icon_num == 3){
	  img_cart_off.style.display="none";
	  img_cart_on.style.display="block";
	}else if(icon_num == 4){
	  img_buy_off.style.display="none";
	  img_buy_on.style.display="block";
	}

}
function iconOut(icon_num2){
	if(icon_num2 == 1){
      img_detail_off.style.display="block";
	  img_detail_on.style.display="none";
	}else if(icon_num2 == 2){
	  img_wish_off.style.display="block";
	  img_wish_on.style.display="none";
	}else if(icon_num2 == 3){
	  img_cart_off.style.display="block";
	  img_cart_on.style.display="none";
	}else if(icon_num2 == 4){
	  img_buy_off.style.display="block";
	  img_buy_on.style.display="none";
	}

}

function tabChange2(tab_id){// 2개 탭
	  for(var i=1; i < 3; i++){
		   if(tab_id == i){
				document.getElementById('tabt'+i).style.background="#f8f8f8";
				document.getElementById('tabt'+i).style.height="37px";
				document.getElementById('tabt'+i).style.borderLeft="1px solid #ccc";
				document.getElementById('tabt'+i).style.borderTop="1px solid #ccc";
				document.getElementById('tabt'+i).style.borderRight="1px solid #ccc";
				document.getElementById('tabt'+i).style.color="#de3831";
				document.getElementById('tabt'+i).style.fontWeight="bold";
				document.getElementById('tabt_view'+i).style.display = "block";
		   }else{
				document.getElementById('tabt'+i).style.background="#fff";
				document.getElementById('tabt'+i).style.height="36px";
				document.getElementById('tabt'+i).style.borderLeft="1px solid #ddd";
				document.getElementById('tabt'+i).style.borderTop="1px solid #ddd";
				document.getElementById('tabt'+i).style.borderRight="1px solid #ddd";
				document.getElementById('tabt'+i).style.color="#666";
				document.getElementById('tabt'+i).style.fontWeight="normal";
				document.getElementById('tabt_view'+i).style.display = "none";		
									
		   }
	  	}
	 }
function tabChange3(tab_id){// 3개 탭
	  for(var i=1; i < 4; i++){
		   if(tab_id == i){
				document.getElementById('tab'+i).style.background="#f8f8f8";
				document.getElementById('tab'+i).style.height="37px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ccc";
				document.getElementById('tab'+i).style.borderTop="1px solid #ccc";
				document.getElementById('tab'+i).style.borderRight="1px solid #ccc";
				document.getElementById('tab'+i).style.color="#de3831";
				document.getElementById('tab'+i).style.fontWeight="bold";
				document.getElementById('tab_view'+i).style.display = "block";
		   }else{
				document.getElementById('tab'+i).style.background="#fff";
				document.getElementById('tab'+i).style.height="36px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ddd";
				document.getElementById('tab'+i).style.borderTop="1px solid #ddd";
				document.getElementById('tab'+i).style.borderRight="1px solid #ddd";
				document.getElementById('tab'+i).style.color="#666";
				document.getElementById('tab'+i).style.fontWeight="normal";
				document.getElementById('tab_view'+i).style.display = "none";		
									
		   }
	  	}
	 }
function tabChange4(tab_id){// 4개 탭
	  for(var i=1; i < 5; i++){
		   if(tab_id == i){
				document.getElementById('tab'+i).style.background="#f8f8f8";
				document.getElementById('tab'+i).style.height="37px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ccc";
				document.getElementById('tab'+i).style.borderTop="1px solid #ccc";
				document.getElementById('tab'+i).style.borderRight="1px solid #ccc";
				document.getElementById('tab'+i).style.color="#de3831";
				document.getElementById('tab'+i).style.fontWeight="bold";
				document.getElementById('tab_view'+i).style.display = "block";
		   }else{
				document.getElementById('tab'+i).style.background="#fff";
				document.getElementById('tab'+i).style.height="36px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ddd";
				document.getElementById('tab'+i).style.borderTop="1px solid #ddd";
				document.getElementById('tab'+i).style.borderRight="1px solid #ddd";
				document.getElementById('tab'+i).style.color="#666";
				document.getElementById('tab'+i).style.fontWeight="normal";
				document.getElementById('tab_view'+i).style.display = "none";		
									
		   }
	  	}
	 }

function tabChange5(tab_id){// 5개 탭
	  for(var i=1; i < 6; i++){
		   if(tab_id == i){
				document.getElementById('tab'+i).style.background="#f8f8f8";
				document.getElementById('tab'+i).style.height="37px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ccc";
				document.getElementById('tab'+i).style.borderTop="1px solid #ccc";
				document.getElementById('tab'+i).style.borderRight="1px solid #ccc";
				document.getElementById('tab'+i).style.color="#de3831";
				document.getElementById('tab'+i).style.fontWeight="bold";
				document.getElementById('tab_view'+i).style.display = "block";
		   }else{
				document.getElementById('tab'+i).style.background="#fff";
				document.getElementById('tab'+i).style.height="36px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ddd";
				document.getElementById('tab'+i).style.borderTop="1px solid #ddd";
				document.getElementById('tab'+i).style.borderRight="1px solid #ddd";
				document.getElementById('tab'+i).style.color="#666";
				document.getElementById('tab'+i).style.fontWeight="normal";
				document.getElementById('tab_view'+i).style.display = "none";		
									
		   }
	  	}
	 }
function tabChange5_noview(tab_id){// 5개 탭
	  for(var i=1; i < 6; i++){
		   if(tab_id == i){
				document.getElementById('tab'+i).style.background="#f8f8f8";
				document.getElementById('tab'+i).style.height="37px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ccc";
				document.getElementById('tab'+i).style.borderTop="1px solid #ccc";
				document.getElementById('tab'+i).style.borderRight="1px solid #ccc";
				document.getElementById('tab'+i).style.color="#de3831";
				document.getElementById('tab'+i).style.fontWeight="bold";
				//document.getElementById('tab_view'+i).style.display = "block";
				if(tab_id==5){
					document.getElementById('brend_box').style.display = "block";
	  			}else{
		   			document.getElementById('brend_box').style.display = "none";
		  		}
		   }else{
				document.getElementById('tab'+i).style.background="#fff";
				document.getElementById('tab'+i).style.height="36px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ddd";
				document.getElementById('tab'+i).style.borderTop="1px solid #ddd";
				document.getElementById('tab'+i).style.borderRight="1px solid #ddd";
				document.getElementById('tab'+i).style.color="#666";
				document.getElementById('tab'+i).style.fontWeight="normal";
				//document.getElementById('tab_view'+i).style.display = "none";		
									
		   }
	  	}
	 }
function tabChange6(tab_id){// 6개 탭
	  for(var i=1; i < 7; i++){
		   if(tab_id == i){
				document.getElementById('tab'+i).style.background="#f8f8f8";
				document.getElementById('tab'+i).style.height="37px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ccc";
				document.getElementById('tab'+i).style.borderTop="1px solid #ccc";
				document.getElementById('tab'+i).style.borderRight="1px solid #ccc";
				document.getElementById('tab'+i).style.color="#de3831";
				document.getElementById('tab'+i).style.fontWeight="bold";
				document.getElementById('tab_view'+i).style.display = "block";
		   }else{
				document.getElementById('tab'+i).style.background="#fff";
				document.getElementById('tab'+i).style.height="36px";
				document.getElementById('tab'+i).style.borderLeft="1px solid #ddd";
				document.getElementById('tab'+i).style.borderTop="1px solid #ddd";
				document.getElementById('tab'+i).style.borderRight="1px solid #ddd";
				document.getElementById('tab'+i).style.color="#666";
				document.getElementById('tab'+i).style.fontWeight="normal";
				document.getElementById('tab_view'+i).style.display = "none";		
									
		   }
	  	}
	 }
function tabChange_brand_cn(tab_id){// 중문 상단 브랜드 탭
	  for(var j=1; j < 3; j++){
	   if(tab_id == j){
		document.getElementById('tabd'+j).style.backgroundColor="#ffffff";
		document.getElementById('tabd'+j).style.color="#cc0c00";
		document.getElementById('tabd'+j).style.fontWeight="bold";
		document.getElementById('brend_view'+j).style.display = "block";
	   }else{
		document.getElementById('tabd'+j).style.backgroundColor="#a44640";
		document.getElementById('tabd'+j).style.color="#fff";
		document.getElementById('tabd'+j).style.fontWeight="normal";
		document.getElementById('brend_view'+j).style.display = "none";							
	   }
	  }
	 }

function tabChange_brand(tab_id){// 국문 상단 브랜드 탭
	  for(var j=1; j < 4; j++){
	   if(tab_id == j){
		document.getElementById('tabd'+j).style.backgroundColor="#ffffff";
		document.getElementById('tabd'+j).style.color="#cc0c00";
		document.getElementById('tabd'+j).style.fontWeight="bold";
		document.getElementById('brend_view'+j).style.display = "block";
	   }else{
		document.getElementById('tabd'+j).style.backgroundColor="#8e0800";
		document.getElementById('tabd'+j).style.color="#cba4a3";
		document.getElementById('tabd'+j).style.fontWeight="normal";
		document.getElementById('brend_view'+j).style.display = "none";							
	   }
	  }
	 }
	 
function tabChange_faq(tab_id6){// 고객센터 FAQ 탭
	  for(var i=1; i < 7; i++){
	   if(tab_id6 == i){
		document.getElementById('tabf'+i).style.backgroundColor="#ffffff";
		document.getElementById('tabf'+i).style.borderBottom="2px solid #ffffff";
		document.getElementById('tabf'+i).style.color="#cc0c00";
		document.getElementById('tabf'+i).style.fontWeight="bold";
		document.getElementById('rst_view'+i).style.display = "block";
	   }else{
		document.getElementById('tabf'+i).style.backgroundColor="#f8f8f8";
		document.getElementById('tabf'+i).style.borderBottom="1px solid #999";
		document.getElementById('tabf'+i).style.color="#666";
		document.getElementById('tabf'+i).style.fontWeight="normal";
		document.getElementById('rst_view'+i).style.display = "none";							
	   }
	  }
	 }
// 마우스 오버되면 보이게 하는 스크립트
function toggle_tip(id,view) { //v9.0
	document.getElementById(id).style.display=view;  
}



/**
 * Fo 로그아웃
 * @param arg
 */
function actionLogoutFo(lang) {
	  	var url = '/comm/login/actionLogoutFo.do';

	  	$.ajax({
		type : "POST",
	    url  : url ,	  
	    dataType : "json",
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		success : function(data) {

		if(lang == "1") {
			location.href ='/Ko';
			
		} else {
			location.href ='/Cn';
		} 

			//location.href ='/';
			//alert('Okay Logout');
		  } , error : function(request, status, error) {
 				//alert('오류가 발생하였습니다.');
 			} , 
 			complete : function(request, status) {
 			//	alert('Okay complate');
 			}
		 
		});  

}