package cn.edu.zucc.sxwc.model;

public class BeanOrderxq {//���������
	private int orderid;//�������
	private String goodsid;//��Ʒ���
	private String lbid;
	public String getLbid() {
		return lbid;
	}
	public static final String[] OrderxqTitles={"�������","�����","��Ʒ���","���۱��","����","�۸�","�ۿ�"};
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(orderid);
		else if(col==1) return String.valueOf(lbid);
		else if(col==2) return String.valueOf(goodsid);
		else if(col==3) return String.valueOf(discountid);
		else if(col==4) return String.valueOf(amount);	
		else if(col==5) return String.valueOf(price);
		else if(col==6) return String.valueOf(discount);
		else return "";
	}
	private String discountid;//���۱��
	private int amount;//����
	private Float price;//�۸�
	private Float discount;//�ۿ�
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
}
