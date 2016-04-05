package com.ktds.oph.member.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.dao.MemberDAO;
import com.ktds.oph.member.vo.MemberListVO;
import com.ktds.oph.member.vo.MemberSearchVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.web.Paging;

public class MemberBiz {
	
	private MemberDAO memberDAO;
	
	public MemberBiz() {
		memberDAO = new MemberDAO();
	}

	public boolean isAdmin(MemberVO loginMember){
		MemberVO member = memberDAO.getMemberByIdAndPassword(loginMember);
		if(member.getMemberTypeId() == 6){
			return true;
		}
		return false;
	}
	
	public boolean login(MemberVO loginMember, HttpServletRequest request) {
		
			// 1. 회원 정보를 가져온다. (task)
			MemberVO member = memberDAO.getMemberByIdAndPassword(loginMember);
			// 2. 회원 정보가 존재한다면, 세션에 집어 넣는다. (3개 메소드 더)
			if ( member != null ) {
				HttpSession session = request.getSession();
				session.setAttribute("_MEMBER_", member); // 세션은 앞뒤에 언더바 그리고 대문자로 (키, VALUE)
			}
			
			// 3. 결과를 보고한다.
		return member!=null;
	}

	public MemberListVO getAllMember(MemberSearchVO searchVO) {
		
		int allMemberCount = memberDAO.getAllMemberCount();
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allMemberCount);
		paging.setPageNumber(searchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		searchVO.setStartIndex( paging.getStartArticleNumber() );
		searchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<MemberVO> members = new ArrayList<MemberVO>();
		members = memberDAO.getAllMember(searchVO);
		// paging 바꾸는 이유 검색 때문에
		
		MemberListVO memberList = new MemberListVO();
		memberList.setMemberList(members);
		memberList.setPaging(paging);
		
		// 몇 번째 페이지
		
		
		return memberList;
		
	}

	public void deleteMembers(String[] deleteMemberEmail, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			for (String memberEmail : deleteMemberEmail ) {
				memberDAO.deleteMember(memberEmail);
				System.out.println("Biz : " + memberEmail);
			}
		}
		
	}

	public MemberVO getMemberInfoByEmail(String memberEmail, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			return memberDAO.getMemberInfoByEmail(memberEmail);
		}
		return null;
	}

	public void modifyMember(String modifyMemberEmail, String modifyMemberTypeId, String modifyMemberPoint,
			String modifyMemberPassword, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			memberDAO.modifyMember(modifyMemberEmail,modifyMemberTypeId,modifyMemberPoint,modifyMemberPassword,member);
		}
		
	}

	public void modifyRankDate(String modifyMemberEmail, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			memberDAO.modifyRankDate(modifyMemberEmail);
		}
	}

}
