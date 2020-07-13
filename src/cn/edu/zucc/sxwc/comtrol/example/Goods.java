package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Goods {
	public List<BeanGoods> loadGoods(BeanLb lb) throws BaseException {
		List<BeanGoods> result=new ArrayList<BeanGoods>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods "
					+ "where lbid = ? order by goodsid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, lb.getLbid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGoods p=new BeanGoods();
				p.setGoodsid(rs.getString(1));
				p.setLbid(rs.getString(2));
				p.setGoodsname(rs.getString(3));
				p.setGprice(rs.getFloat(4));
				p.setGvipprice(rs.getFloat(5));
				p.setGamount(rs.getInt(6));
				p.setGuige(rs.getString(7));
				p.setDatails(rs.getString(8));
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
	public void creategoods(BeanGoods good) throws BaseException{
		// TODO Auto-generated method stub
		if(good.getGoodsid()==null || "".equals(good.getGoodsid()) || good.getGoodsid().length()>50){
			throw new BusinessException("商品编号长度必须是1-50位");
		}
		if(good.getGoodsname()==null || "".equals(good.getGoodsname()) || good.getGoodsname().length()>50){
			throw new BusinessException("商品名称长度必须是1-50位");
		}
		if( good.getGprice()<0){
			throw new BusinessException("价格不能为负");
		}
		
		if(good.getGvipprice()<0){
			throw new BusinessException("会员价不能为负");
		}
		if(good.getGamount()<0){
			throw new BusinessException("数量不能为负");
		}
		if(good.getGuige()==null ||"".equals(good.getGuige()) || good.getGuige().length()>100) {
			throw new BusinessException("规格长度必须是1-50位");
		}
		if(good.getDatails()==null ||"".equals(good.getDatails()) || good.getDatails().length()>100) {
			throw new BusinessException("详情描述必须是1-100个字");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods where lbid=? and goodsid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,good.getLbid());
			pst.setString(2,good.getGoodsid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该商品编号已经存在");
			rs.close();
			pst.close();
			sql="insert into goods(goodsid,lbid,goodsname,gprice,gvipprice,gamount,guige,details) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, good.getGoodsid());
			pst.setString(2, good.getLbid());
			pst.setString(3, good.getGoodsname());
			//user.setPwd(user.getUserid());//默认密码为账号
			pst.setFloat(4,good.getGprice());
			pst.setFloat(5,good.getGvipprice());
			pst.setInt(6, good.getGamount());
			pst.setString(7, good.getGuige());
			pst.setString(8, good.getDatails());
			pst.execute();
			pst.close();
			//sql=""
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
	public void add(BeanGoods curgoods, int amount) throws BaseException{
		// TODO Auto-generated method stub
		BeanGoods u=null;
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			conn.setAutoCommit(false);
				String sql="update goods set gamount=gamount+? where  goodsid =? and lbid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, amount);
				
				pst.setString(2, curgoods.getGoodsid());
				pst.setString(3, curgoods.getLbid());
				pst.execute();
				pst.close();
				conn.commit();
				JOptionPane.showMessageDialog(null, "采购商品成功");
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
		
	}
	
	
}
