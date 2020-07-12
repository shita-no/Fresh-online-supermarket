package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class FrmManager_AddManager extends JDialog implements ActionListener{
	private BeanManager employee=null;
	private final JPanel contentPanel = new JPanel();
	private JTextField edtUserid=new JTextField(50);
	private JTextField edtUsername=new JTextField(50);
	private JPasswordField edtPwd=new JPasswordField(50);
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	private JPanel buttonPane = new JPanel();
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			FrmManager_AddManager dialog = new FrmManager_AddManager();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public FrmManager_AddManager(JDialog f, String s, boolean b) {
		super(f,s,b);
		setTitle("\u6DFB\u52A0\u8D26\u53F7");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(46, 27, 72, 18);
		contentPanel.add(lblNewLabel);
		
		edtUserid = new JTextField();
		edtUserid.setBounds(176, 24, 86, 24);
		contentPanel.add(edtUserid);
		edtUserid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(46, 90, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		edtUsername = new JTextField();
		edtUsername.setBounds(176, 87, 86, 24);
		contentPanel.add(edtUsername);
		edtUsername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setBounds(46, 168, 72, 18);
		contentPanel.add(lblNewLabel_2);
		
		edtPwd = new JPasswordField();
		edtPwd.setBounds(176, 165, 86, 24);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		contentPanel.add(edtPwd);
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
		if(e.getSource()==this.cancelButton) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.okButton){
			String employeeid=this.edtUserid.getText();
			String employeename=this.edtUsername.getText();
			String employeepwd=new String(this.edtPwd.getPassword());
			employee=new BeanManager();
			employee.setEmployeeid(employeeid);
			employee.setEmployeename(employeename);
			employee.setPasswd(employeepwd);
			try {
				(new ExampleEmployeeManager()).createManager(employee);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.employee=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanManager getManager() {
		return employee;
	}
}
