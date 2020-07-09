package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class ExampleUserManager {
	
	public BeanUser login(String userid, String passwd) throws BaseException{
		// TODO Auto-generated method stub
				Connection conn=null;
				try {
					conn=DBUtil.getConnection();
					String sql="select * from userinformation where userid=?";
					java.sql.PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1,userid);
					java.sql.ResultSet rs=pst.executeQuery();
					if(!rs.next()) throw new BusinessException("��¼�˺Ų�����");
					BeanUser u=new BeanUser();
					u.setUserid(rs.getString(1));
					u.setUsername(rs.getString(2));
					u.setSex(rs.getString(3));
					u.setPasswd(rs.getString(4));
					u.setPhonenum(rs.getString(5));
					u.setMail(rs.getString(6));
					u.setCity(rs.getString(7));
					u.setRegtime(rs.getDate(8));
					u.setIsvip(rs.getString(9));
					u.setVipenddate(rs.getDate(10));
					if(!(passwd).equals(u.getPasswd())){
						throw new BusinessException("�������");
					}
					
					rs.close();
					pst.close();
					return u;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DbException(e);
				}
				finally{
					if(conn!=null)
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		
	}

	public BeanUser reg(String userid, String username,String passwd1, String passwd2,String phonenum,String mail,String city)throws BaseException {//��ע��
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(userid==null || "".equals(userid) || userid.length()>20){
					throw new BusinessException("��½�˺ű�����1-50����");
				}
				if(passwd1==null || "".equals(passwd1)){
					throw new BusinessException("���벻��Ϊ��");
				}
				if(!passwd1.equals(passwd2)){
					throw new BusinessException("������������벻һ��");
				}
				
				
				Connection conn=null;
				try {
					conn=DBUtil.getConnection();
					//����û��Ƿ����
					String sql="select * from userinformation where userid=?";
					java.sql.PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1,userid);
					java.sql.ResultSet rs=pst.executeQuery();
					if(rs.next()) {
						//rs.close();
						//pst.close();
						throw new BusinessException("��½�˺��Ѿ�����");
					}
					rs.close();
					pst.close();
					sql="insert into userinformation(userid,username,sex,passwd,phonenum,mail,city,regtime,isvip) value(?,?,?,?,?,?,?,Now(),?)";
					pst=conn.prepareStatement(sql);
					pst.setString(1, userid);
					pst.setString(2, username);
					pst.setString(3, "��");
					pst.setString(4, passwd1);
					pst.setString(5, phonenum);
					pst.setString(6, mail);
					pst.setString(7, city);
					pst.setString(8, "��");
					BeanUser u=new BeanUser();
					u.setUserid(userid);
					u.setUsername(username);
					u.setSex("��");
					u.setPasswd(passwd1);
					u.setPhonenum(phonenum);
					u.setMail(mail);
					u.setCity(city);
					u.setRegtime(new Date(System.currentTimeMillis()));
					u.setIsvip("��");
					pst.execute();
					pst.close();
					return u;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DbException(e);
				}
				finally{
					if(conn!=null)
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
	}
	public BeanUser reg1(String userid, String username,String passwd1, String passwd2,String phonenum,String mail,String city)throws BaseException {//Ůע��
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(userid==null || "".equals(userid) || userid.length()>20){
					throw new BusinessException("��½�˺ű�����1-50����");
				}
				if(passwd1==null || "".equals(passwd1)){
					throw new BusinessException("���벻��Ϊ��");
				}
				if(!passwd1.equals(passwd2)){
					throw new BusinessException("������������벻һ��");
				}
				
				
				Connection conn=null;
				try {
					conn=DBUtil.getConnection();
					//����û��Ƿ����
					String sql="select * from userinformation where userid=?";
					java.sql.PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1,userid);
					java.sql.ResultSet rs=pst.executeQuery();
					if(rs.next()) {
						//rs.close();
						//pst.close();
						throw new BusinessException("��½�˺��Ѿ�����");
					}
					rs.close();
					pst.close();
					sql="insert into userinformation(userid,username,sex,passwd,phonenum,mail,city,regtime,isvip) value(?,?,?,?,?,?,?,Now(),?)";
					pst=conn.prepareStatement(sql);
					pst.setString(1, userid);
					pst.setString(2, username);
					pst.setString(3, "Ů");
					pst.setString(4, passwd1);
					pst.setString(5, phonenum);
					pst.setString(6, mail);
					pst.setString(7, city);
					pst.setString(8, "��");
					BeanUser u=new BeanUser();
					u.setUserid(userid);
					u.setUsername(username);
					u.setSex("Ů");
					u.setPasswd(passwd1);
					u.setPhonenum(phonenum);
					u.setMail(mail);
					u.setCity(city);
					u.setRegtime(new Date(System.currentTimeMillis()));
					u.setIsvip("��");
					pst.execute();
					pst.close();
					return u;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DbException(e);
				}
				finally{
					if(conn!=null)
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
	}
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null||"".equals(oldPwd)) throw new BusinessException("ԭʼ���벻��Ϊ��");
		if(newPwd==null || "".equals(newPwd) ) throw new BusinessException("�����벻��Ϊ��");
		if(!newPwd.equals(newPwd2)) throw new BusinessException("�������������벻ͬ");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select passwd from userinformation where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�˺Ų� ����");
			if(!BeanUser.currentLoginUser.getPasswd().equals(oldPwd)) throw new BusinessException("ԭʼ�������");
			rs.close();
			pst.close();
			sql="update userinformation set passwd=? where userid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, user.getUserid());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public List<BeanUser> loadUser()throws BaseException{
		List<BeanUser> result=new ArrayList<BeanUser>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select userid,username,sex,passwd,phonenum,mail,city,regtime,isvip,vipenddate from userinformation order by userid";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanUser u=new BeanUser();
				u.setUserid(rs.getString(1));
				u.setUsername(rs.getString(2));
				u.setSex(rs.getString(3));
				u.setPasswd(rs.getString(4));
				u.setPhonenum(rs.getString(5));
				u.setMail(rs.getString(6));
				u.setCity(rs.getString(7));
				u.setRegtime(rs.getDate(8));
				u.setIsvip(rs.getString(9));
				u.setVipenddate(rs.getDate(10));
				result.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	public BeanUser loadUser1()throws BaseException{
		BeanUser u=new BeanUser();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select userid,username,sex,passwd,phonenum,mail,city,regtime,isvip,vipenddate from userinformation order by userid";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()){
				
				u.setUserid(rs.getString(1));
				u.setUsername(rs.getString(2));
				u.setSex(rs.getString(3));
				u.setPasswd(rs.getString(4));
				u.setPhonenum(rs.getString(5));
				u.setMail(rs.getString(6));
				u.setCity(rs.getString(7));
				u.setRegtime(rs.getDate(8));
				u.setIsvip(rs.getString(9));
				u.setVipenddate(rs.getDate(10));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return u;
	}
	public void upvip()throws BaseException{
		Connection conn=null;
		if (BeanUser.currentLoginUser.getIsvip()=="��") {
			throw new BusinessException("�Ѿ��ǻ�Ա");
		}
		try {
			conn=DBUtil.getConnection();
			String sql="update userinformation set isvip=?,vipenddate=? where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "��");
			pst.setDate(2, new java.sql.Date(System.currentTimeMillis()+2592000000L));
			pst.setString(3, BeanUser.currentLoginUser.getUserid());
			BeanUser.currentLoginUser.setIsvip("��");
			pst.execute();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
	


