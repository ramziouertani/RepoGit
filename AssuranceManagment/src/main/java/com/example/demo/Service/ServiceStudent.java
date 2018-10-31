package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Student;
import com.example.demo.Repository.studentRepository;

@Service
public class ServiceStudent {
	
	@Autowired
	studentRepository studentRepo;
	
	public List<Student> getStudent(){
		
		return studentRepo.findAll();
		
	}

}
