/* Korean initialisation for the jQuery calendar extension. */
/* Written by DaeKwon Kang (ncrash.dk@gmail.com), Edited by Genie. */
jQuery(function($){
	$(".datepicker").datepicker({
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		dateFormat: 'yy-mm-dd',
		prevText: '이전달',
		nextText: '다음달',
		showOn: "button",
		changeMonth: true,
		changeYear: true,
		weekHeader: 'Wk',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		buttonImageOnly: true,
		showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
		currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
		closeText: '닫기',  // 닫기 버튼 패널
		buttonImage: '/images/btn_calendar.png'
	});
	$('.ui-datepicker-trigger').attr('alt', 'Select Date').attr('title', 'Date').attr('align','absmiddle');
	$(".datepicker").mask("9999-99-99");
	
	
	$(".datepickertime").datetimepicker({
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		dateFormat: 'yy-mm-dd',
		prevText: '이전달',
		nextText: '다음달',
		showOn: "button",
		changeMonth: true,
		changeYear: true,
		weekHeader: 'Wk',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		buttonImageOnly: true,
		showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
		closeText: '닫기',  // 닫기 버튼 패널
		currentText : '현재시간',
        timeFormat: "hh:mm",
		buttonImage: '/images/btn_calendar.png'
	});
	$('.ui-datepicker-trigger').attr('alt', 'Select Date').attr('title', 'Date').attr('align','absmiddle');
	$(".datepicker").mask("9999-99-99");
});

function datepicker_onload(){
	$(".datepicker").datepicker({
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		dateFormat: 'yy-mm-dd',
		prevText: '이전달',
		nextText: '다음달',
		showOn: "button",
		changeMonth: true,
		changeYear: true,
		weekHeader: 'Wk',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		buttonImageOnly: true,
		showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
		currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
		closeText: '닫기',  // 닫기 버튼 패널
		buttonImage: '/images/btn_calendar.png'
	});
	$('.ui-datepicker-trigger').attr('alt', 'Select Date').attr('title', 'Date').attr('align','absmiddle');
	$(".datepicker").mask("9999-99-99");
}