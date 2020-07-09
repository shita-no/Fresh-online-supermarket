package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
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
			throw new BaseException("购买数量大于库存，请重新输入数量");
		BeanShoppingCart u=null;
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select count(goodsid) from shoppingcart where user_name = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			int order_id;
			if(rs.next())
				order_id = rs.getInt(1) + 1;
			else
				order_id = 0;
			rs.close();
			pst.close();
			
			
				sql = "insert into shoppingcart(goodsid,lbid,goodsname,price,amount,userid) values(?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				pst.setString(1, goods.getGoodsid());
				pst.setString(2, goods.getLbid());
				pst.setString(3, goods.getGoodsname());
				pst.setDouble(4, goods.getGprice());
				pst.setInt(5, amount);
				pst.setString(6, BeanUser.currentLoginUser.getUserid());
				pst.execute();
				pst.close();
				u = new BeanShoppingCart();
				u.setGoodsid(goods.getGoodsid());
				u.setLbid(goods.getLbid());
				u.setGoodsname(goods.getGoodsname());
				u.setPrice(goods.getGprice());
				u.setAmount(amount);	
			
			
			
			conn.commit();
			JOptionPane.showMessageDialog(null, "加入购物车成功");
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
	/*public BeanShoppingCar addGoods(BeanGoodsDetails BGD,int num) throws BaseException {
		if(BGD.getGoods_num() < num)
			throw new BusinessException("购买数量大于库存，请重新输入数量");
		BeanShoppingCar BSC = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select count(order_id) from user_shoppingCar where user_name = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_name());
			java.sql.ResultSet rs = pst.executeQuery();
			int order_id;
			if(rs.next())
				order_id = rs.getInt(1) + 1;
			else
				order_id = 0;
			rs.close();
			pst.close();
			sql = "select id,num from user_shoppingCar where goods_name = ? and merchant_name = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, BGD.getGoods_name());
			pst.setString(2, BGD.getMerchant_Name());
			rs = pst.executeQuery();
			int flag = 0;
			if(rs.next()) {
				num += rs.getInt(2);
				if(num > BGD.getGoods_num())
					throw new BusinessException("购买数量大于库存，请重新输入数量");
				flag = 1;
			}
			rs.close();
			pst.close();
			if(flag == 0) {
				sql = "insert into user_shoppingCar(order_id,goods_name,merchant_name,goods_price,num,user_name) values(?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, order_id);
				pst.setString(2, BGD.getGoods_name());
				pst.setString(3, BGD.getMerchant_Name());
				pst.setDouble(4, BGD.getGoods_price());
				pst.setInt(5, num);
				pst.setString(6, BeanUser.currentLoginUser.getUser_name());
				pst.execute();
				pst.close();
				BSC = new BeanShoppingCar();
				BSC.setOrder_id(order_id);
				BSC.setGoods_name(BGD.getGoods_name());
				BSC.setMerchant_name(BGD.getMerchant_Name());
				BSC.setGoods_price(BGD.getGoods_price());
				BSC.setNum(num);	
			}
			else {
				pst.close();
				sql = "update user_shoppingCar set num = ? where goods_name = ? and merchant_name = ?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, num);
				pst.setString(2, BGD.getGoods_name());
				pst.setString(3, BGD.getMerchant_Name());
				pst.execute();
				pst.close();
			}
			conn.commit();
			JOptionPane.showMessageDialog(null, "加入购物车成功");
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
		return BSC;
	}*/
}
