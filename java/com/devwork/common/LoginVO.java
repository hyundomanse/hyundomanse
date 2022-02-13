package com.devwork.common;

/**
 * com.devwork.common 
 *    |_ LoginVO.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class LoginVO {

	private static final long serialVersionUID = 1376985365420931124L;
	
	private String memberId;
	private String memberAuth;
	private String snsType;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
