package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanGoodsorder;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
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
	public List<BeanGoodsorder> loadGorder1() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanGoodsorder> result=new ArrayList<BeanGoodsorder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select orderid,addid,couponid,userid,beprice,endprice,arrivetime,state from goodsorder  order by orderid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanGoodsorder u=new BeanGoodsorder();
				u.setOrderid(rs.getInt(1));
				u.setAddid(rs.getString(2));
				u.setCouponid(rs.getString(3));
				u.setUserid(rs.getString(4));
				u.setBeprice(rs.getFloat(5));
				u.setEndprice(rs.getFloat(6));
				u.setArrivetime(rs.getDate(7));
				u.setState(rs.getString(8));
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
	public void update(int orderid, String userid) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goodsorder where orderid=? and userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			pst.setString(2, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("订单不存在");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? and userid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			pst.setString(2, userid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("配送中".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? and userid=?";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "已送达");
					pst1.setInt(2, orderid);
					pst1.setString(3, userid);
					pst1.execute();
					pst1.close();
				}
				else if("下单".equals(rs.getString(1))){
					throw new BusinessException("请耐心等待商家发货配送");
				}
				else if("已退货".equals(rs.getString(1))){
					throw new BusinessException("订单已退货，无法确认收货");
				}
				else if("已送达".equals(rs.getString(1))){
					throw new BusinessException("货物已被接受");
				}
				else if("退货中".equals(rs.getString(1))){
					throw new BusinessException("请耐心等待商家处理退货");
				}
			}
			rs.close();
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

	public void returngoods(int orderid, String userid) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goodsorder where orderid=? and userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			pst.setString(2, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("订单不存在");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? and userid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			pst.setString(2, userid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("已送达".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? and userid=?";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "退货中");
					pst1.setInt(2, orderid);
					pst1.setString(3, userid);
					pst1.execute();
					pst1.close();
				}
				else if("下单".equals(rs.getString(1))){
					throw new BusinessException("请耐心等待商家发货配送");
				}
				else if("已退货".equals(rs.getString(1))){
					throw new BusinessException("订单已退货，请重新选择订单");
				}
				else if("配送中".equals(rs.getString(1))){
					throw new BusinessException("请耐心等待货物配送");
				}
				else if("退货中".equals(rs.getString(1))){
					throw new BusinessException("商家正在处理退货，请耐心等待");
				}
			}
			rs.close();
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
	public void update1(int orderid) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goodsorder where orderid=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("订单不存在");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? ";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("下单".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? ";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "配送中");
					pst1.setInt(2, orderid);
					pst1.execute();
					pst1.close();
				}
				else if("配送中".equals(rs.getString(1))){
					throw new BusinessException("该订单无发货请求");
				}
				else if("已退货".equals(rs.getString(1))){
					throw new BusinessException("该订单无发货请求");
				}
				else if("已送达".equals(rs.getString(1))){
					throw new BusinessException("该订单无发货请求");
				}
				else if("退货中".equals(rs.getString(1))){
					throw new BusinessException("该订单无发货请求");
				}
			}
			rs.close();
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
	public void returngoods1(int orderid) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goodsorder where orderid=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("订单不存在");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? ";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("退货中".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? ";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "已退货");
					pst1.setInt(2, orderid);
					pst1.execute();
					pst1.close();
				}
				else if("配送中".equals(rs.getString(1))){
					throw new BusinessException("该订单无退货请求");
				}
				else if("已退货".equals(rs.getString(1))){
					throw new BusinessException("该订单无退货请求");
				}
				else if("已送达".equals(rs.getString(1))){
					throw new BusinessException("该订单无退货请求");
				}
				else if("下单".equals(rs.getString(1))){
					throw new BusinessException("该订单无退货请求");
				}
			}
			rs.close();
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


