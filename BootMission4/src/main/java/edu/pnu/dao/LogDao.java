package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class LogDao {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public LogDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootmission", "musthave", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addLog(Map<String, Object> map) {
		String query = "insert into dblog(method, sqlstring, success) values(?, ?, ?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, (String) map.get("method"));
			psmt.setString(2, (String) map.get("sqlstring"));
			psmt.setBoolean(3, (boolean) map.get("success"));
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
