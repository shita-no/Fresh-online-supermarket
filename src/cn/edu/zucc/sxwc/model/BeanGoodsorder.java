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
	public static final String[] GoodsorderTitles={"�������","���͵�ַ���","�Ż�ȯ���","ԭʼ���","������","Ҫ���ʹ�ʱ��","����״̬"};
	public static final String[] GoodsorderTitles1={"�������","���͵�ַ���","�Ż�ȯ���","�û����","ԭʼ���","������","Ҫ���ʹ�ʱ��","����״̬"};
	public String getCell(int col){
		if(col==0) return String.valueOf(orderid);
		else if(col==1) return String.valueOf(addid);
		else if(col==2) return String.valueOf(couponid);
		else if(col==3) return String.valueOf(beprice);
		else if(col==4) return String.valueOf(endprice);	
		else if(col==5) return String.valueOf(arrivetime);
		else if(col==6) return String.valueOf(state);
		else return "";
	}
	public String getCell1(int col){
		if(col==0) return String.valueOf(orderid);
		else if(col==1) return String.valueOf(addid);
		else if(col==2) return String.valueOf(couponid);
		else if(col==3) return String.valueOf(userid);
		else if(col==4) return String.valueOf(beprice);
		else if(col==5) return String.valueOf(endprice);	
		else if(col==6) return String.valueOf(arrivetime);
		else if(col==7) return String.valueOf(state);
		else return "";
	}
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
