package tcejorptset.servl.service;

import java.util.List;

import tcejorptset.servl.bean.News;

public interface INewsService {
	
  void save(News news) throws ServiceException;
  void update(News news) throws ServiceException;
  
  List<News> latestList(int count)  throws ServiceException;
  List<News> latestList()  throws ServiceException;
  List<News> find(String keyWord) throws ServiceException;
//  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException;
}
