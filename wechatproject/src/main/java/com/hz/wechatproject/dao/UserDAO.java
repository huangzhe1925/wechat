package com.hz.wechatproject.dao;

import java.util.List;

import com.hz.wechatproject.db.pojo.User;

public interface UserDAO {
	 
    public List<User> getAllUser();
    
    public User getUserByName(String name);
     
     
}