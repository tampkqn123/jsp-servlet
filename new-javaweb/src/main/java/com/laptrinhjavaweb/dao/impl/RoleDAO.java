package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.mapper.RoleMapper;

import news.laptrinhjavaweb.model.RoleModel;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO{

	@Override
	public List<RoleModel> findAll() {
		String sql = "SELECT * FROM role";
		return query(sql, new RoleMapper());
	}

	@Override
	public RoleModel findOne(Long id) {
		String sql = "SELECT * FROM role WHERE id = ?";
		List<RoleModel> role = query(sql, new RoleMapper(), id);
		return role.isEmpty() ? null : role.get(0);
	}

	@Override
	public RoleModel findByRoleCode(String code) {
		String sql = "SELECT * FROM role WHERE code = ?";
		List<RoleModel> role = query(sql, new RoleMapper(), code);
		return role.isEmpty() ? null : role.get(0);
	}

}
