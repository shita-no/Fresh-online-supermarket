package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanMenu;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Menu {
	public List<BeanMenu> loadMenu(BeanGoods goodsid)throws BaseException{
		List<BeanMenu> result=new ArrayList<BeanMenu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select menuid,goodsid,menuname,ingredients,step,picture from menu where goodsid=? order by menuid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, goodsid.getGoodsid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanMenu u=new BeanMenu();
				u.setMenuid(rs.getString(1));
				u.setGoodsid(rs.getString(2));
				u.setMenuname(rs.getString(3));
				u.setIngredients(rs.getString(4));
				u.setStep(rs.getString(5));
				u.setPicture(rs.getString(6));
				result.add(u);
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
		return result;
	}
}
