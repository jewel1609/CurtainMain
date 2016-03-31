package com.ktds.curtain.reply.biz;

import javax.servlet.http.HttpServletRequest;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.reply.dao.ReplyDAO;
import com.ktds.curtain.reply.vo.ReplyVO;

public class ReplyBiz {
	
	private ReplyDAO replyDAO;
	private MemberBiz memberBiz;
	
	public ReplyBiz() {
		replyDAO = new ReplyDAO();
		memberBiz = new MemberBiz();
	}
	
	public boolean addNewReply(ReplyVO reply, HttpServletRequest request){
		
		// 일반 댓글과 첫 번째 대댓글
		// depth 부모글의 depth + 1
		// order_no 부모글의 order_no+1
		// 존재하는 대댓글을 달때
		// depth 부모글의 depth + 1
		// order_no 현재 parentReplyId 값의 max+1
		
		int orderNo = this.getNewOrderNo(reply);
		reply.setReplyOrder(orderNo);
		
		if ( orderNo > 0 ) {
			replyDAO.updateOrderNoByGroupId(reply);
		}
		
		memberBiz.addPointAndModifyMemberType(null, request);
		return replyDAO.insertReply(reply) > 0;
	}

	private int getNewOrderNo(ReplyVO reply) {
		
		// replyid가 parentid로 쓰이는지 안 쓰이는지
		if ( replyDAO.selectCountParentReplyID(reply) > 0 ) {
			int newOrderNo = replyDAO.selectMaxOrderNoByParentReplyId(reply);
			return newOrderNo + 1;
		}
		else {
			// 일반 댓글이 아니라면
			if ( reply.getParentReplyId() != 0 ) {
				int newOrderNo = replyDAO.selectLatestOrderNoByParentReplyId(reply);
				return newOrderNo + 1;
			}
		}
		
		return 0;
	}


}
