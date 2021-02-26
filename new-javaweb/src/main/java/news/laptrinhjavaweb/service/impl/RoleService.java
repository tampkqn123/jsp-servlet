package news.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IRoleDAO;

import news.laptrinhjavaweb.model.RoleModel;
import news.laptrinhjavaweb.service.IRoleService;

public class RoleService implements IRoleService{
	
	@Inject
	private IRoleDAO roleDAO;

	@Override
	public List<RoleModel> findAll() {	
		return roleDAO.findAll();
	}

}
