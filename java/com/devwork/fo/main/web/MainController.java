package com.devwork.fo.main.web;

import java.util.ArrayList;
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

import com.devwork.common.LoginVO;
import com.devwork.common.util.SessionCookieUtil;
import com.devwork.common.util.StringUtil;
import com.devwork.fo.main.service.MainService;

/**
 * com.devwork.fo.main.web 
 *    |_ MainController.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Controller
public class MainController {

	/** log */
	protected static final Log log = LogFactory.getLog(MainController.class);

	/** mainService */
	@Resource(name = "mainService")
	private MainService mainService;

	MappingJacksonJsonView ajaxMainView = new MappingJacksonJsonView();
	
	/***
	 * 메인페이지 호출
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/testPage.do")
	public String test(ModelMap model, Map<String, Object> commandMap,
			HttpServletRequest request) throws Exception {

		return "testPage";
	}
	/***
	 * 메인페이지 호출
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/main.do")
	public String goMain(ModelMap model, Map<String, Object> commandMap,
			HttpServletRequest request) throws Exception {

		String returnURL 	= "";
		LoginVO loginVo = (LoginVO) SessionCookieUtil.getSessionAttribute(request,"loginVO");
		log.debug("LOGIN_VO=["+loginVo+"]");
		
		String memGrade = "";
		
		if (loginVo != null) {
			memGrade = StringUtil.isNullToString(loginVo.getMemberAuth());
		}
		
		commandMap.put("memberGrade", memGrade);
		//commandMap.put("globalLang" , globalLang);

		model.addAttribute("isMain", "Y");
		model.addAttribute("commandMap", commandMap);
		if (loginVo != null && loginVo.getMemberId() != "") {
			//returnURL = "fo/"+globalLang+"/display/displayMain"; // 추후 페이지 분기 처리
			returnURL = "fo/main"; // 추후 페이지 분기 처리
		} else {
			//returnURL = "fo/"+globalLang+"/display/displayMain";
			returnURL = "fo/main"; // 추후 페이지 분기 처리
		}
		return returnURL;
	}	

	/**
	 * 메인 팝업 리스트 조회
	 * @param model
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/mainPopup.do")
	public String selectPouppList (
			ModelMap model,
			HttpServletRequest request,
			Map<String, Object> commandMap) throws Exception{
		
		try{
			//List mainPopupList = mainService.selectPopupList(commandMap);
			List mainPopupList = new ArrayList();
			
			model.addAttribute("mainPopupList", mainPopupList);
			model.addAttribute("commandMap", commandMap);
		}catch(Exception e){
			model.addAttribute("rtnMsg", "ERROR");
			e.printStackTrace();
		}
		return "fo/mainPopup";
	}	
}
