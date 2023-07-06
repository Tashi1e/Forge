package dao.impl;

import dao.DaoException;
import bean.User;
import bean.UserInfo;
import dao.IUserDAO;
import dao.impl.pool.ConnectionPool;
import dao.impl.pool.ConnectionPoolException;
import util.encrypt.Encryptor;
import util.encrypt.HashB;
import util.encrypt.HashS;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final Encryptor encryptorB = new HashB();
	private final Encryptor encryptorS = new HashS();

//REMOVE GARBAGE
//	private final String GET_USER_INFO_QUERY = "SELECT firstname, lastname, nickname, email, register_date FROM user_details"
//			+ "JOIN users ON user_details.users_id = users.id WHERE users.login = ?";
//
//	private final String GET_USER_ROLE_QUERY = "SELECT role_name FROM roles "
//			+ "JOIN users_has_roles ON roles.id = users_has_roles.roles_id "
//			+ "JOIN users ON users_has_roles.users_id = users.id " + "WHERE users.login = ? AND users.password = ?";
//
//	private final String FIND_EMAIL_QUERY = "SELECT email FROM user_details WHERE user_details.email = ?";
//	
//	private final String FIND_LOGIN_QUERY = "SELECT login FROM users WHERE users.login = ?";
//
//	private final String ADD_USER_QUERY = "INSERT INTO users (login, password) VALUES (? , ?)";
//
//	private final String ADD_USER_INFO_QUERY = "INSERT INTO user_details (users_id, firstname, lastname, nickname, email, register_date) "
//			+ "VALUES (LAST_INSERT_ID(), ?, ?, ?, ?, ?)";
//
//	private final String ADD_USER_ROLE_QUERY = "INSERT INTO users_has_roles (users_id, roles_id) VALUES (LAST_INSERT_ID(), ?)";

	@Override
	public Integer getUserId(String login, String password) throws DaoException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer userId = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DAOQuery.GET_USER_ID_PASSWORD_BY_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String hashedPassword = resultSet.getString("password");
				System.out.println("UserDAO -> getUserId -> Hashed Password  " + hashedPassword); //TEST
				if (encryptorB.compare(password, hashedPassword)) {
					userId = resultSet.getInt("id");
				}
			} 
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException("Can't find user", e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
		return userId;
	}

	
	@Override
	public Integer getUserIdByToken(String selector, String validator) throws DaoException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DAOQuery.GET_USER_ID_BY_TOKEN);
			preparedStatement.setString(1, selector);
			preparedStatement.setString(2, validator);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("users_id");
			} else {
				return null;
			}
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public String getRole(int userId) throws DaoException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DAOQuery.GET_USER_ROLE_QUERY);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString("role_name");
			} else {
				return null;
			}
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException("Can't find user", e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public UserInfo getUserInfo(int userId) throws DaoException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DAOQuery.GET_USER_INFO_QUERY);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setFirstName(resultSet.getString("firstname"));
				userInfo.setLastName(resultSet.getString("lastname"));
				userInfo.setNickName(resultSet.getString("nickname"));
				userInfo.setEmail(resultSet.getString("email"));
				userInfo.setUserRegDate(resultSet.getTimestamp("register_date").toInstant());

				return userInfo;
			} else {
				return null;
			}
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException("Can't find user", e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public boolean registration(User user, UserInfo userInfo) throws DaoException {

		boolean registrationComplete = false;

		String login = user.getLogin();
		String password = encryptorB.encrypt(user.getPassword());
		int roleIndex = 3;

		String firstName = userInfo.getFirstName();
		String lastName = userInfo.getLastName();
		String nickName = userInfo.getNickName();
		String email = userInfo.getEmail();

		Timestamp sqlUserRegDate = new Timestamp(System.currentTimeMillis());

		if (loginExists(login)) {
			throw new DaoException("User with selected LOGIN already exists! Plese chose another one!");
		} else if (emailExists(email)) {
			throw new DaoException("User with selected E-MAIL already exists! Plese chose another one!");
		} else {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {

				connection = connectionPool.takeConnection();
				connection.setAutoCommit(false);
				preparedStatement = connection.prepareStatement(DAOQuery.ADD_USER_QUERY);
				preparedStatement.setString(1, login);
				preparedStatement.setString(2, password);
				preparedStatement.executeUpdate();
				preparedStatement.close();

				preparedStatement = connection.prepareStatement(DAOQuery.ADD_USER_INFO_QUERY);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, nickName);
				preparedStatement.setString(4, email);
				preparedStatement.setTimestamp(5, sqlUserRegDate);
				preparedStatement.executeUpdate();
				preparedStatement.close();

				preparedStatement = connection.prepareStatement(DAOQuery.ADD_USER_ROLE_QUERY);
				preparedStatement.setInt(1, roleIndex);
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
			} catch (ConnectionPoolException e) {
				throw new DaoException();
			} finally {
				login = "";
				password = "";
				connectionPool.closeConnection(connection, preparedStatement);
			}
		}
		return registrationComplete;
	}

	@Override
	public Map <String, String> addToken(int userId, String login, String password) throws DaoException {
		
		Map <String, String> token = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selector = encryptorS.encrypt(login);
		String validator = encryptorS.encrypt(password);
		
		System.out.println("UserDAO -> addToken -> Selector  "+ selector); //TEST
		System.out.println("UserDAO -> addToken -> Validator  "+ validator); //TEST
		System.out.println("UserDAO -> addToken -> userID  " + userId); //TEST
		
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DAOQuery.ADD_USER_TOKEN_QUERY);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, selector);
			preparedStatement.setString(3, validator);
			preparedStatement.executeUpdate();

			connection.commit();

			token = new HashMap<>();
			token.put("selector", selector);
			token.put("validator", validator);
			System.out.println("UserDAO -> token added to DB"); //TEST
			System.out.println("UserDAO -> token Map Selector  " + token.get("selector")); //TEST
			System.out.println("UserDAO -> token Map Validator  " + token.get("validator")); //TEST

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e1); // DB rollback error
			}
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException();
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
	return token;
	}

	private boolean emailExists(String email) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DAOQuery.FIND_EMAIL_QUERY);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	private boolean loginExists(String login) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DAOQuery.FIND_LOGIN_QUERY);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}
}
