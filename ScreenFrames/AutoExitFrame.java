package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

public class AutoExitFrame extends JFrame{
	
	public AutoExitFrame(int error_type){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("System Message");
		setAlwaysOnTop(true);
		this.setSize(250,100);
		setLocation(630,500);
		
		
		JLabel exist_message = new JLabel("");
		if(error_type==0) exist_message.setText("Not Match to ID Condition!");
		else if(error_type==1) exist_message.setText("Alredy Exist ID!");
		else if(error_type==2) exist_message.setText("ID Checked Complete!");
		else if(error_type==3) exist_message.setText("Password Mismatch!");
		else if(error_type==4) exist_message.setText("Not Match to PW Condition!");
		else if(error_type==5) exist_message.setText("Password Checked Complete!");
		else if(error_type==6) exist_message.setText("ID check First!");
		else if(error_type==7) exist_message.setText("Password check First!");
		else if(error_type==8) exist_message.setText("Too short Name");
		else if(error_type==9) exist_message.setText("Incorrect Phone number Format!");		
		else if(error_type==10) exist_message.setText("Invalid Date!");
		else if(error_type==11) exist_message.setText("Unknown Error");
		else if(error_type==12) exist_message.setText("Select Row First");
		else if(error_type==13) exist_message.setText("Choose Not to Change ID");
		else if(error_type==14) exist_message.setText("Login to Admin...");
		else if(error_type==15) exist_message.setText("Login to User...");
		else if(error_type==16) exist_message.setText("Refreshing...");
		else if(error_type==17) exist_message.setText("Search Condition ON!");
		else if(error_type==18) exist_message.setText("Search Condition OFF!");
		else if(error_type==19) exist_message.setText("Signed Out Completely! GoodBYE..");
		else if(error_type==20) exist_message.setText("Logout Complete!! GoodBYE..");


		exist_message.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(exist_message, BorderLayout.CENTER);
		this.setVisible(true);
		auto_exit();
	}
	public void auto_exit() {
		Timer timer = new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		timer.start();
	}
	
	public void quit() {
		this.dispose();
	}

}
