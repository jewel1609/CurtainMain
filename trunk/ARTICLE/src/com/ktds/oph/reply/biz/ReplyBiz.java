package com.ktds.oph.reply.biz;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.reply.dao.ReplyDAO;
import com.ktds.oph.reply.vo.ReplyVO;

public class ReplyBiz {
	
	private ReplyDAO replyDAO;
	
	public ReplyBiz(){
		replyDAO = new ReplyDAO();
	}
	
	public ReplyVO getClaimReplyInfoByReplyId(String replyId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			return replyDAO.getClaimReplyInfoByReplyId(replyId);
		}
		return null;
	}

	public void deleteReply(String replyId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			replyDAO.deleteReply(replyId);
			replyDAO.deleteClaimReply(replyId);
		}
	}

}
