package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanPj;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Pj {
	public List<BeanPj> loadPj(BeanGoods goods) throws BaseException{
		// TODO Auto-generated method stub
		List<BeanPj> result=new ArrayList<BeanPj>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/*String sql="select max(pjid) from pjform where lbid=? and goodsid=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, goods.getLbid());
			pst.setString(2, goods.getGoodsid());
			ResultSet rs=pst.executeQuery();
			int pjid=1;
			if(rs.next()) {
				pjid+=rs.getInt(1);
			}
			else {
				pjid=1;
			}*/
			String sql="select pjid,lbid,userid,goodsid,pjcontent,pjdate,rank,photo from pjform where lbid=? and goodsid=? order by pjid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, goods.getLbid());
			pst.setString(2, goods.getGoodsid());
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanPj u=new BeanPj();
				u.setPjid(rs.getInt(1));
				//u.setUserid(BeanUser.currentLoginUser.getUserid());
				u.setLbid(rs.getString(2));
				u.setUserid(rs.getString(3));
				u.setGoodsid(rs.getString(4));
				u.setPjcontent(rs.getString(5));
				u.setPjDate(rs.getDate(6));
				u.setRank(rs.getInt(7));
				u.setPhoto(rs.getString(8));
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

	public void createpj(BeanPj pj) throws BaseException{
		// TODO Auto-generated method stub
		if(pj.getPjcontent()==null || "".equals(pj.getPjcontent()) || pj.getPjcontent().length()>100){
			throw new BusinessException("评价内容必须是1-100个字");
		}
		if(pj.getRank()<=0 || pj.getRank()>5){
			throw new BusinessException("星级只能为1-5星级");
		}
		Connection conn=null;
		try {
			int pjid=0;
			conn=DBUtil.getConnection();
			String sql1="select userid from pjform where lbid=? and goodsid=?";
			java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
			
			pst1.setString(1,pj.getLbid());
			pst1.setString(2,pj.getGoodsid());
			java.sql.ResultSet rs1=pst1.executeQuery();
			while(rs1.next()) {
				if(rs1.getString(1).equals(BeanUser.currentLoginUser.getUserid()))
					throw new BusinessException("你已经评价过该商品");
			}
			rs1.close();
			pst1.close();
			String sql="select max(pjid) from pjform where lbid=? and goodsid=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,pj.getLbid());
			pst.setString(2,pj.getGoodsid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				pjid=rs.getInt(1)+1;
			}
			else pjid=1;
			rs.close();
			pst.close();
			sql="insert into pjform(pjid,lbid,userid,goodsid,pjcontent,pjdate,rank,photo) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, pjid);
			pst.setString(2, pj.getLbid());
			pst.setString(3, BeanUser.currentLoginUser.getUserid());
			pst.setString(4,pj.getGoodsid());
			pst.setString(5,pj.getPjcontent());
			pst.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			pst.setInt(7, pj.getRank());
			pst.setString(8, pj.getPhoto());
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

	
