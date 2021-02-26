package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

import com.laptrinhjavaweb.constant.SystemConstant;

public class MessageUtil {
	
	public static void showMessage(HttpServletRequest request) {
		if(request.getParameter("message") != null ) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if(message.equals(SystemConstant.INSERT)) {
				messageResponse = "Insert success";
				alert = "success";
			} else if(message.equals(SystemConstant.UPDATE)) {
				messageResponse = "Update success";
				alert = "success";
			} else if(message.equals(SystemConstant.DELETE)) {
				messageResponse = "Delete success";
				alert = "success";
			} else if(message.equals(SystemConstant.ERROR)) {
				messageResponse = "Error system";
				alert = "danger";
			}
			request.setAttribute("messageResponse", messageResponse);
			request.setAttribute("alert", alert);
		}
	}
	
}
