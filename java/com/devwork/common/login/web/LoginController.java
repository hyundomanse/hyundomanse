package com.devwork.common.login.web;

import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import com.devwork.common.LoginAdminVO;
import com.devwork.common.LoginVO;
import com.devwork.common.login.service.LoginService;
import com.devwork.common.map.CommonMap;
import com.devwork.common.util.Base64Utils;
import com.devwork.common.util.SessionCookieUtil;
import com.devwork.common.util.StringUtil;

/**
 * com.devwork.common.login.web 
 *    |_ LoginController.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Controller
public class LoginController {
	/** log */
    protected static final Log log = LogFactory.getLog(LoginController.class);
    
    // 로그인 아이디 , 패스워드 특수문자 필터링 처리
 	public static Pattern escaper = Pattern.compile("([^a-zA-z0-9.])");
    
 	// 	로그인 체크 return value 정의
 	public static final int LOGIN_FAILED 	= 0;
 	public static final int LOGIN_SUCCESS 	= 1;
 	public static final int NOT_AUTH 		= 2;
 	public static final int PASSWORD_ERROR 	= 3;
 	public static final int EMAIL_ERROR 	= 4;
 	public static final int LOGIN_FO_FAILED 	= 5;
 	public static final int LOGIN_FO_SUCCESS 	= 6;
 	
    /** loginService */
	@Resource(name = "loginService")
	private LoginService loginService;
	
    MappingJacksonJsonView ajaxMainView = new MappingJacksonJsonView();
    
	/**
	 * @Method Name: BO 로그인
	 * @param commandMap
	 * @param model
	 * @param loginAdminVO	로그인 input 객체
	 * @param request		세션처리를 위한 HttpServletRequest
	 * @param response
	 * @return
	 * @throws Exception
	 */ 	
	@RequestMapping(value="/bo/login.do")
	public ModelAndView boLogin(
			Map<String, Object> commandMap, ModelMap model ,LoginAdminVO loginAdminVO,
			HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		int userInfoCheck = 0;               // Internet User 정보 존재 Count
	    int processCheck = 1;                // 로그인 체크에 따른 Return Value(의미는 밑에서 기술)
	    
        // 로그인 객체 선언
	    Base64Utils base64	= new Base64Utils();
        String inputId 		= StringUtil.isNullToString(commandMap.get("adminId"));
		String inputPwd 	= StringUtil.isNullToString(commandMap.get("adminPassword"));
		
		log.debug("START_LOGINCHECK!!!");
	    try {
	    	if (!inputId.equals("")) {
	    		inputId = escaper.matcher(inputId).replaceAll("\\\\$1");
				inputPwd = escaper.matcher(inputPwd).replaceAll("\\\\$1");
				log.debug("ID=["+inputId+"]/PWD=["+inputPwd+"]");
				commandMap.put("inputId", inputId);
				commandMap.put("inputPwd",inputPwd);
			} else {
				processCheck = LOGIN_FAILED;
			}
	    	
	    	if (processCheck != LOGIN_FAILED) {
	    		userInfoCheck = loginService.selectAdminCnt(commandMap);
		    	if ( userInfoCheck > 0) {
		    		// Process 결과 셋팅
		    		processCheck = LOGIN_SUCCESS;
		    	} else {
		    		processCheck = LOGIN_FAILED;
		    	}
		    	if (processCheck == LOGIN_SUCCESS ) {
		    		commandMap.put("adminPassword", base64.base64Encoding(inputPwd));
		    		CommonMap cargoMap	= (CommonMap)loginService.selectAdminInfo(commandMap);
		    		
		    		if(cargoMap == null){
		    			processCheck = LOGIN_FAILED;
			    		model.addAttribute("result", "F");
			    		model.addAttribute("message" ,"loginFail");		    			
		    		}else{
			    		String adminId 		= StringUtil.isNullToString(cargoMap.get("adminId"));
			    		String adminAuth 	= StringUtil.isNullToString(cargoMap.get("adminAuth"));
			    		String adminName	= StringUtil.isNullToString(cargoMap.get("adminName"));
			    		String adminDept 	= StringUtil.isNullToString(cargoMap.get("adminDept"));
			    		
			    		loginAdminVO.setAdminId(adminId);
			    		loginAdminVO.setAdminAuth(adminAuth);
			    		loginAdminVO.setAdminName(adminName);
			    		loginAdminVO.setAdminDept(adminDept);
			    		
						 // 로그인 성공, 세션 생성하기
			    		HttpServletRequest httpRequest = (HttpServletRequest)request;
			    		HttpSession session = httpRequest.getSession();
			    		session.setAttribute("loginAdminVO", loginAdminVO);
			    		model.addAttribute("result", "T");		    			
		    		}
		    		
		    	} else {	// if (processCheck == LOGIN_SUCCESS ) {
		    		model.addAttribute("result", "F");
		    		model.addAttribute("message" ,"loginFail!!");
		    	}
		    	
	    	} else {	// if (processCheck != LOGIN_FAILED) {
	    		model.addAttribute("result", "F");
	    		model.addAttribute("message" ,"loginFail~~");
	    	}
	    	 
	    } catch (Exception e) {
	    	model.addAttribute("result", "F");
	    	model.addAttribute("message" ,"loginFail@@@");
	    	e.printStackTrace();
	    }
	    return new ModelAndView(ajaxMainView, model);
	}
	
    /**
     * @Method Name: BO 로그아웃
     * @param request
     * @param model
     * @return
     * @throws Exception
     */ 	
    @RequestMapping(value="/bo/logout.do")
   	public ModelAndView actionAjaxLogout(HttpServletRequest request, ModelMap model) 
   			throws Exception {
    	
    	request.getSession().setAttribute("loginAdminVO", null);
    	return new ModelAndView(ajaxMainView, model);
    }
    
	/**
	 * @Method Name: BO 세션 소멸 여부 체크
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 	
	@RequestMapping("/bo/sessionCheck.do")
	public ModelAndView getSession(ModelMap model,Map<String, Object> commandMap , HttpServletRequest request) throws Exception{
    	String rtnRes 		= "T";
    	LoginAdminVO loginAdminVO = null; 
    	loginAdminVO = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");
		
		if (StringUtil.nullConvert(loginAdminVO.getAdminId()).equals("") ) {
			log.debug("LoginAdminVO=====["+loginAdminVO+"]");
    		rtnRes = "F";
    	} 
		model.addAttribute("rtnRes", rtnRes);
		return new ModelAndView(ajaxMainView, model);
	}    
    
	/**
	 * @Method Name: FO 로그인
	 * @param commandMap
	 * @param model
	 * @param loginVO	로그인 input 객체
	 * @param request	세션처리를 위한 HttpServletRequest
	 * @param response
	 * @return
	 * @throws Exception
	 */ 	
	@RequestMapping(value="/fo/login.do")
	public ModelAndView actionLoginFo(
			Map<String, Object> commandMap, ModelMap model ,LoginVO loginVO,
			HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		int userInfoCheck = 0;               // Internet User 정보 존재 Count
	    int processCheck = 1;                // 로그인 체크에 따른 Return Value(의미는 밑에서 기술)
	    
        // 로그인 객체 선언
        String inputId 		= StringUtil.isNullToString(commandMap.get("userId"));
		String inputPwd 	= StringUtil.isNullToString(commandMap.get("userPassword"));
		
		log.debug("START_LOGINCHECK!!!");
	    try {
	    	if (!inputId.equals("")) {
	    		inputId = escaper.matcher(inputId).replaceAll("\\\\$1");
				inputPwd = escaper.matcher(inputPwd).replaceAll("\\\\$1");
				log.debug("ID=["+inputId+"]/PWD=["+inputPwd+"]");
				commandMap.put("inputId", inputId);
				commandMap.put("inputPwd",inputPwd);
			} else {
				processCheck = LOGIN_FAILED;
			}
	    	
	    	if (processCheck != LOGIN_FAILED) {
	    		userInfoCheck = loginService.selectAdminCnt(commandMap);
		    	if ( userInfoCheck > 0) {
		    		// Process 결과 셋팅
		    		processCheck = LOGIN_SUCCESS;
		    	} else {
		    		processCheck = LOGIN_FAILED;
		    	}
		    	if (processCheck == LOGIN_SUCCESS ) {
		    		CommonMap cargoMap	= (CommonMap)loginService.selectAdminInfo(commandMap);
		    		String memberId 	= StringUtil.isNullToString(cargoMap.get("memberId"));
		    		String memberAuth	= StringUtil.isNullToString(cargoMap.get("memberAuth"));
		    		String snsType 		= StringUtil.isNullToString(cargoMap.get("snsType"));
		    		
		    		loginVO.setMemberId(memberId);
		    		loginVO.setMemberAuth(memberAuth);
		    		loginVO.setSnsType(snsType);
		    		
					 // 로그인 성공, 세션 생성하기
		    		HttpServletRequest httpRequest = (HttpServletRequest)request;
		    		HttpSession session = httpRequest.getSession();
		    		session.setAttribute("loginVO", loginVO);
		    		model.addAttribute("result", "T");
		    		
		    	} else {	// if (processCheck == LOGIN_SUCCESS ) {
		    		model.addAttribute("result", "F");
		    		model.addAttribute("message" ,"loginFail!!");
		    	}
		    	
	    	} else {	// if (processCheck != LOGIN_FAILED) {
	    		model.addAttribute("result", "F");
	    		model.addAttribute("message" ,"loginFail~~");
	    	}
	    	 
	    } catch (Exception e) {
	    	model.addAttribute("result", "F");
	    	model.addAttribute("message" ,"loginFail@@@");
	    	e.printStackTrace();
	    }
	    return new ModelAndView(ajaxMainView, model);
	}   	
   	
    /**
     * @Method Name: FO 로그아웃
     * @param request
     * @param model
     * @return
     * @throws Exception
     */ 	
    @RequestMapping(value="/fo/logout.do")
   	public ModelAndView actionAjaxLogoutFo(HttpServletRequest request, ModelMap model) 
   			throws Exception {
    	
    	request.getSession().setAttribute("loginAdminVO", null);
    	return new ModelAndView(ajaxMainView, model);
    }

	
}
