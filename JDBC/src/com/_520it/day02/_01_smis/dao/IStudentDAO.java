package com._520it.day02._01_smis.dao;

import java.util.List;

import com._520it.day01._05_smis.domain.Student;

/**
 * 包含Student对象的CRUD操作
 * @author Administrator
 *
 */
public interface IStudentDAO {
	/**
	 * 保存学生对象
	 * @param student 需要保存的学生对象
	 */
	void save(Student student);
	
	/**
	 * 删除指定学生对象
	 * @param id 被删除学生的主键值
	 */
	void delete(Long id);
	
	/**
	 * 修改并更新指定id的学生对象信息
	 * @param id 被修改学生对象的主键
	 * @param student 新的学生信息
	 */
	void update(Long id,Student student);
	
	/**
	 * 查询指定id的学生对象
	 * @param id  指定的id
	 * @return  如果存在该id，则返回该id对应的学生对象，否则返回null
	 */
	Student get(Long id);
	
	/**
	 * 查询所有的学生对象
	 * @return 所有的学生对象，没有学生，则返回一个空集
	 */
	List<Student> list();
}
