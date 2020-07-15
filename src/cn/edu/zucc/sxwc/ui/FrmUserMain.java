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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PUBLIC_MEMBER;

import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.comtrol.example.ExampleUserManager;
import cn.edu.zucc.sxwc.comtrol.example.Goods;
import cn.edu.zucc.sxwc.comtrol.example.Lb;
import cn.edu.zucc.sxwc.comtrol.example.Menu;
import cn.edu.zucc.sxwc.comtrol.example.Pj;
import cn.edu.zucc.sxwc.comtrol.example.ShoppingCart;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.model.BeanMenu;
import cn.edu.zucc.sxwc.model.BeanPj;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class FrmUserMain extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	 
	private JMenuBar menuBar = new JMenuBar();
	private JPanel contentPane =new JPanel();
	private JMenu mnNewMenu = new JMenu("������Ϣ");//�û���Ϣ
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("�û���Ϣ");//������ȵ�
	private JMenuItem mntmNewMenuItem_2 = new JMenuItem("���͵�ַ");
	private JMenuItem mntmNewMenuItem = new JMenuItem("��Ʒ����");//��Ʒ����Ӧ��ֱ�Ӱ����������
	private JMenuItem mntmNewMenuItem_3 = new JMenuItem("���ﳵ");
	private JMenu mnNewMenu_1 = new JMenu("�Ż���Ϣ");
	private JMenuItem mntmNewMenuItem_4 = new JMenuItem("�Ż�ȯ");
	private JMenuItem mntmNewMenuItem_5 = new JMenuItem("������Ϣ");
	private final JMenu mnNewMenu_2 = new JMenu("����");
	private final JMenuItem mntmNewMenuItem_6 = new JMenuItem("ˢ����Ʒҳ��");//ѡ��һ����������Ӧ������Ʒ��Ϣ
	//private FrmLogin1 dlgLogin=null;
	private Object tblTitle[]=BeanLb.tableTitles;
	private Object tbllbData[][];
	DefaultTableModel tablbmod=new DefaultTableModel();
	public JTable lbtable=new JTable(tablbmod);
	
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
	private final JButton btnNewButton = new JButton("���빺�ﳵ");
	private final JMenuItem mntmNewMenuItem_7 = new JMenuItem("��Ʒ���۱�");
	private final JMenuItem mntmNewMenuItem_8 = new JMenuItem("�����Ƽ���");
	public void reloadLbTable(){
		try {
			Lb lb=new Lb();
			alllb=lb.loadLb();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
	public void reloadLbGoodsTabel(int lbid){
		if(lbid<0) return;
		curlb=alllb.get(lbid);
		try {
			Goods goods=new Goods();
			lbgoods=goods.loadGoods(curlb);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
	public void reloadGoodsMenuTabel(int goodsid){
		if(goodsid<0) return;
		curgoods=lbgoods.get(goodsid);
		try {
			Menu menu=new Menu();
			goodsmenu=menu.loadMenu(curgoods);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
	
	public FrmUserMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("���������Ϣ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 467);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadLbTable();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		this.getContentPane().add(new JScrollPane(this.lbtable), BorderLayout.WEST);
		this.lbtable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmUserMain.this.lbtable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmUserMain.this.reloadLbGoodsTabel(i);
			}
	    	
	    });
		this.getContentPane().add(new JScrollPane(this.goodtable), BorderLayout.CENTER);
		this.goodtable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmUserMain.this.goodtable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmUserMain.this.reloadGoodsMenuTabel(i);
			}
			
	    });
		
		
		this.getContentPane().add(new JScrollPane(this.menutable), BorderLayout.EAST);
	    this.setJMenuBar(menuBar);
	 
	    JLabel label=new JLabel("����!"+BeanUser.currentLoginUser.getUsername());
	    contentPane.add(label);
	    this.getContentPane().add(contentPane,BorderLayout.SOUTH);
	    
	    contentPane.add(btnNewButton);
	    this.btnNewButton.addActionListener(this);
	    menuBar.add(mnNewMenu);
		
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);//��Ʒ����
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(this);
		
		menuBar.add(mnNewMenu_1);
		
		mnNewMenu_1.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(this);
		mnNewMenu_1.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(this);
		menuBar.add(mnNewMenu_2);
		
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		mnNewMenu_2.add(mntmNewMenuItem_8);
		mntmNewMenuItem_8.addActionListener(this);
		mntmNewMenuItem_6.addActionListener(this);
		mntmNewMenuItem_7.addActionListener(this);
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
			FrmUser dlg=new FrmUser(this,"�û���Ϣ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_2){
			FrmAddress dlg = new FrmAddress(this,"���͵�ַ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_3) {
			int i=this.goodtable.getSelectedRow();
			//BeanGoods goods=this.lbgoods.get(i);
			FrmShoppingCart dlg =new FrmShoppingCart(this, "���ﳵ", true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_4) {
			Frmcoupon dlg=new Frmcoupon(this,"�Ż�ȯ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_5) {
			Frmdiscount dlg=new Frmdiscount(this,"������Ϣ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_6) {
			//this.reloadLbTable();
			int i=this.lbtable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "����ѡ����Ʒ���","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadLbGoodsTabel(i);
			/*FrmUserMain.this.reloadLbGoodsTabel(i);
			int i=this.lbtable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "����ѡ����Ʒ���","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			FrmgoodsAdd dlg=new FrmgoodsAdd(this,"�����Ʒ",true);
			dlg.lbidString=tbllbData[i][0].toString();
			dlg.setVisible(true);
			if(dlg.getGoods()!=null){//ˢ�±��
				this.reloadLbGoodsTabel(i);
			}*/
		}
		else if(e.getSource()==mntmNewMenuItem_7) {
			int i=this.goodtable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Frmpj dlg=new Frmpj(this,"��Ʒ����",true,this.curgoods);
//			dlg.pjgoods=this.curgoods;
			//System.out.println(dlg.pjgoods.getLbid());
			dlg.setVisible(true);
			
		}
		else if(e.getSource()==mntmNewMenuItem_8) {
			Frmremenu dlg=new Frmremenu(this,"�Ż�ȯ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==btnNewButton) {
			int i=this.goodtable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Frmiscart dlg=new Frmiscart(this,"���ﳵ",true);
			dlg.setVisible(true);
			int amount=dlg.loadNum();
			if(amount<=0) {
				JOptionPane.showMessageDialog(null,  "������������Ϊ0�����������¼��빺�ﳵ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ShoppingCart buygood=new ShoppingCart();
			try {
				BeanShoppingCart buy=buygood.addcart(this.curgoods,amount);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,  "�����������ڿ�棬�����¼��빺�ﳵ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//if(dlg.get()!=null){//ˢ�±��
				//this.reloadTable();
			//}
			//this.reloadLbGoodsTabel();
		}
		else if(e.getSource()==mntmNewMenuItem) {
			Frmorder dlg=new Frmorder(this,"��Ʒ����",true);
			dlg.setVisible(true);
		}
	}
}
