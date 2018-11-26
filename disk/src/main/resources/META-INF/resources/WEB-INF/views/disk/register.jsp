<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<div class="col-sm-12 col-md-6 col-md-offset-3 text-center">
		<form class="form-signin" action="${ctx }/disk/register" method="post">
		    <h2 class="form-signin-heading">欢迎注册疯狂网盘</h2>
		    <label for="inputName" class="sr-only">姓名</label>
		    <input id="inputName" 
		    	class="form-control" 
		    	placeholder="姓名" 
		    	required="required" 
		    	autofocus="autofocus"
		    	name="name"
		    	value="${param.name }"/>
		    <label for="inputLoginName" class="sr-only">登录名</label>
		    <input id="inputLoginName" 
		    	class="form-control" 
		    	placeholder="登录名" 
		    	required="required" 
		    	autofocus="autofocus"
		    	name="loginName"
		    	value="${param.loginName }"/>
		    <label for="inputPassword" class="sr-only">密码</label>
		    <input type="password" 
		    	id="inputPassword" 
		    	class="form-control" 
		    	placeholder="登录密码" 
		    	required="required"
		    	name="password"/>
		    <input type="hidden"
				name="${_csrf.parameterName}"
				value="${_csrf.token}"/>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
		    <a class="btn btn-lg btn-primary btn-block" href="${ctx }/security/login">登录</a>
		</form>
	</div>
</body>
</html>