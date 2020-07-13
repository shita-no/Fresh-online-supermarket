package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.sxwc.comtrol.example.Address;
import cn.edu.zucc.sxwc.comtrol.example.Pj;
import cn.edu.zucc.sxwc.model.BeanAddress;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanPj;
import cn.edu.zucc.sxwc.util.BaseException;

public class Frmpj extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Object tblpjTitle[]=BeanPj.PjTitles;
	private Object tblpjData[][];
	DefaultTableModel tabpjModel=new DefaultTableModel();
	private JTable pjTable=new JTable(tabpjModel);
	List<BeanPj> allpj=null;
	public BeanGoods pjgoods=null;
	private final JButton btnNewButton = new JButton("评价");
	private void reloadpjTable(){
		try {
			Pj pj=new Pj();
			allpj=pj.loadPj(pjgoods);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblpjData =  new Object[allpj.size()][BeanPj.PjTitles.length];
		for(int i=0;i<allpj.size();i++){
			for(int j=0;j<BeanPj.PjTitles.length;j++)
				tblpjData[i][j]=allpj.get(i).getCell(j);
		}
		tabpjModel.setDataVector(tblpjData,tblpjTitle);
		this.pjTable.validate();
		this.pjTable.repaint();
	}
	public Frmpj(Frame f, String s, boolean b,BeanGoods good) {
		super(f,s,b);
		this.pjgoods = good;
		setBounds(100, 100, 599, 362);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 578, 262);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadpjTable();
		this.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		//getContentPane().add(contentPanel);
		JScrollPane scrollPane = new JScrollPane(this.pjTable);
		scrollPane.setBounds(0, 0, 569, 238);
		contentPanel.add(scrollPane);
		btnNewButton.setBounds(10, 275, 113, 27);
		
		getContentPane().add(btnNewButton);
		
		this.btnNewButton.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNewButton) {
			Frmpjdoing dlg=new Frmpjdoing(this,"进行评论",true,this.pjgoods);
			dlg.setVisible(true);
			if(dlg.getpj()!=null) {
				this.reloadpjTable();
			}
		}
	}

}
