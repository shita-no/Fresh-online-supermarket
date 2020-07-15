package cn.edu.zucc.sxwc.model;

public class BeanMenu {
	public static final String[] MenuTitles={"²ËÆ×±àºÅ","ÉÌÆ·±àºÅ","²ËÆ×Ãû³Æ","²ËÆ×ÓÃÁÏ","²½Öè","Í¼Æ¬"};
	private String menuid;
	private String goodsid;
	private String lbid;
	public String getLbid() {
		return lbid;
	}
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	private String menuname;
	private String ingredients;//²ËÆ×ÓÃÁÏ
	private String Step;//²½Öè
	private String picture;//Í¼Æ¬
	public String getCell(int col){
		if(col==0) return String.valueOf(menuid);
		else if(col==1) return String.valueOf(goodsid);
		else if(col==2) return String.valueOf(menuname);
		else if(col==3) return String.valueOf(ingredients);
		else if(col==4) return String.valueOf(Step);	
		else if(col==5) return String.valueOf(picture);
		else return "";
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
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getStep() {
		return Step;
	}
	public void setStep(String step) {
		this.Step = step;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
