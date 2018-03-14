package com._520it.day01._05_smis.test;

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
		stu.setName("段誉");
		stu.setAge(17);
		studentDAO.save(stu);
	}

	@Test
	public void testDelete() {
		studentDAO.delete(5L);
	}

	@Test
	public void testUpdate() {
		Student stu= new Student();
		stu.setName("王语嫣");
		stu.setAge(19);
		studentDAO.update(5L, stu);
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
