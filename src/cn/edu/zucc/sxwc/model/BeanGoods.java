package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanGoods {//��Ʒ��Ϣ
	private String goodsid;//��Ʒ���
	private String lbid;//�����
	private String goodsname;//��Ʒ����
	private Float gprice;//�۸�
	private Float gvipprice;//��Ա��
	private int gamount;//����
	private String guige;//���
	private String datails;//����
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
