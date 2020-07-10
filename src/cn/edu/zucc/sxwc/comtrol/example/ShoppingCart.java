package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class ShoppingCart {
	public List<BeanShoppingCart> loadCart()throws BaseException{//购物车
		List<BeanShoppingCart> result=new ArrayList<BeanShoppingCart>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select goodsid,lbid,goodsname,price,amount from shoppingcart where userid =? order by lbid,goodsid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanShoppingCart u=new BeanShoppingCart();
				u.setGoodsid(rs.getString(1));
				u.setLbid(rs.getString(2));
				u.setGoodsname(rs.getString(3));
				u.setPrice(rs.getFloat(4));
				u.setAmount(rs.getInt(5));
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


	public BeanShoppingCart addcart(BeanGoods goods,int amount) throws BaseException{
		// TODO Auto-generated method stub
		
		if(goods.getGamount() < amount)
		{
			throw new BusinessException("购买数量大于库存，请重新加入购物车1");
			
		}
		//int flag=0;
		BeanShoppingCart u=null;
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			conn.setAutoCommit(false);
			/*String sql = "select count(goodsid) from shoppingcart where userid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			//int order_id;
			//if(rs.next())
				//order_id = rs.getInt(1) + 1;
			//else
				//order_id = 0;
			rs.close();
			pst.close();
			sql = "select goodsid,lbid,goodsname,price,amount,userid from shoppingcart where goodsid = ? and userid = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, goods.getGoodsid());
			pst.setString(2, BeanUser.currentLoginUser.getUserid());
			rs = pst.executeQuery();
			//int flag = 0;
			if(rs.next()) {
				amount += rs.getInt(5);
				if(amount > goods.getGamount())
					throw new BusinessException("购买数量大于库存，请重新输入数量aaa");
				flag = 1;
			}*/
			String sql1="select * from shoppingcart where userid=? and goodsid =? and lbid=? ";
			java.sql.PreparedStatement pst1= conn.prepareStatement(sql1);
			pst1.setString(1, BeanUser.currentLoginUser.getUserid());
			pst1.setString(2, goods.getGoodsid());
			pst1.setString(3, goods.getLbid());
			java.sql.ResultSet rs = pst1.executeQuery();
			if(rs.next()) {
				int num=amount;
				amount+=rs.getInt(5);
				if(amount > goods.getGamount())
					throw new BusinessException("购买数量大于库存，请重新加入购物车2");
				String sql="update shoppingcart set amount=amount+? where userid=? and goodsid =? and lbid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, num);
				pst.setString(2, BeanUser.currentLoginUser.getUserid());
				pst.setString(3, goods.getGoodsid());
				pst.setString(4, goods.getLbid());
				pst.execute();
				pst.close();
				conn.commit();
				JOptionPane.showMessageDialog(null, "加入购物车成功");
				//flag=1;
			}
			else {
				String sql = "insert into shoppingcart(goodsid,lbid,goodsname,price,amount,userid) values(?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, goods.getGoodsid());
				pst.setString(2, goods.getLbid());
				pst.setString(3, goods.getGoodsname());
				if(BeanUser.currentLoginUser.getIsvip().equals("是")) {
					
					pst.setDouble(4, goods.getGvipprice());
				}else {
					pst.setDouble(4, goods.getGprice());
				}
				
				pst.setInt(5, amount);
				pst.setString(6, BeanUser.currentLoginUser.getUserid());
				pst.execute();
				pst.close();
			
			
			
			conn.commit();
			JOptionPane.showMessageDialog(null, "加入购物车成功");
			//flag=1;
			}
			/*if(flag==1) {
				String sql2="update goods set gamount=gamount-?  where goodsid =? and lbid=? ";
				java.sql.PreparedStatement pst2= conn.prepareStatement(sql1);
				pst1.setInt(1, beamount);
				pst1.setString(2, goods.getGoodsid());
				pst1.setString(3, goods.getLbid());
				pst1.execute();
				pst1.close();
			}*/
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if(conn != null)
				try {
					conn.rollback();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return u;
	}
	public void deletecart(String userid,String goodsid,String lbid)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from shoppingcart where userid=? and goodsid =? and lbid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, goodsid);
			pst.setString(3, lbid);
			pst.execute();
			
			//java.sql.ResultSet rs=pst.executeQuery();
			pst.close();
			JOptionPane.showMessageDialog(null, "删除成功");
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
