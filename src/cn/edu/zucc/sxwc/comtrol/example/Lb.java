package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Lb {
	public List<BeanLb> loadLb()throws BaseException{
		List<BeanLb> result=new ArrayList<BeanLb>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select lbid,lbname,described from lb order by lbid";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				BeanLb u=new BeanLb();
				u.setLbid(rs.getString(1));
				u.setLbname(rs.getString(2));
				u.setDescribed(rs.getString(3));
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

	public void createLb(BeanLb lb) throws BaseException{
		// TODO Auto-generated method stub
		if(lb.getLbid()==null || "".equals(lb.getLbid()) || lb.getLbid().length()>50){
			throw new BusinessException("类别编号长度必须是1-50位");
		}
		if(lb.getLbname()==null || "".equals(lb.getLbname()) || lb.getLbname().length()>50){
			throw new BusinessException("类别名称长度必须是1-50位");
		}
		if(lb.getDescribed()==null || "".equals(lb.getDescribed()) || lb.getDescribed().length()>100){
			throw new BusinessException("类别描述必须是1-100个字");
		}
		
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from lb where lbid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,lb.getLbid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该类别已经存在");
			rs.close();
			pst.close();
			sql="insert into lb(lbid,lbname,described) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, lb.getLbid());
			pst.setString(2, lb.getLbname());
			//user.setPwd(user.getUserid());//默认密码为账号
			pst.setString(3,lb.getDescribed());
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
