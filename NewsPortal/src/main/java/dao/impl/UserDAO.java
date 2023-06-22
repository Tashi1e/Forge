package dao.impl;

import dao.DaoException;
import bean.UserInfo;
import dao.IUserDAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//@SuppressWarnings("unused")
public class UserDAO implements IUserDAO {

	String AUTHORIZATION_INFO = null;

	public PreparedStatement setQuery () {
		
		PreparedStatement ps = null;
		
		String sqlQuery = "SELECT * FROM users";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/news_db?" + "user=root&password=12345")){
			ps = con.prepareStatement(sqlQuery); //"SELECT * FROM users"
			
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

		return false;
	}

	public String getRole(String login, String password) throws DaoException {

		return null;
	}

	public String getNickName(String login) throws DaoException {

		return null;
	}

	public boolean registration(UserInfo user) throws DaoException {

		return false;
	}

}
