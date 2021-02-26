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
import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.sort.sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

import news.laptrinhjavaweb.model.UserModel;
import news.laptrinhjavaweb.service.IRoleService;
import news.laptrinhjavaweb.service.IUserService;
@WebServlet(urlPatterns = {"/admin-user"})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IRoleService roleService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = FormUtil.toModel(UserModel.class, request);
		String view = "";
		if(model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), 
					new sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(userService.findAll(pageble));
			model.setTotalItem(userService.getTotolItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/user/list.jsp";
		} else if(model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId() != null) {
				model = userService.findOne(model.getId());
			}
			request.setAttribute("roles", roleService.findAll());
			view = "/views/admin/user/edit.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL,model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
}
