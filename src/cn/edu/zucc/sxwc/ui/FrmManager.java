package cn.edu.zucc.sxwc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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

import cn.edu.zucc.sxwc.comtrol.example.ExampleEmployeeManager;
import cn.edu.zucc.sxwc.model.BeanManager;
import cn.edu.zucc.sxwc.util.BaseException;

import java.awt.Window.Type;

public class FrmManager extends JDialog implements ActionListener{

	private  final JPanel contentPanel = new JPanel();
	private JButton btnNewButton = new JButton("添加管理员");
	private JButton btnNewButton_1 = new JButton("密码修改");
	private JButton btnNewButton_2 = new JButton("删除管理员");
	/**
	 * Launch the application.
	 */
	private Object tblTitle[]={"账号","姓名","密码"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	private void reloadUserTable(){
		try {
			List<BeanManager> users=(new ExampleEmployeeManager()).loadAllUsers();
			tblData =new Object[users.size()][3];
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getEmployeeid();
				tblData[i][1]=users.get(i).getEmployeename();
				tblData[i][2]=users.get(i).getPasswd();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/*public static void main(String[] args) {
		try {
			FrmManager dialog = new FrmManager();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public FrmManager(Frame f, String s, boolean b) {
		super(f,s,b);
		setTitle("管理员信息 ");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
//		contentPanel.setLayout(null);
		this.reloadUserTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		userTable.setBounds(10, 38, 678, 382);
		/*JScrollPane scrollPane = new JScrollPane(this.userTable);
		scrollPane.setSize(590, 392);
		scrollPane.setLocation(0, 38);
		this.getContentPane().add(scrollPane);*/
		
		btnNewButton.setBounds(0, 0, 113, 27);
		contentPanel.add(btnNewButton);
		
		
		btnNewButton_1.setBounds(137, 0, 113, 27);
		contentPanel.add(btnNewButton_1);
		
		
		btnNewButton_2.setBounds(281, 0, 113, 27);
		contentPanel.add(btnNewButton_2);
		this.btnNewButton.addActionListener(this);
		this.btnNewButton_1.addActionListener(this);
		this.btnNewButton_2.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
				return;
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnNewButton){
			FrmManager_AddManager dlg=new FrmManager_AddManager(this,"添加账号",true);
			dlg.setVisible(true);
			if(dlg.getManager()!=null){//刷新表格
				this.reloadUserTable();
			}
		}
		else if(e.getSource()==this.btnNewButton_1){
			FrmManager_ChangePwd dlg=new FrmManager_ChangePwd(this,"密码修改",true);
			dlg.setVisible(true);
			if(dlg.getManager()!=null){//刷新表格
				this.reloadUserTable();
			}
		}
		else if(e.getSource()==this.btnNewButton_2){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择账号","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除账号吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String employeeid=this.tblData[i][0].toString();
				try {
					(new ExampleEmployeeManager()).deleteUser(employeeid);
					this.reloadUserTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
