package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JTextField;

import database.DBConnection;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SignInFrame extends JFrame {
	private JTextField id_field;
	private JPasswordField pw_field;
	private JTextField name_field;
	private JTextField phone_field;
	private DBConnection connection;
	private boolean id_checked = false;
	private boolean pw_checked = false;
	JComboBox<Integer> yearBox;
	JComboBox<Integer>monthBox;
	JComboBox<Integer>dayBox;
	JRadioButton male_radio;
	ButtonGroup group;
	private JPasswordField passwordField;

	
	
	public SignInFrame(DBConnection connection) {
		this.connection = connection;
		setResizable(false);
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setSize(310,495);
		this.setLocation(600,300);
		
		id_field = new JTextField();
		id_field.setBounds(91, 54, 130, 26);
		getContentPane().add(id_field);
		id_field.setColumns(10);
		
		pw_field = new JPasswordField();
		pw_field.setEchoChar('*');
		pw_field.setColumns(10);
		pw_field.setBounds(91, 95, 130, 26);
		getContentPane().add(pw_field);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		passwordField.setBounds(91, 133, 130, 26);
		getContentPane().add(passwordField);
		
		name_field = new JTextField();
		name_field.setColumns(10);
		name_field.setBounds(91, 214, 203, 26);
		getContentPane().add(name_field);
		
		phone_field = new JTextField();
		phone_field.setColumns(10);
		phone_field.setBounds(91, 252, 203, 26);
		getContentPane().add(phone_field);
		
		
		
		JLabel id_label = new JLabel("ID");
		id_label.setBounds(25, 59, 61, 16);
		getContentPane().add(id_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setBounds(25, 100, 61, 16);
		getContentPane().add(password_label);
		
		JLabel name_label = new JLabel("Name");
		name_label.setBounds(25, 219, 61, 16);
		getContentPane().add(name_label);
		
		JLabel phone_label = new JLabel("Phone");
		phone_label.setBounds(25, 257, 61, 16);
		getContentPane().add(phone_label);
		
		JButton check_id_button = new JButton("Check");
		check_id_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = id_field.getText();
				if(!check_id(id)) {
					new AutoExitFrame(0);
					id_checked = false;
				}
				else if(connection.is_exists(id)) {
					new AutoExitFrame(1);
					id_checked = false;
				}
				else {
					int result = JOptionPane.showConfirmDialog(null, "Are you Sure to Use This ID?", "Confirm Diaglog", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
					if(result ==JOptionPane.YES_OPTION) {
						id_checked = true;
						check_id_button.setEnabled(false);
						id_field.setEnabled(false);
						new AutoExitFrame(2);
					}
					else {
						id_checked = false;
					}
				}
			}
		});
		check_id_button.setBounds(226, 54, 68, 29);
		getContentPane().add(check_id_button);
		
		JButton check_pw_button = new JButton("Check");
		check_pw_button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String pw = String.valueOf(pw_field.getPassword());
				String check_pw = String.valueOf(passwordField.getPassword());
				if(!pw.equals(check_pw)){
					new AutoExitFrame(3);
					pw_checked = false;
				}
				else if(!check_password(pw)) {
					new AutoExitFrame(4);
					pw_checked = false;
				}
				else {
					int result = JOptionPane.showConfirmDialog(null, "Are you Sure to Use This Password?", "Confirm Diaglog", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
					if(result==JOptionPane.YES_OPTION) {
					new AutoExitFrame(5);
					pw_checked = true;
					check_pw_button.setEnabled(false);
					pw_field.setEnabled(false);
					passwordField.setEnabled(false);
					}
					else pw_checked = false;
				}
			}
		});
		check_pw_button.setBounds(226, 133, 68, 29);
		getContentPane().add(check_pw_button);
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		cancel_button.setBounds(35, 406, 117, 29);
		getContentPane().add(cancel_button);
		
		JButton confirm_button = new JButton("Confirm");
		confirm_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = id_field.getText();
				char[] password = pw_field.getPassword();
				String gender ="";
				if(male_radio.isSelected()) gender = "male";
				else gender = "female";
				//System.out.println(gender);
				String name = name_field.getText();
				String phone = phone_field.getText();
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
					new SigninSuccess(1);
					quit();
				}
				else {
					new SigninFail();
				}

			}
		});
		confirm_button.setBounds(164, 406, 117, 29);
		getContentPane().add(confirm_button);
		JScrollPane scroller = new JScrollPane();
		
		
		yearBox = new JComboBox<Integer>();
		yearBox.setMaximumRowCount(100);
		yearBox.setBounds(91, 290, 130, 27);
		for(int i=1980;i<=2020;i++){
	        yearBox.addItem(i);
		}
		getContentPane().add(yearBox);
		
		monthBox = new JComboBox<Integer>();
		monthBox.setMaximumRowCount(12);
		monthBox.setBounds(91, 331, 90, 27);
		for(int i=1;i<=12;i++){
	        monthBox.addItem(i);
		}
		getContentPane().add(monthBox);
		
		dayBox = new JComboBox<Integer>();
		dayBox.setMaximumRowCount(31);
		dayBox.setBounds(193, 331, 90, 27);
		for(int i=1;i<=31;i++){
	        dayBox.addItem(i);
		}
		getContentPane().add(dayBox);
			
		JLabel gender_label = new JLabel("Gender");
		gender_label.setBounds(25, 181, 61, 16);
		getContentPane().add(gender_label);
		
		JLabel birthday_label = new JLabel("Year");
		birthday_label.setBounds(25, 294, 61, 16);
		getContentPane().add(birthday_label);
		
		male_radio = new JRadioButton("Male");
		male_radio.setBounds(91, 179, 90, 23);
		male_radio.setSelected(true);
		getContentPane().add(male_radio);
		
		JRadioButton female_Radio = new JRadioButton("Female");
		female_Radio.setBounds(193, 177, 90, 23);
		getContentPane().add(female_Radio);
		
		group = new ButtonGroup();
		group.add(male_radio);
		group.add(female_Radio);

		
		JLabel birthday_label_1 = new JLabel("MM/DD");
		birthday_label_1.setBounds(25, 335, 61, 16);
		getContentPane().add(birthday_label_1);
		
		JLabel id_explanation = new JLabel("3~10, 영 소,대문자, 숫자, '_'허용");
		id_explanation.setForeground(Color.GRAY);
		id_explanation.setHorizontalAlignment(SwingConstants.CENTER);
		id_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		id_explanation.setBounds(91, 78, 146, 16);
		getContentPane().add(id_explanation);
		
		JLabel pw_explanation = new JLabel("4~20, 영 소,대문자, 숫자 허용");
		pw_explanation.setHorizontalAlignment(SwingConstants.CENTER);
		pw_explanation.setForeground(Color.GRAY);
		pw_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		pw_explanation.setBounds(91, 120, 130, 16);
		getContentPane().add(pw_explanation);
		
		JLabel phone_explanation = new JLabel("11자리, - 없이 입력");
		phone_explanation.setHorizontalAlignment(SwingConstants.TRAILING);
		phone_explanation.setForeground(Color.GRAY);
		phone_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		phone_explanation.setBounds(164, 276, 130, 16);
		getContentPane().add(phone_explanation);
		
		JLabel name_explanation = new JLabel("한글, 영문 모두 가능");
		name_explanation.setHorizontalAlignment(SwingConstants.TRAILING);
		name_explanation.setForeground(Color.GRAY);
		name_explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		name_explanation.setBounds(164, 236, 130, 16);
		getContentPane().add(name_explanation);
		this.setVisible(true);
	}
	
	public void quit() {
		this.dispose();
	}
	public boolean check_id(String id) {
		String id_regular = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,11}$";
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
		if(connection.insert_record(id, pw, gender, name, phone, selected_date)>0) {
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
