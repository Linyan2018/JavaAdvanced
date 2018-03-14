package com._520it.day02._02_preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import com._520it.util.JdbcUtil;

public class PreparedStatementTest {
	
	//插入数据：张无忌-25
	@Test
	public void testSaveByStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES ('张无忌',25)";
		Connection con = JdbcUtil.getConnection();
		Statement st = con.createStatement();
		st.executeUpdate(sql);
		JdbcUtil.close(con, st, null);
	}
	
	@Test
	public void testSaveByPreparedStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES (?,?)";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, "周芷若");
		pst.setInt(2, 23);
		pst.executeUpdate();
		JdbcUtil.close(con, pst, null);
	}
	
	
}
