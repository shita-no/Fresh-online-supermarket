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
			if(!rs.next()) throw new BusinessException("����������");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? and userid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			pst.setString(2, userid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("������".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? and userid=?";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "���ʹ�");
					pst1.setInt(2, orderid);
					pst1.setString(3, userid);
					pst1.execute();
					pst1.close();
				}
				else if("�µ�".equals(rs.getString(1))){
					throw new BusinessException("�����ĵȴ��̼ҷ�������");
				}
				else if("���˻�".equals(rs.getString(1))){
					throw new BusinessException("�������˻����޷�ȷ���ջ�");
				}
				else if("���ʹ�".equals(rs.getString(1))){
					throw new BusinessException("�����ѱ�����");
				}
				else if("�˻���".equals(rs.getString(1))){
					throw new BusinessException("�����ĵȴ��̼Ҵ����˻�");
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
			if(!rs.next()) throw new BusinessException("����������");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? and userid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			pst.setString(2, userid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("���ʹ�".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? and userid=?";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "�˻���");
					pst1.setInt(2, orderid);
					pst1.setString(3, userid);
					pst1.execute();
					pst1.close();
				}
				else if("�µ�".equals(rs.getString(1))){
					throw new BusinessException("�����ĵȴ��̼ҷ�������");
				}
				else if("���˻�".equals(rs.getString(1))){
					throw new BusinessException("�������˻���������ѡ�񶩵�");
				}
				else if("������".equals(rs.getString(1))){
					throw new BusinessException("�����ĵȴ���������");
				}
				else if("�˻���".equals(rs.getString(1))){
					throw new BusinessException("�̼����ڴ����˻��������ĵȴ�");
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
			if(!rs.next()) throw new BusinessException("����������");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? ";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("�µ�".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? ";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "������");
					pst1.setInt(2, orderid);
					pst1.execute();
					pst1.close();
				}
				else if("������".equals(rs.getString(1))){
					throw new BusinessException("�ö����޷�������");
				}
				else if("���˻�".equals(rs.getString(1))){
					throw new BusinessException("�ö����޷�������");
				}
				else if("���ʹ�".equals(rs.getString(1))){
					throw new BusinessException("�ö����޷�������");
				}
				else if("�˻���".equals(rs.getString(1))){
					throw new BusinessException("�ö����޷�������");
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
			if(!rs.next()) throw new BusinessException("����������");
			rs.close();
			pst.close();
			sql="select state from goodsorder where orderid=? ";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderid);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				if("�˻���".equals(rs.getString(1))) {
					String sql1="update goodsorder set state=? where orderid=? ";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, "���˻�");
					pst1.setInt(2, orderid);
					pst1.execute();
					pst1.close();
				}
				else if("������".equals(rs.getString(1))){
					throw new BusinessException("�ö������˻�����");
				}
				else if("���˻�".equals(rs.getString(1))){
					throw new BusinessException("�ö������˻�����");
				}
				else if("���ʹ�".equals(rs.getString(1))){
					throw new BusinessException("�ö������˻�����");
				}
				else if("�µ�".equals(rs.getString(1))){
					throw new BusinessException("�ö������˻�����");
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


