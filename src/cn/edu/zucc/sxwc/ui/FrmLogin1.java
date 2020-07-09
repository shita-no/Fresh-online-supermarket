package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.comtrol.example.ExampleUserManager;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;

public class FrmLogin1 extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
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
	/*public static void main(String[] args) {
		try {
			FrmLogin1 dialog = new FrmLogin1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public FrmLogin1(Frame f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setBounds(34, 106, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("账号：");
		lblNewLabel_2.setBounds(34, 32, 72, 18);
		contentPanel.add(lblNewLabel_2);
		
		btnNewButton = new JButton("注册");
		btnNewButton.setBounds(14, 190, 113, 27);
		contentPanel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("登录");
		btnNewButton_1.setBounds(160, 190, 113, 27);
		contentPanel.add(btnNewButton_1);
		
		
		edtUserId.setBounds(160, 29, 86, 24);
		contentPanel.add(edtUserId);
		edtUserId.setColumns(10);
		
		
		edtPwd.setBounds(160, 103, 86, 24);
		contentPanel.add(edtPwd);
		edtPwd.setColumns(10);
		
		btnNewButton_2 = new JButton("退出");
		btnNewButton_2.setBounds(304, 190, 113, 27);
		contentPanel.add(btnNewButton_2);
		
		rdbtnNewRadioButton = new JRadioButton("用户");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(75, 154, 132, 27);
		contentPanel.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("管理员");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(268, 154, 132, 27);
		contentPanel.add(rdbtnNewRadioButton_1);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
			this.setVisible(false);
			FrmUserMain FUM = new FrmUserMain();
			FUM.setVisible(true);
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
				FrmManagerMain FMM = new FrmManagerMain();
				FMM.setVisible(true);
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


