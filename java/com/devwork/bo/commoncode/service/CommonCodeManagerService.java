package com.devwork.bo.commoncode.service;

import java.util.List;
import java.util.Map;

import com.devwork.common.map.CommonMap;

/**
 * com.devwork.bo.commoncode.service 
 *    |_ CommonCodeManagerService.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public interface CommonCodeManagerService {
	
	/**
	 * @Method Name: selectCommonCodeList
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	List selectCommonCodeList (Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectCommonCodeListTotCnt
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	int selectCommonCodeListTotCnt (Map<String, Object> commandMap) throws Exception;

	/**
	 * @Method Name: selectCommonCode
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	CommonMap selectCommonCode(Map<String, Object> commandMap) throws Exception;

	/**
	 * @Method Name: insertCommonCode
	 * @param commandMap
	 * @throws Exception
	 */ 	
	void insertCommonCode(Map<String, Object> commandMap) throws Exception;	
	
	/**
	 * @Method Name: updateCommonCode
	 * @param commandMap
	 * @throws Exception
	 */ 	
	void updateCommonCode(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: deleteCommonCode
	 * @param commandMap
	 * @throws Exception
	 */ 	
	void deleteCommonCode(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectCommonCodeDupCheck
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	int selectCommonCodeDupCheck (Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectGroupCode
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	CommonMap selectGroupCode(Map<String, Object> commandMap) throws Exception;
	
	
	/**
	 * @Method Name: selectGroupCodeList
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	List selectGroupCodeList(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectGroupCodeListTotCnt
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 
	int selectGroupCodeListTotCnt(Map<String, Object> commandMap) throws Exception;
	
	
	/**
	 * @Method Name: insertCommonCode
	 * @param commandMap
	 * @throws Exception
	 */ 
	void insertGroupCode(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: updateGroupCode
	 * @param commandMap
	 * @throws Exception
	 */ 
	void updateGroupCode(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectGroupCodeDupCheck
	 * @param commandMap
	 * @throws Exception
	 */
	int selectGroupCodeDupCheck(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: updateGroupCodeUseYn
	 * @param commandMap
	 * @throws Exception
	 */ 
	void updateGroupCodeUseYn(Map<String, Object> commandMap) throws Exception;
	
	
}
