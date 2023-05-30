package service;

import java.util.List;

import bean.News;

public interface INewsService {
  void save();
  void find();
  void update();
  
  List<News> latestList(int count)  throws ServiceException;
  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException;
}
