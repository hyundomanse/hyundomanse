package com.devwork.bo.commoncode.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devwork.bo.commoncode.service.CommonCodeManagerService;
import com.devwork.common.ibatis.dao.CommonDAO;
import com.devwork.common.map.CommonMap;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * com.devwork.bo.commoncode.service.impl 
 *    |_ CommonCodeManagerServiceImpl.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Service("commonCodeManagerService")
public class CommonCodeManagerServiceImpl extends AbstractServiceImpl implements CommonCodeManagerService {

	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	

	/**
	 * @Method Name: selectCommonCodeList
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#selectCommonCodeList(java.util.Map)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	public List selectCommonCodeList (Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectList("BO.CommonCode.selectCommonCodeList", (HashMap<String, Object>) commandMap);
	}

	/**
	 * @Method Name: selectCommonCodeListTotCnt
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#selectCommonCodeListTotCnt(java.util.Map)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	public int selectCommonCodeListTotCnt (Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectListTotCnt("BO.CommonCode.selectCommonCodeListTotCnt", (HashMap<String, Object>)commandMap);
	}

	/**
	 * @Method Name: selectCommonCode
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#selectCommonCode(java.util.Map)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	public CommonMap selectCommonCode(Map<String, Object> commandMap) throws Exception {
		CommonMap map = (CommonMap)commonDAO.selectByPk("BO.CommonCode.selectCommonCode", (HashMap) commandMap);
		return map;
	}

	/**
	 * @Method Name: insertCommonCode
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#insertCommonCode(java.util.Map)
	 * @param commandMap
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	public void insertCommonCode(Map<String, Object> commandMap) throws Exception {
		commonDAO.insert("BO.CommonCode.insertCommonCode", (HashMap) commandMap); 
	}

	/**
	 * @Method Name: updateCommonCode
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#updateCommonCode(java.util.Map)
	 * @param commandMap
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	public void updateCommonCode(Map<String, Object> commandMap) throws Exception {
		commonDAO.update("BO.CommonCode.updateCommonCode", (HashMap) commandMap);
	}
	
	/**
	 * @Method Name: deleteCommonCode
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#deleteCommonCode(java.util.Map)
	 * @param commandMap
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	public void deleteCommonCode(Map<String, Object> commandMap) throws Exception {
		commonDAO.delete("BO.CommonCode.deleteCommonCode", (HashMap) commandMap);
	}
	
	/**
	 * @Method Name: selectCommonCodeDupCheck
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#selectCommonCodeDupCheck(java.util.Map)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	public int selectCommonCodeDupCheck (Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectListTotCnt("BO.CommonCode.selectCommonCodeDupCheck", (HashMap<String, Object>) commandMap);
	}
	
	
	/**
	 * @Method Name: selectGroupCode
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#selectCommonCode(java.util.Map)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 
	@SuppressWarnings("rawtypes")
	@Override
	public CommonMap selectGroupCode(Map<String, Object> commandMap) throws Exception {
		CommonMap map = (CommonMap)commonDAO.selectByPk("BO.CommonCode.selectGroupCode", (HashMap) commandMap);
		return map;
	}
	
	/**
	 * @Method Name: selectGroupCodeList
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#selectCommonCodeList(java.util.Map)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 	
	@SuppressWarnings("rawtypes")
	@Override
	public List selectGroupCodeList(Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectList("BO.CommonCode.selectGroupCodeList", (HashMap<String, Object>) commandMap);
	}
	
	/**
	 * @Method Name: selectGroupCodeListTotCnt
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#selectCommonCodeListTotCnt(java.util.Map)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */ 
	@Override
	public int selectGroupCodeListTotCnt(Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectListTotCnt("BO.CommonCode.selectGroupCodeListTotCnt", (HashMap<String, Object>)commandMap);
	}
	
	/**
	 * @Method Name: insertGroupCode
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#insertGroupCode(java.util.Map)
	 * @param commandMap
	 * @throws Exception
	 */ 
	@SuppressWarnings("rawtypes")
	@Override
	public void insertGroupCode(Map<String, Object> commandMap) throws Exception {
		commonDAO.insert("BO.CommonCode.insertGroupCode", (HashMap) commandMap); 
	}
	
	/**
	 * @Method Name: updateGroupCode
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#updateGroupCode(java.util.Map)
	 * @param commandMap
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateGroupCode(Map<String, Object> commandMap) throws Exception {
		commonDAO.update("BO.CommonCode.updateGroupCode", (HashMap) commandMap);
	}

	/**
	 * @Method Name: selectListTotCnt
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#updateGroupCode(java.util.Map)
	 * @param commandMap
	 * @throws Exception
	 */
	@Override
	public int selectGroupCodeDupCheck(Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectListTotCnt("BO.CommonCode.selectGroupCodeDupCheck", (HashMap<String, Object>) commandMap);
	}
	
	/**
	 * @Method Name: updateGroupCodeUseYn
	 * @see com.devwork.bo.commoncode.service.CommonCodeManagerService#updateGroupCode(java.util.Map)
	 * @param commandMap
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateGroupCodeUseYn(Map<String, Object> commandMap) throws Exception {
		commonDAO.update("BO.CommonCode.updateGroupCodeUseYn", (HashMap) commandMap);
	}	
}
