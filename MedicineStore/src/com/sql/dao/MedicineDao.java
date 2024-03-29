package com.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sql.bean.MedicineBean;
import com.sql.bean.StaffBean;
import com.sql.util.DBUtil;

public class MedicineDao {

	public MedicineDao() {

	}

	// 删除药品信息
	public static void delete(int id) {
		String sql = "delete from medicine where id1 =" + id;

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

	// 更新药品信息
	public void update(int id,MedicineBean medicineBean) {
		String sql = "update medicine set medicineName='" + medicineBean.getMedicineName() + "', medicineId ='"
				+ medicineBean.getMedicineId() + "', medicinetype ='" + medicineBean.getType() + "', inventory ='"
				+ medicineBean.getInventory() + "', producer ='" + medicineBean.getProducer() + "', purchasingPrice ='"
				+ medicineBean.getPurchasingPrice() + "', sellingPrice ='" + medicineBean.getSellingPrice()
				+ "' where id1='" + id + "'";
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
	
	// 更新药品信息
		public void update1(String name,int inventory) {
			String sql = "update medicine set inventory='" + inventory + "'where medicineName='"+name+"'";
			Connection connection = (Connection) DBUtil.getConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
				System.out.println(sql+"更新库存");
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			}
		}

	// 添加药品信息
	public void add(MedicineBean medicineBean) {
		String sql = "insert into medicine(medicineName,medicineId,medicinetype,inventory,producer,purchasingPrice,sellingPrice) values('"
				+ "" + medicineBean.getMedicineName() + "','" + medicineBean.getMedicineId() + "','"
				+ medicineBean.getType() + "','" + medicineBean.getInventory() + "','" + medicineBean.getProducer()
				+ "','" + medicineBean.getPurchasingPrice() + "','" + medicineBean.getSellingPrice() + "')";
		Connection connection = (Connection) DBUtil.getConnection();
		System.out.println(sql+"药品插入");
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate();
			//System.out.println(new Date().toString() + "\t" + medicineBean.getMedicineName() + "保存成功！");
		} catch (Exception e) {
			//System.out.println(new Date().toString() + "\t" + medicineBean.getMedicineName() + "保存失败！");
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
	
	public MedicineBean checkReg(int id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select * from medicine");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (id == resultSet.getInt("id1")) {
					int medicineid = resultSet.getInt("medicineid");
					String medicinename=resultSet.getString("medicinename");
					int medicinetype = resultSet.getInt("medicinetype");
					int inventory = resultSet.getInt("inventory");
					String producer=resultSet.getString("producer");
				    float purchasingprice=resultSet.getFloat("purchasingprice");
					float sellingprice = resultSet.getFloat("sellingprice");
					MedicineBean medicineBean = new MedicineBean(id, medicineid, medicinename, medicinetype, inventory, producer, purchasingprice, sellingprice);
					return medicineBean;
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
	public MedicineBean findmedicine(String name) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select * from medicine");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (name.equals(resultSet.getString("medicineName"))) {
					int medicineid = resultSet.getInt("medicineid");
					String medicinename=resultSet.getString("medicineName");
					int medicinetype = resultSet.getInt("medicinetype");
					int inventory = resultSet.getInt("inventory");
					String producer=resultSet.getString("producer");
				    float purchasingprice=resultSet.getFloat("purchasingprice");
					float sellingprice = resultSet.getFloat("sellingprice");
					MedicineBean medicineBean = new MedicineBean(1, medicineid, medicinename, medicinetype, inventory, producer, purchasingprice, sellingprice);
					return medicineBean;
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
	public boolean checkId(int id) {
		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select id1 from medicine ");
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				if (id == rs.getInt("id1"))
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

	/**
	 * 列出全部药品信息
	 * 
	 * @return
	 */
	public List<MedicineBean> getList() {
		System.out.println(new Date().toString() + "\tMeidcineDao.getList()执行开始！");
		String sql = "select * from medicine";

		Connection connection = (Connection) DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<MedicineBean> medicineBeans = new ArrayList<MedicineBean>();

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id1");
				String medicineName = resultSet.getString("medicineName");
				System.out.println(medicineName+"药品名称");
				int medicineId = resultSet.getInt("medicineId");
				int type = resultSet.getInt("medicinetype");
				int inventory = resultSet.getInt("inventory");
				String producer = resultSet.getString("producer");
				float purchasingPrice = resultSet.getFloat("purchasingPrice");
				float sellingPrice = resultSet.getFloat("sellingPrice");
				medicineBeans.add(new MedicineBean(id, medicineId, medicineName, type, inventory, producer,
						purchasingPrice, sellingPrice));
			}
			System.out.println(new Date().toString() + "\t马佳慧查询成功！查询结果" + medicineBeans.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return medicineBeans;
	}
}
