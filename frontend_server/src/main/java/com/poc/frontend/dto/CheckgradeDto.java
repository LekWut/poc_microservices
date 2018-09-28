package com.poc.frontend.dto;

import java.io.Serializable;

import base.enumtype.CrudMode;

public class CheckgradeDto implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String studentCode;
	private String studentName;
	private Integer acadYear;
	private Integer semesterId;
	private String courseCode;
	private String courseName;
	private String grade;

	private CrudMode itemMode;

	@Override
	public CheckgradeDto clone() throws CloneNotSupportedException {
		return (CheckgradeDto) super.clone();
	}

	public CheckgradeDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CrudMode getItemMode() {
		return itemMode;
	}

	public void setItemMode(CrudMode itemMode) {
		this.itemMode = itemMode;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getAcadYear() {
		return acadYear;
	}

	public void setAcadYear(Integer acadYear) {
		this.acadYear = acadYear;
	}

	public Integer getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Integer semesterId) {
		this.semesterId = semesterId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}