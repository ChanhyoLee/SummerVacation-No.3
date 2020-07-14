package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Window.Type;

public class SigninSuccess extends JFrame{
	public SigninSuccess(int n) {
		setResizable(false);
		setTitle("System Message");
		setAlwaysOnTop(true);
		this.setSize(250,100);
		setLocation(500,500);
		JLabel message_success = new JLabel();
		
		if(n==1) {
			message_success.setText("Created Successfully!");
		}
		else if(n==2) {
			message_success.setText("Changed Successfully!");
		}
		
		message_success.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(message_success, BorderLayout.CENTER);
		this.setVisible(true);
		auto_exit();
	}
	
	public void auto_exit() {
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}		
		};
		Timer timer = new Timer(1000,al);
		timer.start();
	}
	
	public void quit() {
		this.dispose();
	}
}
