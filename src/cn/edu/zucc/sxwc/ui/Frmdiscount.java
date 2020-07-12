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
import cn.edu.zucc.sxwc.comtrol.example.Discount;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanDiscount;
import cn.edu.zucc.sxwc.util.BaseException;

public class Frmdiscount extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Object tbldcTitle[]=BeanDiscount.DiscountTitles;
	private Object tbldcData[][];
	DefaultTableModel tabdcModel=new DefaultTableModel();
	private JTable dcTable=new JTable(tabdcModel);
	List<BeanDiscount> alldc=null;
	
	private void reloaddcTable(){
		try {
			Discount dc=new Discount();
			alldc=dc.loadDc();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tbldcData =  new Object[alldc.size()][BeanDiscount.DiscountTitles.length];
		for(int i=0;i<alldc.size();i++){
			for(int j=0;j<BeanDiscount.DiscountTitles.length;j++)
				tbldcData[i][j]=alldc.get(i).getCell(j);
		}
		tabdcModel.setDataVector(tbldcData,tbldcTitle);
		this.dcTable.validate();
		this.dcTable.repaint();
	}

	public Frmdiscount(Frame f, String s, boolean b) {
		super(f, s, b);
		setTitle("满折信息商品关联表");
		setBounds(100, 100, 693, 400);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 675, 291);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloaddcTable();
		JScrollPane scrollPane = new JScrollPane(this.dcTable);
		scrollPane.setBounds(0, 0, 675, 229);
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(14, 304, 647, 37);
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
