package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanDiscount {//������Ϣ��
	private String discountid;//���۱��
	private String dccontent;//����
	private int syamount;//������Ʒ����
	private Float discount;//�ۿ�
	private Date bedate;
	private Date enddate;
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
