package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanGoodsorder {//商品订单表
	private int orderid;//订单编号
	private String addid;//配送地址编号
	private String couponid;//优惠券编号
	private String userid;//用户编号
	private Float beprice;//原始金额
	private Float endprice;//结算金额
	private Date arrivetime;//要求送达时间
	private String state;//订单状态（下单，配送，送达，退货）
	public static final String[] GoodsorderTitles={"订单编号","配送地址编号","优惠券编号","原始金额","结算金额","要求送达时间","订单状态"};
	public static final String[] GoodsorderTitles1={"订单编号","配送地址编号","优惠券编号","用户编号","原始金额","结算金额","要求送达时间","订单状态"};
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
