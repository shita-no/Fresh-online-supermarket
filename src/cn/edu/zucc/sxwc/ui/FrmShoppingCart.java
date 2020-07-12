package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.sxwc.comtrol.example.Goods;
import cn.edu.zucc.sxwc.comtrol.example.ShoppingCart;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;

import javax.swing.JLabel;

public class FrmShoppingCart extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Object tblTitle[]=BeanShoppingCart.CartTitles;
	private Object tblcartData[][];
	DefaultTableModel tabcartmod=new DefaultTableModel();
	private JTable carttable=new JTable(tabcartmod);
	List<BeanShoppingCart> allcart=null;
	private List<BeanGoods> goods;
	private BeanShoppingCart cart1=null;
	private JLabel lblNewLabel_1= new JLabel(String.valueOf(this.toprice()));
	private JLabel lblNewLabel;

	private void reloadCartTable(){
		try {
			ShoppingCart cart=new ShoppingCart();
			allcart=cart.loadCart();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblcartData =  new Object[allcart.size()][BeanShoppingCart.CartTitles.length];
		for(int i=0;i<allcart.size();i++){
			for(int j=0;j<BeanShoppingCart.CartTitles.length;j++)
				{
					tblcartData[i][j]=allcart.get(i).getCell(j);
				}
			}
			tabcartmod.setDataVector(tblcartData,tblTitle);
			this.carttable.validate();
			this.carttable.repaint();
	}
	public float toprice() {
		float sum = 0;
		try {
			ShoppingCart cart=new ShoppingCart();
			allcart=cart.loadCart();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			
		}
		tblcartData =  new Object[allcart.size()][BeanShoppingCart.CartTitles.length];
		for(int i=0;i<allcart.size();i++){
			for(int j=0;j<BeanShoppingCart.CartTitles.length;j++)
				{
					tblcartData[i][j]=allcart.get(i).getCell(j);
				}
			sum+=Float.parseFloat(allcart.get(i).getCell(5));
			}
		
		return sum;
	}
	/*public float totalprice() {
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
	}*/
	
	public FrmShoppingCart(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("购物车");
		setBounds(100, 100, 577, 403);
		contentPanel.setBounds(0, 207, 432, 47);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadCartTable();
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(this.carttable);
		scrollPane.setBounds(0, 0, 559, 288);
		this.getContentPane().add(scrollPane);
		contentPanel.setLayout(null);
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		this.getContentPane().add(contentPanel);
		
		lblNewLabel = new JLabel("合计:");
		lblNewLabel.setBounds(297, 326, 72, 18);
		getContentPane().add(lblNewLabel);
		{
			btnNewButton = new JButton("结算");
			btnNewButton.setBounds(482, 322, 63, 27);
			getContentPane().add(btnNewButton);
		}
		{
			btnNewButton_1 = new JButton("移除购物车");
			btnNewButton_1.setBounds(14, 322, 107, 27);
			getContentPane().add(btnNewButton_1);
		}
		{
			
			lblNewLabel_1.setBounds(356, 326, 72, 18);
			getContentPane().add(lblNewLabel_1);
		}
		this.btnNewButton_1.addActionListener(this);
		this.btnNewButton.addActionListener(this);
        /*btnNewButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "您的消费总额为"+sum+"元");
                
            }
        });*/
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNewButton) {
			this.setVisible(false);
			FrmJS js = new FrmJS(this,"结算",true);
			js.setVisible(true);
		}
		else if(e.getSource()==btnNewButton_1) {
			int i=this.carttable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择购物车中商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定移除商品吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String goodsid=this.tblcartData[i][0].toString();
				String userid=BeanUser.currentLoginUser.getUserid();
				String lbid=this.tblcartData[i][1].toString();
				try {
					(new ShoppingCart()).deletecart(userid,goodsid,lbid);
					this.reloadCartTable();
					lblNewLabel_1.setText(String.valueOf(this.toprice()));
					//lblNewLabel_1= new JLabel(String.valueOf(this.totalprice()));
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
