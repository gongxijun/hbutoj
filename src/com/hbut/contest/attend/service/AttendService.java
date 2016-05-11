package com.hbut.contest.attend.service;

import com.hbut.contest.attend.vo.Attend;

import java.util.List;

public interface AttendService {
    public void save(Attend attend);

    public void delete(Attend attend);

    public List<Attend> queryContestRegistrants(Integer from, Integer pageSize,
                                                Integer contestId);

    public List<Attend> queryContestAttends(Integer from, Integer pageSize,
                                            Integer contestId, Integer type);

    public Integer countContestAttends(Integer contestId);

    public Attend queryUserAttend(Integer contestId, String username);

    public Integer countUserAttends(String username);

    public Integer getUserRank(Attend attend, Integer type);
}
