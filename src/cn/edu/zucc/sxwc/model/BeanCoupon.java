package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanCoupon {//ÓÅ»İÈ¯
	private String couponid;//ÓÅ»İÈ¯±àºÅ
	private String coucontent;//ÄÚÈİ
	private Float symoney;//ÊÊÓÃ½ğ¶î
	private Float jmmoney;//¼õÃâ½ğ¶î
	private Date bedate;
	private Date enddate;
	public String getCouponid() {
		return couponid;
	}
	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}
	public String getCoucontent() {
		return coucontent;
	}
	public void setCoucontent(String coucontent) {
		this.coucontent = coucontent;
	}
	public Float getSymoney() {
		return symoney;
	}
	public void setSymoney(Float symoney) {
		this.symoney = symoney;
	}
	public Float getJmmoney() {
		return jmmoney;
	}
	public void setJmmoney(Float jmmoney) {
		this.jmmoney = jmmoney;
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
