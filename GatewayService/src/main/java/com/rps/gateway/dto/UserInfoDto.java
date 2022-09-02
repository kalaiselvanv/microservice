package com.rps.gateway.dto;

import java.io.Serializable;

public class UserInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6229541723286581184L;

	private Integer id;

	private String employeeId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	private RoleInfoDto roleId;

	public UserInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfoDto(Integer id, String employeeId, String firstName, String lastName, String userName,
			String password, RoleInfoDto roleId) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleInfoDto getRoleId() {
		return roleId;
	}

	public void setRoleId(RoleInfoDto roleId) {
		this.roleId = roleId;
	}

}
