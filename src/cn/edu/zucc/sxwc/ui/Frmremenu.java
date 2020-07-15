package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.sxwc.comtrol.example.Coupon;
import cn.edu.zucc.sxwc.comtrol.example.Remenu;
import cn.edu.zucc.sxwc.model.BeanCoupon;
import cn.edu.zucc.sxwc.model.BeanMenu;
import cn.edu.zucc.sxwc.model.BeanRemenu;
import cn.edu.zucc.sxwc.util.BaseException;

public class Frmremenu extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Object tblreTitle[]=BeanRemenu.RemenuTitles;
	private Object tblreData[][];
	DefaultTableModel tabreModel=new DefaultTableModel();
	private JTable reTable=new JTable(tabreModel);
	List<BeanRemenu> allre=null;
	private void reloadreTable(){
		try {
			Remenu re=new Remenu();
			allre=re.loadRe();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblreData =  new Object[allre.size()][BeanRemenu.RemenuTitles.length];
		for(int i=0;i<allre.size();i++){
			for(int j=0;j<BeanRemenu.RemenuTitles.length;j++)
				tblreData[i][j]=allre.get(i).getCell(j);
		}
		tabreModel.setDataVector(tblreData,tblreTitle);
		this.reTable.validate();
		this.reTable.repaint();
	}
	public Frmremenu(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("²ËÆ×ÍÆ¼ö±í");
		setBounds(100, 100, 506, 354);
		getContentPane().setLayout(null);
		this.reloadreTable();
		contentPanel.setBounds(0, 0, 488, 308);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		this.getContentPane().add(contentPanel);
		JScrollPane scrollPane = new JScrollPane(this.reTable);
		scrollPane.setBounds(0, 0, 474, 295);
		contentPanel.add(scrollPane);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
