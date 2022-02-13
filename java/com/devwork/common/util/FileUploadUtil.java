package com.devwork.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.devwork.common.map.CommonMap;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovFormBasedFileUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileVo;

/**
 * com.devwork.common.util 
 *    |_ FileUploadUtil.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@SuppressWarnings("unused")
public class FileUploadUtil extends EgovFormBasedFileUtil {
	
	protected static final Log log = LogFactory.getLog(FileUploadUtil.class);

    /**
     * 파일을 Upload 처리한다.
     *
     * @param request
     * @param where
     * @param maxFileSize
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public static List<EgovFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize) throws Exception {
    	List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();
    	MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
    	Iterator fileIter = mptRequest.getFileNames();

    	while (fileIter.hasNext()) {
    		MultipartFile mFile = mptRequest.getFile((String)fileIter.next());
    		EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
    		String tmp = mFile.getOriginalFilename();

            if (tmp.lastIndexOf("\\") >= 0) {
            	tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
            }

            vo.setFileName(tmp);
            vo.setParameterName(mFile.getName());	// 파라미터 name 추가(snkim)
            vo.setContentType(mFile.getContentType());
            vo.setServerSubPath(getTodayString());
            vo.setPhysicalName(getPhysicalFileName());
            vo.setSize(mFile.getSize());
            String randomFileNm = "";
            
	    	for (int i = 1; i <= 8; i++) {
	    		// 영자
	    		if (i % 3 != 0) {
	    			randomFileNm += StringUtil.getRandomStr('a', 'z');
	    		// 숫자
	    		} else {
	    			randomFileNm += StringUtil.getRandomNum(0, 9);
	    		}
	    	}
	    	vo.setFileName(randomFileNm+vo.getFileName());
	    	
            if (mFile.getSize() > 0) {
            	
            	if(where.equals(EgovProperties.getProperty("Globals.eventImageFilePath"))){	//이벤트이미지일경우
                	// set file name
                	if(mFile.getName().equals("VC_bigBannerpath")){
                		saveFile(mFile.getInputStream(), new File(WebUtil.filePathBlackList(where+SEPERATOR+"bigBanner_"+vo.getFileName())));
                	}else if(mFile.getName().equals("VC_smallBannerpath")){
                		saveFile(mFile.getInputStream(), new File(WebUtil.filePathBlackList(where+SEPERATOR+"smallBanner_"+vo.getFileName())));
                	}else if(mFile.getName().equals("VC_contentImagepath")){
                		saveFile(mFile.getInputStream(), new File(WebUtil.filePathBlackList(where+SEPERATOR+"contentImage_"+vo.getFileName())));
                	}else{ }
                }else{
                	saveFile(mFile.getInputStream(), new File(WebUtil.filePathBlackList(where+SEPERATOR+vo.getFileName())));
                }
            	list.add(vo);
            	fileTransfer(where,vo.getFileName());
            }
    	}
    	return list;
    }
    
    
    @SuppressWarnings("rawtypes")
	public static List uploadImgWithThumb(HttpServletRequest request, String where, String thumbWhere , long maxFileSize) throws Exception {
    	List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();
        MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
    	Iterator fileIter = mptRequest.getFileNames();

    	while (fileIter.hasNext()) {
    	    MultipartFile mFile = mptRequest.getFile((String)fileIter.next());
    	    EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
    	    String tmp = mFile.getOriginalFilename();
    	    log.debug("TMP="+tmp);
            if (tmp.lastIndexOf("\\") >= 0) {
            	tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
            }
            log.debug("TMP____=["+tmp+"]");
            
            int index = tmp.lastIndexOf(".");
    	    String fileExt = tmp.substring(index + 1);
            vo.setParameterName(mFile.getName());
            String imaParamNm = vo.getParameterName();
            vo.setContentType(mFile.getContentType());
            vo.setServerSubPath(getTodayString());
            vo.setPhysicalName(getPhysicalFileName());
            vo.setSize(mFile.getSize());
            String randomFileNm = "";
	    	for (int i = 1; i <= 8; i++) {
	    		// 영자
	    		if (i % 3 != 0) {
	    			randomFileNm += StringUtil.getRandomStr('a', 'z');
	    		// 숫자
	    		} else {
	    			randomFileNm += StringUtil.getRandomNum(0, 9);
	    		}
	    	}
	    	vo.setFileName(randomFileNm+"."+fileExt);
	    	log.debug("randomFileNm_[[["+randomFileNm+fileExt);
	    	log.debug("imaParamNm=["+imaParamNm+"]");
	    	
	    	if (!imaParamNm.equals("VC_prdContentsImage")  && !imaParamNm.equals("VC_prdCnContentsImage") ) {
	    		log.debug("file_create");
	            if (mFile.getSize() > 0) {
	            	saveFile(mFile.getInputStream(), new File(WebUtil.filePathBlackList(where+SEPERATOR+vo.getFileName())));
	            	log.debug("file_create1");
	            	fileTransfer(where,vo.getFileName());
	            	log.debug("file_create2=="+vo.getFileName());
	            	//원본이미지파일의 경로+파일명
	            	File originFileName = new File(where+SEPERATOR+vo.getFileName());
	            	//생성할 썸네일파일의 경로+썸네일파일명
	                File thumbFileName = new File(WebUtil.filePathBlackList(thumbWhere+SEPERATOR+vo.getFileName()));
	                try {
		                if (! originFileName.getParentFile().exists()) {
		                	originFileName.getParentFile().mkdirs();
		                }
		                if (! thumbFileName.getParentFile().exists()) {
		                	thumbFileName.getParentFile().mkdirs();
		                }
	                } catch( Exception e) {
	                	log.debug("PARENT_FILE_CREATE_FAIL!!!");
	                }
	                saveThumbFile(mFile.getInputStream(),new File(WebUtil.filePathBlackList(where+SEPERATOR+vo.getFileName())) ,new File(WebUtil.filePathBlackList(thumbWhere+SEPERATOR+"small_"+vo.getFileName())) , 150 , 150 );
	                saveThumbFile(mFile.getInputStream(),new File(WebUtil.filePathBlackList(where+SEPERATOR+vo.getFileName())) ,new File(WebUtil.filePathBlackList(thumbWhere+SEPERATOR+"basic_"+vo.getFileName())) , 300 , 300);
	                fileTransfer(thumbWhere,"small_"+vo.getFileName());	
	                fileTransfer(thumbWhere,"basic_"+vo.getFileName());
	            	list.add(vo);
	            }
	    	}
    	}
        return list;
    }
    
    /**
     * 서버의 파일을 삭제 처리한다.
     * @param filePath
     * @param filePyscName
     * @throws Exception
     */
     public static void deleteFile(String where, String thumbWhere ,String filePyscName) throws Exception {
         if (where != null && !"".equals(thumbWhere) && filePyscName != null && !"".equals(filePyscName)){
         	String deleteFileName 		= where+SEPERATOR+filePyscName;
         	String deleteBasicThumbFileName = thumbWhere+SEPERATOR+"basic_"+filePyscName;
         	String deleteSmallThumbFileName = thumbWhere+SEPERATOR+"small_"+filePyscName;
         	File file = new File(deleteFileName);
         	if (file.exists() && file.isFile()) {
		    	file.delete();
		    }
         	
         	File fileBasicThumb = new File(deleteBasicThumbFileName);
         	if (fileBasicThumb.exists() && fileBasicThumb.isFile()) {
         		fileBasicThumb.delete();
         	}
         	File fileSmallThumb = new File(deleteSmallThumbFileName);
         	if (fileSmallThumb.exists() && fileSmallThumb.isFile()) {
         		fileSmallThumb.delete();
         	}
         }
     }
     
     public static void fileTransfer(String sourceWhere, String filePyscName) throws Exception {
	    try{
	    	InetAddress myAddress = InetAddress.getLocalHost();
	    	String myIp = myAddress.getHostName();
	    	log.debug("DUTY--IP=["+myIp+"]");
	    	String destIp = "";
	    	boolean realServer = false;
	 		//현재는 test로 넣어보고 나중에 전송할 곳으로 변경   
	 		FTPClient ftp = new FTPClient();   //클라이언트 생성
	 		if (myIp.equals("aa-dfs-was01")) {
	 			destIp = "165.141.171.75";
	 			realServer = true;
	 		} else if (myIp.equals("aa-dfs-was02")){
	 			destIp = "165.141.171.85";
	 			realServer = true;
	 		} else {
	 			log.debug("DEV_SERVER!!!!");
	 			realServer = false;
	 		}
	 		
	 		if (realServer) {
	 			try{
			 		ftp.connect(destIp);    //ftp 서버의 아이피
			 		ftp.login("dfwas", "@qhRdfw1");                 
			 		log.debug("sourceWhere="+sourceWhere+"====fileName="+filePyscName);
			 		File f = new File(sourceWhere+filePyscName); //전송할 파일 객체 생성(나중에 경로 변경)
			 		// 	디렉토리 생성
			 		if (! f.getParentFile().exists()) {
			 		    f.getParentFile().mkdirs();
			 		}
			 		
			 		String fol = sourceWhere; //"/dutyFiles/upload/images/";      
			 		FileInputStream fi = new FileInputStream(f);      //파일스트림을 만들어서 넘겨줘야 함.
			 		boolean result = ftp.storeFile(fol+f.getName(), fi); //원격지 저장폴더 및 파일명과 스트림 넘김.
			 		log.debug("result================="+result);
	 			} catch (Exception e) {
	 				e.printStackTrace();
	 			} finally {
	 				ftp.logout();
	 				ftp.disconnect();
	 			}
	 		}
		} catch(IOException ex) { 
			log.debug("error2");
		    ex.printStackTrace();
		}
     }
     
     /**
      * 마일리지 서버 파일 업로드
      * 실패 코드 
      * 0001 - ftp 접속 실패
      * 0002 - 마일리지 적립 대상자 파일 존재 하지 않음 ( dutyFree Server)
      * 0003 - file upload 실패
      * 0004 - file upload 후 file 존재여부 체크
      */
     public static String secureFileTransfer(){
    	 String failCode = "";
    	 String toDay = DateUtil.getToday();
    	 FtpUtil sftp = new FtpUtil();
    	 
    	 try {
	    	 sftp.setServer("165.141.112.170");
	    	 sftp.setUser("userId", "userPw");
	    	 boolean rtn = sftp.login();
		 	 if (!rtn) {
		 		failCode = "0001";	
		 	 }
    		
    		 if (!failCode.equals("")) {
    			return failCode;
    		 }
	    	
	    	 File file = new File("/dfhome/dutyFiles/mileage/test_"+toDay+".in");
	    	
	    	 if (!file.exists()) {
	    		failCode = "0002";
	    		return failCode;
	    	 }
	    	 
	    	 if(sftp.uploadFile("/test/mileageHome", file)){
	    		 log.debug("OK||FILE_NAME:"+file.getName());
	    	 }else{
	    		 log.debug("NOK||FILE_NAME:"+file.getName());
	    	     failCode = "0003";
	    	     return failCode;
	    	 }
	    	
	    	 String fileName	= "/dfhome/dutyFiles/mileage/test_"+toDay+".in"; 
	    	  
	    	 if(sftp.fileCheck("/test/mileageHome", fileName)){
	    		 log.debug("OK");
	    	 }else{
	    		 log.debug("NOK");
	    		 failCode = "0004";
	    		 return failCode;
	    	 }
	    	  sftp.logout();
	    	  
    	 } catch (Exception e) {
    		 log.debug("SECURE_FILE_UPLOAD_FAIL=["+e.getMessage().toLowerCase().toString()+"]");
    		 return failCode;
    	 } finally {
    		  sftp.logout();
    	 }
    	 return failCode;
    }

}
