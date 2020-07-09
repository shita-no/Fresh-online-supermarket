package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class ExampleEmployeeManager {
	public static BeanManager currentLoginManager=null;
	public BeanManager login(String employeeid, String passwd) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select employeeid,passwd from manager where employeeid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,employeeid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登录账号不存在");
			BeanManager u=new BeanManager();
			u.setEmployeeid(rs.getString(1));
			u.setPasswd(rs.getString(2));
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
	public List<BeanManager> loadAllUsers()throws BaseException{
			List<BeanManager> result=new ArrayList<BeanManager>();
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select employeeid,employeename,passwd from manager order by employeeid";
				java.sql.Statement st=conn.createStatement();
				java.sql.ResultSet rs=st.executeQuery(sql);
				while(rs.next()){
					BeanManager u=new BeanManager();
					u.setEmployeeid(rs.getString(1));
					u.setEmployeename(rs.getString(2));
					u.setPasswd(rs.getString(3));
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
	public void createManager(BeanManager Manager)throws BaseException{
		if(Manager.getEmployeeid()==null || "".equals(Manager.getEmployeeid()) || Manager.getEmployeeid().length()>50){
			throw new BusinessException("登陆账号必须是1-50个字");
		}
		if(Manager.getEmployeename()==null || "".equals(Manager.getEmployeename()) || Manager.getEmployeename().length()>50){
			throw new BusinessException("账号名称必须是1-50个字");
		}
		if(Manager.getPasswd()==null || "".equals(Manager.getPasswd()) || Manager.getPasswd().length()>50){
			throw new BusinessException("密码必须是1-50个字");
		}
		
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manager where employeeid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,Manager.getEmployeeid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			sql="insert into manager(employeeid,employeename,passwd) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, Manager.getEmployeeid());
			pst.setString(2, Manager.getEmployeename());
			//user.setPwd(user.getUserid());//默认密码为账号
			pst.setString(3,Manager.getPasswd());
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
	public void changePwd(BeanManager employee, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null||"".equals(oldPwd)) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) ) throw new BusinessException("新密码不能为空");
		if(!newPwd.equals(newPwd2)) throw new BusinessException("新密码两次输入不同");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select passwd from manager where employeeid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanManager.currentLoginManager.getEmployeeid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			if(!BeanManager.currentLoginManager.getPasswd().equals(oldPwd)) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update manager set passwd=? where employeeid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, employee.getEmployeeid());
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
	public void deleteUser(String employeeid) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manager where employeeid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,employeeid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			//if(rs.getDate(1)!=null) throw new BusinessException("该账号已经被删除");
			rs.close();
			pst.close();
			
			sql="delete from manager where employeeid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, employeeid);
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
