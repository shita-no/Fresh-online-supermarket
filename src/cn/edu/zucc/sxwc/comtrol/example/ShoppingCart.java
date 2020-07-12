package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	public List<BeanShoppingCart> loadCart()throws BaseException{//���ﳵ
		List<BeanShoppingCart> result=new ArrayList<BeanShoppingCart>();
		Connection conn=null;
		//Connection conn1=null;
		try {
			conn=DBUtil.getConnection();
			//conn1=DBUtil.getConnection();
			/*String sql1="select amount from shoppingcart where userid=? and goodsid =? and lbid=?";
			java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
			pst1.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs1=pst1.executeQuery();
			while(rs1.next()) {
				
			}*/
			String sql="select a.goodsid,a.lbid,a.goodsname,a.price,a.amount,a.sumprice,b.discount,b.syamount "
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
				if(rs.getInt(5)>=rs.getInt(8)) {
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
			throw new BusinessException("�����������ڿ�棬�����¼��빺�ﳵ1");
			
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
					throw new BusinessException("�����������ڿ�棬�����¼��빺�ﳵ2");
				String sql="update shoppingcart set amount=amount+? where userid=? and goodsid =? and lbid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, num);
				pst.setString(2, BeanUser.currentLoginUser.getUserid());
				pst.setString(3, goods.getGoodsid());
				pst.setString(4, goods.getLbid());
				pst.execute();
				pst.close();
				conn.commit();
				JOptionPane.showMessageDialog(null, "���빺�ﳵ�ɹ�");
				//flag=1;
			}
			else {
				String sql = "insert into shoppingcart(goodsid,lbid,goodsname,price,amount,userid) values(?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, goods.getGoodsid());
				pst.setString(2, goods.getLbid());
				pst.setString(3, goods.getGoodsname());
				if(BeanUser.currentLoginUser.getIsvip().equals("��")) {
					
					pst.setDouble(4, goods.getGvipprice());
				}else {
					pst.setDouble(4, goods.getGprice());
				}
				
				pst.setInt(5, amount);
				pst.setString(6, BeanUser.currentLoginUser.getUserid());
				pst.execute();
				pst.close();
			
			
			
			conn.commit();
			JOptionPane.showMessageDialog(null, "���빺�ﳵ�ɹ�");
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
			JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
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
			
			
			//���ɶ�����
			int orderid = 0;
			String sql = "select count(orderid) from goodsorder";
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
			pst.setString(8, "�µ�");
			pst.execute();
			pst.close();
			//�����ﳵ��Ʒ�������ż��붩������ 
			/*sql = "insert into goodsorder(orderid,addid,couponid,userid,beprice,endprice,arrivetime,state) "
					+ "select user_order.order_id,goods_name,num,goods_price "
					+ "from user_shoppingCar,user_order "
					+ "where user_shoppingCar.merchant_name = user_order.merchant_name and user_order.user_name = user_shoppingCar.user_name order by user_order.order_id";
			pst = conn.prepareStatement(sql);
			pst.execute();
			pst.close();*/
		
			
			
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


	

