package dao.impl;

import dao.DaoException;
import bean.UserInfo;
import dao.IUserDAO;

import java.sql.SQLException;
import java.time.Instant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {

	private final String DB_TYPE_NAME = "jdbc:mysql://localhost/news_db?";
	private final String DB_USER_PASS = "user=root&password=q1w2e3r4t5y6";

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	
	private Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try {
				con = DriverManager.getConnection(DB_TYPE_NAME + DB_USER_PASS);

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

	
	private void closeConnection() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean logination(String login, String password) throws DaoException {

		boolean logination = false;
		String sqlQuery = "SELECT login, password FROM news_db.users WHERE " + "login='" + login + "' AND "
				+ "password='" + password + "'";

		try {
			rs = getPS(sqlQuery).executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1) + rs.getString(2));
				if (rs.getString(1) != null && rs.getString(2) != null)
					logination = true;
			}

			closeConnection();

		} catch (SQLException e) {
			closeConnection();
			throw new DaoException("Wrong Login or Password");
		}
		return logination;
	}

	public String getRole(String login, String password) throws DaoException {

		String role = null;
		String sqlQuery = "SELECT role_name " + "FROM news_db.roles " + "JOIN news_db.users_has_roles "
				+ "ON roles.id = users_has_roles.roles_id " + "JOIN news_db.users "
				+ "ON users_has_roles.users_id = users.id " + "WHERE users.login= '" + login + "'";

		try {

			rs = getPS(sqlQuery).executeQuery();
			while (rs.next()) {
				role = rs.getString(1);
			}
			closeConnection();
		} catch (SQLException e) {
			closeConnection();
			throw new DaoException("Ooops, something went wrong!");
		}
		return role;
	}

	public String getNickName(String login) throws DaoException {

		String nickName = null;
		String sqlQuery = "SELECT nickname " + "FROM news_db.user_details " + "JOIN news_db.users "
				+ "ON user_details.users_id = users.id " + "WHERE users.login = '" + login + "'";

		try {
			rs = getPS(sqlQuery).executeQuery();
			while (rs.next()) {
				nickName = rs.getString(1);
//			System.out.println(nickName);
			}
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

//		System.out.println(user.toString());

		String addUserLogPass = "INSERT INTO news_db.users (login, password) VALUES (? , ?)";

		String getUserId = "SELECT id FROM news_db.users WHERE users.login ='" + login + "'";

		String addUserDetails = "INSERT INTO news_db.user_details (users_id, firstname, lastname, nickname, email) VALUES (?, ?, ?, ?, ?)";

		String addUsersHasRoles = "INSERT INTO news_db.users_has_roles (users_id, roles_id) VALUES (?, ?)";

		try {
			ps = getPS(addUserLogPass);
			ps.setString(1, login);
			ps.setString(2, password);
			ps.executeUpdate();

			rs = getPS(getUserId).executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1);
			}

			ps = getPS(addUserDetails);
			ps.setInt(1, userId);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, nickName);
			ps.setString(5, email);
			ps.executeUpdate();

			ps = getPS(addUsersHasRoles);
			ps.setInt(1, userId);
			ps.setInt(2, 3);
			ps.executeUpdate();

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
