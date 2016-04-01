package com.ktds.curtain.replyLike.biz;

import com.ktds.curtain.reply.dao.ReplyDAO;
import com.ktds.curtain.replyLike.dao.ReplyLikeDAO;
import com.ktds.curtain.replyLike.vo.ReplyLikeVO;

public class ReplyLikeBiz {
	private ReplyLikeDAO replyLikeDAO;
	private ReplyDAO replyDAO;
	
	public ReplyLikeBiz() {
		replyLikeDAO = new ReplyLikeDAO();
		replyDAO = new ReplyDAO();
	}

	public void insertOrDeleteLikeReply(ReplyLikeVO replyLikeVO) {
		if (isExistLikeReply(replyLikeVO)) {
			replyLikeDAO.deleteLike(replyLikeVO);
			replyDAO.minusLikeCount(replyLikeVO);
		}
		else {
			replyLikeDAO.insertLike(replyLikeVO);
			replyDAO.plusLikeCount(replyLikeVO);
		}
	}

	public boolean isExistLikeReply(ReplyLikeVO replyLikeVO) {
		return replyLikeDAO.selectLikeCount(replyLikeVO) > 0;
	}

	public int getReplyLikes(ReplyLikeVO replyLikeVO) {
		return replyDAO.getReplyLikes(replyLikeVO);
	}

}
