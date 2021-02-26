package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, passWord,status);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public List<UserModel> findByRoleId(Long roleId) {
		String sql="SELECT * FROM user WHERE roleid = ?";
		return query(sql, new UserMapper(), roleId);
	}

	@Override
	public Long save(UserModel userModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO user(username, password, ");
		sql.append("fullname, status, roleid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), userModel.getUserName(), userModel.getPassWord(),userModel.getFullName(),
				userModel.getStatus(), userModel.getRoleId(), 
				userModel.getCreatedDate(), userModel.getCreatedBy());
	}

	@Override
	public UserModel findOne(Long id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		List<UserModel> user = query(sql, new UserMapper(), id);
		return user.isEmpty() ? null : user.get(0);
	}

	@Override
	public void update(UserModel updateUser) {
		StringBuilder sql = new StringBuilder("UPDATE user SET username = ?, password = ?, ");
		sql.append("fullname = ?, status = ?, roleid = ?, createddate = ?");
		sql.append("createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateUser.getUserName(), updateUser.getPassWord(), updateUser.getFullName(), 
				updateUser.getStatus(), updateUser.getRoleId(), updateUser.getCreatedDate(), 
				updateUser.getCreatedBy(), updateUser.getModifiedDate(), 
				updateUser.getModifiedBy(), updateUser.getId());
		
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM user WHERE id =?";
		update(sql, id);	
	}

	@Override
	public List<UserModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user");
		if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}
		return query(sql.toString(), new UserMapper());
	}

	@Override
	public int getTotolItem() {
		String sql = "SELECT count(*) FROM user";
		return count(sql);
	}
	
	
	
	
	
	
	
}
