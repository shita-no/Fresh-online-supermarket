package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.sxwc.comtrol.example.Coupon;
import cn.edu.zucc.sxwc.comtrol.example.Discount;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanDiscount;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmdcAdd extends JDialog implements ActionListener{
	private BeanDiscount dc=null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField=new JTextField(50);
	private JTextField textField_1=new JTextField(50);
	private JTextField textField_2=new JTextField(50);
	private JTextField textField_3=new JTextField(50);
	private JTextField textField_4=new JTextField(50);
	private JTextField textField_5=new JTextField(50);
	private JButton okButton = new JButton("»∑»œ");
	private JButton cancelButton = new JButton("»°œ˚");
	public FrmdcAdd(JDialog f, String s, boolean b) {
		super(f,s,b);
		setBounds(100, 100, 576, 440);
		setTitle("ÃÌº”¬˙’€–≈œ¢");
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 519, 343);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("¬˙’€±‡∫≈");
			lblNewLabel.setBounds(42, 13, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿‡±±‡∫≈");
			lblNewLabel_1.setBounds(42, 59, 72, 18);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("…Ã∆∑±‡∫≈");
			lblNewLabel_2.setBounds(42, 113, 72, 18);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("  ”√ ˝¡ø");
			lblNewLabel_3.setBounds(42, 172, 72, 18);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("’€ø€");
			lblNewLabel_4.setBounds(42, 222, 72, 18);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("ƒ⁄»›");
			lblNewLabel_5.setBounds(42, 278, 72, 18);
			contentPanel.add(lblNewLabel_5);
		}
		{
			textField = new JTextField();
			textField.setBounds(237, 10, 86, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(237, 56, 86, 24);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(237, 110, 86, 24);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(237, 169, 86, 24);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(237, 222, 86, 24);
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(237, 278, 86, 24);
			contentPanel.add(textField_5);
			textField_5.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(14, 344, 530, 37);
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
			if(this.textField.getText()==null||"".equals(this.textField.getText()))
			{
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥¬˙’€±‡∫≈","¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_1.getText()==null||"".equals(this.textField_1.getText()))
			{
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥…Ã∆∑¿‡±±‡∫≈","¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_2.getText()==null||"".equals(this.textField_2.getText()))
			{
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥…Ã∆∑±‡∫≈","¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_3.getText()==null||"".equals(this.textField_3.getText()))
			{
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥  ”√ ˝¡ø","¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_4.getText()==null||"".equals(this.textField_4.getText()))
			{
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥’€ø€","¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_5.getText()==null||"".equals(this.textField_5.getText()))
			{
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥ƒ⁄»›","¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String discountid=this.textField.getText();
			String lbid=this.textField_1.getText();
			String goodsid=this.textField_2.getText();
			int syamount=Integer.parseInt(this.textField_3.getText());
			float discount=Float.parseFloat(this.textField_4.getText());
			String dccontent=this.textField_5.getText();
			
			dc=new BeanDiscount();
			dc.setDiscountid(discountid);
			dc.setLbid(lbid);
			dc.setGoodsid(goodsid);
			dc.setSyamount(syamount);
			dc.setDiscount(discount);
			dc.setDccontent(dccontent);
			try {
				(new Discount()).creatediscount(dc);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.dc=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			}
		}
		}
	
		public BeanDiscount getDiscount(){
			return dc;
}
}
