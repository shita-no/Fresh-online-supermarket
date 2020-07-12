package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.sxwc.comtrol.example.Address;
import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.model.BeanAddress;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmAddress_add extends JDialog implements ActionListener{
	private BeanAddress ad=null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField=new JTextField(50);;
	private JTextField textField_1=new JTextField(50);;
	private JTextField textField_2=new JTextField(50);;
	private JTextField textField_3=new JTextField(50);;
	private JTextField textField_4=new JTextField(50);;
	private JTextField textField_5=new JTextField(50);;
	private JTextField textField_6=new JTextField(50);;
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	public FrmAddress_add(JDialog f, String s, boolean b) {
		super(f,s,b);
		setTitle("配送地址");
		setBounds(100, 100, 526, 471);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 464, 318);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("地址编号");
			lblNewLabel.setBounds(28, 13, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("省");
			lblNewLabel_1.setBounds(28, 55, 72, 18);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("市");
			lblNewLabel_2.setBounds(28, 98, 72, 18);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("区");
			lblNewLabel_3.setBounds(28, 141, 72, 18);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("地址");
			lblNewLabel_4.setBounds(28, 187, 72, 18);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("联系人");
			lblNewLabel_5.setBounds(28, 232, 72, 18);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("电话");
			lblNewLabel_6.setBounds(28, 277, 72, 18);
			contentPanel.add(lblNewLabel_6);
		}
		
		textField = new JTextField();
		textField.setBounds(207, 10, 107, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(207, 52, 107, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(207, 95, 107, 24);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(207, 138, 107, 24);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(207, 184, 107, 24);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(207, 229, 107, 24);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(207, 274, 107, 24);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(31, 375, 471, 37);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				
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
		else if(e.getSource()==this.okButton) {
			String addid=this.textField.getText();
			String province=this.textField_1.getText();
			String city=this.textField_2.getText();
			String area=this.textField_3.getText();
			String address=this.textField_4.getText();
			String contacts=this.textField_5.getText();
			String phonenum=this.textField_6.getText();
			
			ad=new BeanAddress();
			ad.setAddid(addid);
			ad.setProvince(province);
			ad.setCity(city);
			ad.setArea(area);
			ad.setAddress(address);
			ad.setContact(contacts);
			ad.setPhonenum(phonenum);
			
			try {
				(new Address()).createManager(ad);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.ad=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanAddress getaddress(){
		return ad;
	}
}
