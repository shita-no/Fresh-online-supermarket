package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanMenu;
import cn.edu.zucc.sxwc.model.BeanRemenu;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Remenu {

	public void addmenu(BeanMenu menu, String ms,String lbid) throws BaseException{
		// TODO Auto-generated method stub
		
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="select * from remenu where  goodsid =? and lbid=? and menuid=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setString(1, menu.getGoodsid());
			pst.setString(2, lbid);
			pst.setString(3, menu.getMenuid());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				throw new BusinessException("该菜谱已在推荐中");
			}
			sql="insert into remenu(menuid,lbid,goodsid,description) value(?,?,?,?) ";
			pst=conn.prepareStatement(sql);
			pst.setString(1,menu.getMenuid() );
			pst.setString(2,lbid );
			pst.setString(3,menu.getGoodsid());
			pst.setString(4, ms);
			pst.execute();
			pst.close();
			conn.commit();
			JOptionPane.showMessageDialog(null, "成功加入推荐表");
	}catch(SQLException e) {
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

	public List<BeanRemenu> loadRe() throws BaseException{
		// TODO Auto-generated method stub
		List<BeanRemenu> result=new ArrayList<BeanRemenu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select menuid,lbid,goodsid,description from remenu order by menuid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanRemenu u=new BeanRemenu();
				u.setMenuid(rs.getString(1));
				u.setLbid(rs.getString(2));
				u.setGoodsid(rs.getString(3));
				u.setDescription(rs.getString(4));
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
}
		


