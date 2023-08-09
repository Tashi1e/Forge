package com.tcejorptset.servl.globalConstants;

public enum UserToken {

	SELECTOR,
	VALIDATOR;
	
	public String getParam () {
		return name().toLowerCase();
	}
}
