package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.sxwc.comtrol.example.Coupon;
import cn.edu.zucc.sxwc.comtrol.example.Goods;
import cn.edu.zucc.sxwc.model.BeanAddress;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frmcouponadd extends JDialog implements ActionListener{
	private BeanCoupon cp=null;
	private final JPanel contentPanel = new JPanel();
    private JButton okButton = new JButton("ȷ��");
	private JButton cancelButton = new JButton("ȡ��");
	private JTextField textField=new JTextField(50);
	private JTextField textField_1=new JTextField(50);
	private JTextField textField_2=new JTextField(50);
	private JTextField textField_3=new JTextField(50);
	public Frmcouponadd(JDialog f, String s, boolean b) {
		super(f,s,b);
		setTitle("����Ż�ȯ");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�Ż�ȯ���");
		lblNewLabel.setBounds(33, 23, 86, 18);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setBounds(33, 75, 86, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("���ý��");
		lblNewLabel_2.setBounds(33, 123, 86, 18);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("������");
		lblNewLabel_3.setBounds(33, 175, 86, 18);
		contentPanel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(162, 20, 86, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(162, 72, 86, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(162, 120, 86, 24);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(162, 172, 86, 24);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 217, 432, 37);
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
		this.cancelButton.addActionListener(this);
		this.okButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.cancelButton) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.okButton){
			if(this.textField.getText()==null||"".equals(this.textField.getText()))
			{
				JOptionPane.showMessageDialog(null, "����д�Ż�ȯ���","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_1.getText()==null||"".equals(this.textField_1.getText()))
			{
				JOptionPane.showMessageDialog(null, "����д�Ż�ȯ����","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_2.getText()==null||"".equals(this.textField_2.getText()))
			{
				JOptionPane.showMessageDialog(null, "����д���ý��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_3.getText()==null||"".equals(this.textField_3.getText()))
			{
				JOptionPane.showMessageDialog(null, "����д������","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String couponid=this.textField.getText();
			String coucontent=this.textField_1.getText();
			float symoney=Float.parseFloat(this.textField_2.getText());
			float jmmoney=Float.parseFloat(this.textField_3.getText());
			
			
			cp=new BeanCoupon();
			cp.setCouponid(couponid);
			cp.setCoucontent(coucontent);
			cp.setSymoney(symoney);
			cp.setJmmoney(jmmoney);
			
		
			try {
				(new Coupon()).createcoupon(cp);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.cp=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanCoupon getcoupon(){
		return cp;
	}
}
