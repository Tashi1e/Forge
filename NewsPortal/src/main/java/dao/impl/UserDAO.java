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

//@SuppressWarnings("unused")
public class UserDAO implements IUserDAO {

	private PreparedStatement setQuery(String sqlQuery) {

		PreparedStatement ps = null;

//		String sqlQuery = "SELECT * FROM users";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost/news_db?" + "user=root&password=12345")) {
				ps = con.prepareStatement(sqlQuery); // "SELECT * FROM users"

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}

	public boolean logination(String login, String password) throws DaoException {

		boolean logination = false;
		String sqlQuery = "SELECT * FROM users";
		PreparedStatement ps = setQuery(sqlQuery);
		try {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				String dbLogin = rs.getString(2);
				String dbPassword = rs.getString(3);
				if (dbLogin.equals(login) && dbPassword.equals(password)) {
					logination = true;
					break;
				}
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return logination;
	}

	public String getRole(String login, String password) throws DaoException {

		String role = null;
		String sqlQuery = "SELECT role_name " + "FROM roles " + "JOIN users_has_roles "
				+ "ON roles.role_name = users_has_roles.roles_id " + "JOIN users "
				+ "ON users_has_roles.users_id = users.id " + "WHERE users.login IS " + login;

		ResultSet rs;
		try {
			rs = setQuery(sqlQuery).getResultSet();
			while (rs.next()) {
				role = rs.getString(1);
			}
			rs.close();

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return role;
	}

	public String getNickName(String login) throws DaoException {

		String nickName = null;
		String sqlQuery = "SELECT nickname " + "FROM user_details " + "JOIN users "
				+ "ON user_details.users_id = users.id " + "WHERE users.login IS " + login;

		ResultSet rs;
		try {
			rs = setQuery(sqlQuery).getResultSet();
			while (rs.next()) {
				nickName = rs.getString(1);
			}
			rs.close();

		} catch (SQLException e) {
			throw new DaoException(e);
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

		PreparedStatement psUserId = null;
		PreparedStatement psLogPass = null;
		PreparedStatement psUsersDetails = null;
		PreparedStatement psUsersHasRoles = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost/news_db?" + "user=root&password=12345")) {
				psLogPass = con.prepareStatement(addUserLogPass);
				psLogPass.executeUpdate();

				psUserId = con.prepareStatement(getUserId);
				rs = psUserId.getResultSet();
				while (rs.next()) {
					userId = rs.getInt(1);
				}
				rs.close();

				psUsersDetails = con.prepareStatement(addUserDetails);
				psUsersDetails.executeUpdate();
				psUsersHasRoles = con.prepareStatement(addUsersHasRoles);
				psUsersHasRoles.executeUpdate();

				psLogPass.close();
				psUserId.close();
				psUsersDetails.close();
				psUsersHasRoles.close();

				registrationComplete = true;

			} catch (SQLException e) {

				try {
					if (rs != null)
						rs.close();
					if (psUserId !=null)
						psUserId.close();
					if (psLogPass !=null)
						psLogPass.close();
					if (psUsersDetails !=null)
						psUsersDetails.close();
					if (psUsersHasRoles != null)
						psUsersHasRoles.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// Wrong Driver Error
			e.printStackTrace();
		}
		return registrationComplete;
	}
}
