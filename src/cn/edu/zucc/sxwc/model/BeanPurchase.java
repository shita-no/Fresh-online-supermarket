package cn.edu.zucc.sxwc.model;

public class BeanPurchase {//��Ʒ�ɹ���
	private String buyid;//�ɹ������
	private String employeeid;
	private String goodsid;//ʳ�ı��
	private int quatity;//����
	private String state;//״̬(�µ�,��;,���)
	public String getBuyid() {
		return buyid;
	}
	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
