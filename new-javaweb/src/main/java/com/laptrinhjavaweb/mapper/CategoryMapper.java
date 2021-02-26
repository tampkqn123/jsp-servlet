package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.laptrinhjavaweb.model.categoryModel;

public class CategoryMapper implements RowMapper<categoryModel>{

	@Override
	public categoryModel mapRow(ResultSet resultSet) {
		try {
			categoryModel category = new categoryModel();
			category.setId(resultSet.getLong("id"));
			category.setCode(resultSet.getString("code"));
			category.setName(resultSet.getString("name"));
			return category;
		} catch (SQLException e) {
			return null;
		}
	}

}
