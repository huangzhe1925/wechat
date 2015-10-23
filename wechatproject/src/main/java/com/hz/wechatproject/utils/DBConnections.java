package com.hz.wechatproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Deprecated
@Component
public class DBConnections {
	
	private static Logger logger=Logger.getLogger(DBConnections.class);

	public Connection getDBConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(PropertiesUtil.getStringProperty(PropertiesUtil.DBSOURCE_SQLITE_URL));
		return conn;
	}
}
