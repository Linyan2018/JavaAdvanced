package com._520it.day02._01_smis.test;

import java.util.List;

import org.junit.Test;

import com._520it.day01._05_smis.dao.IStudentDAO;
import com._520it.day01._05_smis.dao.impl.StudentDAOImpl;
import com._520it.day01._05_smis.domain.Student;

public class StudentDAOTest {
	IStudentDAO studentDAO = new StudentDAOImpl();
	
	@Test
	public void testSave() {
		Student stu= new Student();
		stu.setName("虚竹");
		stu.setAge(33);
		studentDAO.save(stu);
	}

	@Test
	public void testDelete() {
		studentDAO.delete(8L);
	}

	@Test
	public void testUpdate() {
		Student stu= new Student();
		stu.setName("西夏公主");
		stu.setAge(19);
		studentDAO.update(8L, stu);
	}

	@Test
	public void testGet() {
		Student stu = studentDAO.get(1L);
		System.out.println(stu);
	}

	@Test
	public void testList() {
		List<Student> list = studentDAO.list();
		for (Student stu : list) {
			System.out.println(stu);
		}
	}

}
