package cn.edu.zucc.sxwc.comtrol.example;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanDiscount;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Discount {
	public List<BeanDiscount> loadDc() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanDiscount> result=new ArrayList<BeanDiscount>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select discountid,lbid,goodsid,syamount,discount,dccontent,bedate,enddate from discountform order by discountid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			//pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanDiscount u=new BeanDiscount();
				u.setDiscountid(rs.getString(1));
				u.setLbid(rs.getString(2));
				u.setGoodsid(rs.getString(3));
				u.setSyamount(rs.getInt(4));
				u.setDiscount(rs.getFloat(5));
				u.setDccontent(rs.getString(6));
				u.setBedate(rs.getDate(7));
				u.setEnddate(rs.getDate(8));
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
