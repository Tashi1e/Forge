package dao.impl;

import dao.DaoException;
import bean.User;
import bean.UserInfo;
import dao.IUserDAO;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Map;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {

	private final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/news_db";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "q1w2e3r4t5y6";
	
	private final String USER_LOG_PASS_QUERY = 
												"SELECT role_name FROM roles " 
											  + "JOIN users_has_roles "
											  + "ON roles.id = users_has_roles.roles_id " 
											  + "JOIN users " + "ON users_has_roles.users_id = users.id "
											  + "WHERE users.login = ? AND password = ?";
	
	String USER_INFO_QUERY = 
							"SELECT firstname, lastname, nickname, email, register_date " 
						  + "FROM user_details "
						  + "JOIN users " 
						  + "ON users.id = user_details.users_id " 
						  + "WHERE users.login = ?";

	private Connection getConnection() throws DaoException {
		Connection con = null;
		try {
			Class.forName(DB_DRIVER);

			try {
				con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			} catch (SQLException e) {
				throw new DaoException ("Data Base connection error", e);
			}

		} catch (ClassNotFoundException e) {
			throw new DaoException ("Data Base driver error", e);
		}
		return con;

	}


	private void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws DaoException {
		try {
			resultSet.close();
		} catch (SQLException e) {
			throw new DaoException("Failed to close ResultSet", e);
		}
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DaoException("Failed to close PreparedStatement", e);
		}
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Failed to close Connection", e);
		}
	}


	@Override
	public String getRole(String login, String password) throws DaoException {

		String role = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = getConnection();
			preparedStatement = connection.prepareStatement(USER_LOG_PASS_QUERY);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				role = resultSet.getString("role_name");
			}

		} catch (SQLException e) {
			throw new DaoException("Can't find user", e);
		} finally {
			closeConnection(connection, preparedStatement, resultSet);
		}
		return role;
	}

	@Override
	public UserInfo getUserInfo(String login) throws DaoException {

		UserInfo userInfo = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = getConnection();
			preparedStatement = connection.prepareStatement(USER_INFO_QUERY);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userInfo.setFirstName(resultSet.getString("firstname"));
				userInfo.setLastName(resultSet.getString("lastname"));
				userInfo.setNickName(resultSet.getString("nickname"));
				userInfo.setEmail(resultSet.getString("email"));
				userInfo.setRegDate(resultSet.getDate("register_date"));
			}

		} catch (SQLException e) {
			throw new DaoException("Ooops, something went wrong!", e);
		} finally {
			closeConnection(connection, preparedStatement, resultSet);
		}
		return userInfo;
	}

	@Override
	public boolean registration(User user, UserInfo userInfo) throws DaoException {

		boolean registrationComplete = false;
		int userId = 0;
		String firstName = userInfo.getFirstName();
		String lastName = userInfo.getLastName();
		String nickName = userInfo.getNickName();
		String email = userInfo.getEmail();
		Date regDate = userInfo.getRegDate();
		String login = user.getLogin();
		String password = user.getPassword();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String ADD_USER_QUERY = "INSERT INTO news_db.users (login, password) VALUES (? , ?)";

		String getUserId = "SELECT id FROM news_db.users WHERE users.login = ?";

		String ADD_USER_INFO_QUERY = "INSERT INTO news_db.user_details (users_id, firstname, lastname, nickname, email) VALUES (?, ?, ?, ?, ?)";

		String ADD_USER_ROLE_QUERY = "INSERT INTO news_db.users_has_roles (users_id, roles_id) VALUES (?, ?)";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(ADD_USER_QUERY);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(getUserId);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt(1);
			}
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(ADD_USER_INFO_QUERY);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setString(4, nickName);
			preparedStatement.setString(5, email);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(ADD_USER_ROLE_QUERY);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, 3);
			preparedStatement.executeUpdate();

			registrationComplete = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection, preparedStatement, resultSet);
		}
		return registrationComplete;
	}

}
