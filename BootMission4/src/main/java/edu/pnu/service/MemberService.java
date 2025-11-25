package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	private LogDao logDao = new LogDao();
	
	public List<MemberDTO> getAllMember() {
		Map<String, Object> map = memberDao.getAllMember();
		logDao.addLog(map);
		return (List<MemberDTO>) map.get("list");
	}
	public MemberDTO getMemberById(Integer id) {
		Map<String, Object> map = memberDao.getMemberById(id);
		logDao.addLog(map);
		return (MemberDTO) map.get("dto");
	}
	public MemberDTO postMember(MemberDTO memberDTO) {
		Map<String, Object> map = memberDao.postMember(memberDTO);
		logDao.addLog(map);
		return (MemberDTO) map.get("dto");
	}
	public MemberDTO putMember(Integer id, MemberDTO memberDTO) {
		Map<String, Object> map = memberDao.putMember(id, memberDTO);
		logDao.addLog(map);
		return (MemberDTO) map.get("dto");
	}
	 public MemberDTO patchMember(Integer id, MemberDTO memberDTO) {	
		 Map<String, Object> map = memberDao.patchMember(id, memberDTO);
		 logDao.addLog(map);
		 return (MemberDTO) map.get("dto");
	 }
	public Map<String, Object> deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		logDao.addLog(map);
		return map;
	}
}
