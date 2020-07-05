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
import javax.swing.JPasswordField;

public class FrmModifyPwd extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane = new JPanel();
	private JPasswordField passwordField;//‘≠√‹¬Î
	private JPasswordField passwordField_1;//–¬√‹¬Î1
	private JPasswordField passwordField_2;//–¬√‹¬Î2
	private JButton okButton = new JButton("\u786E\u5B9A");
	private JButton cancelButton = new JButton("\u53D6\u6D88");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmModifyPwd dialog = new FrmModifyPwd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmModifyPwd() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel_2 = new JLabel("\u539F\u5BC6\u7801");//‘≠√‹¬Î
			lblNewLabel_2.setBounds(44, 28, 72, 18);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801");//–¬√‹¬Î1
			lblNewLabel_1.setBounds(44, 95, 72, 18);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel = new JLabel("\u65B0\u5BC6\u7801");//–¬√‹¬Î2
			lblNewLabel.setBounds(44, 165, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(156, 25, 101, 24);
			contentPanel.add(passwordField);
		}
		{
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(156, 92, 101, 24);
			contentPanel.add(passwordField_1);
		}
		{
			passwordField_2 = new JPasswordField();
			passwordField_2.setBounds(156, 162, 101, 24);
			contentPanel.add(passwordField_2);
		}
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
			try {
				ExampleUserManager userManager = new ExampleUserManager();
				userManager.changePwd(BeanUser.currentLoginUser,new String(passwordField.getPassword()),new String(passwordField_1.getPassword()),new String(passwordField_2.getPassword()));
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
