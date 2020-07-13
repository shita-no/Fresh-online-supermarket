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
import cn.edu.zucc.sxwc.util.BaseException;

public class Frmcoupon1 extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Object tblcpTitle[]=BeanCoupon.CouponTitles1;
	private Object tblcpData[][];
	DefaultTableModel tabcpModel=new DefaultTableModel();
	private JTable cpTable=new JTable(tabcpModel);
	List<BeanCoupon> allcp=null;
	private JButton button = new JButton("ÃÌº””≈ª›»Ø");
	private void reloadcpTable(){
		try {
			Coupon cp=new Coupon();
			allcp=cp.loadCp1();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblcpData =  new Object[allcp.size()][BeanCoupon.CouponTitles1.length];
		for(int i=0;i<allcp.size();i++){
			for(int j=0;j<BeanCoupon.CouponTitles1.length;j++)
				tblcpData[i][j]=allcp.get(i).getCell1(j);
		}
		tabcpModel.setDataVector(tblcpData,tblcpTitle);
		this.cpTable.validate();
		this.cpTable.repaint();
	}
	public Frmcoupon1(Frame f, String s, boolean b) {
		super(f, s, b);
		setTitle("”≈ª›»Ø");
		setBounds(100, 100, 591, 373);
		contentPanel.setBounds(0, 0, 503, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadcpTable();
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(this.cpTable);
		scrollPane.setBounds(0, 0, 559, 217);
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		
		button.setBounds(0, 274, 113, 27);
		getContentPane().add(button);
		this.button.addActionListener(this);
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
		if(e.getSource()==button) {
			Frmcouponadd dlg=new Frmcouponadd(this, "ÃÌº””≈ª›»Ø", true);
			dlg.setVisible(true);
			if(dlg.getcoupon()!=null) {
				this.reloadcpTable();
			}
		}
	}
}
