package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.pnu.domain.MemberDTO;
import edu.pnu.controller.MemberController;

public class MemberDao {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootmission", "musthave", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO addMemberDTO(MemberDTO memberDTO) {
		MemberDTO m = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement("insert into member(pass, name) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			
			if(psmt.executeUpdate() == 1) {
				rs = psmt.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					m = getMemberById(id);
				}
			}
			System.out.println(psmt.toString().split(": ")[1]);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
			} catch(Exception e) {}
		}
		return null;
	}

}
