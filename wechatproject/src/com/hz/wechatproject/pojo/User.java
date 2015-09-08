package com.hz.wechatproject.pojo;

public class User {
	Integer userId;
	String userName;
	String userPasswd;
	Integer userAccess;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public Integer getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(Integer userAccess) {
		this.userAccess = userAccess;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPasswd=" + userPasswd + ", userAccess=" + userAccess
				+ "]";
	}
	
	
}
