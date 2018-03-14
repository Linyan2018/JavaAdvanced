package com._520it.day02._04_batch;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com._520it.util.JdbcUtil;

//需求:同时向t_student表,插入5000条数据.
public class BatchTest {
	
	//使用Statement，没有批处理
	//43774ms
	@Test
	public void testSaveByStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES('A',11)";
		Connection con = JdbcUtil.getConnection();
		Statement st = con.createStatement();
		
		long begin = System.currentTimeMillis();
		for (int i = 1; i <= 600; i++) {
			sql = "INSERT INTO t_student (name,age) VALUES('A',"+ i + ")";
			st.executeUpdate(sql);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(con, st, null);
	}
	
	//使用Statement，有批处理
	//43783ms
	//39502ms
	@Test
	public void testBatchSaveByStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES('A',11)";
		Connection con = JdbcUtil.getConnection();
		Statement st = con.createStatement();
		
		long begin = System.currentTimeMillis();
		for (int i = 1; i <= 600; i++) {
			sql = "INSERT INTO t_student (name,age) VALUES('A',"+ i + ")";
			st.addBatch(sql);
			if(i%200 == 0){
				st.executeBatch();
				st.clearBatch();//清除批处理
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(con, st, null);
	}
	//使用PreparedStatementStatement，没有批处理
	//41401ms
	//38923ms
	@Test
	public void testSaveByPreparedStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES('A',?)";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		long begin = System.currentTimeMillis();
		for (int i = 1; i <= 600; i++) {
			ps.setInt(1, i);
			ps.executeUpdate();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(con, ps, null);
	}
	
	//使用PreparedStatementStatement，有批处理
	//37577ms
	//328ms
	@Test
	public void testBatchSaveByPreparedStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES('A',?)";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		long begin = System.currentTimeMillis();
		for (int i = 1; i <= 600; i++) {
			ps.setInt(1, i);
			ps.addBatch();
			if(i % 200 == 0){
				ps.executeBatch();
				ps.clearBatch();
				ps.clearParameters();//清除占位符参数
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(con, ps, null);
	}
}
