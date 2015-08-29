package com.hz.wechatproject.pojo;

public class User {
	Integer userId;
	String userName;
	String userPasswd;

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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPasswd=" + userPasswd + "]";
	}
	
	
}
