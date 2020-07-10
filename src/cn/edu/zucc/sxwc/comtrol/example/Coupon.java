package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Coupon {

	public List<BeanCoupon> loadCp() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select couponid,coucontent,symoney,jmmoney,bedate,enddate from coupon where userid =? order by couponid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanCoupon u=new BeanCoupon();
				u.setCouponid(rs.getString(1));
				u.setCoucontent(rs.getString(2));
				u.setSymoney(rs.getFloat(3));
				u.setJmmoney(rs.getFloat(4));
				u.setBedate(rs.getDate(5));
				u.setEnddate(rs.getDate(6));
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

}
