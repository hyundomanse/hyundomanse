package com.devwork.bo.board.service;

import java.util.List;
import java.util.Map;

/**
 * com.devwork.bo.boardController.service 
 *    |_ BoardManagerService.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public interface BoardManagerService {
	
	/**
	 * @Method Name: selectBoardManagerList
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	List selectBoardManagerList (Map<String, Object> commandMap) throws Exception;
	
	/**
	 * @Method Name: selectBoardManagerListTotCnt
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	int selectBoardManagerListTotCnt (Map<String, Object> commandMap) throws Exception;

	/**
	 * @Method Name: insertBoardManager
	 * @param commandMap
	 * @throws Exception
	 */ 	
	void insertBoardManager(Map<String, Object> commandMap) throws Exception;	
	
	/**
	 * @Method Name: updateBoardManager
	 * @param commandMap
	 * @throws Exception
	 */ 	
	Boolean updateBoardManager(Map<String, Object> commandMap) throws Exception;
	
}
