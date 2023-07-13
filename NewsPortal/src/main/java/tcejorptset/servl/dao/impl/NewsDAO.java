package tcejorptset.servl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.dao.INewsDAO;
import tcejorptset.servl.dao.NewsDAOException;
import tcejorptset.servl.util.temp.TempArticleSource;

public class NewsDAO implements INewsDAO {

	private final TempArticleSource tempArticleSource = new TempArticleSource();

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		for (Integer i = 1; i <= 5; i++) {
			News news = tempArticleSource.article(i);
			if (news==null)
				continue;
			else
				result.add(tempArticleSource.article(i));
		}
		return result;
	}

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		for (Integer i = 1; i <= 5; i++) {
			News news = tempArticleSource.article(i);
		if (news==null)
			continue;
		else
			result.add(tempArticleSource.article(i));
	}
		return result;
	}

	@Override
	public News fetchById(int id) throws NewsDAOException {
		return tempArticleSource.article(id);
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
	public void deleteNews(int [] idNewses) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

}
