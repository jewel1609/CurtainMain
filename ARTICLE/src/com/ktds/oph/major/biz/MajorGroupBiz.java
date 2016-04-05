package com.ktds.oph.major.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.major.dao.MajorGroupDAO;
import com.ktds.oph.major.vo.MajorGroupListVO;
import com.ktds.oph.major.vo.MajorGroupSearchVO;
import com.ktds.oph.major.vo.MajorGroupVO;
import com.ktds.oph.major.vo.MajorListVO;
import com.ktds.oph.major.vo.MajorVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.univ.vo.UnivListVO;
import com.ktds.oph.util.web.Paging;

public class MajorGroupBiz {
	
	private MajorGroupDAO majorGroupDAO;
	
	public MajorGroupBiz() {
		majorGroupDAO = new MajorGroupDAO();
	}

	public MajorGroupListVO getAllMajor(MajorGroupSearchVO majorSearchVO) {
		int allMajorGroupCount = majorGroupDAO.getAllMajorGroupCount();
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allMajorGroupCount);
		paging.setPageNumber(majorSearchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		majorSearchVO.setStartIndex( paging.getStartArticleNumber() );
		majorSearchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<MajorGroupVO> majorGroups = new ArrayList<MajorGroupVO>();
		majorGroups = majorGroupDAO.getAllMajorGroup(majorSearchVO);
		// paging 바꾸는 이유 검색 때문에
		
		MajorGroupListVO majorGroupList = new MajorGroupListVO();
		majorGroupList.setMajorGroupList(majorGroups);
		majorGroupList.setPaging(paging);
		
		// 몇 번째 페이지
		return majorGroupList;
		
	}

	public MajorListVO getMajor(MajorGroupSearchVO majorSearchVO, MajorVO majorVO) {
		int allMajorCount = majorGroupDAO.getAllMajorCount(majorVO);
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allMajorCount);
		paging.setPageNumber(majorSearchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		majorSearchVO.setStartIndex( paging.getStartArticleNumber() );
		majorSearchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<MajorVO> majors = new ArrayList<MajorVO>();
		majors = majorGroupDAO.getAllMajor(majorSearchVO, majorVO);
		// paging 바꾸는 이유 검색 때문에
		
		MajorListVO majorList = new MajorListVO();
		majorList.setMajorList(majors);
		majorList.setPaging(paging);
		
		// 몇 번째 페이지
		return majorList;
	}

	public boolean registerMajorGroup(MajorGroupVO majorGroupVO, MemberVO member) {
		int insertCount = 0;
		
		if (member.getMemberTypeId() == 6 ) {
			insertCount = majorGroupDAO.registerMajorGroup(majorGroupVO);
		}
		return insertCount > 0;
	}

	public boolean updateMajorGroupName(MajorGroupVO majorGroupVO, MemberVO member) {
		if (member.getMemberTypeId() == 6 ) {

			String majorGroupName = majorGroupDAO.getMajorGroupName(majorGroupVO);
			if (majorGroupName.equals(majorGroupVO.getMajorGroupName())) {
				return false;
				
			}
			else {
				return majorGroupDAO.updateMajorGroupName(majorGroupVO) > 0;
			}

		}
		return false;
	}

	public void deleteMajorGroups(String[] majorGroupIds, MemberVO member) {
		if (member.getMemberTypeId() == 6 ) {
			for (String majorGroupId : majorGroupIds) {
				majorGroupDAO.deleteMajorGroups(Integer.parseInt(majorGroupId));
				majorGroupDAO.deleteMajors(Integer.parseInt(majorGroupId));
			}
		}

	}

	public boolean registerMajor(MajorVO majorVO, MemberVO member) {
		int insertCount = 0;
		
		if (member.getMemberTypeId() == 6 ) {
			insertCount = majorGroupDAO.registerMajor(majorVO);
		}
		return insertCount > 0;
	}

	public boolean updateMajorName(MajorVO majorVO, MemberVO member) {
		if (member.getMemberTypeId() == 6 ) {

			String majorName = majorGroupDAO.getMajorName(majorVO);
			if (majorName.equals(majorVO.getMajorName())) {
				return false;
				
			}
			else {
				return majorGroupDAO.updateMajorName(majorVO) > 0;
			}

		}
		return false;
	}

	public void deleteMajors(String[] majorIds, MemberVO member) {
		if (member.getMemberTypeId() == 6 ) {
			for (String majorId : majorIds) {
				majorGroupDAO.deleteMajors2(Integer.parseInt(majorId));
			}
		}
		
	}

}
