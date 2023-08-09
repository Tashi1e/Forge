package com.tcejorptset.servl.bean;

public enum ErrorCode {
	ADD_NEWS,
	UPDATE_NEWS,
	DELETE_NEWS,
	REGISTRATION_SUCCESSFUL,
	REGISTRATION_FAILED,
	LOGIN_EXISTS,
	EMAIL_EXISTS,
	SIGN_IN,
	FETCH_NEWS,
	LATEST_NEWS_LITS,
	ERROR_404;

	public String getCode () {
		return name().toLowerCase();
	}
}


