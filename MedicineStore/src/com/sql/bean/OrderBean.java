package com.sql.bean;

public class OrderBean {

	private int id;
	private String orderId;
	private int staffId;// 售货员
	private float money;
	private String createDate;// 日期
	private String medi;// 日期
	private int paytype;// 付账类型
	private int ordertype;
	private int num;// 售货员
	private float jinjia;
	private float shoujia;
	
	public float getShoujia() {
		return shoujia;
	}

	public void setShoujia(float shoujia) {
		this.shoujia = shoujia;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getJinjia() {
		return jinjia;
	}

	public void setJinjia(float jinjia) {
		this.jinjia = jinjia;
	}

	public String getMedi() {
		return medi;
	}

	public void setMedi(String medi) {
		this.medi = medi;
	}

	public int getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
	}

	public OrderBean() {
		super();
	}

	public OrderBean(int id, String orderId, int staffId, float money, String createDate, int paytype,int ordertype,String medi) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.staffId = staffId;
		this.money = money;
		this.createDate = createDate;
		this.paytype = paytype;
		this.ordertype = ordertype;
		this.medi=medi;
	}
	public OrderBean(int id, String orderId, int staffId, float money, String createDate, int paytype,int ordertype,String medi,int num ,float purch,float shou) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.staffId = staffId;
		this.money = money;
		this.createDate = createDate;
		this.paytype = paytype;
		this.ordertype = ordertype;
		this.medi=medi;
		this.num=num;
		this.jinjia=purch;
		this.shoujia=shou;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

}
