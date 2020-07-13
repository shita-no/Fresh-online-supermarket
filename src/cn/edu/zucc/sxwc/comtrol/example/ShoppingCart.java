package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.annotations.Where;
import org.omg.CORBA.PRIVATE_MEMBER;

import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanDiscount;
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
			
			String sql="select a.goodsid,a.lbid,a.goodsname,a.price,a.amount,a.sumprice,b.discount,b.syamount,b.enddate "
					+ "from shoppingcart a,discountform b "
					+ "where a.userid =? and a.goodsid=b.goodsid and a.lbid=b.lbid order by a.lbid,a.goodsid";
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
				if(rs.getInt(5)>=rs.getInt(8)&&rs.getDate(9).after(new java.sql.Date(System.currentTimeMillis()))) {
					u.setSumprice((float)(rs.getFloat(4)*rs.getInt(5)*rs.getFloat(7)*0.1));
					String sql1="update shoppingcart set sumprice=? where userid=? and lbid=? and goodsid=?";
					java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setFloat(1, (float)(rs.getFloat(4)*rs.getInt(5)*rs.getFloat(7)*0.1));
					pst1.setString(2,BeanUser.currentLoginUser.getUserid());
					pst1.setString(3, rs.getString(2));
					pst1.setString(4, rs.getString(1));
					pst1.execute();
				}
				else {
					u.setSumprice((rs.getFloat(4)*rs.getInt(5)));
					String sql1="update shoppingcart set sumprice=? where userid=? and lbid=? and goodsid=?";
					java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setFloat(1, (rs.getFloat(4)*rs.getInt(5)));
					pst1.setString(2,BeanUser.currentLoginUser.getUserid());
					pst1.setString(3, rs.getString(2));
					pst1.setString(4, rs.getString(1));
					pst1.execute();
				}
				result.add(u);
			}
			sql="select a.goodsid,a.lbid,a.goodsname,a.price,a.amount,a.sumprice\r\n" + 
					"from shoppingcart a \r\n" + 
					"where a.userid =?  and not exists\r\n" + 
					"(\r\n" + 
					"select *\r\n" + 
					"from discountform b\r\n" + 
					"where a.lbid = b.lbid and a.goodsid = b.goodsid\r\n" + 
					")\r\n" + 
					"order by a.lbid,a.goodsid ";
			pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			rs=pst.executeQuery();
			while(rs.next()){
				BeanShoppingCart u=new BeanShoppingCart();
				u.setGoodsid(rs.getString(1));
				u.setLbid(rs.getString(2));
				u.setGoodsname(rs.getString(3));
				u.setPrice(rs.getFloat(4));
				u.setAmount(rs.getInt(5));
				u.setSumprice((rs.getFloat(4)*rs.getInt(5)));
				String sql1="update shoppingcart set sumprice=? where userid=? and lbid=? and goodsid=?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setFloat(1, (rs.getFloat(4)*rs.getInt(5)));
				pst1.setString(2,BeanUser.currentLoginUser.getUserid());
				pst1.setString(3, rs.getString(2));
				pst1.setString(4, rs.getString(1));
				pst1.execute();
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
			
			}
			
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

	public float totalprice() {
		float sum = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select price,amount from shoppingcart where userid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) 
				sum += rs.getFloat(1)*rs.getInt(2) ;
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.rollback();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return sum;
	}
	public float totalprice1() {
		float sum = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select sumprice from shoppingcart where userid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) 
				sum += rs.getFloat(1) ;
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.rollback();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return sum;
	}
	public void placeorder(String addid,String couponid) throws BaseException{
		// TODO Auto-generated method stub
		
		List<BeanShoppingCart>result=this.loadCart();
		BeanCoupon coupon = new BeanCoupon();
		float beprice=this.totalprice();
		float endprice=0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			
			//生成订单号
			int orderid = 0;
			String sql = "select max(orderid) from goodsorder";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				orderid = rs.getInt(1) + 1;
			else
				orderid = 1;
			rs.close();
			pst.close();
			sql="select jmmoney from coupon where couponid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, couponid);
			rs=pst.executeQuery();
			if(rs.next()) {
				BeanCoupon c=new BeanCoupon();
				c.setSymoney(rs.getFloat(1));
				endprice=this.totalprice1()-rs.getFloat(1);
			}
			else {
				endprice=this.totalprice1();
			}
			rs.close();
			pst.close();
			sql="insert into goodsorder(orderid,addid,couponid,userid,beprice,endprice,arrivetime,state) value(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			pst.setString(2, addid);
			pst.setString(3, couponid);
			pst.setString(4, BeanUser.currentLoginUser.getUserid());
			pst.setFloat(5, beprice);
			pst.setFloat(6, endprice);
			pst.setDate(7, new java.sql.Date(System.currentTimeMillis()+172800000L));
			pst.setString(8, "下单");
			pst.execute();
			pst.close();
			
			sql="select goodsid,lbid,amount,price from shoppingcart where userid=? ";
			pst=conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			rs=pst.executeQuery();
			while(rs.next()) {
				String sql1="insert into orderxq(orderid,lbid,goodsid,discountid,amount,price,discount)value(?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				
				pst1.setInt(1, orderid);
				pst1.setString(2, rs.getString(2));
				pst1.setString(3, rs.getString(1));
				String sql2="select discountid,discount from discountform where goodsid=? and lbid=? and syamount<=?";
				java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
				pst2.setString(1, rs.getString(1));
				pst2.setString(2, rs.getString(2));
				pst2.setInt(3, rs.getInt(3));
				ResultSet rs2=pst2.executeQuery();
				if(rs2.next()) {
					pst1.setString(4, rs2.getString(1));
					pst1.setFloat(7, rs2.getFloat(2));
					pst1.setFloat(6, (float)(rs.getInt(3)*rs.getFloat(4)*rs2.getFloat(2)*0.1));
				}
				else {
					pst1.setString(4, "");
					pst1.setFloat(7, (float) 10.0);
					pst1.setFloat(6, rs.getInt(3)*rs.getFloat(4));
				}
				
				pst1.setInt(5, rs.getInt(3));
				
				pst1.execute();
				pst1.close();
			}
			
			
			sql="select goodsid,lbid,amount from shoppingcart where userid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			rs=pst.executeQuery();
			while(rs.next()){
				String sql1="update goods set gamount=gamount-? where lbid=? and goodsid=? ";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setFloat(1, rs.getFloat(3));
				pst1.setString(2, rs.getString(2));
				pst1.setString(3, rs.getString(1));
				pst1.execute();
				pst1.close();
			}
			
			
			sql = "delete from shoppingcart where userid = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			pst.execute();
			pst.close();
			sql="delete from coupon where couponid=? and userid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, couponid);
			pst.setString(2, BeanUser.currentLoginUser.getUserid());
			pst.execute();
			pst.close();
			
			
			
			conn.commit();
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
	}
	}


	

