package com.sql.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sql.bean.OrderBean;
import com.sql.bean.OrderBean;
import com.sql.util.Pager;
import com.sql.util.StringUtil;
import com.sql.bean.MedicineBean;
import com.sql.dao.*;
/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		/*
		 * 每一个调用次 Servlet 的地方都需要传递一个method参数用来指明所要经进行的操作
		 */
		String method = request.getParameter("method");
		HttpSession session = request.getSession();
		// 根据method的值调用不同的方法执行不同的操作
		if ("update".equals(method)) {// 修改订单信息
			update(request, response);
		} else if ("delete".equals(method)) {// 删除订单信息
			delete(request, response);
		} else if ("add".equals(method)) {// 添加订单
			add(request, response);
		} else if ("list".equals(method)) {// 查看全部订单信息
			listOrder(request, response);
		}
	}

	/**
	 * 添加订单信息
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.setCharacterEncoding("utf-8");
		OrderDao orderDao = new OrderDao();		
		int staffId = StringUtil.StringToInt(req.getParameter("staffId"));
		String orderId = req.getParameter("orderId");
		String medicine = req.getParameter("medicine");
		int num = StringUtil.StringToInt(req.getParameter("num"));
		//float danjia = StringUtil.StringToFloat(req.getParameter("mon"));
		System.out.println("订单"+orderId);
		float money = StringUtil.StringToFloat(req.getParameter("money"));
		String createDate = req.getParameter("createDate");
		String paytype = req.getParameter("paytype");
		//更新库存
		MedicineDao medao1=new MedicineDao();
		MedicineBean me=new MedicineBean();
		me=medao1.findmedicine(medicine);
		System.out.println(me.getInventory()+"库存");
		int kucun=me.getInventory();
		int paytypee = 0;//现金
		if ("信用卡".equals(paytype)) {
			paytypee = 1;
		} else if ("网上缴费".equals(paytype)) {
			paytypee = 2;
		}
		String ordertypeget = req.getParameter("ordertype");
		int ordertype = 1;//进货
		if ("售出".equals(ordertypeget)) {//售出
			ordertype = 0;
		} 
		if(ordertype==1) {
			kucun=kucun+num;
		}
		if(ordertype==0) {
			kucun=kucun-num;
		}
		// 创建一个订单对象并且设置用户的各个属性
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(orderId);
		orderBean.setStaffId(staffId);
		orderBean.setMedi(medicine);
		orderBean.setMoney(money);
		orderBean.setNum(num);
		orderBean.setJinjia(me.getPurchasingPrice());
		orderBean.setShoujia(me.getSellingPrice());
		orderBean.setCreateDate(createDate);
		orderBean.setPaytype(paytypee);
		orderBean.setOrdertype(ordertype);
		orderDao.add(orderBean);
		System.out.println("订单添加成功");
		
		MedicineDao medao=new MedicineDao();
		medao.update1(medicine, kucun);
		resp.sendRedirect("OrderServlet?method=list&status=2");
	}

	/**
	 * 删除一个订单，需要指定参数为订单的id
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(new Date().toString() + "\tOrderServlet.delete()开始执行！");
		request.setCharacterEncoding("UTF-8");
		int id = StringUtil.StringToInt(request.getParameter("id"));
		System.out.println(new Date().toString() + "\tid = " + id);
		// id 为1的作为超级管理员是不可以删除的
		
			OrderDao orderDao = new OrderDao();
			orderDao.delete(id);
			// 响应重定向查看用户列表，状态status=3表示删除指定id的用户成功
			response.sendRedirect("OrderServlet?method=list&status=2");
	}

	/**
	 * 更新订单信息
	 */
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(new Date().toString() + "\tOrderServlet.update()开始执行");

		req.setCharacterEncoding("utf-8");

		int id = StringUtil.StringToInt(req.getParameter("id"));
		
		OrderDao orderDao = new OrderDao();
		String orderId = req.getParameter("orderId");
		int staffId = StringUtil.StringToInt(req.getParameter("staffId"));
		float money = StringUtil.StringToFloat(req.getParameter("money"));
		String createDate = req.getParameter("createDate");
		int paytype = StringUtil.StringToInt(req.getParameter("paytype"));
	/*	int paytypee = 0;
		if ("信用卡".equals(paytype)) {
			paytypee = 1;
		} else if ("网上缴费".equals(paytype)) {
			paytypee = 2;
		}*/
		System.out.println(id+"出错了！");

		// 创建一个订单对象并且设置用户的各个属性
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(orderId);
		orderBean.setStaffId(staffId);
		orderBean.setMoney(money);
		orderBean.setCreateDate(createDate);
		orderBean.setPaytype(paytype);
		boolean flag = true;
		// 判断用户orderId是否存在
		flag = orderDao.checkId(id);
		if (!flag) {// 修改成功
		//	System.out.println("开始更新！");
			orderDao.update(id,orderBean);
			resp.sendRedirect("OrderServlet?method=list&status=2");
		}
	}

	/*
	 * 查看药品信息
	 */
	private void listOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub req.setCharacterEncoding("utf-8");

		OrderDao orderDao = new OrderDao();
		List<OrderBean> orderBeans = orderDao.getList();
		//Pager pager = orderDao.load(content, pageIndex, pageSize);
		req.setAttribute("orderBeans", orderBeans);
		req.getRequestDispatcher("listOrder.jsp").forward(req, resp);
	}
}
