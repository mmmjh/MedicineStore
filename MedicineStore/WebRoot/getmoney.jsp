<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.sql.dao.*"%>
<%@page import="com.sql.bean.*"%>
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
<body>
	<div class="rightbox">
		<h2 class="mbx">订单中心 &gt; 订单信息 &nbsp;&nbsp;&nbsp;</h2>
		<div class="morebt">
			<div class="cztable">
				<table width="1100px" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center"  width="115">订单编号</td>
						<td align="center">订单类型</td>
						<td align="center">进价</td>
						<td align="center">数量</td>
						<td align="center">售价</td>
						<td align="center">盈利</td>
					</tr>
					<!-- forEach遍历出orderBeans -->
					<%
					OrderDao or=new OrderDao();
					List<OrderBean> conss=or.getList();
					float allmoney=0;
					for(int i=0;i<conss.size();i++){
					%>
						<tr>
							<td align="center"><%=conss.get(i).getOrderId()  %></td>
							<td align="center"><%if(conss.get(i).getOrdertype()==0)%>进货
																<%if(conss.get(i).getOrdertype()==1)%>售出</td>
							<td align="center"><%=conss.get(i).getJinjia()  %></td>
							<td align="center"><%=conss.get(i).getNum()  %></td>
							<td align="center"><%=conss.get(i).getShoujia()  %></td>
							<%if(conss.get(i).getOrdertype()==1)	{%>
								<td align="center"><%=(conss.get(i).getShoujia()-conss.get(i).getJinjia())*conss.get(i).getNum()%></td>
								<%allmoney+=(conss.get(i).getShoujia()-conss.get(i).getJinjia())*conss.get(i).getNum();} %>
								<%if(conss.get(i).getOrdertype()==0)	{%>
								<td align="center"><%=(-conss.get(i).getJinjia())*conss.get(i).getNum()%></td>
								<% allmoney+=-conss.get(i).getJinjia()*conss.get(i).getNum();} %>
						<%} %>
						</tr>
					<tr>
					<td align="center">总盈利</td>
					<td colspan="5" align="right"><%=allmoney %></td>
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
