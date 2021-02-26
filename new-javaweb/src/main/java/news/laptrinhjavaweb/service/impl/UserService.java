package news.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.RoleModel;
import news.laptrinhjavaweb.model.UserModel;
import news.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{
	
	@Inject
	private IUserDAO userDAO;
	
	@Inject
	private IRoleDAO roleDAO;

	@Override
	public UserModel findByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status) {
		return userDAO.findByUserNameAndPassWordAndStatus(userName, passWord, status) ;
	}

	@Override
	public List<UserModel> findByRoleId(Long roleId) {
		return userDAO.findByRoleId(roleId);
	}

	@Override
	public UserModel save(UserModel userModel) {
		userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		RoleModel role = roleDAO.findByRoleCode(userModel.getRoleCode());
		userModel.setRoleId(role.getId());
		Long userId = userDAO.save(userModel);
		return userDAO.findOne(userId);
	}

	@Override
	public UserModel findOne(Long id) {
		UserModel userModel = userDAO.findOne(id);
		RoleModel roleModel = roleDAO.findOne(userModel.getRoleId());
		userModel.setRoleCode(roleModel.getCode());
		return userModel;
	}

	@Override
	public UserModel update(UserModel updateUser) {
		UserModel oldUser = userDAO.findOne(updateUser.getId());
		updateUser.setCreatedDate(oldUser.getCreatedDate());
		updateUser.setCreatedBy(oldUser.getCreatedBy());
		updateUser.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		RoleModel role = roleDAO.findByRoleCode(updateUser.getRoleCode());
		updateUser.setRoleId(role.getId());
		userDAO.update(updateUser);
		return userDAO.findOne(updateUser.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			userDAO.delete(id);
		}
		
	}

	@Override
	public List<UserModel> findAll(Pageble pageble) {
		return userDAO.findAll(pageble);
	}

	@Override
	public int getTotolItem() {
		return userDAO.getTotolItem();
	}

	
	
}
