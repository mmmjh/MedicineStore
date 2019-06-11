<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.sql.dao.MedicineDao"%>
<%@page import="com.sql.dao.StaffDao"%>
<%@page import="java.util.List"%>
<%@page import="com.sql.bean.MedicineBean"%>
<%@page import="com.sql.bean.StaffBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加订单信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
MedicineDao contImp=new MedicineDao();
List<MedicineBean> cons=contImp.getList();   //药品列表
StaffDao scontImp=new StaffDao();
List<StaffBean> conss=scontImp.getList();//员工编号列表
SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
SimpleDateFormat df1= new SimpleDateFormat("yyyyMMdd");//设置日期格式
System.out.println(df1.format(new Date()));// new Date()为获取当前系统时间

%>
<body>
	<div class="rightbox">
		<h2 class="mbx">订单中心 &gt; 订单信息 &nbsp;&nbsp;&nbsp;</h2>
		<div class="morebt">
			<div class="cztable">
				<form action="OrderServlet?method=add" method="post">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right" width="115">订单编号</td>
							<td><input type="text" name="orderId" id="orderId" value=<%=df.format(new Date()) %>></td>
						</tr>
						<tr>
							<td align="right">员工编号</td>
							<td><lable>
								
									<select name="staffId" id="staffId"  style="width:145px;margin-left:0px">
									<%
									for(int i=0;i<conss.size();i++){
									%>								
											  <option><%=conss.get(i).getStaffId() %></option>		
											  <%} %>					
									</select>
							
								</lable></td>
						</tr>
						<tr>
							<td align="right">售出药品</td>
							<td><lable>
								
									<select name="medicine" id="medicine"  style="width:145px;margin-left:0px">
									<%
									for(int i=0;i<cons.size();i++){
									%>								
											  <option><%=cons.get(i).getMedicineName() %></option>		
											  <%} %>					
									</select>
							
								</lable></td>
						</tr>
						<tr>
							<td align="right">数量</td>
							<td><lable>
								
									<select name="num" id="num"  style="width:145px;margin-left:0px">
									<%
									for(int i=1;i<20;i++){
									%>								
											  <option><%=i %></option>		
											  <%} %>					
									</select>
							
								</lable></td>
						</tr>
						
						<tr>
							<td align="right">订单日期</td>
							<td><input type="text" name="createDate" id="createDate" value=<%=df1.format(new Date())%>></td>
						</tr>
						<tr>
							<td align="right">缴费方式</td>
							<td><lable>
								
									<select name="paytype" id="paytype" style="width:145px;margin-left:0px">
										<option>现金</option>
										<option>信用卡</option>
										<option>网上缴费</option>
									</select>
								
								</lable></td>
						</tr>
						<tr>
							<td align="right">订单类型</td>
							<td><lable>
								
									<select name="ordertype" id="ordertype" style="width:145px;margin-left:0px">
										<option>售出</option>
										<option>进货</option>
									</select>
							
								</lable></td>
								<tr>
							<td align="right">总金额</td>
							<td ><input type="text" name="money" id="money"></td>
						</tr>
						</tr>
					</table>
					<p>&nbsp;</p>
					<div align="center">
						<input type="submit" id="button" value="提交"  style="width:400px;border-radius:9px;height:35px;margin-left:-30px" onmouseover="this.style.backgroundColor='#99CC99';" onmouseout="this.style.backgroundColor='';" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
