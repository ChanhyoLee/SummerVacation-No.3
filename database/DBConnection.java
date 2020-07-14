package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {

	private Connection connection;
	private Statement st;
	private ResultSet rs;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chanhyo_schema", "root", "1234");
			st = connection.createStatement();
			
		}
		catch(Exception e) {
			System.out.println("데이터베이스 연결 오류: "+e.getMessage());
		}
	}
	
	public boolean isAdmin(String adminID, char[] adminPassword) {
		try {
			String adminPW = String.valueOf(adminPassword);
			String SQL = "SELECT * FROM ADMIN WHERE adminID = '" + adminID + "' and adminPassword = '" + adminPW + "' ";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) {
			System.out.println("데이터베이스 검색 오류: "+e.getMessage());
		}
		return false;
	}
	
	public boolean isUser(String userID, char[] userPassword) {
		try {
			String userPW = String.valueOf(userPassword);
			String SQL = "SELECT * FROM USER WHERE id = '" + userID + "' and pw = '" + userPW + "' ";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) {
			System.out.println("데이터베이스 검색 오류: "+e.getMessage());
		}
		return false;
	}
	
	public String[] get_user_data(String id) {
		String[] data_set = new String[6];
		try {
			String SQL = "SELECT * FROM USER WHERE id = '" + id+"'";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				if(!rs.getString("id").equals("admin")) {
					data_set[0] = rs.getString("id");
					data_set[1] = rs.getString("pw");
					data_set[2] = rs.getString("gender");
					data_set[3] = rs.getString("name");
					data_set[4] = rs.getString("phone");
					String birthday = String.valueOf(rs.getInt("year"))+"-"+String.valueOf(rs.getInt("month"))+"-"+String.valueOf(rs.getInt("day"));
					data_set[5] = birthday;
				}
			}
		} catch(Exception e) {
			System.out.println("데이터베이스 검색 오류: "+e.getMessage());
		}
		return data_set;	
	}
	
	public int insert_record(String new_id, String new_password, String new_gender, String new_name, String new_phone, int[] new_birthday) {
		int result=0;
		try {
			String SQL = String.format("INSERT IGNORE into USER values (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %d, %d, %d)",new_id, new_password, new_gender, new_name, new_phone, new_birthday[0], new_birthday[1], new_birthday[2]);
			result = st.executeUpdate(SQL);
		} catch(Exception e) {
			System.out.println("데이터베이스 삽입 오류: "+e.getMessage());
		}
		return result;
	}
	
	public int delete_record(String id) {
		int result=0;
		try {
			String SQL = String.format("DELETE IGNORE from USER where id=\"%s\"",id);
			result = st.executeUpdate(SQL);
		} catch(Exception e) {
			System.out.println("데이터베이스 삭제 오류: "+e.getMessage());
		}
		return result;
	}
	
	public int change_record(String new_id, String new_password, String new_gender, String new_name, String new_phone, int[] new_birthday) {
		int result=0;
		try {
			String SQL = String.format("UPDATE IGNORE USER set id=\"%s\", pw=\"%s\", gender=\"%s\", name=\"%s\", phone=\"%s\", year=%d, month=%d, day=%d",new_id, new_password, new_gender, new_name, new_phone, new_birthday[0], new_birthday[1], new_birthday[2]);
			
			result = st.executeUpdate(SQL);
		} catch(Exception e) {
			System.out.println("데이터베이스 수정 오류: "+e.getMessage());
		}
		return result;
	}
	
	public boolean is_exists(String new_id) {
		try {
			String SQL = String.format("SELECT * FROM USER");
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				//System.out.println(rs.getString("id") + " " +new_id);
				if(rs.getString("id").equals(new_id)) {
					return true;
				}
			}
		} catch(Exception e) {
		}
		return false;
	}
	
	public int count_labels() {
		int count = 0;
		try {
			String SQL = "SELECT * FROM USER";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				count++;
			}
		} catch(Exception e){
			System.out.println("데이터베이스 검색 오류: "+e.getMessage());
		}
		return count;
	}
	
	public ArrayList<String[]> get_all_user(String standard_column, String type) {
		ArrayList<String[]> user_data = new ArrayList<String[]>();
		try {
			String SQL = String.format("SELECT * FROM USER ORDER BY %s %s",standard_column, type);
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				if(rs.getString("id").equals("admin")) continue;
				ArrayList<String> temp_string_arraylist = new ArrayList<String>();
				temp_string_arraylist.add(rs.getString("id"));
				temp_string_arraylist.add(rs.getString("pw"));
				temp_string_arraylist.add(rs.getString("gender"));
				temp_string_arraylist.add(rs.getString("name"));
				String temp_phone1 = rs.getString("phone").substring(0,3);
				String temp_phone2 = rs.getString("phone").substring(3,7);
				String temp_phone3 = rs.getString("phone").substring(7);
				temp_string_arraylist.add(temp_phone1+"-"+temp_phone2+"-"+temp_phone3);
				String birthday = String.valueOf(rs.getInt("year"))+"-"+String.valueOf(rs.getInt("month"))+"-"+String.valueOf(rs.getInt("day"));
				temp_string_arraylist.add(birthday);
				user_data.add(temp_string_arraylist.toArray(new String[temp_string_arraylist.size()]));
			}
		} catch(Exception e) {
			System.out.println("데이터베이스 검색 오류"+e.getMessage());
		}
		return user_data;
	}
	
	public ArrayList<String[]> get_matching_user(String standard_column, String regx){
		ArrayList<String[]> user_data = new ArrayList<String[]>();
		try {
			String SQL = String.format("SELECT * FROM USER WHERE %s LIKE '%%%s%%'", standard_column, regx);
			System.out.println(SQL);
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				if(rs.getString("id").equals("admin")) continue;
				ArrayList<String> temp_string_arraylist = new ArrayList<String>();
				temp_string_arraylist.add(rs.getString("id"));
				temp_string_arraylist.add(rs.getString("pw"));
				temp_string_arraylist.add(rs.getString("gender"));
				temp_string_arraylist.add(rs.getString("name"));
				String temp_phone1 = rs.getString("phone").substring(0,3);
				String temp_phone2 = rs.getString("phone").substring(3,7);
				String temp_phone3 = rs.getString("phone").substring(7);
				temp_string_arraylist.add(temp_phone1+"-"+temp_phone2+"-"+temp_phone3);
				String birthday = String.valueOf(rs.getInt("year"))+"-"+String.valueOf(rs.getInt("month"))+"-"+String.valueOf(rs.getInt("day"));
				temp_string_arraylist.add(birthday);
				user_data.add(temp_string_arraylist.toArray(new String[temp_string_arraylist.size()]));
			}
		} catch(Exception e) {
			System.out.println("데이터베이스 검색 오류"+e.getMessage());
		}
		return user_data;
		
	}

	public Statement get_st() {return st;}
	public ResultSet get_rs() {return rs;}
}
