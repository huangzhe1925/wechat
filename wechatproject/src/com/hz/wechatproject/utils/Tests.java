package com.hz.wechatproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 这是个非常简单的SQLite的Java程序, 程序中创建数据库、创建表、然后插入数据， 最后读出数据显示出来
 * 
 * @author zieckey (http://zieckey.cublog.cn)
 */
public class Tests {
	public static void main(String[] args) {
		try {
			// 连接SQLite的JDBC

			Class.forName("org.sqlite.JDBC");

			// 建立一个数据库名zieckey.db的连接，如果不存在就在当前目录下创建之

			Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\sqlite\\test.db");

			Statement stat = conn.createStatement();

//			stat.executeUpdate("create table tbl1(name varchar(20), salary int);");// 创建一个表，两列
//
//			stat.executeUpdate("insert into tbl1 values('ZhangSan',8000);"); // 插入数据
//
//			stat.executeUpdate("insert into tbl1 values('LiSi',7800);");
//			stat.executeUpdate("insert into tbl1 values('WangWu',5800);");
//			stat.executeUpdate("insert into tbl1 values('ZhaoLiu',9100);");

			ResultSet rs = stat.executeQuery("select * from USER;"); // 查询数据

			while (rs.next()) { // 将查询到的数据打印出来

				System.out.print("USER_ID = " + rs.getString("USER_ID") + " "); // 列属性一

				System.out.print("USER_NAME = " + rs.getString("USER_NAME")+" "); // 列属性二
				System.out.println("USER_PASSWD = " + rs.getString("USER_PASSWD")); // 列属性二

			}
			rs.close();
			conn.close(); // 结束数据库的连接

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}