package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil { //Quản lý thông tin người dùng khi đăng nhập
	
	private static SessionUtil sessionUtil = null;
	
	public static SessionUtil getInstance() { //Kiểm tra xem đối tượng đã tồn tại hay chưa, 
												//nếu người đầu tiên đã khởi tạo rồi thì ta ko cần khởi tạo nữa
		if(sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}
	
	public void putValue(HttpServletRequest request, String key, Object value ) {
		request.getSession().setAttribute(key, value);
	}
	
	public Object getValue(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
	public void removeValue(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}
}
