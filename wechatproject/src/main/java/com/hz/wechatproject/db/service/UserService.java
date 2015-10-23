package com.hz.wechatproject.db.service;

import java.util.List;

import com.hz.wechatproject.db.pojo.User;

public interface UserService {
	 
	public List<User> getAllUser();
	
	public User getUserByName(String name);
	
}