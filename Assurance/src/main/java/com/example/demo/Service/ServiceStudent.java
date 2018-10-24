package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepo;

@Service
public class ServiceStudent {
	
	@Autowired
	StudentRepo studentRepo;
	
	public List<Student> getStudent(){
		
		return studentRepo.getAllStudent();
	}

}
