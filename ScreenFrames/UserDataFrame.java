package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import database.DBConnection;

public class UserDataFrame extends JFrame {
	DBConnection connection;
	
	public UserDataFrame(DBConnection connection, String id) {
		this.connection = connection;
		getContentPane().setLayout(null);
		setSize(450,300);
		setLocation(500,500);
		String[] selected_data_set = connection.get_user_data(id);
		
		JLabel id_label = new JLabel("ID");
		id_label.setHorizontalAlignment(SwingConstants.TRAILING);
		id_label.setBounds(20, 30, 91, 15);
		getContentPane().add(id_label);
		
		JLabel pw_label = new JLabel("PW");
		pw_label.setHorizontalAlignment(SwingConstants.TRAILING);
		pw_label.setBounds(20, 70, 91, 16);
		getContentPane().add(pw_label);
		
		JLabel gender_label = new JLabel("GENDER");
		gender_label.setHorizontalAlignment(SwingConstants.TRAILING);
		gender_label.setBounds(20, 110, 91, 16);
		getContentPane().add(gender_label);
		
		JLabel birthday_label = new JLabel("BIRTHDAY");
		birthday_label.setHorizontalAlignment(SwingConstants.TRAILING);
		birthday_label.setBounds(20, 229, 91, 16);
		getContentPane().add(birthday_label);
		
		JLabel name_label = new JLabel("NAME");
		name_label.setHorizontalAlignment(SwingConstants.TRAILING);
		name_label.setBounds(20, 150, 91, 16);
		getContentPane().add(name_label);
		
		JLabel phone_label = new JLabel("PHONE");
		phone_label.setHorizontalAlignment(SwingConstants.TRAILING);
		phone_label.setBounds(20, 190, 91, 16);
		getContentPane().add(phone_label);
		
		JLabel id_label_1 = new JLabel("ID");
		id_label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		id_label_1.setBounds(231, 30, 157, 15);
		id_label_1.setText(selected_data_set[0]);
		getContentPane().add(id_label_1);
		
		JLabel pw_label_1 = new JLabel("PW");
		pw_label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		pw_label_1.setBounds(231, 70, 157, 16);
		pw_label_1.setText(selected_data_set[1]);
		getContentPane().add(pw_label_1);
		
		JLabel gender_label_1 = new JLabel("GENDER");
		gender_label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		gender_label_1.setBounds(231, 110, 157, 16);
		gender_label_1.setText(selected_data_set[2]);
		getContentPane().add(gender_label_1);
		
		JLabel birthday_label_1 = new JLabel("BIRTHDAY");
		birthday_label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		birthday_label_1.setBounds(231, 229, 157, 16);
		birthday_label_1.setText(selected_data_set[5]);
		getContentPane().add(birthday_label_1);
		
		JLabel name_label_1 = new JLabel("NAME");
		name_label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		name_label_1.setBounds(231, 150, 157, 16);
		name_label_1.setText(selected_data_set[3]);
		getContentPane().add(name_label_1);
		
		JLabel phone_label_1 = new JLabel("PHONE");
		phone_label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		phone_label_1.setBounds(231, 190, 157, 16);
		phone_label_1.setText(selected_data_set[4]);
		getContentPane().add(phone_label_1);
		setVisible(true);
	}
}
