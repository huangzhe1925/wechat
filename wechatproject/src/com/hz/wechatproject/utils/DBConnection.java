package com.hz.wechatproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class DBConnection {
	
	private static Logger logger=Logger.getLogger(DBConnection.class);

	static{
		logger.debug("org.sqlite.JDBC");
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}

		// 建立一个数据库名zieckey.db的连接，如果不存在就在当前目录下创建之
	}
	
	public static Connection getDBConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(PropertiesUtil.getProperty(PropertiesUtil.DBSOURCE_SQLITE_URL));
		return conn;
	}
}
