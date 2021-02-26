package com.laptrinhjavaweb.dao;

import java.util.List;

import news.laptrinhjavaweb.model.categoryModel;

public interface ICategotyDAO extends GenericDAO<categoryModel>{
	List<categoryModel> findAll();
	categoryModel findOne(Long id);
	categoryModel findOneByCode(String code);
}
