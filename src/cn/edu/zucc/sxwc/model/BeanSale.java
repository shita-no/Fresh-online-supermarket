package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanSale {//”≈ª›»Ø
	private String saleid;
	private String goodsid;
	private float  saleprice;
	private int saleamount;
	private Date bedate;
	private Date enddate;
	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public float getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(float saleprice) {
		this.saleprice = saleprice;
	}
	public int getSaleamount() {
		return saleamount;
	}
	public void setSaleamount(int saleamount) {
		this.saleamount = saleamount;
	}
	public Date getBedate() {
		return bedate;
	}
	public void setBedate(Date bedate) {
		this.bedate = bedate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
}
