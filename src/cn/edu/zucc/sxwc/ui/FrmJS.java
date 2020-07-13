package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PRIVATE_MEMBER;

import cn.edu.zucc.sxwc.comtrol.example.Address;
import cn.edu.zucc.sxwc.comtrol.example.Coupon;
import cn.edu.zucc.sxwc.comtrol.example.ShoppingCart;
import cn.edu.zucc.sxwc.model.BeanAddress;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrmJS extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel label = new JLabel("收货地址");
	private JLabel lblNewLabel = new JLabel("优惠券");
	private JComboBox cmbaddress = new JComboBox();
	private JComboBox cmbcoupon = new JComboBox();
	private JLabel lblNewLabel_1 = new JLabel("合计:");
	private JLabel lblNewLabel_2 = new JLabel();
	private JButton okButton = new JButton("提交订单");
	private JButton cancelButton = new JButton("返回");
	List<BeanAddress> address=new ArrayList<BeanAddress>();
	List<BeanCoupon>  coupon=new ArrayList<BeanCoupon>();
	public float totalprice1() {
		float sum = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select sumprice from shoppingcart where userid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) 
				sum += rs.getFloat(1) ;
			conn.commit();
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
		return sum;
	}
	public float endprice(String couponid){
		float eprice=0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
		
		String sql="select jmmoney from coupon where couponid=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, couponid);
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			//BeanCoupon c=new BeanCoupon();
			//c.setSymoney(rs.getFloat(1));
			eprice=this.totalprice1()-rs.getFloat(1);
		}
		else {
			eprice=this.totalprice1();
		}
		rs.close();
		pst.close();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {

					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return eprice;
	}
	
	public FrmJS(JDialog f, String s, boolean b) {
		super(f,s,b);
		setBounds(100, 100, 546, 431);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 514, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		cmbaddress.setBounds(168, 23, 208, 24);
		cmbaddress.addItem("------请选择地址------");
		
		try {
			Address ad=new Address();
			address=ad.loadAddress();
		}catch(BaseException e1){
			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(int i=0;i<address.size();i++) {
			cmbaddress.addItem(address.get(i).getAddid()+" "+address.get(i).getProvince()+address.get(i).getCity()+address.get(i).getArea()+address.get(i).getAddress()+" "+address.get(i).getContact()+" "+address.get(i).getPhonenum());
		}
		contentPanel.add(cmbaddress);
		label.setBounds(52, 26, 72, 18);
		contentPanel.add(label);
		lblNewLabel.setBounds(52, 82, 72, 18);
		contentPanel.add(lblNewLabel);
		
		cmbcoupon.setBounds(168, 79, 208, 24);
		cmbcoupon.addItem("------请选择优惠券------");
		try {
			Coupon cp=new Coupon();
			coupon=cp.loadCp();
		}catch(BaseException e1){
			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(coupon.size()==0) {
			cmbcoupon.addItem("您暂时还没有优惠券");
		}else {
			int cnt=0;
			int j=0;
			for(int i=0;i<coupon.size();i++) {
				
				if(coupon.get(i).getEnddate().before(new Date())) {
					j++;
					if(j==coupon.size())cmbcoupon.addItem("无可用优惠券");
					
				}
				else if(coupon.get(i).getSymoney()<=this.totalprice()&&coupon.get(i).getEnddate().after(new Date())) {
					cmbcoupon.addItem(coupon.get(i).getCouponid()+" 满"+coupon.get(i).getSymoney()+"-"+coupon.get(i).getJmmoney());
				}
				else if(coupon.get(i).getSymoney()>this.totalprice()) {
					j++;
					if(j==coupon.size()) {
						cmbcoupon.addItem("无可用优惠券");
					}
				}
			}
		}
		
		contentPanel.add(cmbcoupon);
		
		lblNewLabel_1.setBounds(52, 164, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		lblNewLabel_2.setBounds(168, 164, 208, 18);
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(14, 335, 500, 37);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		this.cmbaddress.addActionListener(this);
		this.cmbcoupon.addActionListener(this);
		this.okButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.cancelButton) {
			this.setVisible(false);
		} else if(e.getSource() == this.okButton) {
			try {
				String str = "";
				int i = 0;
				String addid ;
				if("------请选择地址------".equals(this.cmbaddress.getSelectedItem().toString())||this.cmbaddress.getSelectedIndex()<0) {
					JOptionPane.showMessageDialog(null, "请点击配送地址", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					while(String.valueOf(this.cmbaddress.getSelectedItem()).charAt(i) != ' ') {
						str = str + String.valueOf(this.cmbaddress.getSelectedItem()).charAt(i);
						i++;
					}
					 addid = str;
					/*try {
						addid = Integer.parseInt(str.replaceAll(" ", ""));
					} catch (NumberFormatException e1) {
					    e1.printStackTrace();
					}*/
				}
				
				String str1 = "";
				i = 0;
				String couponid = ""  ;
				if("------请选择优惠券------".equals(this.cmbcoupon.getSelectedItem().toString())||this.cmbcoupon.getSelectedIndex()<0)
				{
					JOptionPane.showMessageDialog(null, "请点击优惠券", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else if("您暂时还没有优惠券".equals(this.cmbcoupon.getSelectedItem().toString())||"无可用优惠券".equals(this.cmbcoupon.getSelectedItem().toString())) {
				couponid="";
			}
				else {
					while(String.valueOf(this.cmbcoupon.getSelectedItem()).charAt(i) != ' ') {
						str1 = str1 + String.valueOf(this.cmbcoupon.getSelectedItem()).charAt(i);
						i++;
					}
					couponid=str1;
				}
				
				ShoppingCart cart=new ShoppingCart();
				cart.placeorder(addid,couponid);
				this.setVisible(false);
				//FrmUserMain a=new FrmUserMain();
				//int lbid =
				//a.reloadLbGoodsTabel(lbid);
				//int lbid=FrmUserMain.this.lbtable.getSelectedRow();
				//FrmUserMain.this.reloadLbGoodsTabel(lbid);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}	
		}else if(e.getSource()==this.cmbcoupon) {
			String str1 = "";
			int i = 0;
			String couponid = ""  ;
			if("------请选择优惠券------".equals(this.cmbcoupon.getSelectedItem().toString())||this.cmbcoupon.getSelectedIndex()<0)
			{
				JOptionPane.showMessageDialog(null, "请点击优惠券", "错误",JOptionPane.ERROR_MESSAGE);
			return;
}else if("您暂时还没有优惠券".equals(this.cmbcoupon.getSelectedItem().toString())||"无可用优惠券".equals(this.cmbcoupon.getSelectedItem().toString())) {
			couponid="";
}
			else {
				while(String.valueOf(this.cmbcoupon.getSelectedItem()).charAt(i) != ' ') {
					str1 = str1 + String.valueOf(this.cmbcoupon.getSelectedItem()).charAt(i);
					i++;
				}
				couponid=str1;
			}
			 lblNewLabel_2.setText(String.valueOf(this.endprice(couponid))); 
		}
		else if(e.getSource()==this.cmbaddress) {
			String str = "";
			int i = 0;
			String addid ="";
			if("------请选择地址------".equals(this.cmbaddress.getSelectedItem().toString())||this.cmbaddress.getSelectedIndex()<0) {
				JOptionPane.showMessageDialog(null, "请点击配送地址", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				while(String.valueOf(this.cmbaddress.getSelectedItem()).charAt(i) != ' ') {
					str = str + String.valueOf(this.cmbaddress.getSelectedItem()).charAt(i);
					i++;
				}
				 addid = str;
			}
		}
	}
	
	public float totalprice() {
		float sum = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select sumprice from shoppingcart where userid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) 
				sum += rs.getFloat(1) ;
			conn.commit();
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
		return sum;
	}
}
