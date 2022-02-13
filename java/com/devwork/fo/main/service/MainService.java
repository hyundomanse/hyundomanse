package com.devwork.fo.main.service;

import java.util.List;
import java.util.Map;

import com.devwork.common.map.CommonMap;

/**
 * com.devwork.fo.main.service 
 *    |_ MainService.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@SuppressWarnings("unused")
public interface MainService {

	/**
	 * 메인 목록 조회
	 * @param commandMap category 조회 조건 정보
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	List selectMainList(Map<String, Object> commandMap) throws Exception;
	
}
