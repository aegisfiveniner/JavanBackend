package com.testjavan.usermanagement.dtos;

public class RoleDTO {
	private long idRole;
	private String roleName;
	public RoleDTO() {
		super();
	}
	public RoleDTO(long idRole, String roleName) {
		super();
		this.idRole = idRole;
		this.roleName = roleName;
	}
	public long getIdRole() {
		return idRole;
	}
	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
