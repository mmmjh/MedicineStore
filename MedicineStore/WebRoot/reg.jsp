<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="com.sql.dao.*"%>
<%@page import="com.sql.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//获取客户端传递过来的参数
	request.setCharacterEncoding("UTF-8");
	String studentname = request.getParameter("username");
	String password = request.getParameter("password");
	UserDao st=new UserDao();
	UserBean user=new UserBean();
	user.setUsername( studentname);
	user.setUserpaw(password);
	st.add(user);
	int pan=-1;
	pan=st.check(user);
	if(pan==0){
%>
<jsp:forward page="error.jsp"></jsp:forward>  
<%}else {%>
	<jsp:forward page="login.jsp"></jsp:forward>  
	<%} %>

	
