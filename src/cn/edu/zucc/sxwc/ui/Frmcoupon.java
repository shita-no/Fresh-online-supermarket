package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.sxwc.comtrol.example.Coupon;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;
import cn.edu.zucc.sxwc.util.BaseException;

public class Frmcoupon extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Object tblcpTitle[]=BeanCoupon.CouponTitles;
	private Object tblcpData[][];
	DefaultTableModel tabcpModel=new DefaultTableModel();
	private JTable cpTable=new JTable(tabcpModel);
	List<BeanCoupon> allcp=null;
	
	private void reloadcpTable(){
		try {
			Coupon cp=new Coupon();
			allcp=cp.loadCp();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblcpData =  new Object[allcp.size()][BeanCoupon.CouponTitles.length];
		for(int i=0;i<allcp.size();i++){
			for(int j=0;j<BeanCoupon.CouponTitles.length;j++)
				tblcpData[i][j]=allcp.get(i).getCell(j);
		}
		tabcpModel.setDataVector(tblcpData,tblcpTitle);
		this.cpTable.validate();
		this.cpTable.repaint();
	}
	public Frmcoupon(Frame f, String s, boolean b) {
		super(f, s, b);
		setTitle("ÓÅ»ÝÈ¯");
		setBounds(100, 100, 535, 300);
		contentPanel.setBounds(0, 0, 503, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadcpTable();
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(this.cpTable);
		scrollPane.setBounds(0, 0, 517, 217);
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 217, 517, 37);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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
		
	}

}
