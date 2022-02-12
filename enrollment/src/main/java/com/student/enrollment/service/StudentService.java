package com.student.enrollment.service;

import java.util.List;

import com.student.enrollment.DTO.StudentDTO;
import com.student.enrollment.exception.StudentEnrollmentException;

public interface StudentService {

	public StudentDTO getStudent(Long studentId) throws StudentEnrollmentException;
	public List<StudentDTO> getAllStudents() throws StudentEnrollmentException;
	public Long addStudent(StudentDTO student) throws StudentEnrollmentException;
	public void updateStudent(Long studentId, String emailId) throws StudentEnrollmentException;
	public void deleteStudent(Long studentId) throws StudentEnrollmentException;
	
}
