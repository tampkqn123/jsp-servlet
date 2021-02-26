package news.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

import news.laptrinhjavaweb.model.UserModel;
import news.laptrinhjavaweb.model.newsModel;
import news.laptrinhjavaweb.service.INewService;

@WebServlet(urlPatterns = {"/api-admin-news"})
public class NewsAPI extends HttpServlet{
	
	@Inject
	private INewService newService;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		newsModel newsModel = HttpUtil.of(request.getReader()).toModel(newsModel.class);
		newsModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		newsModel = newService.save(newsModel);
		mapper.writeValue(response.getOutputStream(), newsModel);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		newsModel updateNews = HttpUtil.of(request.getReader()).toModel(newsModel.class);
		updateNews.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateNews = newService.update(updateNews);
		mapper.writeValue(response.getOutputStream(), updateNews);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		newsModel newsModel = HttpUtil.of(request.getReader()).toModel(newsModel.class);
		newService.delete(newsModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
		
	}
}
