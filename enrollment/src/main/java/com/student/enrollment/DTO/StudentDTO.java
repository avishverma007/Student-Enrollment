package com.student.enrollment.DTO;

public class StudentDTO {

private Long id;
	
	private String name;
	
	private String gender;
	
	private String dob;
	
	private int age;
	
	private String email;
	
	private String mobile;
	
	private String course;

	private String fieldOfStudy;
	
	public StudentDTO() {
		
	}

	public StudentDTO(Long id, String name, String gender, String dob, int age, String email, String mobile, String course,
			String fieldOfStudy) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.age = age;
		this.email = email;
		this.mobile = mobile;
		this.course = course;
		this.fieldOfStudy = fieldOfStudy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

}
