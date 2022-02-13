package com.devwork.fo.fileUpload.service;

import java.util.Map;


/**
 * com.devwork.bo.main.service 
 *    |_ FileUploadService.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public interface FileUploadService {
	
	 /**
     * @Method Name: 파일등록
     * @param commandMap
     * @return
     * @throws Exception
     */ 
	void insertFile(Map<String, Object> commandMap) throws Exception;

    
}
