package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.sxwc.comtrol.example.Goodsorder;
import cn.edu.zucc.sxwc.comtrol.example.Orderxq;
import cn.edu.zucc.sxwc.model.BeanGoodsorder;
import cn.edu.zucc.sxwc.model.BeanOrderxq;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.DBUtil;
import cn.edu.zucc.sxwc.util.DbException;

public class Frmorder1 extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton btnNewButton = new JButton("确认发货");
	private JButton btnNewButton_1 = new JButton("同意退货");
	private Object tblTitle[]=BeanGoodsorder.GoodsorderTitles1;
	private Object tblgorderData[][];
	DefaultTableModel tabgordermod=new DefaultTableModel();
	private JTable gordertable=new JTable(tabgordermod);
	List<BeanGoodsorder> allgorder=null;
	private BeanGoodsorder curgorder=null;
	List<BeanOrderxq>allorder=null;
	
	private Object tblorderTitle[]=BeanOrderxq.OrderxqTitles;
	private Object tblorderData[][];
	DefaultTableModel tabordermod=new DefaultTableModel();
	private JTable ordertable=new JTable(tabordermod);
	
	private void reloadgorderTable(){
		try {
			Goodsorder gorder=new Goodsorder();
			allgorder=gorder.loadGorder1();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblgorderData =  new Object[allgorder.size()][BeanGoodsorder.GoodsorderTitles1.length];
		for(int i=0;i<allgorder.size();i++){
			for(int j=0;j<BeanGoodsorder.GoodsorderTitles1.length;j++)
				{
					tblgorderData[i][j]=allgorder.get(i).getCell1(j);
				}
			}
			tabgordermod.setDataVector(tblgorderData,tblTitle);
			this.gordertable.validate();
			this.gordertable.repaint();
	}
	private void reloadorderTable(int goodsid){
		if(goodsid<0) return;
		curgorder=allgorder.get(goodsid);
		try {
			Orderxq order=new Orderxq();
			allorder=order.loadorder(curgorder);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblorderData =  new Object[allorder.size()][BeanOrderxq.OrderxqTitles.length];
		for(int i=0;i<allorder.size();i++){
			for(int j=0;j<BeanOrderxq.OrderxqTitles.length;j++)
				{
					tblorderData[i][j]=allorder.get(i).getCell(j);
				}
			}
			tabordermod.setDataVector(tblorderData,tblorderTitle);
			this.ordertable.validate();
			this.ordertable.repaint();
	}
	
	public Frmorder1(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("商品订单处理");
		setBounds(100, 100, 790, 514);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 772, 375);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadgorderTable();
		this.getContentPane().add(contentPanel);;
		//getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		JScrollPane scrollPane1 = new JScrollPane(this.ordertable);
		scrollPane1.setBounds(412, 0, 358, 297);
		contentPanel.add(scrollPane1);
		JScrollPane scrollPane = new JScrollPane(this.gordertable);
		scrollPane.setBounds(0, 0, 413, 297);
		contentPanel.add(scrollPane);
		btnNewButton_1.setBounds(407, 411, 113, 27);
		getContentPane().add(btnNewButton_1);
		btnNewButton.setBounds(38, 411, 113, 27);
		getContentPane().add(btnNewButton);
		this.gordertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=Frmorder1.this.gordertable.getSelectedRow();
				if(i<0) {
					return;
				}
				Frmorder1.this.reloadorderTable(i);
			}
		});
		this.btnNewButton.addActionListener(this);
		this.btnNewButton_1.addActionListener(this);
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
		if(e.getSource() == this.btnNewButton) {
			int i=this.gordertable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请先选择商品订单","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanGoodsorder gorder=this.allgorder.get(i);
			if(JOptionPane.showConfirmDialog(this,"确定要发货吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					(new Goodsorder()).update1(gorder.getOrderid());
					this.reloadgorderTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
	}
		else if(e.getSource() == this.btnNewButton_1) {
			int i=this.gordertable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请先选择商品订单","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanGoodsorder gorder=this.allgorder.get(i);
			if(JOptionPane.showConfirmDialog(this,"确定要同意退货吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					(new Goodsorder()).returngoods1(gorder.getOrderid());
					this.reloadgorderTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
}
}
