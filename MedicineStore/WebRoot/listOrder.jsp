<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.sql.dao.*"%>
<%@page import="com.sql.bean.*"%>
<%@page import="com.sql.util.Pager"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看订单信息</title>
<link href="Style/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="Script/jBox/Skins/Blue/jbox.css" rel="stylesheet"
	type="text/css" />
<link href="Style/ks.css" rel="stylesheet" type="text/css" />
<script src="Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="Script/Common.js" type="text/javascript"></script>
<script src="Script/Data.js" type="text/javascript"></script>
</head>
<%
	//筛选
	String content = request.getParameter("content");
	if(content == null || "".equals(content)){
		content = "";
	}
	//分页的条件
	int pageIndex = 1;
	int pageSize = 6;
	try{
		pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
	}catch(Exception e){
	}
	OrderDao userDao = new OrderDao();
// 	List<User> users = userDao.load(content);
	Pager pager = userDao.load(content, pageIndex, pageSize);
	//获得pager中保存的list集合
	List<OrderBean> users = pager.getDatas();
%>
<body>
	<div class="rightbox">
		<h2 class="mbx">订单中心 &gt; 订单信息 &nbsp;&nbsp;&nbsp;</h2>
		<div class="morebt">
			<div class="cztable">
				<table width="1100px" cellpadding="0" cellspacing="0">
					<tr>
						<form action="listOrder.jsp" method="post">
							<td colspan="7">
								请输入用户名或昵称 : &nbsp;
								<input type="text" name="content" size="70"style="border-radius:3px;"> &nbsp; 
								<input type="submit" value="搜索"  style="width:200px;border-radius:9px;height:25px;margin-left:30px" onmouseover="this.style.backgroundColor='#99CC99';" onmouseout="this.style.backgroundColor='';" />
							</td>
						</form>
					</tr>
					<tr>
			<td colspan="7">
				一共有<%=pager.getTotalRecord() %>条数据 , 每页显示<%=pager.getPageSize() %>条数据
				页码 : <%=pageIndex %> / <%=pager.getTotalPage() %>
			</td>
		</tr>
					<tr>
						<td align="center"  width="115">订单编号</td>
						<td align="center"  width="115">订单类型</td>
						<td align="center">售货员编号</td>
						<td align="center">总金额</td>
						<td align="center">日期</td>
						<td align="center">缴费类型</td>
						<td align="center">操作</td>
					</tr>
					<!-- forEach遍历出orderBeans -->
				<%for( OrderBean user : users ){ %>
						<tr>
							<td align="center"><%=user.getOrderId() %></td>
								<td align="center"><%if(user.getOrdertype()==0)%>进货
																<%if(user.getOrdertype()==1)%>售出</td>
							<td align="center"><%=user.getStaffId() %></td>
							<td align="center"><%=user.getMoney() %></td>
							<td align="center"><%=user.getCreateDate()%></td>
							<td align="center"><%if(user.getPaytype()==0) %>现金
									<%if(user.getPaytype()==1) %>信用卡
									<%if(user.getPaytype()==2) %>网上缴费</td>
							<td align="center">
								<a href="updateorder.jsp?method=update&id=<%=user.getId()%>">修改</a>
								<a href="OrderServlet?method=delete&id=<%=user.getId()%>">删除</a>
								</td>
						</tr>
						<%} %>
			
					<tr>
			<td colspan="7" align="center">
				<a href="listOrder.jsp?pageIndex=1&content=<%=content %>">首页</a>
				
				<!-- 如果是第一页的话 , 就不显示 上一页 -->
				<%
					if(pageIndex > 1){
				%>
					<a href="listOrder.jsp?pageIndex=<%=pageIndex-1 %>&content=<%=content %>">上一页</a>
				<%
					}
				%>
				<!-- 页码的显示 , 如果是当前页 , 就不加超链接, 只是显示就可以 , 其他都加上超链接 -->
				<%
					//先获得总页数,对他进行for循环遍历
					int totalPage = pager.getTotalPage();
					for(int i = 1 ; i <=totalPage ; i++){
						if(pageIndex == i){
				%>	
						<%=i %>
					<%
						}else{
					%>		
						<a href="listOrder.jsp?pageIndex=<%=i%>&content=<%=content %>"><%=i %></a>
				<%
						}
					}	
				%>	
				<%
					if( pageIndex < totalPage){
				%>
					<a href="listOrder.jsp?pageIndex=<%=pageIndex + 1 %>&content=<%=content %>">下一页</a>
				<%
					}
				%>
				<a href="listOrder.jsp?pageIndex=<%=totalPage%>&content=<%=content %>">尾页</a>
			</td>
		
		
		
		</tr>
				</table>
				<p>&nbsp;</p>
				<div align="center">
					<input type="button" id="button2" value="添加订单"
						onclick="javascript:window.location.href='addOrder.jsp'"
						class="input2"  style="width:400px;border-radius:9px;height:35px;margin-left:-30px" onmouseover="this.style.backgroundColor='#99CC99';" onmouseout="this.style.backgroundColor='';"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
