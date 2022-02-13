package com.devwork.bo.main.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.devwork.bo.main.service.MainManagerService;
import com.devwork.common.LoginAdminVO;
import com.devwork.common.util.Base64Utils;
import com.devwork.common.util.SessionCookieUtil;

import egovframework.com.cmm.service.EgovProperties;

/**
 * com.devwork.bo.main.web 
 *    |_ MainManagerController.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@SuppressWarnings("unused")
@Controller
public class MainManagerController {
	/** log */
    protected static final Log log = LogFactory.getLog(MainManagerController.class);
    
    /** mainManagerService */
	@Resource(name = "mainManagerService")
	private MainManagerService mainManagerService;
	
	MappingJacksonJsonView ajaxMainView = new MappingJacksonJsonView();
	
	/**
	 * @Method Name: BO 메인페이지 호출
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 	
	@RequestMapping("/boMain.do")
	public String boMain(ModelMap model,Map<String, Object> commandMap , HttpServletRequest request) throws Exception{
		
		String returnURL = "";
		LoginAdminVO user = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");

		if (user != null && user.getAdminId() != "") {
			returnURL = "bo/main";		// 메인페이지 호출.
			
		} else {
			returnURL = "bo/index";		// 로그인페이지 호출.
		}
		return returnURL;
	}	
}
