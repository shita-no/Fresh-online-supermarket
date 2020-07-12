package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanGoodsorder;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Goodsorder {

	public List<BeanGoodsorder> loadGorder() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanGoodsorder> result=new ArrayList<BeanGoodsorder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select orderid,addid,couponid,beprice,endprice,arrivetime,state from goodsorder where userid =? order by orderid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanGoodsorder u=new BeanGoodsorder();
				u.setOrderid(rs.getInt(1));
				u.setAddid(rs.getString(2));
				u.setCouponid(rs.getString(3));
				u.setBeprice(rs.getFloat(4));
				u.setEndprice(rs.getFloat(5));
				u.setArrivetime(rs.getDate(6));
				u.setState(rs.getString(7));
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
