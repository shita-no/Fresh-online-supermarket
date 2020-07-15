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

import org.hibernate.boot.model.relational.InitCommand;

import cn.edu.zucc.sxwc.comtrol.example.ExampleUserManager;
import cn.edu.zucc.sxwc.comtrol.example.Goods;
import cn.edu.zucc.sxwc.comtrol.example.Lb;
import cn.edu.zucc.sxwc.comtrol.example.Remenu;
import cn.edu.zucc.sxwc.model.BeanGoods;
import cn.edu.zucc.sxwc.model.BeanLb;
import cn.edu.zucc.sxwc.model.BeanRemenu;
import cn.edu.zucc.sxwc.model.BeanUser;
import cn.edu.zucc.sxwc.util.BaseException;

public class FrmUser extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Object tblTitle[]=BeanUser.tableTitles;
	private Object tbluserData[][];
	DefaultTableModel tabusermod=new DefaultTableModel();
	private JTable usertable=new JTable(tabusermod);
	List<BeanUser> alluser=null;
	private BeanUser nowuser=null;
	private void reloadUserTable(){
		try {
			ExampleUserManager user=new ExampleUserManager();
			alluser=user.loadUser();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tbluserData =  new Object[alluser.size()][BeanUser.tableTitles.length];
		for(int i=0;i<alluser.size();i++){
			for(int j=0;j<BeanUser.tableTitles.length;j++)
				tbluserData[i][j]=alluser.get(i).getCell(j);
		}
		tabusermod.setDataVector(tbluserData,tblTitle);
		this.usertable.validate();
		this.usertable.repaint();
	}
	/*private void reloadUserTable(){
		
		nowuser=BeanUser.currentLoginUser;
		tbluserData =  new String[1][BeanUser.tableTitles.length];
		
		for( int i=0;i<1;i++){
			for(int j=0;j<BeanUser.tableTitles.length;j++)
				tbluserData[i][j]=nowuser.getCell(j);
		}
			tabusermod.setDataVector(tbluserData,tblTitle);
			this.usertable.validate();
			this.usertable.repaint();
	}*/
	
	private JButton btnNewButton = new JButton("��Ϊ��Ա");
	private final JButton btnNewButton_1 = new JButton("�޸�����");
	
	public FrmUser(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("�û���Ϣ");
		setBounds(100, 100, 805, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reloadUserTable();
		this.getContentPane().add(new JScrollPane(this.usertable), BorderLayout.CENTER);
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		//JScrollPane scrollPane = new JScrollPane(this.usertable);
		//this.getContentPane().add(scrollPane, BorderLayout.SOUTH);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		btnNewButton.setBounds(314, 0, 113, 27);
		contentPanel.add(btnNewButton);
		this.btnNewButton.addActionListener(this);
		
		
		//btnNewButton_1.setBounds(41, 0, 113, 27);
		//contentPanel.add(btnNewButton_1);
		this.btnNewButton_1.addActionListener(this);
		this.getContentPane().add(contentPanel,BorderLayout.SOUTH);
		
		contentPanel.add(btnNewButton_1);
		
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
		if (e.getSource() == this.btnNewButton) {
			try {
				ExampleUserManager user=new ExampleUserManager();
				user.upvip();
				this.reloadUserTable();
				this.setVisible(false);
				this.setVisible(true);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		else if(e.getSource() == this.btnNewButton_1) {
			FrmModifyPwd dlg=new FrmModifyPwd(this,"�����޸�",true);
			dlg.setVisible(true);
			
			this.reloadUserTable();
			
		}
	}
}
