package news.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.sort.sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

import news.laptrinhjavaweb.model.newsModel;
import news.laptrinhjavaweb.service.ICategoryService;
import news.laptrinhjavaweb.service.INewService;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewsController extends HttpServlet {

	private static final long serialVersionUID = 7010810210580447556L;

	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String pageStr = request.getParameter("page"); String maxPageItemStr =
		 * request.getParameter("maxPageItem"); if(pageStr != null) {
		 * model.setPage(Integer.parseInt(pageStr)); } else { model.setPage(1); }
		 * if(maxPageItemStr != null) {
		 * model.setMaxPageItem(Integer.parseInt(maxPageItemStr)); }
		 */
		newsModel model = FormUtil.toModel(newsModel.class, request);
		String view = "";
		if (model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/news/list.jsp";
		} else if(model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId() != null) {
				model = newService.findOne(model.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			view ="/views/admin/news/edit.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
