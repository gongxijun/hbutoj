package com.hbut.vote.dao;

import com.hbut.vote.vo.Vote;

public interface VoteDAO {
    public void save(Vote vote);

    public boolean isVoteBefore(String username, Integer messageId);

    public Integer getVotes(Integer messageId);
}
