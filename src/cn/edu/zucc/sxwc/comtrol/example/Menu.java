package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanMenu;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Menu {
	public List<BeanMenu> loadMenu(BeanGoods goodsid)throws BaseException{
		List<BeanMenu> result=new ArrayList<BeanMenu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select menuid,goodsid,menuname,ingredients,step,picture from menu where goodsid=? and lbid=? order by menuid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, goodsid.getGoodsid());
			pst.setString(2, goodsid.getLbid());
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

	public void createmenu(BeanMenu menu) throws BaseException{
		// TODO Auto-generated method stub
		if(menu.getMenuid()==null || "".equals(menu.getMenuid()) || menu.getMenuid().length()>50){
			throw new BusinessException("菜谱号长度必须是1-50位");
		}
		if(menu.getMenuname()==null || "".equals(menu.getMenuname()) || menu.getMenuname().length()>50){
			throw new BusinessException("菜谱名称长度必须是1-50位");
		}
		
		if(menu.getIngredients()==null ||"".equals(menu.getIngredients()) || menu.getIngredients().length()>100) {
			throw new BusinessException("菜谱用料必须是1-50位");
		}
		if(menu.getStep()==null ||"".equals(menu.getStep()) || menu.getStep().length()>100) {
			throw new BusinessException("步骤必须是1-100个字");
		}
		if(menu.getPicture()==null ||"".equals(menu.getPicture()) || menu.getPicture().length()>100) {
			throw new BusinessException("图片描述必须是1-100个字");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from menu where lbid=? and goodsid=? and menuid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,menu.getLbid());
			pst.setString(2,menu.getGoodsid());
			pst.setString(3,menu.getMenuid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该菜谱编号已经存在");
			rs.close();
			pst.close();
			sql="insert into menu(menuid,lbid,goodsid,menuname,ingredients,step,picture) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, menu.getMenuid());
			pst.setString(2, menu.getLbid());
			pst.setString(3, menu.getGoodsid());
			pst.setString(4,menu.getMenuname());
			pst.setString(5,menu.getIngredients());
			pst.setString(6, menu.getStep());
			pst.setString(7, menu.getPicture());
			
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
