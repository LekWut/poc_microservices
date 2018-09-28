package base.test.domain;
// Generated Mar 10, 2017 11:22:59 AM by Hibernate Tools 5.1.0.Final

import java.util.Date;

/**
 * TestUser generated by hbm2java
 */
public class TestUser implements java.io.Serializable {

	private String username;
	private int version;
	private String password;
	private String userType;
	private String userNameTh;
	private String userNameEn;
	private String email;
	private String activeStaus;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;

	public TestUser() {
	}

	public TestUser(String username, String password, String createdBy, Date createdDate) {
		this.username = username;
		this.password = password;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public TestUser(String username, String password, String userType, String userNameTh, String userNameEn,
			String email, String activeStaus, String createdBy, Date createdDate, String updatedBy,
			Date updatedDate) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.userNameTh = userNameTh;
		this.userNameEn = userNameEn;
		this.email = email;
		this.activeStaus = activeStaus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserNameTh() {
		return this.userNameTh;
	}

	public void setUserNameTh(String userNameTh) {
		this.userNameTh = userNameTh;
	}

	public String getUserNameEn() {
		return this.userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActiveStaus() {
		return this.activeStaus;
	}

	public void setActiveStaus(String activeStaus) {
		this.activeStaus = activeStaus;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}