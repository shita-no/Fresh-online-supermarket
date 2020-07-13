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
import cn.edu.zucc.sxwc.comtrol.example.Lb;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmlbAdd extends JDialog implements ActionListener{
	private BeanLb lb=null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField=new JTextField(50);
	private JTextField textField_1=new JTextField(50);
	private JTextField textField_2=new JTextField(100);
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	private JPanel buttonPane = new JPanel();
	public FrmlbAdd(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("添加商品类别");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("类别编号");
		lblNewLabel.setBounds(57, 31, 72, 18);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("类别名称");
		lblNewLabel_1.setBounds(57, 98, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("类别描述");
		lblNewLabel_2.setBounds(57, 167, 72, 18);
		contentPanel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(188, 28, 86, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 95, 86, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(188, 164, 86, 24);
		contentPanel.add(textField_2);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		textField_2.setColumns(10);
		{
			
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
			String lbid=this.textField.getText();
			String lbname=this.textField_1.getText();
			String described=this.textField_2.getText();
			lb=new BeanLb();
			lb.setLbid(lbid);
			lb.setLbname(lbname);
			lb.setDescribed(described);
			try {
				(new Lb()).createLb(lb);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.lb=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanLb getLb() {
		return lb;
	}
}
