package com.devwork.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * com.devwork.common.exception 
 *    |_ ExcepHndlr.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class ExcepHndlr implements ExceptionHandler {

    protected Log log = LogFactory.getLog(this.getClass());

    public void occur(Exception ex, String packageName) {
		log.debug(" EgovServiceExceptionHandler run...............");
		try {
			log.debug(" EgovServiceExceptionHandler try ");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
