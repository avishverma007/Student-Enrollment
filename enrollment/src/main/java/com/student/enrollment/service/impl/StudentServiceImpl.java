package com.student.enrollment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.enrollment.DTO.StudentDTO;
import com.student.enrollment.entity.Student;
import com.student.enrollment.exception.StudentEnrollmentException;
import com.student.enrollment.repository.StudentRepository;
import com.student.enrollment.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentDTO getStudent(Long studentId) throws StudentEnrollmentException {
		Optional<Student> optional=studentRepository.findById(studentId);
		Student s=optional.orElseThrow(() -> new StudentEnrollmentException("Student_NOT_FOUND"));
		StudentDTO sdto=new StudentDTO();
		sdto.setAge(s.getAge());
		sdto.setCourse(s.getCourse());
		sdto.setDob(s.getDob());
		sdto.setEmail(s.getEmail());
		sdto.setFieldOfStudy(s.getFieldOfStudy());
		sdto.setGender(s.getGender());
		sdto.setId(s.getId());
		sdto.setMobile(s.getMobile());
		sdto.setName(s.getName());
		return sdto;
	}

	@Override
	public List<StudentDTO> getAllStudents() throws StudentEnrollmentException {
		Iterable<Student> students = studentRepository.findAll();
		List<StudentDTO> l = new ArrayList<>();
		for(Student s:students) {
			StudentDTO sdto=new StudentDTO();
			sdto.setAge(s.getAge());
			sdto.setCourse(s.getCourse());
			sdto.setDob(s.getDob());
			sdto.setEmail(s.getEmail());
			sdto.setFieldOfStudy(s.getFieldOfStudy());
			sdto.setGender(s.getGender());
			sdto.setId(s.getId());
			sdto.setMobile(s.getMobile());
			sdto.setName(s.getName());
			l.add(sdto);
		}
		return l;
	}

	@Override
	public void updateStudent(Long studentId, String emailId) throws StudentEnrollmentException {
		Optional<Student> optional = studentRepository.findById(studentId);
		Student s =optional.orElseThrow(() -> new StudentEnrollmentException("Student_NOT_FOUND"));
		s.setEmail(emailId);
	}

	@Override
	public void deleteStudent(Long studentId) throws StudentEnrollmentException {
		Optional<Student> optional = studentRepository.findById(studentId);
		optional.orElseThrow(() -> new StudentEnrollmentException("Student_NOT_FOUND"));
		studentRepository.deleteById(studentId);
		
	}

	@Override
	public Long addStudent(StudentDTO student) throws StudentEnrollmentException {
		Student s=new Student();
		s.setAge(student.getAge());
		s.setCourse(student.getCourse());
		s.setDob(student.getDob());
		s.setEmail(student.getEmail());
		s.setFieldOfStudy(student.getFieldOfStudy());
		s.setGender(student.getGender());
		s.setId(student.getId());
		s.setMobile(student.getMobile());
		s.setName(student.getName());
		Student s1=studentRepository.save(s);
		return s1.getId();
	}

}
