package com._520it.day01._02_ddl_catch;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

//使用JDBC创建一张表
public class DDLTest {
	@Test
	public void testName() throws Exception {
		String sql = "CREATE TABLE  t_student(id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20),age INT)";
		//1):加载注册驱动.
		Class.forName("com.mysql.jdbc.Driver");
		//2):获取连接对象.
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
		//3):创建/获取语句对象
		Statement stmt = con.createStatement();
		//4):执行SQL语句
		stmt.executeUpdate(sql);
		//5):释放资源
		stmt.close();
		con.close();
	}
	
	//处理异常正常关闭资源
		@Test
		public void testHandleException() {
			String sql = "CREATE TABLE  t_student(id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20),age INT)";
			Connection con = null;
			Statement stmt = null;
			try{
				//1):加载注册驱动.
				Class.forName("com.mysql.jdbc.Driver");
				//2):获取连接对象.
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
				//3):创建/获取语句对象
				stmt = con.createStatement();
				//4):执行SQL语句
				stmt.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				//5):释放资源
				try {
					if(stmt != null){
						stmt.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						if(con != null){
							con.close();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	//处理异常正常关闭资源:Java7自动关闭资源
	@Test
	public void testAutoHandleByJava7() {
		String sql = "CREATE TABLE  t_student(id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20),age INT)";
		try{
			//1):加载注册驱动.
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try(
			//声明打开资源
			//2):获取连接对象.
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
			//3):创建/获取语句对象
			Statement stmt = con.createStatement();)
		{
			//4):执行SQL语句
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
