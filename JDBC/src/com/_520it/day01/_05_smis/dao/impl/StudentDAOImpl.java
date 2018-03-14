package com._520it.day01._05_smis.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com._520it.day01._05_smis.dao.IStudentDAO;
import com._520it.day01._05_smis.domain.Student;

public class StudentDAOImpl implements IStudentDAO{
	
	//InSERT INTO t_student (name,age) VALUES (?,?)
	public void save(Student student) {
		StringBuilder sql = new StringBuilder(80);
		sql.append("InSERT INTO t_student (name,age) VALUES(");
		sql.append("'").append(student.getName()).append("'");
		sql.append(",").append(student.getAge());
		sql.append(")");
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
			st = con.createStatement();
			st.executeUpdate(sql.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			try {
				if(st != null){
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(con != null){
						con.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					
				}
			}
		}
	}

	public void delete(Long id) {
		String sql = "DELETE FROM t_student WHERE id = " + id;
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			try {
				if(st != null){
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(con != null){
						con.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					
				}
			}
		}
	}

	public void update(Long id, Student student) {
		StringBuilder sql = new StringBuilder(80);
		sql.append("UPDATE t_student SET name = ");
		sql.append("'").append(student.getName()).append("'");
		sql.append(",").append("age = ").append(student.getAge());
		sql.append(" WHERE id = ").append(id);
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
			st = con.createStatement();
			st.executeUpdate(sql.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			try {
				if(st != null){
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(con != null){
						con.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					
				}
			}
		}
	}

	public Student get(Long id) {
		String sql = "SELECT * FROM t_student WHERE id = " + id;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
			st = con.createStatement();
			rs = st.executeQuery(sql);
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
			try {
				if(rs != null){
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(st != null){
						st.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(con != null){
							con.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						
					}
				}
			}
		}
		return null;
	}

	public List<Student> list() {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM t_student";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","admin");
			st = con.createStatement();
			rs = st.executeQuery(sql);
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
			try {
				if(rs != null){
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(st != null){
						st.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(con != null){
							con.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						
					}
				}
			}
		}
		return list;
	}

}
