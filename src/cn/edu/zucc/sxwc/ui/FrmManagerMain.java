package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrmManagerMain extends JFrame implements ActionListener{

	private JPanel contentPane =new JPanel();
	private JMenu mnNewMenu = new JMenu("系统管理");
	private JMenuItem mntmNewMenuItem = new JMenuItem("管理员信息管理");
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("商品采购管理");
	private JMenuItem mntmNewMenuItem_2 = new JMenuItem("用户管理");
	private final JMenu menu = new JMenu("\u4F18\u60E0\u5238\u7BA1\u7406");
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManagerMain frame = new FrmManagerMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public FrmManagerMain() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("管理员管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu mnNewMenu = new JMenu("系统管理");
		menuBar.add(mnNewMenu);
		
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(this);
		
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(this);
	
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(this);
		
		this.setJMenuBar(menuBar);
		
		menuBar.add(menu);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.mntmNewMenuItem){
			FrmManager dlg=new FrmManager(this,"管理员信息管理",true);//FrmUserManager dlg=new FrmUserManager(this,"用户管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_1){
			FrmPurchase dlg = new FrmPurchase();//FrmReaderTypeManager dlg=new FrmReaderTypeManager(this,"读者类别管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.mntmNewMenuItem_2){//基础数据优惠数据
			//FrmReaderManager dlg=new FrmReaderManager(this,"读者管理",true);
			//dlg.setVisible(true);
		}
	}

}
