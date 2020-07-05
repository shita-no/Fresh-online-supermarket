package cn.edu.zucc.sxwc.model;

public class BeanManager {//管理员信息表
	public static BeanManager currentLoginManager=null;
	private String employeeid;//员工编号
	private String employeename;//员工姓名
	private String passwd;//员工密码s
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}


