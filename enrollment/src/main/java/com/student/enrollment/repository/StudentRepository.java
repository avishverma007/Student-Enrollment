package com.student.enrollment.repository;

import org.springframework.data.repository.CrudRepository;

import com.student.enrollment.entity.Student;

public interface StudentRepository extends CrudRepository<Student,Long>{

}
