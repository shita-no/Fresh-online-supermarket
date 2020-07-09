package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
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

import cn.edu.zucc.sxwc.comtrol.example.Goods;
import cn.edu.zucc.sxwc.comtrol.example.ShoppingCart;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanShoppingCart;

import cn.edu.zucc.sxwc.util.BaseException;

public class FrmShoppingCart extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Object tblTitle[]=BeanShoppingCart.CartTitles;
	private Object tblcartData[][];
	DefaultTableModel tabcartmod=new DefaultTableModel();
	private JTable carttable=new JTable(tabcartmod);
	List<BeanShoppingCart> allcart=null;
	private List<BeanGoods> goods;
	private BeanShoppingCart cart=null;
	private void reloadCartTable(){
		try {
			ShoppingCart cart=new ShoppingCart();
			allcart=cart.loadCart();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblcartData =  new Object[allcart.size()][BeanShoppingCart.CartTitles.length];
		for(int i=0;i<allcart.size();i++){
			for(int j=0;j<BeanShoppingCart.CartTitles.length;j++)
				tblcartData[i][j]=allcart.get(i).getCell(j);
		}
			tabcartmod.setDataVector(tblcartData,tblTitle);
			this.carttable.validate();
			this.carttable.repaint();
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			FrmShoppingCart dialog = new FrmShoppingCart();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public FrmShoppingCart(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("购物车");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadCartTable();
		this.getContentPane().add(new JScrollPane(this.carttable), BorderLayout.CENTER);
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		this.getContentPane().add(contentPanel,BorderLayout.SOUTH);
		{
			btnNewButton = new JButton("结算");
			contentPanel.add(btnNewButton);
		}
		{
			btnNewButton_1 = new JButton("移除购物车");
			contentPanel.add(btnNewButton_1);
		}
		this.btnNewButton.addActionListener(this);
		this.btnNewButton_1.addActionListener(this);
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
	//public BeanReader getReader() {
		//return reader;
	//}
}
