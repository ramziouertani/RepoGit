package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Student;

@Repository
public class StudentRepo {

	public List<Student> getAllStudent () {
		List<Student> listStudent = new ArrayList<Student>();
		Student s1 = new Student();
		    s1.id = 1;
		    s1.name = "ramzi";
		    s1.age=29;
	      listStudent.add(s1);		
		return listStudent;
	}
}
