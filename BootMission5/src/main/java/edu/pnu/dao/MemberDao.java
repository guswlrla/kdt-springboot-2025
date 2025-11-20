package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberDTO;

@Repository
public class MemberDao {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;

	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootmission", "musthave", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 검색(Read - select All)
	public List<MemberDTO> getAllMember() {
		List<MemberDTO> list = new ArrayList<>();

		String query = "select * from member";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 검색(Read – select One)
	public MemberDTO getMemberById(Integer id) {
		MemberDTO dto = new MemberDTO();
		String query = "select * from member where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return dto;
	}

	// 입력(Create - insert)
	public MemberDTO postMember(MemberDTO memberDTO) {
		MemberDTO dto = null;
		String query = "insert into member(pass, name) values (?, ?)";
		try {
			psmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());

			if (psmt.executeUpdate() == 1) {
				rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					dto = getMemberById(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 수정(Update – 객체 교체)
	public MemberDTO putMember(Integer id, MemberDTO memberDTO) {
		MemberDTO dto = null;
		String query = "update member set pass=?, name=? where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.setInt(3, id);

			if (psmt.executeUpdate() == 1) {
				dto = getMemberById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 수정(Update – 일부정보수정)
	public MemberDTO patchMember(Integer id, MemberDTO memberDTO) {
		MemberDTO dto = getMemberById(id);

		String query = "update member set pass=ifnull(?, pass), name=ifnull(?, name) where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.setInt(3, id);

			if (psmt.executeUpdate() == 1) {
				dto = getMemberById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public void deleteMember(Integer id) {
		String query = "delete from member where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
