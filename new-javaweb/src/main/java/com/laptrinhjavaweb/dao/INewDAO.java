package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.newsModel;

public interface INewDAO extends GenericDAO<newsModel>{
	newsModel findOne(Long id);
	List<newsModel> findByCategoryId(Long categoryId);
	Long save(newsModel newsModel);
	void update(newsModel updateNews);
	void delete(long id);
	List<newsModel> findAll(Pageble pageble);
	int getTotalItem ();
}
