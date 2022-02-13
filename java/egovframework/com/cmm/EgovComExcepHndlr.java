package egovframework.com.cmm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

/**
 * egovframework.com.cmm 
 *    |_ EgovComExcepHndlr.java
 * 
 * @comment: 공통서비스의 exception 처리 클래스
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class EgovComExcepHndlr implements ExceptionHandler {

    protected Log log = LogFactory.getLog(this.getClass());

    /*
    @Resource(name = "otherSSLMailSender")
    private SimpleSSLMail mailSender;
     */
    /**
     * 발생된 Exception을 처리한다.
     */
    public void occur(Exception ex, String packageName) {
	//log.debug(" EgovServiceExceptionHandler run...............");
	try {
	    //mailSender. send(ex, packageName);
	    //log.debug(" sending a alert mail  is completed ");
	    log.error(packageName, ex);
	} catch (Exception e) {
	    //e.printStackTrace();
		log.fatal(packageName, ex);// 2011.10.10 보안점검 후속조치
	    //throw new RuntimeException(ex);	
	}
    }
}
