package com.hbut.common.action;

import com.google.common.collect.Lists;
import com.hbut.contest.attend.service.AttendService;
import com.hbut.contest.attend.vo.Attend;
import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

public class UpdateRatingAction extends ActionSupport {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(UpdateRatingAction.class);
    private static final long serialVersionUID = 1L;
    private AttendService attendService;
    private ContestService contestService;

    private String username;
    private List<Attend> attendList;
    private List<Integer> rankList;

    private Integer contestId = 0;
    private Contest contest;

    public List<Integer> getRankList() {
        return rankList;
    }

    public void setRankList(List<Integer> rankList) {
        this.rankList = rankList;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public String queryStandingsList() throws Exception {

        try {
            // 检查有没有这个比赛
            // System.out.println(contestId + " " + page);

            Contest contest_ = contestService.queryContest(contestId, "USER");
            if (null == contest_) {
                this.addFieldError("contestId", "No such contest.");
                return ERROR;
            }
            contest = contest_;

            Integer nCount = attendService.countContestAttends(contestId);
            List<Attend> attendList_ = attendService.queryContestAttends(0, nCount,
                    contestId, contest_.getType());
            rankList = Lists.newArrayList();
            for (Attend c : attendList_) {
                rankList.add(attendService.getUserRank(c, contest_.getType()));
            }
            attendList = attendList_;
        } catch (Exception e) {
            // TODO: handle exception
            this.addFieldError("tip", "Unknown error.");
            logger.error("Unknown error.", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public AttendService getAttendService() {
        return attendService;
    }

    public void setAttendService(AttendService attendService) {
        this.attendService = attendService;
    }

    public ContestService getContestService() {
        return contestService;
    }

    public void setContestService(ContestService contestService) {
        this.contestService = contestService;
    }

    public List<Attend> getAttendList() {
        return attendList;
    }

    public void setAttendList(List<Attend> attendList) {
        this.attendList = attendList;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

}
