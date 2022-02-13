package com.devwork.bo.adminAuth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devwork.bo.adminAuth.service.AdminAuthManagerService;
import com.devwork.common.ibatis.dao.CommonDAO;
import com.devwork.common.map.CommonMap;
import com.devwork.common.util.Base64Utils;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * com.devwork.bo.adminAuth.service.impl 
 *    |_ AdminAuthManagerServiceImpl.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Service("adminAuthManagerService")
public class AdminAuthManagerServiceImpl extends AbstractServiceImpl implements AdminAuthManagerService {

	@Resource(name="commonDAO")
	private CommonDAO commonDAO;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectAdminAuthList (Map<String, Object> commandMap) throws Exception {
		List list = commonDAO.selectList("BO.AdminAuth.selectAdminAuthList", (HashMap<String, Object>) commandMap);
		
		Map resultMap		= new HashMap();
		List resultList  	= new ArrayList();
		String adminEmail	= "";
		String adminTel		= "";
		String adminMobile	= "";
		/*base64 복호화*/
		Base64Utils base64	= new Base64Utils();
		
		if(list != null){
			for (int i=0; i<list.size(); i++) {
				resultMap	= new HashMap();
				resultMap	= (Map)list.get(i);
				adminEmail	= base64.base64decoding(resultMap.get("adminEmail").toString());
				adminTel	= base64.base64decoding(resultMap.get("adminTel").toString());
				adminMobile	= base64.base64decoding(resultMap.get("adminMobile").toString());
				
				
				log.debug("이메일 " + resultMap.get("adminEmail").toString());
				log.debug("이메일 " + adminEmail);
				log.debug("전화번호 " + resultMap.get("adminTel").toString());
				log.debug("전화번호 " + adminTel);
				log.debug("모바일 " + resultMap.get("adminMobile").toString());
				log.debug("모바일 " + adminMobile);
				
				
				
				
				
				
				resultMap.put("ADMIN_EMAIL"	, adminEmail);
				resultMap.put("ADMIN_TEL"	, adminTel);
				resultMap.put("ADMIN_MOBILE", adminMobile);
				resultList.add(resultMap);				
			}
		}
		return resultList;
	}

	public int selectAdminAuthListTotCnt (Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectListTotCnt("BO.AdminAuth.selectAdminAuthListTotCnt", (HashMap<String, Object>)commandMap);
	}

	@SuppressWarnings("rawtypes")
	public CommonMap selectAdminAuth(Map<String, Object> commandMap) throws Exception {
		CommonMap map = (CommonMap)commonDAO.selectByPk("BO.AdminAuth.selectAdminAuth", (HashMap) commandMap);
		CommonMap resultMap = new CommonMap();

		/*base64 복호화*/
		Base64Utils base64	= new Base64Utils();
		resultMap.put("ADMIN_ID"		, map.get("adminId"));
		resultMap.put("ADMIN_AUTH"		, map.get("adminAuth"));
		resultMap.put("ADMIN_PASSWORD"	, base64.base64decoding(map.get("adminPassword").toString()));
		resultMap.put("ADMIN_NAME"		, map.get("adminName"));
		resultMap.put("ADMIN_DEPT"		, map.get("adminDept"));
		resultMap.put("ADMIN_EMAIL"		, base64.base64decoding(map.get("adminEmail").toString()));
		resultMap.put("ADMIN_TEL"		, base64.base64decoding(map.get("adminTel").toString()));
		resultMap.put("ADMIN_MOBILE"	, base64.base64decoding(map.get("adminMobile").toString()));
		resultMap.put("USE_YN"			, map.get("useYn"));
		resultMap.put("REG_ID"			, map.get("regId"));
		resultMap.put("REG_DT"			, map.get("regDt"));
		resultMap.put("MOD_ID"			, map.get("modId"));
		resultMap.put("MOD_DT"			, map.get("modDt"));
		
		return resultMap;
	}

	@SuppressWarnings("rawtypes")
	public void insertAdminAuth(Map<String, Object> commandMap) throws Exception {
		/*base64 암호화*/
		Base64Utils base64	= new Base64Utils();
		commandMap.put("searchAdminPassword", base64.base64Encoding(commandMap.get("searchAdminPassword").toString()));
		commandMap.put("searchAdminEmail"	, base64.base64Encoding(commandMap.get("searchAdminEmail").toString()));
		commandMap.put("searchAdminTel"		, base64.base64Encoding(commandMap.get("searchAdminTel").toString()));
		commandMap.put("searchAdminMobile"	, base64.base64Encoding(commandMap.get("searchAdminMobile").toString()));		
		
		commonDAO.insert("BO.AdminAuth.insertAdminAuth", (HashMap) commandMap); 
	}

	@SuppressWarnings("rawtypes")
	public void updateAdminAuth(Map<String, Object> commandMap) throws Exception {
		/*base64 암호화*/
		Base64Utils base64	= new Base64Utils();
		commandMap.put("searchAdminPassword", base64.base64Encoding(commandMap.get("searchAdminPassword").toString()));
		commandMap.put("searchAdminEmail"	, base64.base64Encoding(commandMap.get("searchAdminEmail").toString()));
		commandMap.put("searchAdminTel"		, base64.base64Encoding(commandMap.get("searchAdminTel").toString()));
		commandMap.put("searchAdminMobile"	, base64.base64Encoding(commandMap.get("searchAdminMobile").toString()));
		
		commonDAO.update("BO.AdminAuth.updateAdminAuth", (HashMap) commandMap);
	}

	@SuppressWarnings("rawtypes")
	public void deleteAdminAuth(Map<String, Object> commandMap) throws Exception {
		commonDAO.delete("BO.AdminAuth.deleteAdminAuth", (HashMap) commandMap);
	}

	public int selectAdminAuthDupCheck (Map<String, Object> commandMap) throws Exception {
		return commonDAO.selectListTotCnt("BO.AdminAuth.selectAdminAuthDupCheck", (HashMap<String, Object>) commandMap);
	}	
}
