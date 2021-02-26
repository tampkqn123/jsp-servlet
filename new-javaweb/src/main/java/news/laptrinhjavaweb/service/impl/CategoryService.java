package news.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategotyDAO;

import news.laptrinhjavaweb.model.categoryModel;
import news.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService{
	
	@Inject
	private ICategotyDAO categoryDao;
	
	@Override
	public List<categoryModel> findAll() {
		return categoryDao.findAll();
	}

}
