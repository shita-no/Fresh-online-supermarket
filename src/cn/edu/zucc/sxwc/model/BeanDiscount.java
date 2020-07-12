package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanDiscount {//满折信息表+满折商品关联表
	public static final String[] DiscountTitles={"满折编号","类别编号","商品编号","适用商品数量","折扣","内容","起始日期","结束日期"};
	private String discountid;//满折编号
	private String dccontent;//内容
	private int syamount;//适用商品数量
	private Float discount;//折扣
	private Date bedate;
	private Date enddate;
	private String goodsid;
	private String lbid;
	public String getCell(int col){
		if(col==0) return String.valueOf(discountid);
		else if(col==1) return String.valueOf(lbid);
		else if(col==2) return String.valueOf(goodsid);
		else if(col==3) return String.valueOf(syamount);
		else if(col==4) return String.valueOf(discount);
		else if(col==5) return String.valueOf(dccontent);	
		else if(col==6) return String.valueOf(bedate);
		else if(col==7) return String.valueOf(enddate);
		else return "";
	}
	public String getLbid() {
		return lbid;
	}
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getDiscountid() {
		return discountid;
	}
	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}
	public String getDccontent() {
		return dccontent;
	}
	public void setDccontent(String dccontent) {
		this.dccontent = dccontent;
	}
	public int getSyamount() {
		return syamount;
	}
	public void setSyamount(int syamount) {
		this.syamount = syamount;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
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
