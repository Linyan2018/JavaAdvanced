package com._520it.day01._01_connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class ConnectionTest {
	
	@Test
	public void test1() throws Exception{
		//手动加载注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接对象
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
		Thread.sleep(3000);
	}
	
	@Test
	public void test2() throws Exception{
		//从JDK1.6开始自动加载注册驱动，不建议
		//获取连接对象
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
		Thread.sleep(3000);
	}
}
