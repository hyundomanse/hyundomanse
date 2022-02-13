package com.devwork.common.resolver;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * com.devwork.common.resolver 
 *    |_ CommandMapArgumentResolver.java
 * 
 * @comment: 클래스명 작성 시 명사형의 명시적인 명칭을 사용하며 메소드명은 동사+명사형으로 한다.
 * 			 메소드명의 길이는 가급적 20자를 넘지 않도록 한다.
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class CommandMapArgumentResolver implements WebArgumentResolver {

	/**
	 * Controller의 메소드 argument에 commandMap이라는 Map 객체가 있다면 
	 * HTTP request 객체에 있는 파라미터이름과 값을 commandMap에 담아 returng한다.
	 * 배열인 파라미터 값은 배열로 Map에 저장한다.
	 * 
	 * @param methodParameter - 메소드 파라미터의 타입,인덱스등의 정보 
	 * @param webRequest - web request 객체
	 * @return argument에 commandMap(java.util.Map)이 있으면 commandMap, 없으면<code>UNRESOLVED</code>.
	 * @exception Exception
	 */
	public Object resolveArgument(MethodParameter methodParameter, 	NativeWebRequest webRequest) throws Exception {
		
		Class<?> clazz = methodParameter.getParameterType();
		String paramName = methodParameter.getParameterName();
		
		if(clazz.equals(Map.class) && paramName.equals("commandMap")) {
			Map<String, Object> commandMap = new HashMap<String, Object>();
			HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
			Enumeration<?> enumeration = request.getParameterNames();
            
			while(enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				String[] values = request.getParameterValues(key);
				
				if(values != null) {
					commandMap.put(key, (values.length > 1) ? values : values[0] );
				}
			}
			return commandMap;
		}
		return UNRESOLVED;
	} 

}
