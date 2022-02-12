package com.student.enrollment.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.enrollment.DTO.StudentDTO;
import com.student.enrollment.exception.StudentEnrollmentException;
import com.student.enrollment.service.StudentService;

@RestController
@RequestMapping(value = "/studentenrollment")
public class StudentAPI {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private Environment environment;
	
	public ResponseEntity<List<StudentDTO>> getAllStudents() throws StudentEnrollmentException {
		List<StudentDTO> sdtoList = studentService.getAllStudents();
		return new ResponseEntity<>(sdtoList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/customers/{customerId}")
	public ResponseEntity<StudentDTO> getCustomer(@PathVariable Long studentId) throws StudentEnrollmentException {
		StudentDTO sdto = studentService.getStudent(studentId);
		return new ResponseEntity<>(sdto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/students")
	public ResponseEntity<String> addStudent(@RequestBody StudentDTO student) throws StudentEnrollmentException {
		Long studentId = studentService.addStudent(student);
	//	String successMessage = environment.getProperty("API.INSERT_SUCCESS") + studentId;
		String msg="success";
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/students/{studentId}")
	public ResponseEntity<String> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO sdto)
			throws StudentEnrollmentException {
		studentService.updateStudent(studentId, sdto.getEmail());
	//	String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		String msg="success";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/customers/{customerId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) throws StudentEnrollmentException {
		studentService.deleteStudent(studentId);
	//	String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		String msg="success";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
