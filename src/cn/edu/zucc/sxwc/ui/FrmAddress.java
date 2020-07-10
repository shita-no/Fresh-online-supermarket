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

import cn.edu.zucc.sxwc.comtrol.example.Address;
import cn.edu.zucc.sxwc.comtrol.example.Coupon;
import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.model.BeanAddress;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;

public class FrmAddress extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Object tbladdTitle[]=BeanAddress.AddressTitles;
	private Object tbladdData[][];
	DefaultTableModel tabaddModel=new DefaultTableModel();
	private JTable adTable=new JTable(tabaddModel);
	List<BeanAddress> allad=null;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private void reloadcpTable(){
		try {
			Address ad=new Address();
			allad=ad.loadAddress();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tbladdData =  new Object[allad.size()][BeanAddress.AddressTitles.length];
		for(int i=0;i<allad.size();i++){
			for(int j=0;j<BeanAddress.AddressTitles.length;j++)
				tbladdData[i][j]=allad.get(i).getCell(j);
		}
		tabaddModel.setDataVector(tbladdData,tbladdTitle);
		this.adTable.validate();
		this.adTable.repaint();
	}
	
	public FrmAddress(Frame f, String s, boolean b) {
		super(f, s, b);
		setTitle("配送地址");
		setBounds(100, 100, 601, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 569, 241);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadcpTable();
		//getContentPane().add(contentPanel);
		JScrollPane scrollPane = new JScrollPane(this.adTable);
		scrollPane.setBounds(0, 0, 569, 192);
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		btnNewButton = new JButton("添加地址");
		btnNewButton.setBounds(14, 201, 113, 27);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("修改地址");
		btnNewButton_1.setBounds(180, 201, 113, 27);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("删除地址");
		btnNewButton_2.setBounds(358, 201, 113, 27);
		contentPanel.add(btnNewButton_2);
		
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
			FrmAddress_add dlg=new FrmAddress_add(this, "添加地址", true);
			dlg.setVisible(true);
			if(dlg.getaddress()!=null) {
				this.reloadcpTable();
			}
		}
		else if(e.getSource()==btnNewButton_2) {
			int i=this.adTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除所选地址吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String addid=this.tbladdData[i][0].toString();
				String userid=BeanUser.currentLoginUser.getUserid();
				try {
					(new Address()).deleteAdd(addid,userid);
					this.reloadcpTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
