package com.hz.wechatproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class DBConnections {
	
	private static Logger logger=Logger.getLogger(DBConnections.class);

	static{
		logger.debug("org.sqlite.JDBC");
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
	}
	
	public Connection getDBConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(PropertiesUtil.getProperty(PropertiesUtil.DBSOURCE_SQLITE_URL));
		return conn;
	}
}
