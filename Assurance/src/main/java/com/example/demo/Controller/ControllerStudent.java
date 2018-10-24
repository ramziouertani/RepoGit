package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Student;
import com.example.demo.Service.ServiceStudent;

@RestController
@RequestMapping("/api")
public class ControllerStudent {

	@Autowired
	ServiceStudent serviceStudent;
	
	public List<Student> getStudent(){
		return serviceStudent.getStudent();
	}
}
