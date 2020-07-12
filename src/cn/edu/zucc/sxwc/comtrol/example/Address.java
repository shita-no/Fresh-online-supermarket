package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanAddress;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Address {
	public List<BeanAddress> loadAddress() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanAddress> result=new ArrayList<BeanAddress>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select addid,province,city,area,address,contacts,phonenum from addressform where userid =? order by addid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanAddress u=new BeanAddress();
				u.setAddid(rs.getString(1));
				//u.setUserid(BeanUser.currentLoginUser.getUserid());
				u.setProvince(rs.getString(2));
				u.setCity(rs.getString(3));
				u.setArea(rs.getString(4));
				u.setAddress(rs.getString(5));
				u.setContact(rs.getString(6));
				u.setPhonenum(rs.getString(7));
				
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

	public void deleteAdd(String addid, String userid) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from addressform where addid=? and userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,addid);
			pst.setString(2, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("所选不 存在");
			//if(rs.getDate(1)!=null) throw new BusinessException("该账号已经被删除");
			rs.close();
			pst.close();
			
			sql="delete from addressform where addid=? and userid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, addid);
			pst.setString(2, userid);
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

	public void createManager(BeanAddress ad) throws BaseException{
		// TODO Auto-generated method stub
		if(ad.getAddid()==null || "".equals(ad.getAddid()) || ad.getAddid().length()>50){
			throw new BusinessException("地址编号必须是1-50个字");
		}
		if(ad.getProvince()==null  ){
			throw new BusinessException("'省'不能为空");
		}
		if(ad.getCity()==null || "".equals(ad.getCity()) ){
			throw new BusinessException("'市'必须填写");
		}
		if(ad.getArea()==null || "".equals(ad.getArea()) ){
			throw new BusinessException("'区'必须填写");
		}
		if(ad.getAddress()==null || "".equals(ad.getAddress()) ){
			throw new BusinessException("'地址'必须填写");
		}
		if(ad.getContact()==null || "".equals(ad.getContact()) ){
			throw new BusinessException("'联系人'必须填写");
		}
		if(ad.getPhonenum()==null || "".equals(ad.getPhonenum()) ){
			throw new BusinessException("'电话'必须填写");
		}
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from addressform where userid=? and addid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserid());
			pst.setString(2, ad.getAddid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("你的地址编号已经存在");
			rs.close();
			pst.close();
			sql="insert into addressform(addid,userid,province,city,area,address,contacts,phonenum) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, ad.getAddid());
			pst.setString(2, BeanUser.currentLoginUser.getUserid());
			pst.setString(3, ad.getProvince());
			pst.setString(4,ad.getCity());
			pst.setString(5, ad.getArea());
			pst.setString(6, ad.getAddress());
			pst.setString(7, ad.getContact());
			pst.setString(8, ad.getPhonenum());
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
