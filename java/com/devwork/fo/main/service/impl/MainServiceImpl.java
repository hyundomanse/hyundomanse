package com.devwork.fo.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devwork.common.ibatis.dao.CommonDAO;
import com.devwork.fo.main.service.MainService;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * com.devwork.fo.main.service.impl 
 *    |_ MainServiceImpl.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Service("mainService")
public class MainServiceImpl extends AbstractServiceImpl implements MainService{

	/** CommonDAO */
    @Resource(name="commonDAO")
    private CommonDAO commonDAO;
    
    /**
     * @Method Name: selectMainList
     * @see com.devwork.fo.main.service.MainService#selectMainList(java.util.Map)
     * @param commandMap
     * @return
     * @throws Exception
     */ 	
    @SuppressWarnings("rawtypes")
	public List selectMainList(Map<String, Object> commandMap) throws Exception {
    	List cateList = commonDAO.selectList("BO.CommonCode.selectCommonCodeList", (HashMap) commandMap);
		return cateList;
    }

    
}
