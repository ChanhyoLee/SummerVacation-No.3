package ScreenFrames;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class SearchConditionFrame extends JFrame{
	private static final String NOT_SELECTABLE_OPTION = " --Select an Field-- ";
	private JTextField condition_field;
	AdminFrame af;
	public SearchConditionFrame(AdminFrame af) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Search Condition");
		setSize(320,160);
		setLocation(500,500);
		getContentPane().setLayout(null);
		
		JComboBox<String> column_combobox = new JComboBox<String>();
		column_combobox.setBounds(36, 22, 250, 27);
		getContentPane().add(column_combobox);
	    column_combobox.setModel(new DefaultComboBoxModel<String>() {
	        private static final long serialVersionUID = 1L;
	        boolean selectionAllowed = true;

	        @Override
	        public void setSelectedItem(Object anObject) {
	          if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
	            super.setSelectedItem(anObject);
	          } else if (selectionAllowed) {
	            // Allow this just once
	            selectionAllowed = false;
	            super.setSelectedItem(anObject);
	          }
	        }
	      });
	    column_combobox.addItem(NOT_SELECTABLE_OPTION);
	    column_combobox.addItem("ID");	column_combobox.addItem("PW");
	    column_combobox.addItem("GENDER");  column_combobox.addItem("NAME");
	    column_combobox.addItem("PHONE");	column_combobox.addItem("BIRTHDAY");
		
		condition_field = new JTextField();
		condition_field.setBounds(36, 55, 250, 26);
		getContentPane().add(condition_field);
		condition_field.setText(af.get_regx());
		condition_field.setColumns(10);
		
		JButton cancel_button = new JButton("OFF");
		cancel_button.setBounds(36, 93, 117, 29);
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				af.set_search_on(false);
				af.set_regx("");
				af.set_match_column("id");
				new AutoExitFrame(18);
				quit();
			}
		});
		getContentPane().add(cancel_button);
		
		JButton confirm_button = new JButton("ON");
		confirm_button.setBounds(165, 93, 117, 29);
		confirm_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				af.set_match_column((String)column_combobox.getSelectedItem());
				af.set_regx(condition_field.getText());
				af.set_search_on(true);
				new AutoExitFrame(17);
				quit();
			}
		});
		getContentPane().add(confirm_button);
		setVisible(true);
	}
	
	public void quit() {
		this.dispose();
	}
}
