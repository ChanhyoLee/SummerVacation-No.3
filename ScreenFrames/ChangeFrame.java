package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.Color;
import javax.swing.SwingConstants;

import database.DBConnection;

public class ChangeFrame extends JFrame{
	private JTextField password_current_field;
	private JTextField name_current_field;
	private JTextField id_after_field;
	private JTextField id_current_field;
	private JTextField name_after_field;
	private JTextField phone_current_field;
	private JTextField phone_after_field;
	private JTextField password_after_field;
	private JRadioButton male_radio;
	private boolean id_checked = false;
	private boolean pw_checked = false;
	private DBConnection connection;
	private String selected_id;
	private ChangeFrame cf = this;
	
	public ChangeFrame(DBConnection connection, String selected_id) {
		this.connection = connection;
		this.selected_id = selected_id;
		String[] selected_data_set = connection.get_user_data(selected_id);
		
		setTitle("Change Data");
		setSize(470,500);
		setLocation(500,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		password_current_field = new JTextField();
		password_current_field.setEnabled(false);
		password_current_field.setBounds(85, 81, 130, 26);
		password_current_field.setText(selected_data_set[1]);
		getContentPane().add(password_current_field);
		password_current_field.setColumns(10);
		
		password_after_field = new JTextField();
		password_after_field.setColumns(10);
		password_after_field.setBounds(227, 81, 130, 26);
		password_after_field.setText(selected_data_set[1]);
		getContentPane().add(password_after_field);
		
		id_after_field = new JTextField();
		id_after_field.setColumns(10);
		id_after_field.setBounds(227, 43, 130, 26);
		id_after_field.setText(selected_data_set[0]);
		getContentPane().add(id_after_field);
		
		id_current_field = new JTextField();
		id_current_field.setEnabled(false);
		id_current_field.setColumns(10);
		id_current_field.setBounds(85, 43, 130, 26);
		id_current_field.setText(selected_data_set[0]);
		getContentPane().add(id_current_field);
		
		name_current_field = new JTextField();
		name_current_field.setEnabled(false);
		name_current_field.setBounds(85, 119, 130, 26);
		name_current_field.setText(selected_data_set[3]);
		getContentPane().add(name_current_field);
		name_current_field.setColumns(10);
		
		name_after_field = new JTextField();
		name_after_field.setColumns(10);
		name_after_field.setBounds(227, 119, 130, 26);
		name_after_field.setText(selected_data_set[3]);
		getContentPane().add(name_after_field);
		
		phone_current_field = new JTextField();
		phone_current_field.setEnabled(false);
		phone_current_field.setColumns(10);
		phone_current_field.setBounds(85, 208, 130, 26);
		phone_current_field.setText(selected_data_set[4]);
		getContentPane().add(phone_current_field);
		
		phone_after_field = new JTextField();
		phone_after_field.setColumns(10);
		phone_after_field.setBounds(227, 208, 130, 26);
		phone_after_field.setText(selected_data_set[4]);
		getContentPane().add(phone_after_field);
		
		JLabel id_label = new JLabel("ID");
		id_label.setBounds(12, 48, 61, 16);
		getContentPane().add(id_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setBounds(12, 86, 61, 16);
		getContentPane().add(password_label);
		
		JLabel name_label = new JLabel("Name");
		name_label.setBounds(12, 124, 61, 16);
		getContentPane().add(name_label);
		
		JLabel phone_label = new JLabel("Phone");
		phone_label.setBounds(12, 213, 61, 16);
		getContentPane().add(phone_label);
		
		String year = selected_data_set[5].split("-")[0];
		System.out.println(year);
		JComboBox<Integer> yearBox = new JComboBox<Integer>();
		yearBox.setMaximumRowCount(100);
		yearBox.setBounds(129, 288, 130, 27);
		for(int i=1980;i<=2020;i++){
	        yearBox.addItem(i);
		}
		yearBox.setSelectedItem(Integer.parseInt(year));
		getContentPane().add(yearBox);
		
		String month = selected_data_set[5].split("-")[1];
		System.out.println(month);
		JComboBox<Integer> monthBox = new JComboBox<Integer>();
		monthBox.setMaximumRowCount(12);
		monthBox.setBounds(129, 329, 90, 27);
		for(int i=1;i<=12;i++){
	        monthBox.addItem(i);
		}
		monthBox.setSelectedItem(Integer.parseInt(month));
		getContentPane().add(monthBox);
		
		String day = selected_data_set[5].split("-")[2];
		System.out.println(day);
		JComboBox<Integer> dayBox = new JComboBox<Integer>();
		dayBox.setMaximumRowCount(31);
		dayBox.setBounds(231, 329, 90, 27);
		for(int i=1;i<=31;i++){
	        dayBox.addItem(i);
		}
		dayBox.setSelectedItem(Integer.parseInt(day));
		getContentPane().add(dayBox);
		
		JButton check_id_button = new JButton("Check");
		check_id_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = id_after_field.getText();
				if(id_current_field.getText().equals(id_after_field.getText())){
					new AutoExitFrame(13);
					id_checked = true;
					check_id_button.setEnabled(false);
					id_after_field.setEnabled(false);
					return;
				}
				else if(!check_id(id)) {
					new AutoExitFrame(0);
					id_checked = false;
				}
				else if(connection.is_exists(id)) {
					new AutoExitFrame(1);
					id_checked = false;
				}
				else {
					new AutoExitFrame(2);
					id_checked = true;
					check_id_button.setForeground(Color.RED);
					check_id_button.setEnabled(false);
					id_after_field.setEnabled(false);
				}
			}
		});
		check_id_button.setBounds(376, 43, 68, 29);
		getContentPane().add(check_id_button);
		
