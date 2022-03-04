package com.student.enrollment.service.impl;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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



	@SuppressWarnings("unchecked")
	@Override
	public Long addStudentJson(StudentDTO student) throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("id",student.getId());
		obj.put("name",student.getName());
		obj.put("gender",student.getGender());
		obj.put("age",student.getAge());
		obj.put("email",student.getEmail());
		obj.put("mobile",student.getMobile());
		obj.put("course",student.getCourse());
		obj.put("fieldOfStudy",student.getFieldOfStudy());
		
		@SuppressWarnings("resource")
		FileWriter file=new FileWriter("json\\test.json",false);
		file.write(obj.toJSONString());
		file.close();
		return (Long) obj.get("id");
		
	}

	@Override
	public StudentDTO getStudentJson(Long studentId) throws Exception {
		JSONParser jsonParse=new JSONParser();
		
		JSONObject obj=(JSONObject) jsonParse.parse(new FileReader("json\\test.json"));
		StudentDTO dto=new StudentDTO();
		dto.setId((Long) obj.get("id"));
		dto.setName((String) obj.get("name"));
		dto.setGender((String) obj.get("gender"));
		dto.setAge((int) obj.get("age"));
		dto.setEmail((String) obj.get("email"));
		dto.setMobile((String) obj.get("mobile"));
		dto.setCourse((String) obj.get("course"));
		dto.setFieldOfStudy((String) obj.get("fieldOfStudy"));
		return dto;

	}

	@Override
	public void updateStudentJson(Long studentId, String emailId) throws Exception {
		JSONParser jsonParse=new JSONParser();
		JSONObject obj=(JSONObject) jsonParse.parse(new FileReader("json\\test.json"));
		
		if(null==obj) throw new StudentEnrollmentException("Student_NOT_FOUND");
		
		if(studentId.equals(obj.get("id"))) {
			obj.put("email",emailId);
		}
		
		@SuppressWarnings("resource")
		FileWriter file=new FileWriter("json\\test.json",false);
		file.write(obj.toJSONString());
	}
	
	

}
