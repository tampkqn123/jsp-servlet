package news.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategotyDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.categoryModel;
import news.laptrinhjavaweb.model.newsModel;
import news.laptrinhjavaweb.service.INewService;

public class NewService implements INewService{

	@Inject
	private INewDAO newDAO;
	
	@Inject
	private ICategotyDAO categoryDAO;
	
	@Override
	public List<newsModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public newsModel save(newsModel newsModel) {
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		categoryModel category = categoryDAO.findOneByCode(newsModel.getCategoryCode());
		newsModel.setCategoryId(category.getId());
		Long newId = newDAO.save(newsModel);
		return newDAO.findOne(newId);
	}

	@Override
	public newsModel update(newsModel updateNews) {
		newsModel oldNews = newDAO.findOne(updateNews.getId());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		categoryModel category = categoryDAO.findOneByCode(updateNews.getCategoryCode());
		updateNews.setCategoryId(category.getId());
		newDAO.update(updateNews);
		return newDAO.findOne(updateNews.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			newDAO.delete(id);
		}
	}

	@Override
	public List<newsModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {	
		return newDAO.getTotalItem();
	}

	@Override
	public newsModel findOne(long id) {
		newsModel newsModel = newDAO.findOne(id);
		categoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryId());
		newsModel.setCategoryCode(categoryModel.getCode());
		return newsModel;
	}

}
