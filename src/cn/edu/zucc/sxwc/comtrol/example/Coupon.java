package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Where;

import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Coupon {

	public List<BeanCoupon> loadCp() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select couponid,coucontent,symoney,jmmoney,bedate,enddate from coupon where userid =? order by couponid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanCoupon u=new BeanCoupon();
				u.setCouponid(rs.getString(1));
				u.setCoucontent(rs.getString(2));
				u.setSymoney(rs.getFloat(3));
				u.setJmmoney(rs.getFloat(4));
				u.setBedate(rs.getDate(5));
				u.setEnddate(rs.getDate(6));
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
	public List<BeanCoupon> loadCp1() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select couponid,coucontent,symoney,jmmoney,bedate,enddate,userid from coupon order by couponid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			//pst.setString(1,BeanManager.currentLoginManager.getEmployeeid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanCoupon u=new BeanCoupon();
				u.setCouponid(rs.getString(1));
				u.setCoucontent(rs.getString(2));
				u.setSymoney(rs.getFloat(3));
				u.setJmmoney(rs.getFloat(4));
				u.setBedate(rs.getDate(5));
				u.setEnddate(rs.getDate(6));
				u.setUserid(rs.getString(7));
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
	public void createcoupon(BeanCoupon cp) throws BaseException{
		// TODO Auto-generated method stub
		if(cp.getCouponid()==null || "".equals(cp.getCouponid()) || cp.getCouponid().length()>50){
			throw new BusinessException("优惠券编号必须是1-50个字");
		}
		if(cp.getCoucontent()==null || "".equals(cp.getCoucontent()) || cp.getCoucontent().length()>50){
			throw new BusinessException("优惠券内容不能为空");
		}
		if(cp.getSymoney()<0 ){
			throw new BusinessException("适用金额不能为负");
		}
		if(cp.getJmmoney()<0 ){
			throw new BusinessException("减免金额不能为负");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from coupon where couponid=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,cp.getCouponid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该优惠券编号已经存在");
			rs.close();
			pst.close();
			sql="insert into coupon(couponid,coucontent,symoney,jmmoney,bedate,enddate,userid) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, cp.getCouponid());
			pst.setString(2, cp.getCoucontent());
			pst.setFloat(3, cp.getSymoney());
			pst.setFloat(4,cp.getJmmoney());
			pst.setDate(5,new java.sql.Date(System.currentTimeMillis()));
			pst.setDate(6, new java.sql.Date(System.currentTimeMillis()+2592000000L));
			pst.setString(7, "");
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
	public void getcoupon() throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select couponid,coucontent,symoney,jmmoney,bedate,enddate,userid from coupon  ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				/*BeanCoupon u=new BeanCoupon();
				u.setCouponid(rs.getString(1));
				u.setCoucontent(rs.getString(2));
				u.setSymoney(rs.getFloat(3));
				u.setJmmoney(rs.getFloat(4));
				u.setBedate(rs.getDate(5));
				u.setEnddate(rs.getDate(6));
				u.setUserid(rs.getString(7));*/
				if("".equals(rs.getString(7))) {
					String sql1="update coupon set userid=? where couponid=?";
					java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, BeanUser.currentLoginUser.getUserid());
					pst1.setString(2, rs.getString(1));
					pst1.execute();
					pst1.close();
				}
			}
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
		
	}
	
}
