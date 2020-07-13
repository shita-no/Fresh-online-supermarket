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
import cn.edu.zucc.sxwc.comtrol.example.Lb;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.util.BaseException;
import cn.edu.zucc.sxwc.util.BusinessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmgoodsAdd extends JDialog implements ActionListener{
	private BeanGoods good=null;
	public String lbidString=null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField=new JTextField(50);
	private JTextField textField_1=new JTextField(50);
	private JTextField textField_2=new JTextField(50);
	private JTextField textField_3=new JTextField(50);
	private JTextField textField_4=new JTextField(50);
	private JTextField textField_5=new JTextField(50);
	private JTextField textField_6=new JTextField(50);
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	private JPanel buttonPane = new JPanel();
	public FrmgoodsAdd(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("添加商品");
		setBounds(100, 100, 565, 429);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 539, 320);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品编号");
		lblNewLabel.setBounds(56, 13, 111, 18);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("商品名称");
		lblNewLabel_1.setBounds(56, 55, 111, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("设置商品价格");
		lblNewLabel_2.setBounds(56, 102, 111, 18);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("设置商品会员价");
		lblNewLabel_3.setBounds(56, 145, 111, 18);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("采购数量");
		lblNewLabel_4.setBounds(56, 191, 111, 18);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("设置规格");
		lblNewLabel_5.setBounds(56, 237, 111, 18);
		contentPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("描述详情");
		lblNewLabel_6.setBounds(56, 289, 111, 18);
		contentPanel.add(lblNewLabel_6);
		
		
		textField.setBounds(220, 10, 86, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		
		textField_1.setBounds(220, 52, 86, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		
		textField_2.setBounds(220, 99, 86, 24);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		
		textField_3.setBounds(220, 142, 86, 24);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		
		textField_4.setBounds(220, 188, 86, 24);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		
		textField_5.setBounds(220, 237, 86, 24);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
	
		textField_6.setBounds(220, 286, 86, 24);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		{
			buttonPane.setBounds(50, 333, 432, 37);
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
				JOptionPane.showMessageDialog(null, "请填写商品编号","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_1.getText()==null||"".equals(this.textField_1.getText()))
			{
				JOptionPane.showMessageDialog(null, "请填写商品编号","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_2.getText()==null||"".equals(this.textField_2.getText()))
			{
				JOptionPane.showMessageDialog(null, "请填写价格","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_3.getText()==null||"".equals(this.textField_3.getText()))
			{
				JOptionPane.showMessageDialog(null, "请填写会员价","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_4.getText()==null||"".equals(this.textField_4.getText()))
			{
				JOptionPane.showMessageDialog(null, "请填写数量","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_5.getText()==null||"".equals(this.textField_5.getText()))
			{
				JOptionPane.showMessageDialog(null, "请填写规格","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.textField_6.getText()==null||"".equals(this.textField_6.getText()))
			{
				JOptionPane.showMessageDialog(null, "请描述详情","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String goodsid=this.textField.getText();
			String goodsname=this.textField_1.getText();
			float gprice=Float.parseFloat(this.textField_2.getText());
			float gvipprice=Float.parseFloat(this.textField_3.getText());
			int gamount=Integer.parseInt(this.textField_4.getText());
			String guige=this.textField_5.getText();
			String details=this.textField_6.getText();
			
			good=new BeanGoods();
			good.setGoodsid(goodsid);
			good.setLbid(lbidString);
			good.setGoodsname(goodsname);
			good.setGprice(gprice);
			good.setGvipprice(gvipprice);
			good.setGamount(gamount);
			good.setGuige(guige);
			good.setDatails(details);
			try {
				(new Goods()).creategoods(good);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.good=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanGoods getGoods() {
		return good;
	}
}
