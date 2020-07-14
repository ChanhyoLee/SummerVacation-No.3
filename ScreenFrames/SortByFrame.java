package ScreenFrames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SortByFrame extends JFrame{
	
	ButtonGroup column_group = new ButtonGroup();
	ButtonGroup type_group = new ButtonGroup();
	AdminFrame af;
	
	public SortByFrame(AdminFrame af) {
		this.af = af;
		setSize(540,280);
		setLocation(500,500);
		this.setResizable(false);
		setTitle("Order By");
		getContentPane().setLayout(null);
		
		JRadioButton id_radio = new JRadioButton("ID");
		id_radio.setBounds(17, 43, 46, 23);
		id_radio.setSelected(true);
		getContentPane().add(id_radio);
		
		JRadioButton pw_radio = new JRadioButton("PW");
		pw_radio.setBounds(75, 43, 50, 23);
		getContentPane().add(pw_radio);
		
		JRadioButton name_radio = new JRadioButton("NAME");
		name_radio.setBounds(137, 43, 74, 23);
		getContentPane().add(name_radio);
		
		JRadioButton phone_radio = new JRadioButton("PHONE");
		phone_radio.setBounds(223, 43, 81, 23);
		getContentPane().add(phone_radio);
		
		JRadioButton birthday_radio = new JRadioButton("BIRTHDAY");
		birthday_radio.setBounds(308, 43, 104, 23);
		getContentPane().add(birthday_radio);
		
		JRadioButton gender_radio = new JRadioButton("GENDER");
		gender_radio.setBounds(413, 43, 141, 23);
		getContentPane().add(gender_radio);
		
		column_group.add(id_radio);
		column_group.add(pw_radio);
		column_group.add(name_radio);
		column_group.add(phone_radio);
		column_group.add(birthday_radio);
		column_group.add(gender_radio);
		
		JRadioButton ascending_radio = new JRadioButton("Ascending");
		ascending_radio.setBounds(17, 128, 141, 23);
		ascending_radio.setSelected(true);
		getContentPane().add(ascending_radio);
		
		JRadioButton descending_radio = new JRadioButton("Descending");
		descending_radio.setBounds(170, 128, 141, 23);
		getContentPane().add(descending_radio);
		
		type_group.add(ascending_radio);
		type_group.add(descending_radio);
		
		JLabel column_label = new JLabel("Column");
		column_label.setBounds(17, 15, 61, 16);
		getContentPane().add(column_label);
		
		JLabel type_label = new JLabel("Type");
		type_label.setBounds(17, 100, 61, 16);
		getContentPane().add(type_label);
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.setBounds(137, 189, 117, 29);
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		getContentPane().add(cancel_button);
		
		JButton confirm_button = new JButton("Confirm");
		confirm_button.setBounds(301, 189, 117, 29);
		confirm_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(descending_radio.isSelected()) {
					af.set_type("DESC");
				}
				else af.set_type("ASC");
				if(id_radio.isSelected()) af.set_standard_column("ID");
				else if(pw_radio.isSelected()) af.set_standard_column("PW");
				else if(name_radio.isSelected()) af.set_standard_column("NAME");
				else if(phone_radio.isSelected()) af.set_standard_column("PHONE");
				else if(birthday_radio.isSelected()) af.set_standard_column("YEAR, MONTH, DAY");
				else if(gender_radio.isSelected()) af.set_standard_column("GENDER");	
				quit();
			}
		});
		getContentPane().add(confirm_button);

		
		this.setVisible(true);
	}
	
	public void quit() {
		this.dispose();
	}

}
