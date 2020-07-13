package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanDiscount;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
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

	public void creatediscount(BeanDiscount dc) throws BaseException{
		// TODO Auto-generated method stub
		if(dc.getDiscountid()==null || "".equals(dc.getDiscountid()) || dc.getDiscountid().length()>50){
			throw new BusinessException("满折编号必须是1-50个字");
		}
		if(dc.getLbid()==null || "".equals(dc.getLbid()) || dc.getLbid().length()>50){
			throw new BusinessException("类别编号必须是1-50个字");
		}
		if(dc.getGoodsid()==null || "".equals(dc.getGoodsid()) || dc.getGoodsid().length()>50 ){
			throw new BusinessException("商品编号必须是1-50个字");
		}
		if(dc.getSyamount()<0 ){
			throw new BusinessException("数量不能为负");
		}
		if(dc.getDiscount()<0 ){
			throw new BusinessException("折扣不能为负");
		}
		if(dc.getDccontent()==null || "".equals(dc.getDccontent()) || dc.getDccontent().length()>50 ){
			throw new BusinessException("内容必须是1-50个字");
		}

		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from discountform where discountid=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,dc.getDiscountid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该满折的满折编号已经存在");
			rs.close();
			pst.close();
			sql="insert into discountform(discountid,lbid,goodsid,syamount,discount,dccontent,bedate,enddate) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, dc.getDiscountid());
			pst.setString(2, dc.getLbid());
			pst.setString(3, dc.getGoodsid());
			pst.setInt(4,dc.getSyamount());
			pst.setFloat(5, dc.getDiscount());
			pst.setString(6, dc.getDccontent());
			pst.setDate(7,new java.sql.Date(System.currentTimeMillis()));
			pst.setDate(8, new java.sql.Date(System.currentTimeMillis()+2592000000L));
			
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
