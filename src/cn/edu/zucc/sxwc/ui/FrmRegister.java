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

public class FrmRegister extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField edtUserId = new JTextField(20);;
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("\u6CE8\u518C");
	private JButton cancelButton = new JButton("\u53D6\u6D88");
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setBounds(40, 41, 72, 18);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setBounds(40, 94, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801");
		lblNewLabel_2.setBounds(40, 157, 72, 18);
		contentPanel.add(lblNewLabel_2);
		
		
		edtUserId.setBounds(188, 38, 86, 24);
		contentPanel.add(edtUserId);
		edtUserId.setColumns(10);
		
		
		edtPwd.setBounds(188, 91, 86, 24);
		contentPanel.add(edtPwd);
		
		
		edtPwd2.setBounds(188, 154, 86, 24);
		contentPanel.add(edtPwd2);
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
			String userid=this.edtUserId.getText();
			String passwd1=new String(this.edtPwd.getPassword());
			String passwd2=new String(this.edtPwd2.getPassword());
			ExampleUserManager userManager = new ExampleUserManager();
			try {
				BeanUser user=userManager.reg(userid, passwd1, passwd2);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
		}
	}
}
