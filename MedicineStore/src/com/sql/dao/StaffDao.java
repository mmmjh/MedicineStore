package com.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sql.util.Pager;
import com.sql.bean.StaffBean;
import com.sql.util.DBUtil;

public class StaffDao {

	public StaffDao() {

	}

	// 删除个人信息
	public void delete(int id) {
		String sql = "delete from staff where staffId =" + id;
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

	// 更新个人信息
	public void update(int id, StaffBean staffBean) {
		String sql = "update staff set staffName='" + staffBean.getStaffName() +"', born='"+staffBean.getBorn()+
				"',edu='"+staffBean.getEdu()+"',major='"+staffBean.getMajor()+"',degree1='"+staffBean.getDegree1()+
				"',loca='"+staffBean.getLoca()+"',email='"+staffBean.getEmail()+"',pict='"+
				staffBean.getPict()+"',nation='"+staffBean.getNation()+"', staffId='" + staffBean.getStaffId()
				+ "', sex='" + staffBean.getSex() + "', age='" + staffBean.getAge() + "', tel='" + staffBean.getTel()
				+ "', duty='" + staffBean.getDuty() + "', workingAge='" + staffBean.getWorkingAge() + "' where id="
				+ id;
		System.out.println(sql);
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

	// 添加个人信息
	@SuppressWarnings("deprecation")
	public void add(StaffBean staffBean) {
		String sql = "insert into staff(staffName,staffId,sex,age,tel,duty,workingAge) values('" + ""
				+ staffBean.getStaffName() + "','" + staffBean.getStaffId() + "','" + staffBean.getSex() + "','"
				+ staffBean.getAge() + "','" + staffBean.getTel() + "','" + staffBean.getDuty() + "','"
				+ staffBean.getWorkingAge() + "')";
		System.out.println("开始添加：" + "\t" + sql);
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(new Date().toString() + "\t" + staffBean.getStaffName() + "保存失败！");
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}

	/**
	 * 检查该用户id是否已经存在，当用户注册的时候调用
	 * 
	 * @param name
	 *            用户名
	 * @return 如果用户名存在，返回false，否则返回true
	 */
	public StaffBean checkReg(int id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select * from staff");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (id == resultSet.getInt("staffId")) {
					String staffName = resultSet.getString("staffName");
					int staffId = resultSet.getInt("staffId");
					String sex = resultSet.getString("sex");
					int age = resultSet.getInt("age");
					String duty = resultSet.getString("duty");
					String tel = resultSet.getString("tel");
					float workingAge = resultSet.getFloat("workingAge");
					String born = resultSet.getString("born");
					String nation = resultSet.getString("nation");
					String major = resultSet.getString("major");
					String edu = resultSet.getString("edu");
					String degree1 = resultSet.getString("degree1");
					String loca = resultSet.getString("loca");
					String email = resultSet.getString("email");
					String pict = resultSet.getString("pict");
					StaffBean staffBean = new StaffBean(id, staffId, staffName, sex, age, tel, duty, workingAge,born,nation,major,edu,degree1,loca,email,pict);
					return staffBean;
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
	 * 检查该id是否已经存在，当用户注册的时候调用
	 * 
	 * @return 如果用户名存在，返回false，否则返回true
	 */
	public boolean checkId(int id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select id from staff");
			rs= preparedStatement.executeQuery();
			
			while (rs.next()) {
				if (id == rs.getInt("id"))
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return true;
	}

	public boolean checkStaffId(int id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select staffId from staff");
			rs= preparedStatement.executeQuery();
			while (rs.next()) {
				if (id == rs.getInt("id"))
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return true;
	}

	public List<StaffBean> getList() {
		String sql = "select * from staff";
		System.out.println(sql+"验证员工");
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<StaffBean> staffBeans = new ArrayList<StaffBean>();

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			resultSet= preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String staffName = resultSet.getString("staffName");
				int staffId = resultSet.getInt("staffId");
				String sex = resultSet.getString("sex");
				int age = resultSet.getInt("age");
				String duty = resultSet.getString("duty");
				String tel = resultSet.getString("tel");
				float workingAge = resultSet.getFloat("workingAge");
				String born = resultSet.getString("born");
				String nation = resultSet.getString("nation");
				String major = resultSet.getString("major");
				String edu = resultSet.getString("edu");
				String degree1 = resultSet.getString("degree1");
				String loca = resultSet.getString("loca");
				String email = resultSet.getString("email");
				String pict = resultSet.getString("pict");
				staffBeans.add(new StaffBean(id, staffId, staffName, sex, age, tel, duty, workingAge,born,nation,major,edu,degree1,loca,email,pict));
			}
			//System.out.println(new Date().toString() + "\t查询成功！查询结果" + staffBeans.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return staffBeans;
	}
	
	public Pager<StaffBean> load(String content, int pageIndex, int pageSize) {
		Pager<StaffBean> pager = new  Pager<StaffBean>();
		Connection connection = DBUtil.getConnection();
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//准备sql语句
		String sql = "select * from staff ";
		//数据总条数
		String  sqlCount = "select count(*) from staff";
		//集合中只能放入user对象
		List<StaffBean> users = new ArrayList<StaffBean>();
		StaffBean user = null;
		try {
			if (pageIndex <= 0) {
				pageIndex = 1;
			}
			
			int start = (pageIndex-1)*pageSize;
			
			if (content == null || "".equals(content)) {
				sql += "";
			}else {
				sql += " where staffName like '%" + content +"%' or sex like '%" + content +"%'";
				
				sqlCount += " where staffName like '%" + content +"%'or sex like '%" + content +"%'";
				
			}
			
			preparedStatement = connection.prepareStatement(sqlCount);
			resultSet = preparedStatement.executeQuery();
			
			//总记录数
			int totalRecord = 0;
			
			while(resultSet.next()) {
				totalRecord = resultSet.getInt(1);
				System.out.println(totalRecord+"总");
			}	
			//总页数
			int totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
			
			//加分页
			sql += "limit ?,?";
			
			
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, pageSize);
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		//	preparedStatement = connection.prepareStatement(sql);
			System.out.println(sql+"出错了");
			resultSet = preparedStatement.executeQuery();
			System.out.println("出错了1");
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setStaffId(resultSet.getInt("staffId"));
				user.setStaffName(resultSet.getString("staffName"));
				user.setSex(resultSet.getString("sex"));
				user.setAge(resultSet.getInt("age"));
				user.setTel(resultSet.getString("tel"));
				System.out.print(resultSet.getString("tel")+"电话");
				user.setDuty(resultSet.getString("duty"));
				user.setWorkingAge(resultSet.getFloat("workingAage"));
				user.setBorn(resultSet.getString("born"));
				user.setNation(resultSet.getString("nation"));
				user.setEdu(resultSet.getString("edu"));
				user.setDegree1(resultSet.getString("degree1"));
				user.setMajor(resultSet.getString("major"));
				user.setLoca(resultSet.getString("loca"));
				user.setLoca(resultSet.getString("email"));
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
