package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class FrmManager_ChangePwd extends JDialog implements ActionListener{
	private BeanManager employee=null;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	private JLabel lblNewLabel = new JLabel("原密码");
	private JLabel lblNewLabel_1 = new JLabel("新密码");
	private JLabel lblNewLabel_2 = new JLabel("新密码");
	private JPasswordField edtPwdOld=new JPasswordField(50);
	private JPasswordField edtPwd=new JPasswordField(50);
	private JPasswordField edtPwd2=new JPasswordField(50);
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			FrmManager_ChangePwd dialog = new FrmManager_ChangePwd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public FrmManager_ChangePwd(JDialog f, String s, boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("原密码");
			lblNewLabel.setBounds(37, 33, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblNewLabel_1 = new JLabel("新密码");
		lblNewLabel_1.setBounds(37, 107, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("新密码");
		lblNewLabel_2.setBounds(37, 186, 72, 18);
		contentPanel.add(lblNewLabel_2);
		
		edtPwdOld = new JPasswordField();
		edtPwdOld.setBounds(189, 30, 113, 24);
		contentPanel.add(edtPwdOld);
		
		edtPwd = new JPasswordField();
		edtPwd.setBounds(189, 104, 113, 24);
		contentPanel.add(edtPwd);
		
		edtPwd2 = new JPasswordField();
		edtPwd2.setBounds(189, 183, 113, 24);
		contentPanel.add(edtPwd2);
		{
			JPanel buttonPane = new JPanel();
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
		this.okButton .addActionListener(this);
		this.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.cancelButton)
			this.setVisible(false);
		else if(e.getSource()==this.okButton){
			String employeePwdOld=new String(this.edtPwdOld.getPassword());
			String employeePwd=new String(this.edtPwd.getPassword());
			String employeePwd2=new String(this.edtPwd2.getPassword());
			employee=new BeanManager();
			employee.setEmployeeid(employeePwdOld);
			employee.setEmployeename(employeePwd);
			employee.setPasswd(employeePwd2);
			try {
				ExampleEmployeeManager employeeManager = new ExampleEmployeeManager();
				employeeManager.changePwd(BeanManager.currentLoginManager,new String(edtPwdOld.getPassword()),new String(edtPwd.getPassword()),new String(edtPwd2.getPassword()));
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	public BeanManager getManager() {
		return employee;
	}
}
