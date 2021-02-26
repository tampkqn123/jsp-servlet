package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.laptrinhjavaweb.model.RoleModel;
import news.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setFullName(resultSet.getString("fullname"));
			user.setPassWord(resultSet.getString("password"));
			user.setStatus(resultSet.getInt("status"));
			user.setRoleId(resultSet.getLong("roleid"));
			user.setCreatedDate(resultSet.getTimestamp("createddate"));
			user.setCreatedBy(resultSet.getString("createdby"));
			if(resultSet.getTime("modifieddate") != null) {
				user.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
			}
			if(resultSet.getString("modifiedby") != null) {
				user.setModifiedBy(resultSet.getString("modifiedby"));
			}
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return user;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
