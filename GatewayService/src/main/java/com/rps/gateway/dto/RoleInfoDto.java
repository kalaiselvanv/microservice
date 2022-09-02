package com.rps.gateway.dto;

import java.io.Serializable;

public class RoleInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264518167978376944L;

	private Integer id;

	private String roleName;

	public RoleInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleInfoDto(Integer id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
