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

import cn.edu.zucc.sxwc.comtrol.example.Goods;
import cn.edu.zucc.sxwc.comtrol.example.Menu;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanMenu;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmmenuAdd extends JDialog implements ActionListener{
	private BeanMenu menu=null;
	public String goodidString=null;
	public String lbid2=null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField=new JTextField(50);
	private JTextField textField_1=new JTextField(50);
	private JTextField textField_2=new JTextField(100);
	private JTextField textField_3=new JTextField(100);
	private JTextField textField_4=new JTextField(50);
	private JButton okButton = new JButton("È·¶¨");
	private JButton cancelButton = new JButton("È¡Ïû");
	public FrmmenuAdd(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("Ìí¼Ó²ËÆ×");
		setBounds(100, 100, 519, 400);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 291);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("²ËÆ×±àºÅ");
		lblNewLabel.setBounds(38, 25, 72, 18);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("²ËÆ×Ãû³Æ");
		lblNewLabel_1.setBounds(38, 87, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("²ËÆ×ÓÃÁÏ");
		lblNewLabel_2.setBounds(38, 144, 72, 18);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("²½Öè");
		lblNewLabel_3.setBounds(38, 199, 72, 18);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Í¼Æ¬");
		lblNewLabel_4.setBounds(38, 260, 72, 18);
		contentPanel.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(221, 22, 86, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 84, 86, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 141, 86, 24);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(221, 196, 86, 24);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(221, 257, 86, 24);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(33, 304, 432, 37);
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
			String menuid=this.textField.getText();
			String menuname=this.textField_1.getText();
			String ingredients=this.textField_2.getText();
			String step=this.textField_3.getText();
			String picture=this.textField_4.getText();
			menu=new BeanMenu();
			menu.setMenuid(menuid);
			menu.setLbid(lbid2);
			menu.setGoodsid(goodidString);
			menu.setMenuname(menuname);
			menu.setIngredients(ingredients);
			menu.setIngredients(ingredients);
			menu.setStep(step);
			menu.setPicture(picture);
			try {
				(new Menu()).createmenu(menu);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.menu=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanMenu getmenu() {
		return menu;
	}
}
