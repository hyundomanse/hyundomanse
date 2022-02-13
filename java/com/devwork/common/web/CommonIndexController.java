package com.devwork.common.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devwork.common.LoginAdminVO;
import com.devwork.common.util.SessionCookieUtil;

@Controller
public class CommonIndexController implements ApplicationContextAware ,InitializingBean {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
    
	protected static final Log log = LogFactory.getLog(CommonIndexController.class);
	
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
		log.debug("CommonIndexController setApplicationContext method has called!");
	}
	
	/**
	 * @Method Name: BO INDEX 호출
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 	
	@RequestMapping("/boIndex.do")
	public String boIndex(ModelMap model , HttpServletRequest request){
		
		String returnUrl = "";
		try {
			LoginAdminVO loginAdminVo = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");

			if (loginAdminVo!= null && loginAdminVo.getAdminId() != null && loginAdminVo.getAdminId() != "") {
				returnUrl = "forward:/boMain.do";	// 메인페이지 호출.
			} else {
				returnUrl = "bo/index";		// 로그인페이지 호출.
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnUrl = "bo/index";			// 로그인페이지 호출.
			e.printStackTrace();
		}
		return returnUrl;
	}

}
