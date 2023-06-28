package dao.impl;

import dao.DaoException;
import bean.User;
import bean.UserInfo;
import dao.IUserDAO;

import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	private final String USER_INFO_QUERY = 
											"SELECT firstname, lastname, nickname, email, register_date " 
										  + "FROM user_details "
										  + "JOIN users " 
										  + "ON users.id = user_details.users_id " 
										  + "WHERE users.login = ?";
	
	private final String ADD_USER_QUERY = "INSERT INTO users (login, password) VALUES (? , ?)";

	private final String ADD_USER_INFO_QUERY = "INSERT INTO user_details (users_id, firstname, lastname, nickname, email, register_date) "
											 + "VALUES (LAST_INSERT_ID(), ?, ?, ?, ?, ?)";

	private final String ADD_USER_ROLE_QUERY = "INSERT INTO users_has_roles (users_id, roles_id) VALUES (LAST_INSERT_ID(), ?)"; 


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
				userInfo = new UserInfo();
				userInfo.setFirstName(resultSet.getString("firstname"));
				userInfo.setLastName(resultSet.getString("lastname"));
				userInfo.setNickName(resultSet.getString("nickname"));
				userInfo.setEmail(resultSet.getString("email"));
				
				Timestamp sqlUserRegDate = resultSet.getTimestamp("register_date");
				userInfo.setUserRegDate(sqlUserRegDate.toInstant());
				
			}

		} catch (SQLException e) {
			throw new DaoException("Can't get user info", e);
		} finally {
			closeConnection(connection, preparedStatement, resultSet);
		}
		return userInfo;
	}

	@Override
	public boolean registration(User user, UserInfo userInfo) throws DaoException {

		boolean registrationComplete = false;
		
		String firstName = userInfo.getFirstName();
		String lastName = userInfo.getLastName();
		String nickName = userInfo.getNickName();
		String email = userInfo.getEmail();
		
        Timestamp sqlUserRegDate = new Timestamp(System.currentTimeMillis());
        
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if (getRole(user.getLogin(), user.getPassword())!=null) {
			throw new DaoException("User with selected LOGIN already exists! Plese chose another one!");
		}
		else if (getUserInfo(user.getLogin()).getEmail()!=null && getUserInfo(user.getLogin()).getEmail().equals(email)) {
			throw new DaoException("User with selected E-MAIL already exists! Plese chose another one!");
		}
		else {

		try {
			
			connection = getConnection();			
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(ADD_USER_QUERY);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(ADD_USER_INFO_QUERY);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, nickName);
			preparedStatement.setString(4, email);
			preparedStatement.setTimestamp(5, sqlUserRegDate);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(ADD_USER_ROLE_QUERY);
			preparedStatement.setInt(1, 3);
			preparedStatement.executeUpdate();

			connection.commit();
			
			registrationComplete = true;

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e1); // DB rollback error
			}
			throw new DaoException("Save data failed", e);
		} finally {
			closeConnection(connection, preparedStatement);
		}
		}
		return registrationComplete;
	}
	
	
	private Connection getConnection() throws DaoException {
		Connection con = null;
		try {
			Class.forName(DB_DRIVER);

			try {
				con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			} catch (SQLException e) {
				throw new DaoException (e);  //Data Base connection error 
			}

		} catch (ClassNotFoundException e) {
			throw new DaoException (e);  //Data Base driver error
		}
		return con;

	}


	private void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws DaoException {
		try {
			if (resultSet!=null)
			resultSet.close();
		} catch (SQLException e) {
			throw new DaoException(e); // Failed to close ResultSet
		}
		try {
			if (preparedStatement!=null)
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DaoException(e); // Failed to close PreparedStatement
		}
		try {
			if (connection!=null)
			connection.close();
		} catch (SQLException e) {
			throw new DaoException(e); // Failed to close Connection 
		}
	}
	
	private void closeConnection(Connection connection, PreparedStatement preparedStatement) throws DaoException {
		try {
			if (preparedStatement!=null)
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DaoException(e); // Failed to close PreparedStatement
		}
		try {
			if (connection!=null)
			connection.close();
		} catch (SQLException e) {
			throw new DaoException(e); // Failed to close Connection
		}
	}
	
	
}
