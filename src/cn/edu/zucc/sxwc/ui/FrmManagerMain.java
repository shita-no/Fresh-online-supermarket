package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.comtrol.example.Goods;
import cn.edu.zucc.sxwc.comtrol.example.Lb;
import cn.edu.zucc.sxwc.comtrol.example.Menu;
import cn.edu.zucc.sxwc.comtrol.example.Remenu;
import cn.edu.zucc.sxwc.comtrol.example.ShoppingCart;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.model.BeanMenu;
import cn.edu.zucc.sxwc.model.BeanRemenu;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FrmManagerMain extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	 
	private JMenuBar menuBar = new JMenuBar();
	private JPanel contentPane =new JPanel();
	private JMenu mnNewMenu = new JMenu("系统信息");//用户信息
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("管理员信息");//改密码等等
	private JMenuItem mntmNewMenuItem = new JMenuItem("商品订单处理");
	private JMenu mnNewMenu_1 = new JMenu("优惠信息");
	private JMenuItem mntmNewMenuItem_4 = new JMenuItem("优惠券");
	private JMenuItem mntmNewMenuItem_5 = new JMenuItem("满折信息");
	private final JMenu mnNewMenu_2 = new JMenu("类别管理");
	private final JMenuItem mntmNewMenuItem_6 = new JMenuItem("添加类别");//选完一个类别后里面应该有商品信息
	//private FrmLogin1 dlgLogin=null;
	private Object tblTitle[]=BeanLb.tableTitles;
	private Object tbllbData[][];
	DefaultTableModel tablbmod=new DefaultTableModel();
	private JTable lbtable=new JTable(tablbmod);
	
	private Object tblGoodsTitle[]=BeanGoods.GoodsTitles;
	private Object tblGoodsData[][];
	DefaultTableModel tabGoodsModel=new DefaultTableModel();
	private JTable goodtable=new JTable(tabGoodsModel);
	
	private Object tblMenuTitle[]=BeanMenu.MenuTitles;
	private Object tblMenuData[][];
	DefaultTableModel tabMenuModel=new DefaultTableModel();
	private JTable menutable=new JTable(tabMenuModel);
	
	
	List<BeanLb> alllb=null;
	private BeanLb curlb=null;
	List<BeanGoods> lbgoods=null;
	public  BeanGoods curgoods=null;
	List<BeanMenu> goodsmenu=null;
	public  BeanMenu curmenu=null;
	private final JMenu mnNewMenu_3 = new JMenu("商品管理");
	private final JMenuItem mntmNewMenuItem_8 = new JMenuItem("添加商品");
	private final JMenuItem mntmNewMenuItem_9 = new JMenuItem("添加已有商品数量");
	private final JMenu mnNewMenu_4 = new JMenu("菜谱管理");
	private final JMenuItem mntmNewMenuItem_2 = new JMenuItem("添加菜谱");
	private final JMenuItem mntmNewMenuItem_3 = new JMenuItem("向用户推荐菜谱");
	private void reloadLbTable(){
		try {
			Lb lb=new Lb();
			alllb=lb.loadLb();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tbllbData =  new Object[alllb.size()][BeanLb.tableTitles.length];
		for(int i=0;i<alllb.size();i++){
			for(int j=0;j<BeanLb.tableTitles.length;j++)
				tbllbData[i][j]=alllb.get(i).getCell(j);
		}
			tablbmod.setDataVector(tbllbData,tblTitle);
			this.lbtable.validate();
			this.lbtable.repaint();
	}
	private void reloadLbGoodsTabel(int lbid){
		if(lbid<0) return;
		curlb=alllb.get(lbid);
		try {
			Goods goods=new Goods();
			lbgoods=goods.loadGoods(curlb);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGoodsData =new Object[lbgoods.size()][BeanGoods.GoodsTitles.length];
		for(int i=0;i<lbgoods.size();i++){
			for(int j=0;j<BeanGoods.GoodsTitles.length;j++)
				tblGoodsData[i][j]=lbgoods.get(i).getCell(j);
		}
		tabGoodsModel.setDataVector(tblGoodsData,tblGoodsTitle);
		this.goodtable.validate();
		this.goodtable.repaint();
	}
	private void reloadGoodsMenuTabel(int goodsid){
		if(goodsid<0) return;
		curgoods=lbgoods.get(goodsid);
		try {
			Menu menu=new Menu();
			goodsmenu=menu.loadMenu(curgoods);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblMenuData =new Object[goodsmenu.size()][BeanMenu.MenuTitles.length];
		for(int i=0;i<goodsmenu.size();i++){
			for(int j=0;j<BeanMenu.MenuTitles.length;j++)
				tblMenuData[i][j]=goodsmenu.get(i).getCell(j);
		}
		tabMenuModel.setDataVector(tblMenuData,tblMenuTitle);
		this.menutable.validate();
		this.menutable.repaint();
	}
	
	public FrmManagerMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("商品管理信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 467);
		contentPane.setBounds(542, 1019, 1, 1);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadLbTable();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		this.getContentPane().add(new JScrollPane(this.lbtable), BorderLayout.WEST);
		this.lbtable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmManagerMain.this.lbtable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmManagerMain.this.reloadLbGoodsTabel(i);
			}
	    	
	    });
		this.getContentPane().add(new JScrollPane(this.goodtable), BorderLayout.CENTER);
		this.goodtable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmManagerMain.this.goodtable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmManagerMain.this.reloadGoodsMenuTabel(i);
			}
			
	    });
		this.menutable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmManagerMain.this.menutable.getSelectedRow();
				if(i<0) {
					return;
				}
				curmenu=goodsmenu.get(i);
			}
			
	    });
		
		
		this.getContentPane().add(new JScrollPane(this.menutable), BorderLayout.EAST);
	    this.setJMenuBar(menuBar);
	   
	 
	    JLabel label=new JLabel("您好!"+BeanManager.currentLoginManager.getEmployeeid()+"号管理员");
	    
	    contentPane.add(label);
	    this.getContentPane().add(contentPane,BorderLayout.SOUTH);
	    /*btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index=goodtable.getSelectedRow();
				if(index==-1) {
					JOptionPane.showMessageDialog(null,"请选择一行商品进行购买");
				}
				FrmUserMain.this.dispose();
				BeanShoppingCart cart=tblMenuData[].get(index);
			}
		});*/
	    
	    
		menuBar.add(mnNewMenu);
		
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);//商品订单
		mntmNewMenuItem.addActionListener(this);
		
		menuBar.add(mnNewMenu_1);
		
		mnNewMenu_1.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(this);
		mnNewMenu_1.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(this);
		menuBar.add(mnNewMenu_2);
		
		mnNewMenu_2.add(mntmNewMenuItem_6);
		mntmNewMenuItem_6.addActionListener(this);
		mntmNewMenuItem_2.addActionListener(this);
		mntmNewMenuItem_3.addActionListener(this);
		menuBar.add(mnNewMenu_3);
		
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		mnNewMenu_3.add(mntmNewMenuItem_9);
		
		menuBar.add(mnNewMenu_4);
		
		mnNewMenu_4.add(mntmNewMenuItem_2);
		
		mnNewMenu_4.add(mntmNewMenuItem_3);
		mntmNewMenuItem_8.addActionListener(this);
		mntmNewMenuItem_9.addActionListener(this);
		//setContentPane(contentPane);
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
		if(e.getSource()==this.mntmNewMenuItem_1){
			FrmManager dlg=new FrmManager(this,"管理员信息",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.mntmNewMenuItem_2) {
			int i=this.goodtable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请先选中商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			FrmmenuAdd dlg=new FrmmenuAdd(this,"添加菜谱",true);
			dlg.goodidString=tblGoodsData[i][0].toString();
			dlg.lbid2=tblGoodsData[i][1].toString();
			dlg.setVisible(true);
			if(dlg.getmenu()!=null){//刷新表格
				this.reloadGoodsMenuTabel(i);
			}
		}
		else if(e.getSource()==this.mntmNewMenuItem_3) {
			int i=this.menutable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请先选中菜谱","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int j=this.goodtable.getSelectedRow();
			if(j<0) {
				JOptionPane.showMessageDialog(null,  "请先选中商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Frmms dlg=new Frmms(this,"添加推荐语",true);
			dlg.setVisible(true);
			String ms=dlg.description;
			//this.curmenu.getLbid()
			Remenu remenu=new Remenu();
			try {
				remenu.addmenu(this.curmenu,ms,tblGoodsData[j][1].toString());
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,  e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==mntmNewMenuItem_4) {
			Frmcoupon1 dlg=new Frmcoupon1(this,"优惠券",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_5) {
			Frmdiscount dlg=new Frmdiscount(this,"满折信息",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem) {
			Frmorder1 dlg=new Frmorder1(this,"商品订单处理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_6) {
			FrmlbAdd dlg=new FrmlbAdd(this,"添加商品类别",true);
			dlg.setVisible(true);
			if(dlg.getLb()!=null){//刷新表格
				this.reloadLbTable();
			}
		}
		else if(e.getSource()==mntmNewMenuItem_8) {
			int i=this.lbtable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请先选择商品类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			FrmgoodsAdd dlg=new FrmgoodsAdd(this,"添加商品",true);
			dlg.lbidString=tbllbData[i][0].toString();
			dlg.setVisible(true);
			if(dlg.getGoods()!=null){//刷新表格
				this.reloadLbGoodsTabel(i);
			}
		}
		else if(e.getSource()==mntmNewMenuItem_9) {
			int i=this.goodtable.getSelectedRow();
			int j=this.lbtable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请先选择想要采购的商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Frmgoodsamount dlg=new Frmgoodsamount(this,"采购所选商品",true);
			dlg.setVisible(true);
			int amount=dlg.loadamount();
			if(amount<=0) {
				JOptionPane.showMessageDialog(null,  "采购数量不能为0或负数","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Goods buygood=new Goods();
			try {
				buygood.add(this.curgoods,amount);
				this.reloadLbGoodsTabel(j);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,  "购买数量大于库存","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
			
			//if(dlg.get()!=null){//刷新表格
				//this.reloadTable();
			//}
			//this.reloadLbGoodsTabel();
		}
	}


