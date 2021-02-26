<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>
</head>
<body>
	<div class="container">
		<div class="login-form">
			<div class="main-div">
				<c:if test="${not empty message}">
					<div class="alert alert-${alert}">
							${message}
					</div>
				</c:if>
				<form action="<c:url value='/dang-nhap'/>" id="formLogin"
		method="POST">

					<div class="form-group">


						<input type="text" class="form-control" id="userName" name="userName"
							placeholder="Tên Đăng Nhập">

					</div>

					<div class="form-group">

						<input type="password" class="form-control" id="passWord" name="passWord"
							placeholder="Mật Khẩu">

					</div>
					<!-- <div class="forgot">
						<a href="reset.html">Forgot password?</a>
					</div> -->
					<input type="hidden" value="login"  name="action"/>
					<button type="submit" class="btn btn-primary">Đăng Nhập</button>

				</form>
	</div>
</body>
</html>