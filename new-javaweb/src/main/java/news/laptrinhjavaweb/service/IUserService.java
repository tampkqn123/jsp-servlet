package news.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status);
	List<UserModel> findByRoleId(Long roleId);
	UserModel save(UserModel userModel);
	UserModel findOne(Long id);
	UserModel update(UserModel updateUser);
	void delete(long[] ids);
	List<UserModel> findAll(Pageble pageble);
	int getTotolItem();
}
