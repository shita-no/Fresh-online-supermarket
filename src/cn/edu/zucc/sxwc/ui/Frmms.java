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

public class Frmms extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	public String description=null;
	private JButton okButton = new JButton("»∑∂®");
	private JButton cancelButton = new JButton("»°œ˚");
	private final JLabel lblNewLabel = new JLabel("ÃÌº”√Ë ˆ£∫");
	private final JTextField textField = new JTextField();
	public Frmms(Frame f, String s, boolean b) {
		super(f,s,b);
		textField.setBounds(191, 87, 110, 36);
		textField.setColumns(10);
		setTitle("ÃÌº”Õ∆ºˆ”Ô");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			lblNewLabel.setBounds(38, 87, 72, 39);
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
		if(e.getSource()==this.cancelButton)
			this.setVisible(false);
		else if(e.getSource()==this.okButton){
			if(this.textField.getText()==null||"".equals(this.textField.getText())) {
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥≤À∆◊√Ë ˆ","¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			description=this.textField.getText();
			this.setVisible(false);
			
			
		}
	}

}
