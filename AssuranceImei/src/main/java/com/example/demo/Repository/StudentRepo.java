package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository
public class StudentRepo {
	
	
	public StudentRepo() {
		
	}
	
	public List<Student> init() {
		
		List<Student> listStudent = new ArrayList();
		Student student1 = new Student();
		student1.setId(12);
		student1.setName("test");
		student1.setPassportNumber("C0Y12978");
		
		
		listStudent.add(student1);
		
		return listStudent;
	}

}
