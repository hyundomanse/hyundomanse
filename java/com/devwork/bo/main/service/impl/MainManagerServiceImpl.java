package com.devwork.bo.main.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devwork.bo.main.service.MainManagerService;
import com.devwork.common.ibatis.dao.CommonDAO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("mainManagerService")
public class MainManagerServiceImpl extends AbstractServiceImpl implements MainManagerService{

	/** commonDAO */
    @Resource(name="commonDAO")
    private CommonDAO commonDAO;
    
}
