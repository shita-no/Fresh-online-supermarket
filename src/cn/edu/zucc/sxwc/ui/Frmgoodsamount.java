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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frmgoodsamount extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	private final JLabel lblNewLabel = new JLabel("采购数量:");
	private final JTextField textField = new JTextField(50);
	public Frmgoodsamount(Frame f, String s, boolean b) {
		super(f,s,b);
		textField.setBounds(166, 72, 86, 24);
		textField.setColumns(10);
		setTitle("\u91C7\u8D2D\u6240\u9009\u5546\u54C1");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			lblNewLabel.setBounds(36, 75, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			contentPanel.add(textField);
		}
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
			if(this.textField.getText()==null||"".equals(this.textField.getText())) {
				JOptionPane.showMessageDialog(null, "请输入数量","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
	public int loadamount() {
		return Integer.valueOf(this.textField.getText());
	}
}
