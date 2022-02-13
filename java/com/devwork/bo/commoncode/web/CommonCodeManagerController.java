package com.devwork.bo.commoncode.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.devwork.bo.commoncode.service.CommonCodeManagerService;
import com.devwork.common.LoginAdminVO;
import com.devwork.common.map.CommonMap;
import com.devwork.common.util.DateUtil;
import com.devwork.common.util.StringUtil;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * com.devwork.bo.commoncode.web 
 *    |_ CommonCodeManagerController.java  
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Controller
public class CommonCodeManagerController {
	
	/* log */
    protected static final Log log = LogFactory.getLog(CommonCodeManagerController.class);
    
    /* commonCodeManagerService */
    @Resource(name="commonCodeManagerService")
    private CommonCodeManagerService commonCodeManagerService;
    
    MappingJacksonJsonView ajaxMainView = new MappingJacksonJsonView();
    
    /**
     * @Method Name: 공통코드관리 목록호출
     * @param model
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */    
    @RequestMapping(value="/bo/commoncode/commonCodeListCall.do")
    public String commonCodeListCall (
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
    	
    	return "bo/commoncode/commonCodeList";
    }
    
    /**
	 * 공통코드관리 목록조회
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value="/bo/commoncode/commonCodeList.do")
    public ModelAndView commonCodeList (
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
	    	List commonCodeList = commonCodeManagerService.selectGroupCodeList(commandMap);
	    	int totCnt = commonCodeManagerService.selectGroupCodeListTotCnt(commandMap);
	    	
	    	paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
			commandMap.put("totalPageCount", paginationInfo.getTotalPageCount());
			commandMap.put("totCnt", totCnt);
	    	
	    	model.addAttribute("commandMap", commandMap);
	    	model.addAttribute("commonCodeList", commonCodeList);
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView(ajaxMainView, model);
    }
    
    
    
    /**
	 * 공통코드관리 상세 (공통코드) 목록조회
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/bo/commoncode/commonCodeDetailList.do")
    public ModelAndView commonCodeDetailList (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
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
	    	List commonCodeList = commonCodeManagerService.selectCommonCodeList(commandMap);
	    	int totCnt = commonCodeManagerService.selectCommonCodeListTotCnt(commandMap);
	    	
	    	paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
			commandMap.put("totalPageCount", paginationInfo.getTotalPageCount());
			commandMap.put("totCnt", totCnt);
	    	
	    	model.addAttribute("commandMap", commandMap);
	    	model.addAttribute("commonCodeList", commonCodeList);
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView(ajaxMainView, model);
    }
        
    
    
    
    
    
    /**
	 * 공통코드 상세화면
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/commoncode/commonCodeViewCall.do")
    public String commonCodeViewCall (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
    	try{
    		CommonMap commonCodeMap = commonCodeManagerService.selectGroupCode(commandMap);
    		
    		model.addAttribute("commonCodeMap", commonCodeMap);
    		model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "bo/commoncode/commonCodeView";
    }    
    
    /**
	 * 그룹코드 등록페이지 호출
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/commoncode/commonCodeRegistCall.do")
    public String commonCodeRegistCall (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
    	model.addAttribute("commandMap", commandMap);
    	return "bo/commoncode/commonCodeRegist";
    }
    
    /**
	 * 그룹코드 등록
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/commoncode/groupCodeRegist.do")
    public String groupCodeRegist (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    		
    	//등록자 id 가져오기
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		LoginAdminVO loginAdminVO = (LoginAdminVO)session.getAttribute("loginAdminVO");
		String sessionId = loginAdminVO.getAdminId();
		commandMap.put("sessionId", sessionId);
		
    	try{
    		commonCodeManagerService.insertGroupCode(commandMap);
    		model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/bo/commoncode/commonCodeListCall.do";
    }    
    
    /**
 	 * 그룹코드 수정
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
     @RequestMapping(value="/bo/commoncode/groupCodeModify.do")
     public String groupCodeModify (
     		ModelMap model,
     		HttpServletRequest request,
     		Map<String, Object> commandMap) throws Exception {
     		
    	//등록자 id 가져오기
 		HttpServletRequest httpRequest = (HttpServletRequest)request;
 		HttpSession session = httpRequest.getSession();
 		LoginAdminVO loginAdminVO = (LoginAdminVO)session.getAttribute("loginAdminVO");
 		String sessionId = loginAdminVO.getAdminId();
 		commandMap.put("sessionId", sessionId);
    	 
     	try{
     		commonCodeManagerService.updateGroupCode(commandMap);
     		model.addAttribute("commandMap", commandMap);
     	}catch(Exception e){
     		e.printStackTrace();
     	}
     	return "forward:/bo/commoncode/commonCodeViewCall.do";
     }
     
     /**
 	 * 그룹코드 사용여부 수정
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
     @RequestMapping(value="/bo/commoncode/groupCodeUseYn.do")
     public String groupCodeUseYn (
     		ModelMap model,
     		HttpServletRequest request,
     		Map<String, Object> commandMap) throws Exception {
     	
     	try{
     		//등록자 id 가져오기
     		HttpServletRequest httpRequest = (HttpServletRequest)request;
     		HttpSession session = httpRequest.getSession();
     		LoginAdminVO loginAdminVO = (LoginAdminVO)session.getAttribute("loginAdminVO");
     		String sessionId = loginAdminVO.getAdminId();
     		commandMap.put("sessionId", sessionId);
     		
     		commonCodeManagerService.updateGroupCodeUseYn(commandMap);
     		model.addAttribute("commandMap", commandMap);
     		
     	}catch(Exception e){
     		e.printStackTrace();
     	}
     	return "forward:/bo/commoncode/commonCodeViewCall.do";
     }
     
    /**
	 * 그룹코드 중복코드검색
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/commoncode/groupCodeDupCheck.do")
    public ModelAndView groupCodeDupCheck(
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
    	try{
	    	int chkCnt = commonCodeManagerService.selectGroupCodeDupCheck(commandMap);
	    	commandMap.put("chkCnt", chkCnt);
	    	model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView(ajaxMainView, model);
    }
    
    /**
	 * 공통코드 등록
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/commoncode/commonCodeRegist.do")
    public String commonCodeRegist (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    		
    	//등록자 id 가져오기
 		HttpServletRequest httpRequest = (HttpServletRequest)request;
 		HttpSession session = httpRequest.getSession();
 		LoginAdminVO loginAdminVO = (LoginAdminVO)session.getAttribute("loginAdminVO");
 		String sessionId = loginAdminVO.getAdminId();
 		commandMap.put("sessionId", sessionId);
    	String codeSeq = (String) commandMap.get("codeSeq");
    	
    	try{
    		commonCodeManagerService.insertCommonCode(commandMap);
    		model.addAttribute("commandMap", commandMap);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/bo/commoncode/commonCodeViewCall.do?codeSeq="+codeSeq;
    }    
    
    /**
 	 * 공통코드 수정
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
     @RequestMapping(value="/bo/commoncode/commonCodeModify.do")
     public String commonCodeModify (
     		ModelMap model,
     		HttpServletRequest request,
     		Map<String, Object> commandMap) throws Exception {
    	 
    	//등록자 id 가져오기
  		HttpServletRequest httpRequest = (HttpServletRequest)request;
  		HttpSession session = httpRequest.getSession();
  		LoginAdminVO loginAdminVO = (LoginAdminVO)session.getAttribute("loginAdminVO");
  		String sessionId = loginAdminVO.getAdminId();
  		commandMap.put("sessionId", sessionId);
     	String codeSeq = (String) commandMap.get("codeSeq");
     	
     	try{
     		commonCodeManagerService.updateCommonCode(commandMap);
     		model.addAttribute("commandMap", commandMap);
     	}catch(Exception e){
     		e.printStackTrace();
     	}
     	return "redirect:/bo/commoncode/commonCodeViewCall.do?codeSeq="+codeSeq;
     }
     
     /**
 	 * 공통코드 삭제
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
     @RequestMapping(value="/bo/commoncode/commonCodeDelete.do")
     public String commonCodeDelete (
     		ModelMap model,
     		HttpServletRequest request,
     		Map<String, Object> commandMap) throws Exception {
     	
     	try{
     		commonCodeManagerService.deleteCommonCode(commandMap);
     		model.addAttribute("commandMap", commandMap);
     	}catch(Exception e){
     		e.printStackTrace();
     	}
     	return "forward:/bo/commoncode/commonCodeListCall.do";
     }
     
    /**
	 * 공통코드 중복코드검색
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/commoncode/commonCodeDupCheck.do")
    public ModelAndView commonCodeDupCheck(
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {
    	
    	try{
	    	int chkCnt = commonCodeManagerService.selectCommonCodeDupCheck(commandMap);
	    	commandMap.put("chkCnt", chkCnt);
	    	model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView(ajaxMainView, model);
    }
    
}
