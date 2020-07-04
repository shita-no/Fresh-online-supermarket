package cn.edu.zucc.sxwc.model;

public class BeanAddress {//配送地址表
	private String addid;//地址编号
	private String userid;//用户编号
	private String province;//省市区
	private String city;
	private String area;
	private String contact;//联系人
	private String phonenum;
	private String address;//地址
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
