package cn.edu.zucc.sxwc.model;

public class BeanManager {//����Ա��Ϣ��
	public static BeanManager currentLoginManager=null;
	private String employeeid;//Ա�����
	private String employeename;//Ա������
	private String passwd;//Ա������s
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


