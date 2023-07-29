package tcejorptset.servl.dao;

import java.util.List;

import tcejorptset.servl.bean.News;


public interface INewsDAO {
	List<News> getListByKeyword(String keyword, String locale) throws NewsDAOException;
	List<News> getLatestsList(int count, String locale) throws NewsDAOException;
	News fetchById(int newsId, String locale) throws NewsDAOException;
	void addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNews(int [] newsId)throws NewsDAOException;
}
