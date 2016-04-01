package com.ktds.curtain.replyDislike.biz;

import com.ktds.curtain.reply.dao.ReplyDAO;
import com.ktds.curtain.replyDislike.dao.ReplyDislikeDAO;
import com.ktds.curtain.replyDislike.vo.ReplyDislikeVO;

public class ReplyDislikeBiz {
	
	private ReplyDislikeDAO replyDislikeDAO;
	private ReplyDAO replyDAO;
	
	public ReplyDislikeBiz() {
		replyDislikeDAO = new ReplyDislikeDAO();
		replyDAO = new ReplyDAO();
	}

	public boolean isExistDislikeReply(ReplyDislikeVO replyDislikeVO) {
		return replyDislikeDAO.selectDislikeCount(replyDislikeVO) > 0;
	}

	public void insertOrDeleteDislikeReply(ReplyDislikeVO replyDislikeVO) {
		if (isExistDislikeReply(replyDislikeVO)) {
			replyDislikeDAO.deleteDislike(replyDislikeVO);
			replyDAO.minusDislikeCount(replyDislikeVO);
		}
		else {
			replyDislikeDAO.insertDislike(replyDislikeVO);
			replyDAO.plusDislikeCount(replyDislikeVO);
		}
	}

	public int getReplyDislikes(ReplyDislikeVO replyDislikeVO) {
		return replyDAO.getReplyDislikes(replyDislikeVO);
	}

}
