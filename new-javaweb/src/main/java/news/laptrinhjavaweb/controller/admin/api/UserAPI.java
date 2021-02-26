package news.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

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
import news.laptrinhjavaweb.service.IUserService;


@WebServlet(urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet{
	@Inject
	private IUserService userService;

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		userModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		userModel = userService.save(userModel);
		mapper.writeValue(response.getOutputStream(), userModel);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel updateUser = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		updateUser.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateUser = userService.update(updateUser);
		mapper.writeValue(response.getOutputStream(), updateUser);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel deleteUser = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		userService.delete(deleteUser.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
}
