package com.hbut.contest.service;

import java.util.List;

import com.hbut.contest.vo.Contest;

public interface ContestService {
    public List<Contest> queryContests(Integer from, Integer pageSize,
                                       Integer type, String order, String mode);

    public Contest queryContest(Integer contestId, String mode);

    public void save(Contest contest);

    public Integer countContests(Integer type, String mode);
}
