package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.Timer;

import database.DBConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.im.InputContext;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.UIManager;

public class HomeFrame extends JFrame{
	private JTextField id_field;
	private JPasswordField pw_field;
	private JButton btnSignIn;
	private JPanel panel = new JPanel();
	private DBConnection connection = new DBConnection();
	private JLabel error_label = new JLabel();
	private JLabel language_label;
	
	public HomeFrame() {
		setResizable(false);
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
		setTitle("로그인");
		setSize(400,200);
		setLocation(500,200);
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		pw_field = new JPasswordField();
		pw_field.setEchoChar('*');
		pw_field.setColumns(10);
		pw_field.setBounds(112, 99, 130, 26);
		getContentPane().add(pw_field);
		
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "";
				char[] password = new char[20];
				id = id_field.getText();
				password = pw_field.getPassword();
				if(connection.isAdmin(id, password)) {
					//System.out.println("관리자여부: "+true);
					new AutoExitFrame(14);
					quit();
					ActionListener al = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new AdminFrame(connection, connection.get_st(), connection.get_rs());
							((Timer)e.getSource()).stop();
						}
					};
					final Timer next_frame = new Timer(1000, al);
					next_frame.start();

				}
				else if(connection.isUser(id, password)){
					//System.out.println("사용자여부: "+connection.isUser(id, password));
					new AutoExitFrame(15);
					quit();
					ActionListener al = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new UserFrame(id_field.getText(), connection);
							((Timer)e.getSource()).stop();
						}
					};
					final Timer next_frame = new Timer(1000, al);
					next_frame.start();
				}
				else {
					pw_field.setText("");
					error_label.setText("Please Check Your ID & PW");
					error_label.setForeground(Color.RED);
					error_label.setVisible(true);
				}
			}
		});
		
		id_field = new JTextField();
		id_field.setBounds(112, 51, 130, 26);
		getContentPane().add(id_field);
		id_field.setColumns(10);
		login_button.setBounds(265, 51, 117, 29);
		getContentPane().add(login_button);
		
		
		
		btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error_label.setVisible(false);
				pw_field.setText("");
				SignInFrame signframe = new SignInFrame(connection);
			}
		});
		btnSignIn.setBounds(265, 99, 117, 29);
		getContentPane().add(btnSignIn);
		
		JLabel id_label = new JLabel("ID");
		id_label.setBounds(39, 56, 61, 16);
				

		error_label.setBounds(112, 128, 194, 16);
		error_label.setVisible(false);
		getContentPane().add(error_label);
		getContentPane().add(id_label);
		
		JLabel pw_label = new JLabel("PW");
		pw_label.setBounds(39, 104, 61, 16);
		getContentPane().add(pw_label);
		


		panel.setBounds(6, 6, 388, 166);
		//ImageIcon image = new ImageIcon("backgroud.jpeg");
		
		//System.out.println(image.toString());
		this.setVisible(true);
	}
	
	public void quit() {
		this.dispose();
	}
	
}
