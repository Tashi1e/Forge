package com.tcejorptset.servl.util.validation;

public class ValidationProvider {

	private static final ValidationProvider instance = new ValidationProvider();

	private final UserDataValidation userDataValidation = new UserDataValidationImpl();

	private ValidationProvider() {};

	public UserDataValidation getUserDataValidation() {
		return userDataValidation;
	}

	public static ValidationProvider getInstance() {
		return instance;
	}
}
