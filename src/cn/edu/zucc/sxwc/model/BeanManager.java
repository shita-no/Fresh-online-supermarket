package cn.edu.zucc.sxwc.model;

public class BeanManager {//����Ա��Ϣ��
	public static BeanManager currentLoginManager=null;
	public static final String[] tableTitles={"�˺�","����","����"};
	private String employeeid;//Ա�����
	private String employeename;//Ա������
	private String passwd;//Ա������
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


