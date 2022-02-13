package com.devwork.common.login.service;

import java.util.Map; 

import com.devwork.common.map.CommonMap;

/**
 * com.devwork.common.login.service 
 *    |_ LoginService.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public interface LoginService {

    /**
     * @Method Name: 관리자 등록 유무 체크
     * @param commandMap
     * @return
     * @throws Exception
     */ 	
    int selectAdminCnt(Map<String, Object> commandMap) throws Exception;
    
    /**
     * @Method Name: 관리자 정보 조회
     * @param commandMap
     * @return
     * @throws Exception
     */ 	
    CommonMap selectAdminInfo(Map<String, Object> commandMap) throws Exception;
}
