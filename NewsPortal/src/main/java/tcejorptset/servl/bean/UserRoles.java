package tcejorptset.servl.bean;

public enum UserRoles {
	
	ADMIN,
	EDITOR,
	USER,
	GUEST;
	
	public String getRole () {
		return name().toLowerCase();
	}

}
