package tcejorptset.servl.service;

import java.util.List;

import tcejorptset.servl.bean.News;

public interface INewsService {
	
  void save(News news) throws ServiceException;
  void update(News news) throws ServiceException;
  void delete(int [] newsId) throws ServiceException;
  
  List<News> latestList(int count, String locale)  throws ServiceException;
  List<News> latestList(String locale)  throws ServiceException;
  List<News> find(String keyWord, String locale) throws ServiceException;
  News findById(int id, String locale) throws ServiceException;
}
