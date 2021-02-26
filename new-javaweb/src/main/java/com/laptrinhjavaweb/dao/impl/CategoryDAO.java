package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategotyDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;

import news.laptrinhjavaweb.model.categoryModel;

public class CategoryDAO extends AbstractDAO<categoryModel> implements ICategotyDAO{

	@Override
	public List<categoryModel> findAll() {
		String sql ="SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public categoryModel findOne(Long id) {
		String sql ="SELECT * FROM category WHERE id = ?";
		List<categoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty() ? null : category.get(0);
	}

	@Override
	public categoryModel findOneByCode(String code) {
		String sql ="SELECT * FROM category WHERE code = ?";
		List<categoryModel> category = query(sql, new CategoryMapper(), code);
		return category.isEmpty() ? null : category.get(0);
	}
	

}
