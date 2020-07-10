package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanAddress;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Address {
	public List<BeanAddress> loadAddress() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanAddress> result=new ArrayList<BeanAddress>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select addid,province,city,area,address,contacts,phonenum from addressform where userid =? order by addid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanAddress u=new BeanAddress();
				u.setAddid(rs.getString(1));
				//u.setUserid(BeanUser.currentLoginUser.getUserid());
				u.setProvince(rs.getString(2));
				u.setCity(rs.getString(3));
				u.setArea(rs.getString(4));
				u.setAddress(rs.getString(5));
				u.setContact(rs.getString(6));
				u.setPhonenum(rs.getString(7));
				
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

	public void deleteAdd(String addid, String userid) throws BaseException{
		// TODO Auto-generated method stub
		
	}
}
