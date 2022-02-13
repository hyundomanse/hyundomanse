package com.devwork.common;

/**
 * com.devwork.common 
 *    |_ LoginAdminVO.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class LoginAdminVO {
	
@SuppressWarnings("unused")
private static final long serialVersionUID = -8274004534207618049L;
	
	private String adminId;
	private String adminAuth;
	private String adminName;
	private String adminDept;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminAuth() {
		return adminAuth;
	}
	public void setAdminAuth(String adminAuth) {
		this.adminAuth = adminAuth;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminDept() {
		return adminDept;
	}
	public void setAdminDept(String adminDept) {
		this.adminDept = adminDept;
	}

}
