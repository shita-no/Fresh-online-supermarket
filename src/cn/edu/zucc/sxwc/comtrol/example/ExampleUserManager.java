package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


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
					String sql="select userid,passwd,regtime from userinformation where userid=?";
					java.sql.PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1,userid);
					java.sql.ResultSet rs=pst.executeQuery();
					if(!rs.next()) throw new BusinessException("登录账号不存在");
					BeanUser u=new BeanUser();
					u.setUserid(rs.getString(1));
					u.setPasswd(rs.getString(2));
					u.setRegtime(rs.getDate(3));
					if(!(passwd).equals(u.getPasswd())){
						throw new BusinessException("密码错误");
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

	public BeanUser reg(String userid, String passwd1, String passwd2)throws BaseException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(userid==null || "".equals(userid) || userid.length()>20){
					throw new BusinessException("登陆账号必须是1-20个字");
				}
				if(passwd1==null || "".equals(passwd1)){
					throw new BusinessException("密码不能为空");
				}
				if(!passwd1.equals(passwd2)){
					throw new BusinessException("两个输入的密码不一致");
				}
				
				
				Connection conn=null;
				try {
					conn=DBUtil.getConnection();
					//检查用户是否存在
					String sql="select * from userinformation where userid=?";
					java.sql.PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1,userid);
					java.sql.ResultSet rs=pst.executeQuery();
					if(rs.next()) {
						//rs.close();
						//pst.close();
						throw new BusinessException("登陆账号已经存在");
					}
					rs.close();
					pst.close();
					sql="insert into userinformation(userid,passwd,regtime) value(?,?,Now())";
					pst=conn.prepareStatement(sql);
					pst.setString(1, userid);
					pst.setString(2, passwd1);
					BeanUser u=new BeanUser();
					u.setUserid(userid);
					u.setPasswd(passwd1);
					u.setRegtime(new Date(System.currentTimeMillis()));
					
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
		if(oldPwd==null||"".equals(oldPwd)) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) ) throw new BusinessException("新密码不能为空");
		if(!newPwd.equals(newPwd2)) throw new BusinessException("新密码两次输入不同");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select passwd from userinformation where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			if(!BeanUser.currentLoginUser.getPasswd().equals(oldPwd)) throw new BusinessException("原始密码错误");
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
	
	

}
