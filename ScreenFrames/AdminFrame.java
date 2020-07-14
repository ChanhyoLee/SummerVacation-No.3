package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import database.DBConnection;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.Font;

public class AdminFrame extends JFrame{
	
	private String header[] = {"ID", "PASSWORD", "GENDER", "NAME", "PHONE", "BIRTHDAY"};
	private ArrayList<String[]> contents;
	private DBConnection connection;
	private Statement st;
	private ResultSet rs;
	private JTable table;
	private String standard_column="id";
	private String order="ASC";
	private AdminFrame af = this;
	private boolean search_on = false;
	private String regx="";
	private String match_column="id";
	
	public AdminFrame(DBConnection connection, Statement st, ResultSet rs) {
		this.connection = connection;
		this.st = st;
		this.rs = rs;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setTitle("Admin Page");
		this.setSize(750,700);
		this.setLocation(100,100);
		getContentPane().setLayout(null);
		
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                int result = JOptionPane.showConfirmDialog(null, "Are you sure to exit ?", "Comfirm Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
                if(result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
		
		contents = connection.get_all_user(standard_column, order);
		table = new JTable();
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		String[][] temp = contents.toArray(new String[contents.size()][6]);
		DefaultTableModel model = new DefaultTableModel(temp, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setSize(600,670);
		scrollpane.setViewportView(table);
		//model.fireTableDataChanged();
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		table.setBounds(0, 0, 600, 670);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.setRowHeight(50);
		//getContentPane().add(table);
		getContentPane().add(scrollpane);
		
		JButton create_button = new JButton("Add New User");
		create_button.setBounds(612, 71, 132, 35);
		create_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignInFrame(connection);
			}
		});
		getContentPane().add(create_button);
		
		JButton change_button = new JButton("Revise User");
		change_button.setBounds(612, 139, 132, 35);
		change_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0) {
					new AutoExitFrame(12);
				}
				else if(table.getSelectedRowCount()==1) {
					new ChangeFrame(connection, (String)table.getValueAt(table.getSelectedRow(),0));
				}
					
			}
		});
		getContentPane().add(change_button);
		
		JButton delete_button = new JButton("Delete User");
		delete_button.setBounds(612, 209, 132, 35);
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0) {
					new AutoExitFrame(12);
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
				if(result ==JOptionPane.YES_OPTION) connection.delete_record((String)table.getValueAt(table.getSelectedRow(), 0));
			}
		});
		getContentPane().add(delete_button);
		
		JButton refresh_button = new JButton("Refresh");
		refresh_button.setBounds(612, 281, 132, 35);
		refresh_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AutoExitFrame(16);
				Timer refresh_timer = new Timer(950, null);
				ActionListener al = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(search_on) {
							contents = connection.get_matching_user(match_column, regx);
						}
						else {
							contents = connection.get_all_user(standard_column, order);
						}
						String[][] temp = contents.toArray(new String[contents.size()][6]);
						DefaultTableModel model = new DefaultTableModel(temp, header) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						table.setModel(model);
						table.getColumnModel().getColumn(2).setPreferredWidth(50);
						table.getColumnModel().getColumn(4).setPreferredWidth(100);
						scrollpane.setViewportView(table);
						refresh_timer.stop();
					}
				};
				refresh_timer.addActionListener(al);
				refresh_timer.start();

			}
		});
		getContentPane().add(refresh_button);
		
		JButton logout_button = new JButton("Logout");
		logout_button.setBounds(612, 641, 132, 29);
		logout_button.setForeground(Color.RED);
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to Logout?", "Confirm Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
				if(result ==JOptionPane.YES_OPTION) {
					new AutoExitFrame(20);
					quit();
					ActionListener al = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new HomeFrame();
							((Timer)e.getSource()).stop();
						}
					};
					final Timer next_frame = new Timer(1000, al);
					next_frame.start();
				}
			}
		});
		getContentPane().add(logout_button);
		
		JButton sort_button = new JButton("Sort By");
		sort_button.setBounds(612, 470, 132, 35);
		sort_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SortByFrame(af);
			}
		});
		getContentPane().add(sort_button);
		
		JButton search_button = new JButton("Search Condition");
		search_button.setBounds(612, 350, 132, 35);
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchConditionFrame(af);
			}
		});
		getContentPane().add(search_button);
		//pack();
		setVisible(true);
	}
	
	
	public void set_type(String type) {this.order = type;}
	public void set_standard_column(String column) {this.standard_column = column;}
	public void set_regx(String regx) {this.regx = regx;}
	public void set_match_column(String match_column) {this.match_column = match_column;}
	public void set_search_on(boolean x) {this.search_on = x;}
	
	public String get_regx() {return regx;}
	public String get_match_column() {return match_column;}

	public void quit() {
		this.dispose();
	}
}
