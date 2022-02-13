package com.devwork.bo.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devwork.bo.board.service.BoardManagerService;
import com.devwork.common.ibatis.dao.CommonDAO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * com.devwork.bo.board.service.impl 
 *    |_ BoardManagerServiceImpl.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Service("boardManagerService")
public class BoardManagerServiceImpl extends AbstractServiceImpl implements BoardManagerService {

	@Resource(name="commonDAO")
	private CommonDAO commonDAO;

	@SuppressWarnings({ "rawtypes" })
	public List selectBoardManagerList (Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectList("BO.BoardManager.selectBoardManagerList", (HashMap<String, Object>) commandMap);
	}

	public int selectBoardManagerListTotCnt (Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectListTotCnt("BO.BoardManager.selectBoardManagerListTotCnt", (HashMap<String, Object>)commandMap);
	}

	@SuppressWarnings("rawtypes")
	public void insertBoardManager(Map<String, Object> commandMap) throws Exception {
		commonDAO.insert("BO.BoardManager.insertBoardManager", (HashMap) commandMap); 
	}

	@SuppressWarnings("rawtypes")
	public Boolean updateBoardManager(Map<String, Object> commandMap) throws Exception {
		try{
			int updateCnt = commonDAO.update("BO.BoardManager.updateBoardManager", (HashMap) commandMap);
			if (updateCnt > 0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}
	
}
