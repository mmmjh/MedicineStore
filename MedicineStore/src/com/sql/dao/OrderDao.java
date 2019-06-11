package com.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sql.bean.OrderBean;
import com.sql.bean.StaffBean;
import com.sql.util.DBUtil;
import com.sql.util.Pager;

public class OrderDao {

	public OrderDao() {

	}

	// 删除订单信息
	public void delete(int id) {
		String sql = "delete from order1 where id1 =" + id;
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}

	// 更新订单信息
	public void update(int id,OrderBean orderBean) {
		String sql = "update order1 set orderId='" + orderBean.getOrderId() + "', staffId ='" + orderBean.getStaffId()
				+ "', money ='" + orderBean.getMoney() + "', createDate ='" + orderBean.getCreateDate()
				+ "', paytype ='" + orderBean.getPaytype() + "',ordertype='"+orderBean.getOrdertype()+"' where id1 ='" + id + "'";
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}

	// 添加订单信息
	public void add(OrderBean orderBean) {
		String sql = "insert into order1(orderId,staffId,money,createDate,paytype,ordertype,medi,num,purch,sell) values('" 
				+ orderBean.getOrderId() + "','" + orderBean.getStaffId() + "','" + orderBean.getMoney() + "','"
				+ orderBean.getCreateDate() + "','" + orderBean.getPaytype() + "','"+orderBean.getOrdertype()+"','"+orderBean.getMedi()+"',"+orderBean.getNum()+","+orderBean.getJinjia()+","+orderBean.getShoujia()+")";
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		System.out.println(sql+"添加订单");
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}

	/**
	 * 检查该id是否已经存在
	 * 
	 * @return 如果id存在，返回false，否则返回true
	 */
	public boolean checkId(int id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select id1 from order1");
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if (id == rs.getInt("id1"))
					System.out.println("成功找到");
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		System.out.println("mei找到");
		return true;
	}
	
	public OrderBean checkReg(String id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select * from order1");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				if (id.equals(resultSet.getString("orderId"))) {
					int idd = resultSet.getInt("id1");
					String orderId = resultSet.getString("orderId");
					int staffId = resultSet.getInt("staffId");
					float money = resultSet.getFloat("money");
					String createDate = resultSet.getString("createDate");
					int paytype=resultSet.getInt("paytype");
					int ordertype=resultSet.getInt("ordertype");
					String medi=resultSet.getString("medi");
					OrderBean orderBean = new OrderBean(idd,orderId,staffId,money,createDate,paytype,ordertype,medi);
					return orderBean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return null;
	}
	public OrderBean checkReg(int id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select * from order1");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {	
				if (id==resultSet.getInt("id1")) {
					int idd = resultSet.getInt("id1");
					String orderId = resultSet.getString("orderId");
					int staffId = resultSet.getInt("staffId");
					float money = resultSet.getFloat("money");
					String createDate = resultSet.getString("createDate");
					int paytype=resultSet.getInt("paytype");
					int ordertype=resultSet.getInt("ordertype");	
					String medi=resultSet.getString("medi");
					OrderBean orderBean = new OrderBean(idd,orderId,staffId,money,createDate,paytype,ordertype,medi);
					return orderBean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return null;
	}
	/**
	 * 列出全部订单信息
	 * 
	 * @return
	 */
	public List<OrderBean> getList() {
	//	System.out.println(new Date().toString() + "\tOrderDao.getList()执行开始！");
		String sql = "select * from order1";
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id1");
				String orderId = resultSet.getString("orderId");
				int staffId = resultSet.getInt("staffId");
				float money = resultSet.getFloat("money");
				String createDate = resultSet.getString("createDate");
				int paytype = resultSet.getInt("paytype");
				int ordertype=resultSet.getInt("ordertype");
				String medi=resultSet.getString("medi");
				int num=resultSet.getInt("num");
				int purch=resultSet.getInt("purch");
				float shou=resultSet.getFloat("sell");
				orderBeans.add(new OrderBean(id, orderId, staffId, money, createDate, paytype,ordertype,medi,num,purch,shou));
			}
			System.out.println(new Date().toString() + "\t查询成功！查询结果" + orderBeans.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return orderBeans;
	}
	public Pager<OrderBean> load(String content, int pageIndex, int pageSize) {
		Pager<OrderBean> pager = new  Pager<OrderBean>();
		Connection connection = DBUtil.getConnection();
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//准备sql语句
		String sql = "select * from order1 ";
		//数据总条数
		String  sqlCount = "select count(*) from order1";
		//集合中只能放入user对象
		List<OrderBean> users = new ArrayList<OrderBean>();
		OrderBean user = null;
		try {
			if (pageIndex <= 0) {
				pageIndex = 1;
			}
			
			int start = (pageIndex-1)*pageSize;
			
			if (content == null || "".equals(content)) {
				sql += "";
			}else {
				sql += " where createDate like '%" + content +"%' or money like '%" + content +"%'";
				sqlCount += " where createDate like '%" + content +"%'or money like '%" + content +"%'";
				
			}
			
			preparedStatement = connection.prepareStatement(sqlCount);
			resultSet = preparedStatement.executeQuery();
			
			//总记录数
			int totalRecord = 0;
			
			while(resultSet.next()) {
				totalRecord = resultSet.getInt(1);
			}	
			//总页数
			int totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
			
			//加分页
			sql += "limit ?,?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			System.out.println(sql+"模糊查询");
			while(resultSet.next()) {
				user = new OrderBean();
				user.setId(resultSet.getInt("id1"));
				user.setOrderId(resultSet.getString("orderId"));
				user.setStaffId(resultSet.getInt("staffId"));
				user.setMoney(resultSet.getFloat("money"));
				user.setCreateDate(resultSet.getString("createDate"));
				user.setOrdertype(resultSet.getInt("ordertype"));				
				users.add(user);
			}	
			//往分页对象里面设置数据
			pager.setDatas(users);
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			pager.setTotalPage(totalPage);
			pager.setTotalRecord(totalRecord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return pager;
	}

}
