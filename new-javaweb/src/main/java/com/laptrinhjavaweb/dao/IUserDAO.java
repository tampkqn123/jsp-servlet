package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
		UserModel findByUserNameAndPassWordAndStatus (String userName, String passWord, Integer status);
		List<UserModel> findByRoleId(Long id);
		Long save(UserModel userModel);
		UserModel findOne(Long roleId);
		void update(UserModel updateUser);
		void delete(long id);
		List<UserModel> findAll(Pageble pageble);
		int getTotolItem();
}
	
	
