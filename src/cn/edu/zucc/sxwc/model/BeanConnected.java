package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanConnected {//������Ʒ������
	private String discountid;//���۱��
	private String goodsid;//��Ʒ���
	private Date bedate;
	private Date endate;
	public String getDiscountid() {
		return discountid;
	}
	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public Date getBedate() {
		return bedate;
	}
	public void setBedate(Date bedate) {
		this.bedate = bedate;
	}
	public Date getEndate() {
		return endate;
	}
	public void setEndate(Date endate) {
		this.endate = endate;
	}
	
	
}
