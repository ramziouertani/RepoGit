package com.example.demo.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Student;
import com.example.demo.Service.ServiceStudent;

@RestController
@RequestMapping(value= "/api",produces = "application/json")
public class ControllerStudent {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	ServiceStudent serviceStudent;
	
	@GetMapping("/students")
	public List<Student> getStudent(){
		logger.info("logger start test list");
		return serviceStudent.getStudent();
	}
}
