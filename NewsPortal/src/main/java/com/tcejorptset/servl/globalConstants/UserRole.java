package com.tcejorptset.servl.globalConstants;

public enum UserRole {
	
	ADMIN,
	EDITOR,
	USER,
	GUEST;
	
	public String getRole () {
		return name().toLowerCase();
	}

}
