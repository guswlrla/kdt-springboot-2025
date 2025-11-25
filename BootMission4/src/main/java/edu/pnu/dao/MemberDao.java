package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberDTO;

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
	public Map<String, Object> getAllMember() {
		List<MemberDTO> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();

		String query = "select * from member";
		map.put("sqlstring", query);
		map.put("method", "GET");
		
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
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		map.put("list", list);
		return map;
	}

	// 검색(Read – select One)
	public Map<String, Object> getMemberById(Integer id) {
		MemberDTO dto = new MemberDTO();
		Map<String, Object> map = new HashMap<>();
		
		String query = "select * from member where id=?";
		map.put("sqlstring", query);
		map.put("method", "GET");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				map.put("success", true);
			} else {
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		map.put("dto", dto);
		return map;
	}

	// 입력(Create - insert)
	public Map<String, Object> postMember(MemberDTO memberDTO) {
		MemberDTO dto = null;
		Map<String, Object> map = new HashMap<>();
		
		String query = "insert into member(pass, name) values (?, ?)";
		map.put("sqlstring", query);
		map.put("method", "POST");
		
		try {
			psmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());

			if (psmt.executeUpdate() == 1) {
				rs = psmt.getGeneratedKeys();
				
				if (rs.next()) {
					int id = rs.getInt(1);
					Map<String, Object> rmap = getMemberById(id);
					dto = (MemberDTO) rmap.get("dto");
					
					if (dto != null) {
	                    map.put("success", true);
	                } else {
	                    map.put("success", false);
	                }
				} else {
					map.put("success", false);
				}
			}
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		map.put("dto", dto);
		return map;
	}

	// 수정(Update – 객체 교체)
	public Map<String, Object> putMember(Integer id, MemberDTO memberDTO) {
		MemberDTO dto = null;
		Map<String, Object> map = new HashMap<>();
		
		String query = "update member set pass=?, name=? where id=?";
		map.put("sqlstring", query);
		map.put("method", "PUT");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.setInt(3, id);

			if (psmt.executeUpdate() == 1) {
				Map<String, Object> rmap = getMemberById(id);
				dto = (MemberDTO) rmap.get("dto");
				
				if (dto != null) {
                    map.put("success", true);
                } else {
                    map.put("success", false);
                }
			} else {
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		map.put("dto", dto);
		return map;
	}

	// 수정(Update – 일부정보수정)
	public Map<String, Object> patchMember(Integer id, MemberDTO memberDTO) {
		MemberDTO dto = null;
	    Map<String, Object> map = new HashMap<>();

		String query = "update member set pass=ifnull(?, pass), name=ifnull(?, name) where id=?";
		map.put("sqlstring", query);
		map.put("method", "PATCH");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.setInt(3, id);

			if (psmt.executeUpdate() == 1) {
				Map<String, Object> rmap = getMemberById(id);
				dto = (MemberDTO) rmap.get("dto");
				
				if (dto != null) {
                    map.put("success", true);
                } else {
                    map.put("success", false);
                }
			} else {
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		map.put("dto", dto);
		return map;
	}

	public Map<String, Object> deleteMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		String query = "delete from member where id=?";
		map.put("sqlstring", query);
		map.put("method", "DELETE");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			map.put("success", true);
		} 
		catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}

}
