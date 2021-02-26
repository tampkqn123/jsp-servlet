package com.laptrinhjavaweb.dao;

import java.util.List;

import news.laptrinhjavaweb.model.RoleModel;

public interface IRoleDAO extends GenericDAO<RoleModel>{
	
	List<RoleModel> findAll();
	RoleModel findOne(Long id);
	RoleModel findByRoleCode(String code);
}
