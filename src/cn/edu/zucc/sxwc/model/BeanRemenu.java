package cn.edu.zucc.sxwc.model;

public class BeanRemenu {//²ËÆ×ÍÆ¼ö±í
	private String menuid;//²ËÆ×±àºÅ
	private String goodsid;//ÉÌÆ·±àºÅ
	private String description;//ÃèÊö
	private String lbid;
	public static final String[] RemenuTitles={"²Ëµ¥±àºÅ","Àà±ð±àºÅ","ÉÌÆ·±àºÅ","ÃèÊö"};
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
