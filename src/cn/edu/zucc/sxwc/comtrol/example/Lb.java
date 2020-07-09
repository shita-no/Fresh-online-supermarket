package cn.edu.zucc.sxwc.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.util.BaseException;
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
}
