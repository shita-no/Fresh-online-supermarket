package cn.edu.zucc.sxwc.model;

public class BeanRemenu {//�����Ƽ���
	private String menuid;//���ױ��
	private String goodsid;//��Ʒ���
	private String description;//����
	private String lbid;
	public static final String[] RemenuTitles={"�˵����","�����","��Ʒ���","����"};
	public String getCell(int col){
		if(col==0) return String.valueOf(menuid);
		else if(col==1) return String.valueOf(lbid);
		else if(col==2) return String.valueOf(goodsid);
		else if(col==3) return String.valueOf(description);
		else return "";
	}
	public String getLbid() {
		return lbid;
	}
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
