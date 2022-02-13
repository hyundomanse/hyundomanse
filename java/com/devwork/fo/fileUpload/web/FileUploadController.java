package com.devwork.fo.fileUpload.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.devwork.common.LoginAdminVO;
import com.devwork.common.map.CommonMap;
import com.devwork.fo.fileUpload.service.FileUploadService;

import egovframework.com.cmm.service.EgovProperties;


/**
 * com.devwork.bo.main.web 
 *    |_ FileUploadController.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@SuppressWarnings("unused")
@Controller
public class FileUploadController {
	/** log */
    protected static final Log log = LogFactory.getLog(FileUploadController.class);
    
    /** fileUploadService */
	@Resource(name = "fileUploadService")
	private FileUploadService fileUploadService;
	
	MappingJacksonJsonView ajaxMainView = new MappingJacksonJsonView();
	
	private static final Log logger = LogFactory.getLog(FileUploadController.class);
	 
	String defaultFileUploadPath = EgovProperties.getProperty("Globals.defaultFileUploadPath");
	/**
	 * @Method Name: 메인페이지 호출
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 	
	@RequestMapping("/foFileUploadPage.do")
	public String foFileUploadPage(ModelMap model,Map<String, Object> commandMap , HttpServletRequest request) throws Exception {
		
		System.out.println("filePath:"+defaultFileUploadPath);
		return "fo/fileUpload/fileUpload";
	}
	
	/**
	 * @Method Name: 파일업로드
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 
	@RequestMapping(value = "/fileupload.do",method = RequestMethod.POST)
	public String upload(@RequestParam(value="uploadfile", required=false) MultipartFile uploadfile, HttpServletRequest request){
		logger.info("upload() POST 호출");
		logger.info("파일 ContentType:"+ uploadfile.getContentType());
		logger.info("파일 Name:"+ uploadfile.getName());
		logger.info("파일 오리지널파일이름:"+ uploadfile.getOriginalFilename());
		logger.info("파일 크기:"+ uploadfile.getSize());
		
		//등록자 id 가져오기
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		LoginAdminVO loginAdminVO = (LoginAdminVO)session.getAttribute("loginAdminVO");
		String sessionId = loginAdminVO.getAdminId();
		
		//확장자명
		String extensionNm = uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf("."));
		logger.info("확장자명:"+ extensionNm);
		
		//파일 이름 변경(난수)
	    /*
	    UUID uuid = UUID.randomUUID();
	    String saveName = uuid + "";
	     */
		// 파일 이름 변경 (시분초)
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMMddHHmmssSSS");
		Date time = new Date();
		String nowDate = sdf.format(time);
				
		logger.info("nowDate:"+ nowDate);
		String saveName = nowDate + extensionNm;
	    logger.info("saveName:"+ saveName);
		
	    saveFile(uploadfile, saveName);
	    
	    //db에 파일 정보 저장 
	    Boolean saveFileCheck = saveFileInfo(uploadfile, extensionNm, nowDate, sessionId);
	    
	    if(saveFileCheck){
	    	System.out.println("File Delete Success"); //성공
	    }else{
	    	System.out.println("File Delete Fail"); //실패
	    }
	    
	    
	    return "fo/fileUpload/fileUpload";
	}
	
	/**
	 * @Method Name: 파일저장
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 
	private String saveFile(MultipartFile file, String saveName){

	    // 저장할 File 객체를 생성(껍데기 파일)
	    File saveFile = new File(defaultFileUploadPath, saveName); // 저장할 폴더 이름, 저장할 파일 이름

	    try {
	        file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }

	    return saveName;
	} // end saveFile(
	
	
	/**
	 * @Method Name: 파일삭제
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 	
	@RequestMapping("/deleteFile.do")
	public String deleteFile(HttpServletRequest request) throws Exception {
		String filePath = "C:/eGovFrameDev-3.7.0-64bit/workspace/project/upload/9a7b0116-53ff-4ad5-af2d-0ef38b1262b4_db.jpg";
        boolean delYn = true;
        File file = new File(filePath);
 
        if(file.exists()) {
 
            delYn = file.delete();
 
            if(delYn){
                System.out.println("File Delete Success"); //성공
 
            }else{
 
                System.out.println("File Delete Fail"); //실패
            }
 
        }else{
            System.out.println("File Not Found"); //미존재
        }
        
        return "fo/fileUpload/fileUpload";
	}
	
	
	/**
	 * @Method Name: db에 파일 정보 저장
	 * @param model
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */ 
	private Boolean saveFileInfo(MultipartFile uploadfile, String extensionNm, String fileName, String sessionId){
		Boolean rslt = false;
		
		logger.info("upload() POST 호출");
		logger.info("파일 ContentType:"+ uploadfile.getContentType());
		logger.info("파일 Name:"+ uploadfile.getName());
		logger.info("파일 오리지널파일이름:"+ uploadfile.getOriginalFilename());
		logger.info("파일 크기:"+ uploadfile.getSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("fileId", 1);
		paramMap.put("fileSort", 1);
		paramMap.put("fileType", "1");
		paramMap.put("fileSrc", "/");
		paramMap.put("fileName", fileName);
		paramMap.put("fileExt", extensionNm);
		paramMap.put("fileSize", uploadfile.getSize());
		paramMap.put("orgFileName", uploadfile.getOriginalFilename());
		paramMap.put("sessionId", sessionId);
		
		int saveFileCheck = 0;
		
		try {
			fileUploadService.insertFile(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(saveFileCheck > 0){
			rslt = true;
		}
		
		return rslt;
		
	} // end saveFile(
}
