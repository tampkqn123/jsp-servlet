package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.paging.Pageble;

import news.laptrinhjavaweb.model.newsModel;

public class NewDAO extends AbstractDAO<newsModel> implements INewDAO {

	@Override
	public List<newsModel> findByCategoryId(Long categoryId) {
		String sql ="SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(newsModel newsModel) {
		//String sql ="INSERT INTO news(title, content, categoryid) VALUES(?, ?, ?)";
		StringBuilder sql = new StringBuilder("INSERT INTO news(title, content, ");
		sql.append("thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(), 
				newsModel.getThumbnail(), newsModel.getShortDescription(),
				newsModel.getCategoryId(), newsModel.getCreatedDate(), newsModel.getCreatedBy());
	}

	@Override
	public newsModel findOne(Long id) {
		String sql ="SELECT * FROM news WHERE id = ?";
		List<newsModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(newsModel updateNews) {
		  StringBuilder sql = new
		  StringBuilder("UPDATE news SET title = ?, thumbnail = ?, ");
		  sql.append("shortdescription = ?, content = ?, categoryid = ?, ");
		  sql.append("createddate = ?, createdby = ?, modifieddate =?, modifiedby = ? WHERE id = ?");
		  update(sql.toString(), updateNews.getTitle(), updateNews.getThumbnail(),
		  updateNews.getShortDescription(), updateNews.getContent(),
		  updateNews.getCategoryId(), updateNews.getCreatedDate(),
		  updateNews.getCreatedBy(), updateNews.getModifiedDate(), 
		  updateNews.getModifiedBy(), updateNews.getId());
		 
		
		/*
		 * String sql
		 * ="UPDATE news SET title = ?, thumbnail = ?, shortdescription = ?, content = ?, categoryid = ?, createddate = ?, createdby = ? WHERE id = ?"
		 * ; return update(sql, updateNews.getTitle(), updateNews.getThumbnail(),
		 * updateNews.getShortDescription(), updateNews.getContent(),
		 * updateNews.getCategoryId(), updateNews.getCreatedDate());
		 */
		 
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id =?";
		update(sql, id);
	}

	@Override
	public List<newsModel> findAll(Pageble pageble) {
//		String sql ="SELECT * FROM news LIMIT ?, ?";
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}

}
