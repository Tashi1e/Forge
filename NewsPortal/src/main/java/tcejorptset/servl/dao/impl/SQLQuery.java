package tcejorptset.servl.dao.impl;

final class SQLQuery {

// GARBAGE	
//	protected static final String GET_USER_INFO_QUERY = "SELECT firstname, lastname, nickname, email, register_date FROM user_details"
//			+ "JOIN users ON user_details.users_id = users.id WHERE users.login = ?";
//
//	protected static final String GET_USER_ROLE_QUERY = "SELECT role_name FROM roles "
//			+ "JOIN users_has_roles ON roles.id = users_has_roles.roles_id "
//			+ "JOIN users ON users_has_roles.users_id = users.id " + "WHERE users.login = ? AND users.password = ?";
	

	static final String FIND_EMAIL_QUERY = "SELECT email FROM user_details WHERE user_details.email = ?";
	
	static final String FIND_LOGIN_QUERY = "SELECT login FROM users WHERE users.login = ?";

	static final String ADD_USER_QUERY = "INSERT INTO users (login, password) VALUES (? , ?);";

	static final String ADD_USER_INFO_QUERY = "INSERT INTO user_details (users_id, firstname, lastname, nickname, email, register_date) "
											+ "VALUES (LAST_INSERT_ID(), ?, ?, ?, ?, ?);";

	static final String ADD_USER_ROLE_QUERY = "INSERT INTO users_has_roles (users_id, roles_id) VALUES (LAST_INSERT_ID(), ?);";
	
	static final String ADD_USER_TOKEN_QUERY = "INSERT INTO user_token (users_id, selector, validator) VALUES (?, ?, ?);";

	static final String GET_USER_ID_PASSWORD_BY_LOGIN = "SELECT id, password FROM users WHERE users.login = ?";
	
	static final String GET_USER_ID_BY_TOKEN = "SELECT users_id FROM user_token WHERE selector = ? AND validator = ?";
	
	static final String GET_USER_INFO_QUERY = "SELECT firstname, lastname, nickname, email, register_date "
											+ "FROM user_details WHERE user_details.users_id = ?";

	static final String GET_USER_ROLE_QUERY = "SELECT role_name FROM roles "
														+ "JOIN users_has_roles ON roles.id = users_has_roles.roles_id  "
														+ "WHERE users_has_roles.users_id = ?";
	
	private SQLQuery () {};
}
