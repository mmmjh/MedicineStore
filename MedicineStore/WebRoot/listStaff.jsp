<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看药品信息</title>
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
		<h2 class="mbx">员工信息  &nbsp;&nbsp;&nbsp;</h2>
		<div class="morebt">
			<div class="cztable" style="width:1100px">
				<table width="1100px" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">序号</td>
						<td align="center">员工编号</td>
						<td align="center" width="115">姓名</td>
						<td align="center"">职位</td>
						<td align="center"">操作</td>
					</tr>
					<!-- forEach遍历出medicineBeans -->
					<%int m=1; %>
					<c:forEach items="${staffBeans}" var="item" varStatus="status">
						<tr>
							<td align="center"><%=m %></td>		
							<td align="center">${item.staffId}</a></td>
							<td align="center">${item.staffName}</td>
							<td align="center">${item.duty}</td>
							<td align="center">
								<a href="infor.jsp?method=delete&id=${item.staffId}">查看详细信息</a>
								<a href="updateStaff.jsp?method=update&id=${item.staffId}">修改</a>
								<a href="StaffServlet?method=delete&id=${item.staffId}">删除</a>
							</td>
						</tr>
					<%m++; %>
					</c:forEach>
				</table>
				<p>&nbsp;</p>
				<div align="center">
					<input type="button" id="button2" value="添加员工"
						onclick="javascript:window.location.href='addStaff.jsp'"
						class="input2"  style="width:400px;border-radius:9px;height:35px;margin-left:-20px" onmouseover="this.style.backgroundColor='#99CC99';" onmouseout="this.style.backgroundColor='';" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
