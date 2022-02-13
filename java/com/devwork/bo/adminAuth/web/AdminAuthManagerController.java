package com.devwork.bo.adminAuth.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.devwork.bo.adminAuth.service.AdminAuthManagerService;
import com.devwork.common.LoginAdminVO;
import com.devwork.common.map.CommonMap;
import com.devwork.common.util.DateUtil;
import com.devwork.common.util.SessionCookieUtil;
import com.devwork.common.util.StringUtil;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * com.devwork.bo.adminAuth.web 
 *    |_ AdminManagerController.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Controller
public class AdminAuthManagerController {
	
	/* log */
    protected static final Log log = LogFactory.getLog(AdminAuthManagerController.class);
    
    /* adminAuthManagerService */
    @Resource(name="adminAuthManagerService")
    private AdminAuthManagerService adminAuthManagerService;
    
    MappingJacksonJsonView ajaxMainView = new MappingJacksonJsonView();
    

    /**
     * @Method Name: adminAuthListCall
     * @param model
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */ 	
    @RequestMapping(value="/bo/adminAuth/adminAuthListCall.do")
    public String adminAuthListCall (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		if(StringUtil.nullConvert(commandMap.get("pageIndex")).equals("") || StringUtil.nullConvert(commandMap.get("pageIndex")) == null) {
			paginationInfo.setCurrentPageNo(1);
		} else {
			paginationInfo.setCurrentPageNo(Integer.parseInt(StringUtil.nullConvert(commandMap.get("pageIndex"))));
		}
		
		// 날짜설정
		String toDate 	= DateUtil.getToday();
		String fromDate	= DateUtil.addDay(toDate, -31);

		commandMap.put("fromDate"	, DateUtil.formatDate(fromDate, "-"));
		commandMap.put("toDate"		, DateUtil.formatDate(toDate, "-"));			
		
		commandMap.put("pageIndex", paginationInfo.getCurrentPageNo());
		model.addAttribute("commandMap", commandMap);
    	
    	return "bo/adminAuth/adminAuthList";
    }
    
    /**
	 * 관리자 목록조회
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value="/bo/adminAuth/adminAuthList.do")
    public ModelAndView adminAuthList (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
		String toDate 	= DateUtil.getToday();
		String fromDate	= DateUtil.addDay(toDate, -31);

		// 입력받은 일자 변환
        if(StringUtil.nullConvert(commandMap.get("searchFromDate")) != null && StringUtil.nullConvert(commandMap.get("searchFromDate")).length() == 10) {
        	commandMap.put("searchFromDate",	StringUtil.isNullToString(commandMap.get("searchFromDate")).replace("-", ""));
        }else{
        	commandMap.put("searchFromDate",	StringUtil.isNullToString(toDate).replace("-", ""));
        }

        if(StringUtil.nullConvert(commandMap.get("searchToDate")) != null && StringUtil.nullConvert(commandMap.get("searchToDate")).length() == 10) {
        	commandMap.put("searchToDate",	StringUtil.isNullToString(commandMap.get("searchToDate")).replace("-", ""));
        }else{
        	commandMap.put("searchToDate",	StringUtil.isNullToString(toDate).replace("-", ""));
        }
        
    	// 페이징처리 
		PaginationInfo paginationInfo = new PaginationInfo();
		if(StringUtil.nullConvert(commandMap.get("pageIndex")).equals("") || StringUtil.nullConvert(commandMap.get("pageIndex")) == null) {
			paginationInfo.setCurrentPageNo(1);
		} else {
			paginationInfo.setCurrentPageNo(Integer.parseInt(StringUtil.nullConvert(commandMap.get("pageIndex"))));
		}
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(10);
		
		commandMap.put("firstIndex", paginationInfo.getFirstRecordIndex()+1);
		commandMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		commandMap.put("recordCountPerPage", paginationInfo.getFirstRecordIndex());
		commandMap.put("pageIndex", paginationInfo.getCurrentPageNo());
		
    	try{
	    	List adminAuthList = adminAuthManagerService.selectAdminAuthList(commandMap);
	    	int totCnt = adminAuthManagerService.selectAdminAuthListTotCnt(commandMap);
	    	
	    	paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			commandMap.put("totalPageCount", paginationInfo.getTotalPageCount());
			commandMap.put("totCnt", totCnt);
	    	
	    	model.addAttribute("commandMap", commandMap);
	    	model.addAttribute("adminAuthList", adminAuthList);
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView(ajaxMainView, model);
    }
    
    /**
	 * 관리자 상세화면
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/adminAuth/adminAuthViewCall.do")
    public String adminAuthViewCall (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
    	try{
    		CommonMap adminAuthMap = adminAuthManagerService.selectAdminAuth(commandMap);
    		
    		model.addAttribute("adminAuthMap", adminAuthMap);
    		model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "bo/adminAuth/adminAuthView";
    }    
    
    /**
	 * 관리자 등록페이지 호출
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/adminAuth/adminAuthRegistCall.do")
    public String adminAuthRegistCall (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
		
    	model.addAttribute("commandMap", commandMap);
    	return "bo/adminAuth/adminAuthRegist";
    }
    
    /**
	 * 관리자 등록
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/adminAuth/adminAuthRegist.do")
    public String adminRegist (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {

    	try{
    		LoginAdminVO loginAdminVo = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");
    		commandMap.put("sessionAdminId", loginAdminVo.getAdminId());
    		adminAuthManagerService.insertAdminAuth(commandMap);
    		
    		model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/bo/adminAuth/adminAuthListCall.do";
    }    
    
    /**
 	 * 관리자 수정
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
     @RequestMapping(value="/bo/adminAuth/adminAuthModify.do")
     public String adminAuthModify (
     		ModelMap model,
     		HttpServletRequest request,
     		Map<String, Object> commandMap) throws Exception {
     	
     	try{
    		LoginAdminVO loginAdminVo = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");
    		commandMap.put("sessionAdminId", loginAdminVo.getAdminId());     		
     		adminAuthManagerService.updateAdminAuth(commandMap);
     		
     		model.addAttribute("commandMap", commandMap);
     	}catch(Exception e){
     		e.printStackTrace();
     	}
     	return "forward:/bo/adminAuth/adminAuthViewCall.do";
     }
     
     /**
 	 * 관리자 삭제
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
     @RequestMapping(value="/bo/adminAuth/adminAuthDelete.do")
     public String adminAuthDelete (
     		ModelMap model,
     		HttpServletRequest request,
     		Map<String, Object> commandMap) throws Exception {
     	
     	try{
     		adminAuthManagerService.deleteAdminAuth(commandMap);
     		model.addAttribute("commandMap", commandMap);
     	}catch(Exception e){
     		e.printStackTrace();
     	}
     	return "forward:/bo/adminAuth/adminAuthListCall.do";
     }
     
    /**
	 * 관리자아이디 중복코드검색
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/adminAuth/adminAuthDupCheck.do")
    public ModelAndView adminAuthDupCheck(
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
    	try{
	    	int chkCnt = adminAuthManagerService.selectAdminAuthDupCheck(commandMap);
	    	commandMap.put("chkCnt", chkCnt);
	    	model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView(ajaxMainView, model);
    }

}
