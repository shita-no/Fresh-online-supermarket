package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;

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
			if(!rs.next()) throw new BusinessException("µÇÂ¼ÕËºÅ²»´æÔÚ");
			BeanManager u=new BeanManager();
			u.setEmployeeid(rs.getString(1));
			u.setPasswd(rs.getString(2));
			if(!(passwd).equals(u.getPasswd())){
				throw new BusinessException("ÃÜÂë´íÎó");
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
}
