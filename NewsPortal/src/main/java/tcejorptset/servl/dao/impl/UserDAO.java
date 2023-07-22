package tcejorptset.servl.dao.impl;

import tcejorptset.servl.bean.User;
import tcejorptset.servl.bean.UserInfo;
import tcejorptset.servl.dao.DaoException;
import tcejorptset.servl.dao.IUserDAO;
import tcejorptset.servl.dao.impl.pool.ConnectionPool;
import tcejorptset.servl.dao.impl.pool.ConnectionPoolException;
import tcejorptset.servl.util.encrypt.Encryptor;
import tcejorptset.servl.util.encrypt.HashB;
import tcejorptset.servl.util.encrypt.HashS;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final Encryptor encryptorB = new HashB();
	private final Encryptor encryptorS = new HashS();

	@Override
	public Integer getUserId(String login, String password) throws DaoException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer userId = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.GET_USER_ID_PASSWORD_BY_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String hashedPassword = resultSet.getString("password");
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

		System.out.println(selector + "\n" + validator); // TEST
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.GET_USER_ID_BY_TOKEN);
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
			preparedStatement = connection.prepareStatement(SQLQuery.GET_USER_ROLE_QUERY);
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
			preparedStatement = connection.prepareStatement(SQLQuery.GET_USER_INFO_QUERY);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setFirstName(resultSet.getString("firstname"));
				userInfo.setLastName(resultSet.getString("lastname"));
				userInfo.setNickName(resultSet.getString("nickname"));
				userInfo.setEmail(resultSet.getString("email"));
				userInfo.setUserRegDate(resultSet.getTimestamp("register_date").toInstant());
				userInfo.setUserId(userId);

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

		if (objectExists(login, SQLQuery.FIND_LOGIN_QUERY)) {
			throw new DaoException("User with selected LOGIN already exists! Plese chose another one!");
		}
		if (objectExists(email, SQLQuery.FIND_EMAIL_QUERY)) {
			throw new DaoException("User with selected E-MAIL already exists! Plese chose another one!");
		}

		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;

		try {

			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatement1 = connection.prepareStatement(SQLQuery.ADD_USER_QUERY);
			preparedStatement1.setString(1, login);
			preparedStatement1.setString(2, password);
			preparedStatement1.executeUpdate();

			preparedStatement2 = connection.prepareStatement(SQLQuery.ADD_USER_INFO_QUERY);
			preparedStatement2.setString(1, firstName);
			preparedStatement2.setString(2, lastName);
			preparedStatement2.setString(3, nickName);
			preparedStatement2.setString(4, email);
			preparedStatement2.setTimestamp(5, sqlUserRegDate);
			preparedStatement2.executeUpdate();

			preparedStatement3 = connection.prepareStatement(SQLQuery.ADD_USER_ROLE_QUERY);
			preparedStatement3.setInt(1, roleIndex);
			preparedStatement3.executeUpdate();

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
			connectionPool.closeConnection(preparedStatement1, preparedStatement2);
			connectionPool.closeConnection(connection, preparedStatement3);
		}
		return registrationComplete;
	}

	@Override
	public Map<String, String> addUpdateToken(int userId) throws DaoException {
		if (objectExists(userId, SQLQuery.FIND_USER_TOKEN)) {
			return tokenOps(userId, SQLQuery.UPDATE_USER_TOKEN_QUERY);
		} else {
			return tokenOps(userId, SQLQuery.ADD_USER_TOKEN_QUERY);
		}
	}

	private Map<String, String> tokenOps(int userId, String sqlQuery) throws DaoException {

		Map<String, String> token = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selector = encryptorS.encrypt(RandomStringUtils.randomAlphabetic(16));
		String validator = encryptorS.encrypt(RandomStringUtils.randomAlphabetic(32));

		System.out.println("tokenOPS"); // TEST

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, selector);
			preparedStatement.setString(2, validator);
			preparedStatement.setInt(3, userId);
			preparedStatement.executeUpdate();

			connection.commit();

			token = new HashMap<>();
			token.put("selector", selector);
			token.put("validator", validator);

			System.out.println(token.toString()); // TEST
		} catch (SQLException | ConnectionPoolException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e1); // DB rollback error
			}
			System.out.println("DAO exception"); // TEST
//			throw new DaoException(e);
			e.printStackTrace();
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
		return token;
	}

	private boolean objectExists(Object object, String sqlQuery) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setObject(1, object);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

//GARBAGE	
//	private boolean emailExists(String email) throws DaoException { 
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//
//		try {
//			connection = connectionPool.takeConnection();
//			preparedStatement = connection.prepareStatement(SQLQuery.FIND_EMAIL_QUERY);
//			preparedStatement.setString(1, email);
//			resultSet = preparedStatement.executeQuery();
//			return resultSet.next();
//
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		} catch (ConnectionPoolException e) {
//			throw new DaoException(e);
//		} finally {
//			connectionPool.closeConnection(connection, preparedStatement, resultSet);
//		}
//	}
//
//	private boolean loginExists(String login) throws DaoException {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//
//		try {
//			connection = connectionPool.takeConnection();
//			preparedStatement = connection.prepareStatement(SQLQuery.FIND_LOGIN_QUERY);
//			preparedStatement.setString(1, login);
//			resultSet = preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		} catch (ConnectionPoolException e) {
//			throw new DaoException(e);
//		} finally {
//			connectionPool.closeConnection(connection, preparedStatement, resultSet);
//		}
//	}
}
