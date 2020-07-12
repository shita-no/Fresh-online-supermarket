package cn.edu.zucc.sxwc.model;

public class BeanManager {//管理员信息表
	public static BeanManager currentLoginManager=null;
	public static final String[] tableTitles={"账号","姓名","密码"};
	private String employeeid;//员工编号
	private String employeename;//员工姓名
	private String passwd;//员工密码
	public String getCell(int col){
		if(col==0) return String.valueOf(employeeid);
		else if(col==1) return String.valueOf(employeename);
		else if(col==2) return String.valueOf(passwd);
		
		else return "";
	}
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


