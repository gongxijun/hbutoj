package com.hbut.contest.action;

import com.hbut.contest.problem.service.CProblemService;
import com.hbut.contest.problem.vo.CProblem;
import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.hbut.solution.service.SolutionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContestShowAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ContestShowAction.class);
    private ContestService contestService;
    private Contest contest;
    private Integer contestId;
    private List<CProblem> problemList;
    private CProblemService cproblemService;
    private SolutionService solutionService;
    private List<Integer> problemStatusList;
    private long timeLeft = 0;

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public SolutionService getSolutionService() {
        return solutionService;
    }

    public void setSolutionService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    public List<Integer> getProblemStatusList() {
        return problemStatusList;
    }

    public void setProblemStatusList(List<Integer> problemStatusList) {
        this.problemStatusList = problemStatusList;
    }

    public String contestShow() throws Exception {
        try {
            contest = contestService.queryContest(contestId, "USER");
            if (null == contest) {
                ActionContext.getContext().put("tip", "No such contest.");
                return ERROR;
            }
            Date nowTime = new Date();
            if (nowTime.getTime() < contest.getStart_time().getTime()) {
                timeLeft = -1;
            } else if (nowTime.getTime() > contest.getEnd_time().getTime()) {
                timeLeft = 0;
            } else {
                timeLeft = (contest.getEnd_time().getTime() - nowTime.getTime()) / 1000;

            }

            // System.out.println(contest.getTitle());
            problemList = cproblemService.queryProblems(contestId);

            String username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (username != null) {
                String sql = new String();
                problemStatusList = new ArrayList<Integer>();
                for (CProblem p : problemList) {
                    sql = "select count(s.solution_id) from Solution s where username='"
                            + username
                            + "' and s.problem_id="
                            + p.getProblem_id()
                            + " and contest_id="
                            + contestId;
                    if (solutionService.countSolutions(sql) > 0) {
                        // 5 AC
                        sql = "select count(s.solution_id) from Solution s where username='"
                                + username
                                + "' and s.verdict=5  and s.problem_id="
                                + p.getProblem_id()
                                + " and contest_id="
                                + contestId;
                        if (solutionService.countSolutions(sql) > 0) {
                            // System.out.println("AC" + p.getProblem_id());
                            problemStatusList.add(1);
                        } else {
                            // System.out.println("WE" + p.getProblem_id());
                            problemStatusList.add(2); // WA Other
                        }
                    } else {
                        // System.out.println("NO" + p.getProblem_id());
                        problemStatusList.add(0);
                    }
                }
            }
            // System.out.println(problemList.size());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("contestShow: ", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public List<CProblem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<CProblem> problemList) {
        this.problemList = problemList;
    }

    public CProblemService getCproblemService() {
        return cproblemService;
    }

    public void setCproblemService(CProblemService cproblemService) {
        this.cproblemService = cproblemService;
    }

    public ContestService getContestService() {
        return contestService;
    }

    public void setContestService(ContestService contestService) {
        this.contestService = contestService;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }
}
