package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;
import com.kh.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;
public class MemberController {
	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	public Member findOneById(String memberId) {
		return mDao.selectOneById(memberId);
	}

	public List<Member> showMemberList() {
		List<Member> mList = mDao.selectList();
		return mList;
	}
	
	public int registerMember(Member member) {
		int result = mDao.insertMember(member);
		return result;
	}
	
	public int updateMember(Member member) {
		int result = mDao.updateMember(member);
		return result;
	}

	
	public int deleteMember(String memberId) {
		return mDao.deleteMember(memberId);
	}

}
