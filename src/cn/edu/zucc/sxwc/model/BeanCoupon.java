package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanCoupon {//�Ż�ȯ
	public static final String[] CouponTitles={"�Ż�ȯ���","����","���ý��","������","��ʼ����","��������"};
	private String couponid;//�Ż�ȯ���
	private String coucontent;//����
	private Float symoney;//���ý��
	private Float jmmoney;//������
	private Date bedate;
	private Date enddate;
	private String userid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(couponid);
		else if(col==1) return String.valueOf(coucontent);
		else if(col==2) return String.valueOf(symoney);
		else if(col==3) return String.valueOf(jmmoney);
		else if(col==4) return String.valueOf(bedate);	
		else if(col==5) return String.valueOf(enddate);
		else return "";
	}
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
