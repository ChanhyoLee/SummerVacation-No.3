package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

public class SigninFail extends JFrame{
	public SigninFail() {
		setAlwaysOnTop(true);
		this.setSize(250,100);
		setLocation(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("System Message");
		
		JLabel message_fail = new JLabel("Fail to Create!");
		message_fail.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(message_fail, BorderLayout.CENTER);
		setVisible(true);
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
