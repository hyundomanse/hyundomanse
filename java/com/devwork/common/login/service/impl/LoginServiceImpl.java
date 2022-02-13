package com.devwork.common.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devwork.common.ibatis.dao.CommonDAO;
import com.devwork.common.login.service.LoginService;
import com.devwork.common.map.CommonMap;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * com.devwork.common.login.service.impl 
 *    |_ LoginServiceImpl.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Service("loginService")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {
	
	/** SampleDAO */
    @Resource(name="commonDAO")
    private CommonDAO commonDAO;
	
    /**
     * @Method Name: selectAdminCnt
     * @see com.devwork.common.login.service.LoginService#selectAdminCnt(java.util.Map)
     * @param commandMap
     * @return
     * @throws Exception
     */ 	
    @SuppressWarnings("rawtypes")
	public int selectAdminCnt(Map<String, Object> commandMap) throws Exception {
    	return (Integer)commonDAO.selectByPk("BO.Admin.selectAdminCnt", (HashMap)commandMap);
    }

    /**
     * @Method Name: selectAdminInfo
     * @see com.devwork.common.login.service.LoginService#selectAdminInfo(java.util.Map)
     * @param commandMap
     * @return
     * @throws Exception
     */ 	
    @SuppressWarnings("rawtypes")
	public CommonMap selectAdminInfo(Map<String, Object> commandMap) throws Exception {
    	CommonMap map = (CommonMap)commonDAO.selectByPk("BO.Admin.selectAdminInfo", (HashMap) commandMap);
		return map;
    }
}
