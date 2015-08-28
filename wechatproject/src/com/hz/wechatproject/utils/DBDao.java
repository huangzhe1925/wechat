package com.hz.wechatproject.utils;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBDao {

	@Autowired
	private static DBConnection dbconn;
	
	public static ResultSet executeQuery(String sql){
		if(dbconn==null){
			return null;
		}
		
		return null;
	}
}
