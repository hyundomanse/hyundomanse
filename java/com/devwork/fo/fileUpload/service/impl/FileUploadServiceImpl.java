package com.devwork.fo.fileUpload.service.impl;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devwork.common.ibatis.dao.CommonDAO;
import com.devwork.fo.fileUpload.service.FileUploadService;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("fileUploadService")
public class FileUploadServiceImpl extends AbstractServiceImpl implements FileUploadService{

	/** commonDAO */
    @Resource(name="commonDAO")
    private CommonDAO commonDAO;

    @SuppressWarnings("rawtypes")
	public void insertFile(Map<String, Object> commandMap) throws Exception {
		commonDAO.insert("BO.File.insertFile", (HashMap) commandMap); 
	}
    
}
