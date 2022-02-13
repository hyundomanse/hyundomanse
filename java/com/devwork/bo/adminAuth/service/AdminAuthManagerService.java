package com.devwork.bo.adminAuth.service;

import java.util.List;
import java.util.Map;

import com.devwork.common.map.CommonMap;


/**
 * com.devwork.bo.admin.service 
 *    |_ AdminManagerService.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public interface AdminAuthManagerService {
	
	/**
	 * @Method Name: selectAdminAuthList
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	List selectAdminAuthList (Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectAdminAuthListTotCnt
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	int selectAdminAuthListTotCnt (Map<String, Object> commandMap) throws Exception;

	/**
	 * @Method Name: selectAdminAuth
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	CommonMap selectAdminAuth(Map<String, Object> commandMap) throws Exception;

	/**
	 * @Method Name: insertAdminAuth
	 * @param commandMap
	 * @throws Exception
	 */ 	
	void insertAdminAuth(Map<String, Object> commandMap) throws Exception;	
	
	/**
	 * @Method Name: updateAdminAuth
	 * @param commandMap
	 * @throws Exception
	 */ 	
	void updateAdminAuth(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: deleteAdminAuth
	 * @param commandMap
	 * @throws Exception
	 */ 	
	void deleteAdminAuth(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectAdminAuthDupCheck
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	int selectAdminAuthDupCheck (Map<String, Object> commandMap) throws Exception;
	
	
}
