package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanUser {//�û���Ϣ
	public static BeanUser currentLoginUser=null;
	public static final String[] tableTitles={"�û����","����","�Ա�","����","�ֻ�����","����","���ڳ���","ע��ʱ��","�Ƿ��Ա","��Ա��ֹ����"};
	private String userid;//�û����
	private String username;
	private String sex;//�Ա�
	public String getCell(int col){
		if(col==0) return String.valueOf(userid);
		else if(col==1) return String.valueOf(username);
		else if(col==2) return String.valueOf(sex);
		else if(col==3) return String.valueOf(passwd);
		else if(col==4) return String.valueOf(phonenum);	
		else if(col==5) return String.valueOf(mail);
		else if(col==6) return String.valueOf(city);
		else if(col==7) return String.valueOf(regtime);
		else if(col==8) return String.valueOf(isvip);
		else if(col==9) return String.valueOf(vipenddate);
		else return "";
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	private String passwd;//����
	private String phonenum;//�ֻ�����
	private String mail;
	private String city;//���ڳ���
	private Date regtime;//ע��ʱ��
	private String isvip;//�Ƿ��Ա
	private Date vipenddate;//��Ա��ֹ����
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	public String getIsvip() {
		return isvip;
	}
	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}
	public Date getVipenddate() {
		return vipenddate;
	}
	public void setVipenddate(Date vipenddate) {
		this.vipenddate = vipenddate;
	}
	
	
}
