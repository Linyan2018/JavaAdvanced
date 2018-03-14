package com._520it.day02._05_blob;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com._520it.util.JdbcUtil;

public class BlobTest {
	
	//把二进制数据存储到表中
	@Test
	public void testWrite() throws Exception {
		String sql = "INSERT INTO t_image (content) VALUES(?)";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBlob(1, new FileInputStream("E:/JavaLearning/JavaSuperLearning/JDBC/fig.png"));
		ps.executeUpdate();
		JdbcUtil.close(con,ps,null);
	}
	//把表中的二进制读取出来
	@Test
	public void testRead() throws Exception {
		String sql = "SELECT * FROM t_image WHERE id = ?";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, 1);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			Blob blob = rs.getBlob("content");
			InputStream in = blob.getBinaryStream();
			Files.copy(in, Paths.get("E:/123.png"));
		}
		JdbcUtil.close(con,ps,rs);
	}
}
