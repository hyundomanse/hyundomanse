package com.devwork.common.ibatis.com;

/**
 * com.devwork.common.ibatis.com 
 *    |_ SqlMapClientRefreshable.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public interface SqlMapClientRefreshable {
	void refresh() throws Exception;
	void setCheckInterval(int ms);
}
