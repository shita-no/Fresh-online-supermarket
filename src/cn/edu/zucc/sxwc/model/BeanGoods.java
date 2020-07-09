package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanGoods {//商品信息
	public static final String[] GoodsTitles={"商品编号","类别编号","商品名称","商品价格","会员价","数量","规格","详情"};
	private String goodsid;//商品编号
	private String lbid;//类别编号
	private String goodsname;//商品名称
	private Float gprice;//价格
	private Float gvipprice;//会员价
	private int gamount;//数量
	private String guige;//规格
	private String datails;//详情
	public String getCell(int col){
		if(col==0) return String.valueOf(goodsid);
		else if(col==1) return String.valueOf(lbid);
		else if(col==2) return String.valueOf(goodsname);
		else if(col==3) return String.valueOf(gprice);
		else if(col==4) return String.valueOf(gvipprice);	
		else if(col==5) return String.valueOf(gamount);
		else if(col==6) return String.valueOf(guige);
		else if(col==7) return String.valueOf(datails);
		else return "";
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getLbid() {
		return lbid;
	}
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public Float getGprice() {
		return gprice;
	}
	public void setGprice(Float gprice) {
		this.gprice = gprice;
	}
	public Float getGvipprice() {
		return gvipprice;
	}
	public void setGvipprice(Float gvipprice) {
		this.gvipprice = gvipprice;
	}
	public int getGamount() {
		return gamount;
	}
	public void setGamount(int gamount) {
		this.gamount = gamount;
	}
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public String getDatails() {
		return datails;
	}
	public void setDatails(String datails) {
		this.datails = datails;
	}
}
