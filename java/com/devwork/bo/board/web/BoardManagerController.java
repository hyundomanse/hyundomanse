package com.devwork.bo.board.web;

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

import com.devwork.bo.board.service.BoardManagerService;
import com.devwork.common.LoginAdminVO;
import com.devwork.common.util.DateUtil;
import com.devwork.common.util.SessionCookieUtil;
import com.devwork.common.util.StringUtil;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * com.devwork.bo.boardController.web 
 *    |_ BoardManagerController.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Controller
public class BoardManagerController {
	
	/* log */
    protected static final Log log = LogFactory.getLog(BoardManagerController.class);
    
    /* boardManagerService */
    @Resource(name="boardManagerService")
    private BoardManagerService boardManagerService;
    
    MappingJacksonJsonView ajaxMainView = new MappingJacksonJsonView();
    
    /**
	 * 게시판관리 목록호출
	 * @param model
	 * @return
	 * @throws Exception 
	 */    
    @RequestMapping(value="/bo/board/boardManagerListCall.do")
    public String boardManagerListCall (
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
    	
    	return "bo/board/boardManagerList";
    }
    
    /**
	 * 게시판관리 목록조회
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value="/bo/board/boardManagerList.do")
    public ModelAndView boardManagerList (
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
	    	List boardMgrList = boardManagerService.selectBoardManagerList(commandMap);
	    	int totCnt = boardManagerService.selectBoardManagerListTotCnt(commandMap);
	    	
	    	paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			commandMap.put("totalPageCount", paginationInfo.getTotalPageCount());
			commandMap.put("totCnt", totCnt);
	    	
	    	model.addAttribute("commandMap", commandMap);
	    	model.addAttribute("boardMgrList", boardMgrList);
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView(ajaxMainView, model);
    }
  
    
    /**
	 * 게시판관리 등록
	 * @param model
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value="/bo/board/boardManagerRegist.do")
    public String boardManagerRegist (
    		ModelMap model,
    		HttpServletRequest request,
    		Map<String, Object> commandMap) throws Exception {

    	try{
    		LoginAdminVO loginAdminVo = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");
    		commandMap.put("sessionAdminId", loginAdminVo.getAdminId());
    		boardManagerService.insertBoardManager(commandMap);
    		
    		model.addAttribute("commandMap", commandMap);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/bo/board/boardManagerListCall.do";
    }    
    
    /**
 	 * 게시판관리 수정
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
     @SuppressWarnings("unused")
	@RequestMapping(value="/bo/board/boardManagerModify.do")
     public ModelAndView boardManagerModify (
     		ModelMap model,
     		HttpServletRequest request,
     		Map<String, Object> commandMap) throws Exception {
    	 	Boolean rtnSuccess 	= false;

    	try{
    		LoginAdminVO loginAdminVo = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");
    		commandMap.put("sessionAdminId", loginAdminVo.getAdminId());     		
    		rtnSuccess = boardManagerService.updateBoardManager(commandMap);
     		model.addAttribute("commandMap", commandMap);
     	}catch(Exception e){
     		rtnSuccess = false;
     	}
     	return new ModelAndView(ajaxMainView, model);
     }

}
