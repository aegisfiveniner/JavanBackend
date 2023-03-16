package com.testjavan.usermanagement.dtos;

import java.util.Date;

public class UserDTO {
	private long idUser;
	private RoleDTO role;
	private String username;
	private String email;
	private String password;
	private Date createdDate;
	private Date lastModifiedDate;
	public UserDTO() {
		super();
	}
	public UserDTO(long idUser, RoleDTO role, String username, String email,
			String password, Date createdDate, Date lastModifiedDate) {
		super();
		this.idUser = idUser;
		this.role = role;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}	
}
