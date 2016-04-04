package com.ktds.oph.major.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.major.dao.MajorGroupDAO;
import com.ktds.oph.major.vo.MajorGroupListVO;
import com.ktds.oph.major.vo.MajorGroupSearchVO;
import com.ktds.oph.major.vo.MajorGroupVO;
import com.ktds.oph.major.vo.MajorListVO;
import com.ktds.oph.major.vo.MajorVO;
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

}
