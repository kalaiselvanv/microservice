package com.rps.gateway.dto;

public class TokenDto {

	private String token;
	private String userName;
	private Integer id;

	private String employeeId;
	private String firstName;
	private String lastName;
	private boolean adminRoleCheck;

	private RoleInfoDto roleId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoleInfoDto getRoleId() {
		return roleId;
	}

	public void setRoleId(RoleInfoDto roleId) {
		this.roleId = roleId;
	}

	public boolean isAdminRoleCheck() {
		return adminRoleCheck;
	}

	public void setAdminRoleCheck(boolean adminRoleCheck) {
		this.adminRoleCheck = adminRoleCheck;
	}

}
