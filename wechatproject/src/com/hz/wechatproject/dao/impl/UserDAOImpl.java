package com.hz.wechatproject.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hz.wechatproject.dao.UserDAO;
import com.hz.wechatproject.pojo.User;


public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO{

	@Override
	public List<User> getAllUser() {
		System.out.println(this.getSqlSession());
		return 	this.getSqlSession().selectList("com.hz.wechatproject.pojo.User.getAllUser");
	}

}
