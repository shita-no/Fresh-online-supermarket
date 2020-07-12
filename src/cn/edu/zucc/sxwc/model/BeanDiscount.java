package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanDiscount {//������Ϣ��+������Ʒ������
	public static final String[] DiscountTitles={"���۱��","�����","��Ʒ���","������Ʒ����","�ۿ�","����","��ʼ����","��������"};
	private String discountid;//���۱��
	private String dccontent;//����
	private int syamount;//������Ʒ����
	private Float discount;//�ۿ�
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
