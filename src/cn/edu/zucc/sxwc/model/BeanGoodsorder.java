package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanGoodsorder {//��Ʒ������
	private int orderid;//�������
	private String addid;//���͵�ַ���
	private String couponid;//�Ż�ȯ���
	private String userid;//�û����
	private Float beprice;//ԭʼ���
	private Float endprice;//������
	private Date arrivetime;//Ҫ���ʹ�ʱ��
	private String state;//����״̬���µ������ͣ��ʹ�˻���
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getAddid() {
		return addid;
	}
	public void setAddid(String addid) {
		this.addid = addid;
	}
	public String getCouponid() {
		return couponid;
	}
	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Float getBeprice() {
		return beprice;
	}
	public void setBeprice(Float beprice) {
		this.beprice = beprice;
	}
	public Float getEndprice() {
		return endprice;
	}
	public void setEndprice(Float endprice) {
		this.endprice = endprice;
	}
	public Date getArrivetime() {
		return arrivetime;
	}
	public void setArrivetime(Date arrivetime) {
		this.arrivetime = arrivetime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
