package com.hz.wechatproject.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBDao {

	private static Logger logger = Logger.getLogger(DBDao.class);

	@Autowired
	private DBConnections dbconn;

	private Connection conn;

	public ResultSet executeQuery(String sql) {

		getConnectionReady();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return rs;
	}
	
	private boolean getConnectionReady() {
		if (dbconn == null) {
			return false;
		}
		if (null == conn) {
			try {
				conn = dbconn.getDBConnection();
			} catch (SQLException e) {
				logger.error(e);
				return false;
			}
		}

		return true;
	}
}
