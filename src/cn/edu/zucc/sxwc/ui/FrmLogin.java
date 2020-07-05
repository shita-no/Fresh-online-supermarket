package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.Desktop.Action;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.comtrol.example.ExampleUserManager;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;
import javassist.expr.NewArray;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class FrmLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		super();
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setBounds(34, 106, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("账号：");
		lblNewLabel_2.setBounds(34, 32, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		btnNewButton = new JButton("注册");
		btnNewButton.setBounds(14, 190, 113, 27);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("登录");
		btnNewButton_1.setBounds(160, 190, 113, 27);
		contentPane.add(btnNewButton_1);
		
		
		edtUserId.setBounds(160, 29, 86, 24);
		contentPane.add(edtUserId);
		edtUserId.setColumns(10);
		
		
		edtPwd.setBounds(160, 103, 86, 24);
		contentPane.add(edtPwd);
		edtPwd.setColumns(10);
		
		btnNewButton_2 = new JButton("退出");
		btnNewButton_2.setBounds(304, 190, 113, 27);
		contentPane.add(btnNewButton_2);
		
		rdbtnNewRadioButton = new JRadioButton("用户");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(75, 154, 132, 27);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("管理员");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(268, 154, 132, 27);
		contentPane.add(rdbtnNewRadioButton_1);
		
		btnNewButton_1.addActionListener(this);
		btnNewButton_2.addActionListener(this);
		this.btnNewButton.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() ==this.btnNewButton_1) {
			if(this.rdbtnNewRadioButton.isSelected()) {
			String userid=this.edtUserId.getText();
			String passwd=new String(this.edtPwd.getPassword());
			try {
				ExampleUserManager userManager = new ExampleUserManager();
				BeanUser.currentLoginUser= userManager.login(userid,passwd);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			//FrmRegister fFrmRegister = new FrmRegister();
			//fFrmRegister.setVisible(true);
			this.setVisible(false);
			}
			else if(this.rdbtnNewRadioButton_1.isSelected()){
				String employeeid=this.edtUserId.getText();
				String passwd=new String(this.edtPwd.getPassword());
				try {
					ExampleEmployeeManager employeeManager = new ExampleEmployeeManager();
					BeanManager.currentLoginManager= employeeManager.login(employeeid,passwd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				this.setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(null,"请先选择登录身份");
			}
			
		} else if (e.getSource() == this.btnNewButton_2) {
			System.exit(0);
		} else if(e.getSource()==this.btnNewButton){
			if(this.rdbtnNewRadioButton.isSelected()) {
				FrmRegister dlg=new FrmRegister();
				dlg.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null,"请先选择用户进行注册");
			}
		}
	}
}
