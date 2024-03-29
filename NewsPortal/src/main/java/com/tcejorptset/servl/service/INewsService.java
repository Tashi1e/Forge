package com.tcejorptset.servl.service;

import java.util.List;

import com.tcejorptset.servl.bean.News;

public interface INewsService {
	
  void save(News news) throws ServiceException;
  void update(News news) throws ServiceException;
  void delete(int [] newsId) throws ServiceException;
  
  List<News> latestList(int count)  throws ServiceException;
  List<News> latestList()  throws ServiceException;
  List<News> find(String keyWord) throws ServiceException;
  News findById(int id) throws ServiceException;
}
