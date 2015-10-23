package com.hz.wechatproject.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.hz.wechatproject.dao.UserDAO;
import com.hz.wechatproject.db.pojo.User;

public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO{

	private static Logger logger = Logger.getLogger(UserDAOImpl.class); 
	
	@Override
	public List<User> getAllUser() {
		return 	this.getSqlSession().selectList("com.hz.wechatproject.pojo.User.getAllUser");
	}

	@Override
	public User getUserByName(String name) {
		return 	this.getSqlSession().selectOne("com.hz.wechatproject.pojo.User.getUserByName",name);
	}

}
