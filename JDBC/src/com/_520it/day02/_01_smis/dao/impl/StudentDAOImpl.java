package com._520it.day02._01_smis.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com._520it.day01._05_smis.dao.IStudentDAO;
import com._520it.day01._05_smis.domain.Student;
import com._520it.util.JdbcUtil;

public class StudentDAOImpl implements IStudentDAO{
	//InSERT INTO t_student (name,age) VALUES (?,?)
	public void save(Student student) {
		String sql = "INSERT INTO t_student (name,age) VALUES(?,?)";
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, student.getName());
			pst.setInt(2, student.getAge());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtil.close(con,pst,null);
		}
	}

	public void delete(Long id) {
		String sql = "DELETE FROM t_student WHERE id = " + id;
		Connection con = null;
		Statement st = null;
		try {
			con = JdbcUtil.getConnection();
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtil.close(con,st,null);
		}
	}

	public void update(Long id, Student student) {
		String sql = "UPDATE t_student SET name = ?,age = ? WHERE id = ?";
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, student.getName());
			pst.setInt(2, student.getAge());
			pst.setLong(3,id);
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtil.close(con,pst,null);
		}
	}

	public Student get(Long id) {
		String sql = "SELECT * FROM t_student WHERE id = ?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setLong(1,id);
			rs = pst.executeQuery();
			//处理结果集：把结果集中的每一行封装成一个对象
			if(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getLong("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				return stu;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtil.close(con,pst,rs);
		}
		return null;
	}

	public List<Student> list() {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM t_student";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			//处理结果集：把结果集中的每一行封装成一个对象
			while(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getLong("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtil.close(con,pst,rs);
		}
		return list;
	}

}
