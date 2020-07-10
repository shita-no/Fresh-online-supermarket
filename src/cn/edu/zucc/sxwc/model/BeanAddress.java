package cn.edu.zucc.sxwc.model;

public class BeanAddress {//配送地址表
	public static final String[] AddressTitles={"地址编号","省","市","区","地址","联系人","电话"};
	private String addid;//地址编号
	private String userid;//用户编号
	private String province;//省市区
	private String city;
	private String area;
	private String contact;//联系人
	private String phonenum;
	private String address;//地址
	public String getCell(int col){
	    	 if(col==0) return String.valueOf(addid);
		else if(col==1) return String.valueOf(province);
		else if(col==2) return String.valueOf(city);
		else if(col==3) return String.valueOf(area);
		else if(col==4) return String.valueOf(address);
		else if(col==5) return String.valueOf(contact);	
		else if(col==6) return String.valueOf(phonenum);
		else return "";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddid() {
		return addid;
	}
	public void setAddid(String addid) {
		this.addid = addid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
}
