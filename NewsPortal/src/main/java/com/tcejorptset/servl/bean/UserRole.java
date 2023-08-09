package com.tcejorptset.servl.bean;

public enum UserRole {
	
	ADMIN,
	EDITOR,
	USER,
	GUEST;
	
	public String getRole () {
		return name().toLowerCase();
	}

}
