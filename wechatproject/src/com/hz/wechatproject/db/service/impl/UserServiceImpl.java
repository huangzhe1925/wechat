package com.hz.wechatproject.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.wechatproject.dao.UserDAO;
import com.hz.wechatproject.db.pojo.User;
import com.hz.wechatproject.db.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDAO userDAO;

	@Override
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}

	@Override
	public User getUserByName(String name) {
		return userDAO.getUserByName(name);
	}
 
}