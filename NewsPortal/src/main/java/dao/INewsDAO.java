package dao;

import java.util.List;

import bean.News;


public interface INewsDAO {
	List<News> getList() throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int newsId) throws NewsDAOException;
	int addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNews(int [] newsId)throws NewsDAOException;
}
