package egovframework.com.utl.wed.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devwork.common.util.StringUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovFormBasedFileUtil;

/**
 * egovframework.com.utl.wed.web 
 *    |_ EgovWebEditorImageController.java
 * 
 * @comment: 웹에디터 이미지 upload 처리
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@SuppressWarnings("unused")
@Controller
public class EgovWebEditorImageController {

    /** 첨부파일 위치 지정 */
    private final String uploadDir = EgovProperties.getProperty("Globals.imageStorePath");

    /** 첨부 최대 파일 크기 지정 */
    private final long maxFileSize = 1024 * 1024 * 100;   //업로드 최대 사이즈 설정 (100M)

    public static final String SEPERATOR = File.separator;
    

    /**
     * 이미지 view를 제공한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/utl/web/imageSrc.do",method=RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String section 	= request.getParameter("section");
    	String subPath 	= request.getParameter("path");
		String physical = request.getParameter("physical");
		String mimeType = request.getParameter("contentType");
		//String globalLang = StringUtil.isNullToString(request.getParameter("globalLang"));
			
		if (subPath.equals("editor")) {
			//subPath = subPath+SEPERATOR+globalLang;
			subPath = subPath+SEPERATOR;
		}
		
		EgovFormBasedFileUtil.viewFile(response, uploadDir, section ,subPath, physical, mimeType);
    }
}
