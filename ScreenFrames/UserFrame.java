package ScreenFrames;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import database.DBConnection;

public class UserFrame extends JFrame{
	String header[] = {"ID", "PASSWORD", "NAME", "PHONE", "BIRTHDAY"};
	ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>();
	DBConnection connection;
	String user_id;
	String[] image_array = new String[4];
	int k =0;
	
	public UserFrame(String id, DBConnection connection) {
		user_id = id;
		this.connection = connection;
		image_array[0] = "background.jpeg";
		image_array[1] = "background1.jpeg";
		image_array[2] = "background4.jpeg";
		image_array[3] = "background6.jpeg";
		
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
		getContentPane().setLayout(null);
		setLocation(400,200);
		setSize(480,500);
		setTitle("Welcome! "+id);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 600, 22);
		
		JMenu account_menu = new JMenu("Account");
		menuBar.add(account_menu);		
		JMenuItem show_menuitem = new JMenuItem("Show Information");
		JMenuItem revise_menuitem = new JMenuItem("Revise");
		JMenuItem logout_menuitem = new JMenuItem("Log Out");
		logout_menuitem.setForeground(Color.RED);
		JMenuItem signout_menuitem = new JMenuItem("Sign Out");
		signout_menuitem.setForeground(Color.LIGHT_GRAY);
		account_menu.add(show_menuitem);
		account_menu.add(revise_menuitem);
		account_menu.add(logout_menuitem);
		account_menu.add(signout_menuitem);
		show_menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserDataFrame(connection, id);
			}
		});
		revise_menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeFrame(connection, id);
			}
		});
		logout_menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to Logout?", "Confirm Diaglog", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
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
		signout_menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to Sign Out?", "Confirm Diaglog", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
				if(result ==JOptionPane.YES_OPTION) {
					connection.delete_record(id);
					new AutoExitFrame(19);
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
		
		JMenu page_menu = new JMenu("Page");
		menuBar.add(page_menu);
		JMenuItem next = new JMenuItem("Next"); 
		JMenuItem previous = new JMenuItem("Previous");
		JLabel background_label = new JLabel("New label");
		page_menu.add(next);
		page_menu.add(previous);
		background_label.setBounds(0, 22, 600, 480);
		getContentPane().add(background_label);
		background_label.setIcon(new ImageIcon(image_array[k]));
		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				background_label.setIcon(new ImageIcon(image_array[(++k)%4]));
			}
		});
		
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background_label.setIcon(new ImageIcon(image_array[(--k)%4]));
			}
		});
		getContentPane().add(menuBar);
			
		this.setVisible(true);
		
	}
	public void quit() {
		this.dispose();
	}
}