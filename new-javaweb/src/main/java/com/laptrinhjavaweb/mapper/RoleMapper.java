package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.laptrinhjavaweb.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel>{

	@Override
	public RoleModel mapRow(ResultSet rs) {
		try {
			RoleModel role = new RoleModel();
			role.setId(rs.getLong("id"));
			role.setCode(rs.getString("code"));
			role.setName(rs.getString("name"));
			return role;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
