package tcejorptset.servl.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.dao.INewsDAO;
import tcejorptset.servl.dao.NewsDAOException;
import tcejorptset.servl.dao.impl.pool.ConnectionPool;
import tcejorptset.servl.dao.impl.pool.ConnectionPoolException;
import tcejorptset.servl.util.localeFormats.DateTimeFormatConverter;

public class NewsDAO implements INewsDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final ContentIO contentTextIO = ContentIO.getInstance();
	private final DateTimeFormatConverter formatConverter = DateTimeFormatConverter.getInstance(); 

	
	@Override
	public List<News> getLatestsList(int count, String locale) throws NewsDAOException {
		List<News> latestNews = new ArrayList<News>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.LATEST_NEWS_QUERY);
			preparedStatement.setInt(1, count);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				var news = new News();
				int newsId = resultSet.getInt("id");
				news.setId(newsId);
				news.setUserId(resultSet.getInt("users_id"));
				news.setTitle(resultSet.getString("title"));
				news.setBrief(resultSet.getString("brief"));
				news.setContent(contentTextIO.getContent(newsId));
				news.setImage(contentTextIO.getImagePath(newsId));
				news.setStatus(resultSet.getShort("status"));
				
				Instant dateTime = resultSet.getTimestamp("news_date").toInstant();
				news.setDate(formatConverter.getLocalDateShort(locale, dateTime));

				latestNews.add(news);
			}

		} catch (ConnectionPoolException | SQLException | IOException e) {
			throw new NewsDAOException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
		return latestNews;
	}

	
	@Override
	public List<News> getListByKeyword(String keyword, String locale) throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.FETHC_NEWS_BY_KEYWORD);
			preparedStatement.setString(1, "%"+keyword+"%");
			preparedStatement.setString(2, "%"+keyword+"%");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				var news = new News();
				int newsId = resultSet.getInt("id");
				news.setId(newsId);
				news.setUserId(resultSet.getInt("users_id"));
				news.setTitle(resultSet.getString("title"));
				news.setBrief(resultSet.getString("brief"));
				news.setContent(contentTextIO.getContent(newsId));
				news.setImage(contentTextIO.getImagePath(newsId));
				news.setStatus(resultSet.getShort("status"));
				
				Instant dateTime = resultSet.getTimestamp("news_date").toInstant();
				news.setDate(formatConverter.getLocalDateShort(locale, dateTime));

				result.add(news);
			}

		} catch (ConnectionPoolException | SQLException | IOException e) {
			throw new NewsDAOException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public News fetchById(int id, String locale) throws NewsDAOException {
		News news = new News();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.FETCH_NEWS_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				news.setId(id);
				news.setUserId(resultSet.getInt("users_id"));
				news.setTitle(resultSet.getString("title"));
				news.setBrief(resultSet.getString("brief"));
				news.setContent(contentTextIO.getContent(id));
				news.setImage(contentTextIO.getImagePath(id));
				news.setStatus(resultSet.getShort("status"));
				
				Instant dateTime = resultSet.getTimestamp("news_date").toInstant();
				news.setDate(formatConverter.getLocalDateTime(locale, dateTime));
			}
			
		} catch (ConnectionPoolException | SQLException | IOException e) {
			throw new NewsDAOException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
		return news;
	}

	@Override
	public void addNews(News news) throws NewsDAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Timestamp newsRegDate = Timestamp.from(Instant.now());

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQLQuery.SAVE_NEWS_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, news.getUserId());
			preparedStatement.setString(2, news.getTitle());
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setTimestamp(4, newsRegDate);
			preparedStatement.setInt(5, 123); // status
			preparedStatement.executeUpdate();
			
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
			int newsId = resultSet.getInt(1);
			contentTextIO.setContent(newsId, news.getContent());
			contentTextIO.setImage(newsId, news.getImgPart());
			}

		} catch (ConnectionPoolException | SQLException | IOException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new NewsDAOException (e1);
			}
			throw new NewsDAOException("Adding News Fails!!!", e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Timestamp newsRegDate = Timestamp.from(Instant.now());
		
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQLQuery.EDIT_NEWS_QUERY);
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setString(2, news.getBrief());
			preparedStatement.setTimestamp(3, newsRegDate);
			preparedStatement.setInt(4, 123); // status
			preparedStatement.setInt(5, news.getId());
			preparedStatement.executeUpdate();

			contentTextIO.setContent(news.getId(), news.getContent());
			contentTextIO.setImage(news.getId(), news.getImgPart());

		} catch (ConnectionPoolException | SQLException | IOException e) {
			throw new NewsDAOException("News update fails!!!", e);
		}finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public void deleteNews(int[] idNewses) throws NewsDAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.DELETE_NEWS_QUERY);

			for (int newsId : idNewses) {
				preparedStatement.setInt(1, newsId);
				preparedStatement.execute();
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new NewsDAOException("Fail to delete some news!", e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
	}
}
