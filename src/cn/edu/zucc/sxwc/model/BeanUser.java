package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanUser {//�û���Ϣ
	public static BeanUser currentLoginUser=null;
	private String userid;//�û����
	private String username;
	private int sex;//�Ա�
	private String passwd;//����
	private String phonenum;//�ֻ�����
	private String mail;
	private String city;//���ڳ���
	private Date regtime;//ע��ʱ��
	private int isvip;//�Ƿ��Ա
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
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
	public int getIsvip() {
		return isvip;
	}
	public void setIsvip(int isvip) {
		this.isvip = isvip;
	}
	public Date getVipenddate() {
		return vipenddate;
	}
	public void setVipenddate(Date vipenddate) {
		this.vipenddate = vipenddate;
	}
	
}
