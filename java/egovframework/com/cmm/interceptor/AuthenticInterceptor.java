package egovframework.com.cmm.interceptor;


import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.devwork.common.LoginAdminVO;
import com.devwork.common.LoginVO;
import com.devwork.common.util.SessionCookieUtil;

/**
 * egovframework.com.cmm.interceptor 
 *    |_ AuthenticInterceptor.java
 * 
 * @comment: 세션에 계정정보(LoginAdmVO)가 있는지 여부로 인증 여부를 체크한다.
 *           계정정보(LoginAdmVO)가 없다면, 로그인 페이지로 이동한다.
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	private Set<String> permittedURL;
	
	public void setPermittedURL(Set<String> permittedURL) {
		this.permittedURL = permittedURL;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {	
		
		String requestURI = request.getRequestURI(); //요청 URI
		boolean isPermittedURL = false; 
		LoginAdminVO admin = (LoginAdminVO) SessionCookieUtil.getSessionAttribute(request, "loginAdminVO");
		LoginVO user = (LoginVO) SessionCookieUtil.getSessionAttribute(request, "loginVO");
		String prevPage = request.getHeader("REFERER");
		System.out.println("prevPage="+prevPage+"]");
		
		for(Iterator<String> it = this.permittedURL.iterator(); it.hasNext();){
			String urlPattern = request.getContextPath() + (String) it.next();

			if(Pattern.matches(urlPattern, requestURI)){// 정규표현식을 이용해서 요청 URI가 허용된 URL에 맞는지 점검함.
				isPermittedURL = true;
			}
		}
		
		if(!isPermittedURL){
			HttpServletRequest httpRequest = (HttpServletRequest)request;
    		HttpSession session = httpRequest.getSession();
    		
    		/* BO 페이지 접근 시 체크 */
    		if (admin == null && requestURI.indexOf("/bo/") > -1) {
        		if(admin == null){
        			if (session.getAttribute("adminId") == null){
        				session.setAttribute("loginExpired", "Y");
        				ModelAndView modelAndView = new ModelAndView("redirect:/boMain.do");
        				throw new ModelAndViewDefiningException(modelAndView);
        			}    			
        		}    			
    		}
    		
    		/* FO 페이지 접근 시 체크 */
    		

			return true;			
			
		}else{
			return true;
		}

	}

}
