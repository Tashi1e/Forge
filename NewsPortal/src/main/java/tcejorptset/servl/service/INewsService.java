package tcejorptset.servl.service;

import java.util.List;

import tcejorptset.servl.bean.News;

public interface INewsService {
  void save();
  void find();
  void update();
  
  List<News> latestList(int count)  throws ServiceException;
  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException;
}
