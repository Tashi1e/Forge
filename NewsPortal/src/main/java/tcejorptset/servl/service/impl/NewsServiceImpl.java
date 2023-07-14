package tcejorptset.servl.service.impl;

import java.util.List;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.dao.DaoProvider;
import tcejorptset.servl.dao.INewsDAO;
import tcejorptset.servl.dao.NewsDAOException;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;

public class NewsServiceImpl implements INewsService{

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	
	
	@Override
	public void save(News news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<News> find(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(News news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		
		try {
			return newsDAO.getLatestsList(5);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

}
