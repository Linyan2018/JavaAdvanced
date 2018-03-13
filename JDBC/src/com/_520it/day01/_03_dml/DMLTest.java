package com._520it.day01._03_dml;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

//针对t_student表进行增删改操作
public class DMLTest {
	//插入乔峰33
	@Test
	public void testInsert() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES ('乔峰',33)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
		Statement st = con.createStatement();
		int ret = st.executeUpdate(sql);
		System.out.println(ret);
		st.close();
		con.close();
	}
	
	//把id为2的乔峰改为萧峰，34
	@Test
	public void testUpdate() throws Exception {
		String sql = "UPDATE t_student SET name = '萧峰',age = 34 WHERE id = 2";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
		Statement st = con.createStatement();
		int ret = st.executeUpdate(sql);
		System.out.println(ret);
		st.close();
		con.close();
	}
	
	//删除阿朱
	@Test
	public void testDelete() throws Exception {
		String sql = "DELETE FROM t_student WHERE name = '阿朱'";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
		Statement st = con.createStatement();
		int ret = st.executeUpdate(sql);
		System.out.println(ret);
		st.close();
		con.close();
	}
}
