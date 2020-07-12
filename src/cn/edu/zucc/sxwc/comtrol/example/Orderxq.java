package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanGoodsorder;
import cn.edu.zucc.sxwc.model.BeanOrderxq;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Orderxq {

	public List<BeanOrderxq> loadorder(BeanGoodsorder gorder) throws BaseException{
		// TODO Auto-generated method stub
		List<BeanOrderxq> result=new ArrayList<BeanOrderxq>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from orderxq "
					+ "where orderid = ? order by orderid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, gorder.getOrderid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanOrderxq p=new BeanOrderxq();
				p.setOrderid(rs.getInt(1));
				p.setLbid(rs.getString(2));
				p.setGoodsid(rs.getString(3));
				p.setDiscountid(rs.getString(4));
				p.setAmount(rs.getInt(5));
				p.setPrice(rs.getFloat(6));
				p.setDiscount(rs.getFloat(7));
				
				result.add(p);
				}
			rs.close();
			pst.close();
		}catch (SQLException e) {
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
