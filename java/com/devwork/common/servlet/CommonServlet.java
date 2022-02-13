package com.devwork.common.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.devwork.common.util.SessionCookieUtil;
import com.devwork.common.util.StringUtil;

/**
 * com.devwork.common.servlet 
 *    |_ CommonServlet.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class CommonServlet extends HttpServlet { 

	private static final long serialVersionUID = 1L;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String globalServletPath = request.getServletPath();
		String sesExfired = "N";
		try {
			sesExfired = StringUtil.isNullToString(SessionCookieUtil.getSessionAttribute(request, "loginExpired"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("SESSION_EXFIRED_EXCEPTIOON");
			e.printStackTrace();
		}  
		log.debug("sesExfired="+sesExfired);
		request.setAttribute("globalServletPath", globalServletPath);
		RequestDispatcher dispatcher = request.getRequestDispatcher( "/GlobalIndex.do?sesExf="+sesExfired);
		dispatcher.forward(request, response);
		 return;  
     } 
	 
	 /**
	 * Post 요청시 doGet 메소드 호출.
	 * @param req HttpServletRequest 요청 객체
	 * @param res HttpServletResponse 응답 객체
	 */
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 doGet(request ,response);
     } 

}
