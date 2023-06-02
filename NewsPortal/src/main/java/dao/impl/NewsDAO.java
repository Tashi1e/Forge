package dao.impl;

import java.util.ArrayList;
import java.util.List;

import bean.News;
import bean.tempArticles.TempArticlesController;
import dao.INewsDAO;
import dao.NewsDAOException;

public class NewsDAO implements INewsDAO {
	
	private final TempArticlesController tempArticlesController = new TempArticlesController();

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		
		for (int i=1; i<=5; i++)
			result.add(tempArticlesController.article(1));

		return result;
	}

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		for (int i=1; i<=5; i++)
			result.add(tempArticlesController.article(1));

		return result;
	}

	@Override
	public News fetchById(int id) throws NewsDAOException {
		return tempArticlesController.article(1);
	}

	@Override
	public int addNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

}
