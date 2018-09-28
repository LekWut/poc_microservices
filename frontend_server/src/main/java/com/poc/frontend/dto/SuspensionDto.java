package com.poc.frontend.dto;

import java.io.Serializable;
import java.util.Date;

import base.enumtype.CrudMode;

public class SuspensionDto implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String studentCode;
	private String studentName;
	private String suspensionRemark;
	private Date suspensionDate;
	private String suspendedFlag;

	private CrudMode itemMode;

	@Override
	public SuspensionDto clone() throws CloneNotSupportedException {
		return (SuspensionDto) super.clone();
	}

	public SuspensionDto() {

	}

	public SuspensionDto(String studentCode, String studentName, String suspensionRemark, Date suspensionDate, String suspendedFlag) {
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.suspensionRemark = suspensionRemark;
		this.suspensionDate = suspensionDate;
		this.suspendedFlag = suspendedFlag;
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

	public String getSuspensionRemark() {
		return suspensionRemark;
	}

	public void setSuspensionRemark(String suspensionRemark) {
		this.suspensionRemark = suspensionRemark;
	}

	public Date getSuspensionDate() {
		return suspensionDate;
	}

	public void setSuspensionDate(Date suspensionDate) {
		this.suspensionDate = suspensionDate;
	}

	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	public void setSuspendedFlag(String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}
	
	public boolean isSuspendedFlagBoolean() {
		boolean suspendedFlagBoolean = false;
		if ("Y".equals(this.suspendedFlag)) {
			suspendedFlagBoolean = true;
		}
		return suspendedFlagBoolean;
	}

	public void setSuspendedFlagBoolean(boolean suspendedFlagBoolean) {
		if (suspendedFlagBoolean) {
			this.suspendedFlag = "Y";
		} else {
			this.suspendedFlag = "N";
		}
	}

}