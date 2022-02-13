package com.devwork.common;

/**
 * com.devwork.common 
 *    |_ ConstCode.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class ConstCode {

	private ConstCode(){}

	/*공통*/
	public static final String USEYN_Y	= "Y";
	public static final String USEYN_N	= "N";
	
	/*관리자권한관리 조회구분*/
	public static final String ADMIN_AUTH_LIST_SEARCH_TYPE01	= "01";	// 전체
	public static final String ADMIN_AUTH_LIST_SEARCH_TYPE02	= "02";	// 이름
	public static final String ADMIN_AUTH_LIST_SEARCH_TYPE03	= "03";	// 아이디
	
	/*공통코드관리 조회구분*/
	public static final String COMMON_CODE_LIST_SEARCH_TYPE01	= "01";	// 전체
	public static final String COMMON_CODE_LIST_SEARCH_TYPE02	= "02";	// 등록자ID
	public static final String COMMON_CODE_LIST_SEARCH_TYPE03	= "03";	// 수정자ID
	
	/*관리자권한관리 권한코드*/
	public static final String ADMIN_AUTH_01	= "01";					// 슈퍼관리자
	public static final String ADMIN_AUTH_02	= "02";					// 서브관리자
	
	/*게시판종류 구분*/
	public static final String BOARD_TYPE01	= "01";						// 텍스트게시판
	public static final String BOARD_TYPE02	= "02";						// 이미지게시판
	public static final String BOARD_TYPE03	= "03";						// 동영상게시판
	
}
