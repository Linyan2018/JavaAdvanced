package com._520it.day02._06_returnpk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com._520it.util.JdbcUtil;

public class ReturnGeneratePKTest {

	@Test
	public void testStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES('小倩',19)";
		Connection con = JdbcUtil.getConnection();
		Statement st = con.createStatement();
		st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);//设置需要返回自动生成的主键
		ResultSet rs = st.getGeneratedKeys();//获取自动生成的主键
		if(rs.next()){
			long pk = rs.getLong(1);
			System.out.println(pk);
		}
		JdbcUtil.close(con, st, rs);
	}
	
	@Test
	public void testPreparedStatement() throws Exception {
		String sql = "INSERT INTO t_student (name,age) VALUES(?,?)";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, "宁采臣");
		ps.setInt(2, 23);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();//获取自动生成的主键
		if(rs.next()){
			long pk = rs.getLong(1);
			System.out.println(pk);
		}
		JdbcUtil.close(con, ps, rs);
	}
}
