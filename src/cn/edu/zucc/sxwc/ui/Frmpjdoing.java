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
import cn.edu.zucc.sxwc.comtrol.example.Pj;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanPj;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frmpjdoing extends JDialog implements ActionListener{
	private BeanPj pj=null;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton = new JButton("确认");
	private JButton cancelButton = new JButton("取消");
	private final JLabel lblNewLabel = new JLabel("评论内容");
	private final JLabel lblNewLabel_1 = new JLabel("星级");
	private final JLabel lblNewLabel_2 = new JLabel("照片");
	private final JTextField textField = new JTextField(100);
	private final JTextField textField_1 = new JTextField(50);
	private final JTextField textField_2 = new JTextField(50);
	public  BeanGoods pjdogoods=null;
	public Frmpjdoing(JDialog  f, String s, boolean b,BeanGoods good) {
		super(f, s, b);
		this.pjdogoods=good;
		textField_2.setBounds(188, 169, 86, 24);
		textField_2.setColumns(10);
		textField_1.setBounds(188, 97, 86, 24);
		textField_1.setColumns(10);
		textField.setBounds(188, 24, 86, 24);
		textField.setColumns(10);
		setTitle("进行评论");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 217);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			lblNewLabel.setBounds(39, 27, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNewLabel_1.setBounds(39, 100, 72, 18);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lblNewLabel_2.setBounds(39, 172, 72, 18);
			contentPanel.add(lblNewLabel_2);
		}
		{
			contentPanel.add(textField);
		}
		{
			contentPanel.add(textField_1);
		}
		{
			contentPanel.add(textField_2);
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
				if(this.textField.getText()==null||"".equals(this.textField.getText()))
				{
					JOptionPane.showMessageDialog(null, "请填写评价内容","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(this.textField_1.getText()==null||"".equals(this.textField_1.getText()))
				{
					JOptionPane.showMessageDialog(null, "请填写星级","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(this.textField_2.getText()==null||"".equals(this.textField_2.getText()))
				{
					JOptionPane.showMessageDialog(null, "请上传照片","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String pjcontent=this.textField.getText();
				int rank=Integer.parseInt(this.textField_1.getText());
				String photo=this.textField_2.getText();
				pj=new BeanPj();
				pj.setLbid(pjdogoods.getLbid());
				pj.setGoodsid(pjdogoods.getGoodsid());
				pj.setPjcontent(pjcontent);
				pj.setRank(rank);
				pj.setPhoto(photo);
				
				try {
					(new Pj()).createpj(pj);
					this.setVisible(false);
				} catch (BaseException e1) {
					this.pj=null;
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		
	}
	public BeanPj getpj(){
		return pj;
	}
}
