package com.ktds.oph.univ.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.member.vo.MemberListVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.univ.dao.UnivDAO;
import com.ktds.oph.univ.vo.UnivListVO;
import com.ktds.oph.univ.vo.UnivSearchVO;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.web.Paging;

public class UnivBiz {
	
	private UnivDAO univDAO;
	
	public UnivBiz() {
		univDAO = new UnivDAO();
	}

	public UnivListVO getAllUniv(UnivSearchVO univSearchVO) {
		int allUnivCount = 0;

		if ( univSearchVO.getSearchType().equals("1") ) {
			allUnivCount = univDAO.getAllUnivCount();
		}
		else if ( univSearchVO.getSearchType().equals("2") ) {
			allUnivCount = univDAO.getArticleByUnivNameCount(univSearchVO);
		}
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allUnivCount);
		paging.setPageNumber(univSearchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		univSearchVO.setStartIndex( paging.getStartArticleNumber() );
		univSearchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<UnivVO> univs = new ArrayList<UnivVO>();
		
		if ( univSearchVO.getSearchType().equals("1") ) {
			univs = univDAO.getAllUniv(univSearchVO);
		}
		else if ( univSearchVO.getSearchType().equals("2") ) {
			univs = univDAO.getArticleByUnivName(univSearchVO);
		}
		
		// paging 바꾸는 이유 검색 때문에
		
		UnivListVO univList = new UnivListVO();
		univList.setUnivList(univs);
		univList.setPaging(paging);
		
		// 몇 번째 페이지
		
		
		return univList;
		
		
	}

	public void deleteUnivs(String[] deleteUnivIds, MemberVO member) {
		
		if (member.getMemberTypeId() == 6 ) {
			for (String deleteUnivId : deleteUnivIds ) {
				univDAO.deleteUniv(deleteUnivId);
			}
		}
	}

	public boolean updateUnivName(UnivVO univVO, MemberVO member) {
		
		if (member.getMemberTypeId() == 6 ) {

			String univName = univDAO.getUnivName(univVO);
			if (univName.equals(univVO.getUnivName())) {
				return false;
				
			}
			else {
				return univDAO.updateUnivName(univVO) > 0;
			}

		}
		return false;
		
	}

	public boolean registerUniv(UnivVO univVO, MemberVO member) {
		
		int insertCount = 0;
		
		if (member.getMemberTypeId() == 6 ) {
			insertCount = univDAO.registerUniv(univVO);
		}
		return insertCount > 0;
	}

}
