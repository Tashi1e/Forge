package dao.impl;

import dao.DaoException;
import bean.UserInfo;
import dao.IUserDAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {
	
	private final String DB_TYPE_NAME = "jdbc:mysql://localhost/news_db?";
	private final String DB_USER_PASS = "user=root&password=12345";
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private Connection getConnection () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try 
			{con = DriverManager.getConnection(DB_TYPE_NAME + DB_USER_PASS);
			
			} catch (SQLException e) {
				// TODO Connection Error
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Driver Error
			e.printStackTrace();
		}
		return con;
	}

	
	private PreparedStatement getPS(String sqlQuery) throws SQLException {
		ps = getConnection().prepareStatement(sqlQuery);
		return ps;
	}
	
	private void closeConnection () {
		try {
			if (rs != null)
				rs.close();
			if (ps !=null)
				ps.close();
			if (con !=null)
				con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean logination(String login, String password) throws DaoException {

		boolean logination = false;
		String sqlQuery = "SELECT (login, password) FROM users WHERE "
				+ "login="+login
				+ " AND "
				+ "password="+password;
		try {
			rs = getPS(sqlQuery).getResultSet();
			if (rs!=null) 
				logination=true;
			closeConnection();
			
		} catch (SQLException e) {
			closeConnection();
			throw new DaoException("Wrong Login or Password");
		}
		return logination;
	}

	
	public String getRole(String login, String password) throws DaoException {

		String role = null;
		String sqlQuery = "SELECT role_name " + "FROM roles " + "JOIN users_has_roles "
				+ "ON roles.role_name = users_has_roles.roles_id " + "JOIN users "
				+ "ON users_has_roles.users_id = users.id " + "WHERE users.login IS " + login;

		try {
			rs = getPS(sqlQuery).getResultSet();
				role = rs.getString(1);
				closeConnection();
		} catch (SQLException e) {
			closeConnection();
			throw new DaoException("Ooops, something went wrong!");
		}
		return role;
	}

	public String getNickName(String login) throws DaoException {

		String nickName = null;
		String sqlQuery = "SELECT nickname " + "FROM user_details " + "JOIN users "
				+ "ON user_details.users_id = users.id " + "WHERE users.login IS " + login;
		
		try {
			rs = getPS(sqlQuery).getResultSet();
				nickName = rs.getString(1);
				closeConnection();
		} catch (SQLException e) {
			closeConnection();
			throw new DaoException("Ooops, something went wrong!");
		}
		return nickName;
	}

	public boolean registration(UserInfo user) throws DaoException {

		boolean registrationComplete = false;
		int userId = 0;
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String nickName = user.getNickName();
		String email = user.getEmail();
		Instant regDate = user.getRegDate();
		String login = user.getLogin();
		String password = user.getPassword();
		String role = user.getRole();

		String addUserLogPass = "INSERT INTO users (login, password) VALUES (" + login + ", " + password + ")";

		String getUserId = "SELECT FROM users (id) WHERE users.login IS " + login;

		String addUserDetails = "INSERT INTO user_details (firstname, lastname, nickname, email, register_date) VALUES ("
				+ firstName + ", " + lastName + ", " + nickName + ", " + email + ", " + regDate + ")";

		String addUsersHasRoles = "INSERT INTO users_has_roles (users_id, roles_id) VALUES (" + userId + ", " + 3 + ")";

		try {
				getPS(addUserLogPass).executeUpdate();
				userId = getPS(getUserId).getResultSet().getInt(1);
				getPS(addUserDetails).executeUpdate();
				getPS(addUsersHasRoles).executeUpdate();

				closeConnection();
				registrationComplete = true;

			} catch (SQLException e) {
				closeConnection();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return registrationComplete;
	}
}
