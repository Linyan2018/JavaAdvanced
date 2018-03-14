package com._520it.day02._03_tx;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com._520it.util.JdbcUtil;

//事务管理操作
public class TransactionTest {
	@Test
	public void test1() {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1):检查张无忌的账户余额是否大于等于1000.
			String sql = "SELECT * FROM account WHERE  name = ? AND balance >= ?";
			ps = con.prepareStatement(sql);
			ps.setString(1,"张无忌");
			ps.setInt(2, 1000);
			rs = ps.executeQuery();
			if(!rs.next()){
				throw new RuntimeException("账户余额不足");
			}
			//设置取消事务的自动提交
			con.setAutoCommit(false);
			//2):从张无忌的账户余额中减少1000.
			sql = "UPDATE account SET balance = balance - ? WHERE name = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1000);
			ps.setString(2, "张无忌");
			ps.executeUpdate();
			
			//=====================================================
			//异常模拟程序中断
			int a = 1/0;//模拟停电
			//=====================================================
			
			//3):再在赵敏的账户余额中增加1000.
			sql = "UPDATE account SET balance = balance + ? WHERE name = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1000);
			ps.setString(2, "赵敏");
			ps.executeUpdate();
			
			//提交事务
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				//回滚事务，不回滚事务，别的事务就拿不到事务锁
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			JdbcUtil.close(con, ps, rs);	
		}
	}
}
