package com.hz.wechatproject.dao;

import java.util.List;

import com.hz.wechatproject.pojo.User;

public interface UserDAO {
	 
    /**
     * 查询用户
     * @return
     */
    public List<User> getAllUser();
     
     
}