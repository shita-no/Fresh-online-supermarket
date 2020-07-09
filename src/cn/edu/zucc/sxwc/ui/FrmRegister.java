package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.sxwc.comtrol.example.ExampleUserManager;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class FrmRegister extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField edtUserId = new JTextField(50);
	private JPasswordField edtPwd = new JPasswordField(50);
	private JPasswordField edtPwd2 = new JPasswordField(50);
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("\u6CE8\u518C");
	private JButton cancelButton = new JButton("\u53D6\u6D88");
	private JTextField edtname = new JTextField(20);
	private JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
	private JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
	private JTextField edtphonenum=new JTextField(50);
	private JTextField edtmail=new JTextField(50);
	private JTextField edtcity=new JTextField(50);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmRegister dialog = new FrmRegister();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmRegister() {
		this.setModal(true);
		setBounds(100, 100, 556, 422);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setBounds(40, 41, 72, 18);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setBounds(40, 143, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801");
		lblNewLabel_2.setBounds(40, 183, 72, 18);
		contentPanel.add(lblNewLabel_2);
		
		
		edtUserId.setBounds(188, 38, 86, 24);
		contentPanel.add(edtUserId);
		edtUserId.setColumns(10);
		
		
		edtPwd.setBounds(188, 140, 86, 24);
		contentPanel.add(edtPwd);
		
		
		edtPwd2.setBounds(188, 180, 86, 24);
		contentPanel.add(edtPwd2);
		
		JLabel lblNewLabel_3 = new JLabel("姓名");
		lblNewLabel_3.setBounds(40, 79, 72, 18);
		contentPanel.add(lblNewLabel_3);
		
		
		edtname.setBounds(188, 76, 86, 24);
		contentPanel.add(edtname);
		edtname.setColumns(10);
		
		
		rdbtnNewRadioButton.setBounds(40, 107, 93, 27);
		contentPanel.add(rdbtnNewRadioButton);
		
		
		rdbtnNewRadioButton_1.setBounds(188, 107, 93, 27);
		contentPanel.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("手机号码");
		lblNewLabel_4.setBounds(40, 220, 72, 18);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("邮箱");
		lblNewLabel_5.setBounds(40, 259, 72, 18);
		contentPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("所在城市");
		lblNewLabel_6.setBounds(40, 303, 72, 18);
		contentPanel.add(lblNewLabel_6);
		
		edtphonenum = new JTextField();
		edtphonenum.setBounds(188, 217, 86, 24);
		contentPanel.add(edtphonenum);
		edtphonenum.setColumns(10);
		
		edtmail = new JTextField();
		edtmail.setBounds(188, 256, 86, 24);
		contentPanel.add(edtmail);
		edtmail.setColumns(10);
		
		edtcity = new JTextField();
		edtcity.setBounds(188, 300, 86, 24);
		contentPanel.add(edtcity);
		edtcity.setColumns(10);
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
		if(e.getSource()==this.cancelButton)
			this.setVisible(false);
		else if(e.getSource()==this.okButton){
			if(this.rdbtnNewRadioButton.isSelected()) {

			String userid=this.edtUserId.getText();
			String username=this.edtname.getText();
			String passwd1=new String(this.edtPwd.getPassword());
			String passwd2=new String(this.edtPwd2.getPassword());
			String phonenum=new String(this.edtphonenum.getText());
			String mail=new String(this.edtmail.getText());
			String city=new String(this.edtcity.getText());
			ExampleUserManager userManager = new ExampleUserManager();
			try {
				BeanUser user=userManager.reg(userid,username,passwd1, passwd2,phonenum,mail,city);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
			else if(this.rdbtnNewRadioButton_1.isSelected()) {
				String userid=this.edtUserId.getText();
				String username=this.edtname.getText();
				String passwd1=new String(this.edtPwd.getPassword());
				String passwd2=new String(this.edtPwd2.getPassword());
				String phonenum=new String(this.edtphonenum.getText());
				String mail=new String(this.edtmail.getText());
				String city=new String(this.edtcity.getText());
				ExampleUserManager userManager = new ExampleUserManager();
				try {
					BeanUser user=userManager.reg1(userid,username,passwd1, passwd2,phonenum,mail,city);
					this.setVisible(false);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			
		}
	}
}
