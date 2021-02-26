package news.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.newsModel;

public interface INewService {
	
	List<newsModel> findByCategoryId (Long categoryId);
	newsModel save(newsModel newsModel);
	newsModel update(newsModel updateNews);
	void delete(long[] ids);
	List<newsModel> findAll(Pageble pageble);
	int getTotalItem();
	newsModel findOne(long id);
}







