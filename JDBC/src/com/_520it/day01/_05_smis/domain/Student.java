package com._520it.day01._05_smis.domain;

import lombok.Data;

//学生对象
@Data
public class Student {
	private Long id;	//主键
	private String name;//姓名
	private Integer age;//年龄
}