		JButton check_pw_button = new JButton("Check");
		check_pw_button.setBounds(376, 81, 68, 29);
		check_pw_button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String pw = String.valueOf(password_after_field.getText());
				if(!check_password(pw)) {
					new AutoExitFrame(4);
					pw_checked = false;
				}
				else {
					new AutoExitFrame(5);
					pw_checked = true;
					check_pw_button.setForeground(Color.RED);
					check_pw_button.setEnabled(false);
					password_after_field.setEnabled(false);
				}
			}
		});

		getContentPane().add(check_pw_button);
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.setBounds(111, 400, 117, 29);
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		getContentPane().add(cancel_button);
		
		JButton confirm_button = new JButton("Confirm");
		confirm_button.setBounds(240, 400, 117, 29);
		confirm_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = id_after_field.getText();
				char[] password =password_after_field.getText().toCharArray();
				String gender ="";
				if(male_radio.isSelected()) gender = "male";
				else gender = "female";
				//System.out.println(gender);
				String name = name_after_field.getText();
				String phone = phone_after_field.getText();
				//System.out.println("phonenumber: "+phone);
				int selected_year = (int)yearBox.getSelectedItem();
				int selected_month = (int)monthBox.getSelectedItem();
				int selected_day = (int)dayBox.getSelectedItem();
				int[] selected_date = new int[] {selected_year, selected_month, selected_day};
				int confirm = confirm(id, password, gender, name, phone, selected_date);
				if(!id_checked) {
					new AutoExitFrame(6);
				}
				else if(!pw_checked) {
					new AutoExitFrame(7);
				}
				else if(confirm == 1) {
					new AutoExitFrame(0);
				}
				else if(confirm == 2) {
					new AutoExitFrame(4);
				}
				else if(confirm ==3) {
					new AutoExitFrame(8);
				}
				else if(confirm ==4) {
					new AutoExitFrame(9);
				}
				else if(confirm ==5) {
					new AutoExitFrame(10);
				}
				else if(confirm ==-1) {
					new AutoExitFrame(11);
				}
				else if(id_checked && pw_checked && confirm==0) {
					//System.out.println(confirm(id, password, gender, name, phone, selected_date));
					new SigninSuccess(2);
					quit();
				}
				else {
					new SigninFail();
				}

			}
		});

		getContentPane().add(confirm_button);
		
		JLabel gender_label = new JLabel("Gender");
		gender_label.setBounds(12, 177, 61, 16);
		getContentPane().add(gender_label);
		
		JLabel birthday_label = new JLabel("Year");
		birthday_label.setBounds(12, 288, 61, 16);
		getContentPane().add(birthday_label);
		
		male_radio = new JRadioButton("Male");
		male_radio.setSelected(true);
		male_radio.setBounds(129, 173, 90, 23);
		getContentPane().add(male_radio);
		
		JRadioButton female_Radio = new JRadioButton("Female");
		female_Radio.setBounds(231, 171, 90, 23);
		getContentPane().add(female_Radio);
		
		ButtonGroup gender_button = new ButtonGroup();
		gender_button.add(male_radio);
		gender_button.add(female_Radio);
		
		if(selected_data_set[2].equals("male")) {
			male_radio.setSelected(true);
		}
		else {
			female_Radio.setSelected(true);
		}
		
		JLabel birthday_label_1 = new JLabel("MM/DD");
		birthday_label_1.setBounds(12, 329, 61, 16);
		getContentPane().add(birthday_label_1);
		
		JLabel id_explanation = new JLabel("3~10, 영 소,대문자, 숫자, '_'허용");
		id_explanation.setHorizontalAlignment(SwingConstants.CENTER);
		id_explanation.setForeground(Color.GRAY);
		id_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		id_explanation.setBounds(227, 66, 146, 16);
		getContentPane().add(id_explanation);
		
		JLabel pw_explanation = new JLabel("4~20, 영 소,대문자, 숫자 허용");
		pw_explanation.setHorizontalAlignment(SwingConstants.CENTER);
		pw_explanation.setForeground(Color.GRAY);
		pw_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		pw_explanation.setBounds(227, 102, 130, 16);
		getContentPane().add(pw_explanation);
		
		JLabel phone_explanation = new JLabel("11자리, - 없이 입력");
		phone_explanation.setHorizontalAlignment(SwingConstants.TRAILING);
		phone_explanation.setForeground(Color.GRAY);
		phone_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		phone_explanation.setBounds(227, 234, 130, 16);
		getContentPane().add(phone_explanation);
		
		JLabel name_explanation = new JLabel("한글, 영문 모두 가능");
		name_explanation.setHorizontalAlignment(SwingConstants.TRAILING);
		name_explanation.setForeground(Color.GRAY);
		name_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		name_explanation.setBounds(227, 143, 130, 16);
		getContentPane().add(name_explanation);
		
		JLabel current_label = new JLabel("Current");
		current_label.setBounds(129, 15, 47, 16);
		getContentPane().add(current_label);
		
		JLabel after_label = new JLabel("After");
		after_label.setBounds(274, 15, 47, 16);
		getContentPane().add(after_label);
		

		this.setVisible(true);
	}

	public void quit() {
		this.dispose();
	}
	
	public void get_user(String id) {
		
	}
	
	public boolean check_id(String id) {
		String id_regular = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
		//System.out.println(id + ": "+id.matches(id_regular));
		if(id.length()<3) return false;
		else if(id.length()>10) return false;
		else if(!id.matches(id_regular)) return false;
		return true;
	}
	public boolean check_password(String id) {
		String pw_regular = "^[a-zA-Z0-9]{4,20}$";
		if(id.length()<4) return false;
		else if(id.length()>20) return false;
		else if(!id.matches(pw_regular)) return false;
		return true;
		
	}

	public boolean check_phone(String phone) {
		if(phone.length()!=11) return false;
		else if(!phone.substring(0,3).equals("010")) {
			return false;
		}
		else return true;
	}
	public boolean check_date(int year, int month, int day) {
		if(month==2) {
			if(is_leap_year(year)) {
				if(day>29) return false;
			}
			else {
				if(day>28) return false;
			}
		}
		else if(Arrays.asList(new int[] {1,3,5,7,8,10,12}).contains(month)) {
			if(day>31) return false;
		}
		else if(Arrays.asList(new int[] {4,6,9,11}).contains(month)) {
			if(day>30) return false;
		}
		return true;
	}
	public int confirm(String id, char[] password, String gender, String name, String phone, int[] selected_date) {
		if(!check_id(id)) return 1;
		else if(!check_password(String.valueOf(password))) return 2;
		else if(name.length()<3) return 3;
		else if(!check_phone(phone)) return 4;
		else if(!check_date(selected_date[0], selected_date[1], selected_date[2])) return 5;
		String pw = String.valueOf(password);
		if(connection.change_record(id, pw, gender, name, phone, selected_date)>0) {
			return 0;
		}
		else return -1;
	}
	public boolean is_leap_year(int year) {
		if(year%400==0) {
			return true;
		}
		else if(year%100==0) {
			return false;
		}
		else if(year%4==0) {
			return true;
		}
		else return false;
	}
}
