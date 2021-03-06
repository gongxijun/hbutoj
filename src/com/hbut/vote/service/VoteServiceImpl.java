package com.hbut.vote.service;

import com.hbut.vote.dao.VoteDAO;
import com.hbut.vote.vo.Vote;

public class VoteServiceImpl implements VoteService {

    private VoteDAO voteDao;

    public VoteDAO getVoteDao() {
        return voteDao;
    }

    public void setVoteDao(VoteDAO voteDao) {
        this.voteDao = voteDao;
    }

    public Integer getVotes(Integer messageId) {
        // TODO Auto-generated method stub
        return voteDao.getVotes(messageId);
    }

    public boolean isVoteBefore(String username, Integer messageId) {
        // TODO Auto-generated method stub
        return voteDao.isVoteBefore(username, messageId);
    }

    public void save(Vote vote) {
        // TODO Auto-generated method stub
        voteDao.save(vote);
    }

}
