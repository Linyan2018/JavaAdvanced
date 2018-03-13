package com._520it.day01._03_dql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

//查询操作
public class DQLTest {
	
	//需求1：查询t_student表中有多少条数据
	@Test
	public void testCount() throws Exception {
		String sql = "SELECT COUNT(id) count FROM t_student";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			long totalCount = rs.getLong("count");
			System.out.println(totalCount);
		}
		rs.close();
		st.close();
		con.close();
	}
	
	//需求2：查询id为2 的学生信息
	@Test
	public void testQuerySingle() throws Exception {
		String sql = "SELECT * FROM t_student WHERE id = 2";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "admin");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println(id + ", " + name + ", " + age);
		}
		rs.close();
		st.close();
		con.close();
	}
	
	//需求3：查询所有的学生信息
	@Test
	public void testQueryAll() throws Exception {
		String sql = "SELECT * FROM t_student";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "admin");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println(id + ", " + name + ", " + age);
		}
		rs.close();
		st.close();
		con.close();
	}
	
}
