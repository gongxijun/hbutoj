package com.hbut.solution.action;

import com.hbut.contest.problem.service.CProblemService;
import com.hbut.contest.problem.vo.CProblem;
import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.hbut.solution.service.SolutionService;
import com.hbut.solution.vo.Solution;
import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContestSolutionListAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -5701824724377184171L;
    private static final Logger logger = LoggerFactory.getLogger(ContestSolutionListAction.class);
    private SolutionService solutionService;
    private ContestService contestService;
    private CProblemService cproblemService;
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private List<Solution> solutionList;
    private List<Integer> timeOutList;

    public List<Integer> getTimeOutList() {
        return timeOutList;
    }

    public void setTimeOutList(List<Integer> timeOutList) {
        this.timeOutList = timeOutList;
    }

    private Integer languageId;
    private Integer verdictId;
    private List<Integer> pageList;
    private String problemId;
    private String username;
    private Integer intRowCount = 0;
    private Integer pageSize = 50;
    private Integer page = 1;
    private Integer contestId = 0;
    private Contest contest;
    private List<CProblem> problemList;
    private Integer pageCount = 0;
    private List<Integer> isPrivateList;
    private long timeLeft = 0;

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public List<Integer> getIsPrivateList() {
        return isPrivateList;
    }

    public void setIsPrivateList(List<Integer> isPrivateList) {
        this.isPrivateList = isPrivateList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String queryStatusList() throws Exception {
        try {
            String sql_count = new String();
            String sql_query = new String();
            String sql_condition = new String();
            // System.out.println(contestId+ " "+ username+" "+page);
            // System.out.println(contestId);
            Contest contest_ = contestService.queryContest(contestId, "ADMIN");
            if (null == contest_) {
                this.addFieldError("contestId", "No such contest.");
                return ERROR;
            }

            contest = contest_;

            Date nowTime = new Date();
            if (nowTime.getTime() < contest.getStart_time().getTime()) {
                timeLeft = -1;
            } else if (nowTime.getTime() > contest.getEnd_time().getTime()) {
                timeLeft = 0;
            } else {
                timeLeft = (contest.getEnd_time().getTime() - nowTime.getTime()) / 1000;
            }

            sql_count = "select count(s.solution_id) from Solution s where s.contest_id="
                    + contestId;
            sql_query = "select s from Solution s where s.contest_id="
                    + contestId;
            if (pageSize > 100) {
                pageSize = 100;
            }

            if (null != problemId) {
                Integer problemId_ = new Integer(0);
                problemId_ = cproblemService.queryProblemByNum(problemId,
                        contestId).getProblem_id();
                if (problemId_ != null) {
                    sql_condition += " and s.problem_id=" + problemId_;
                }
            }
            if (null != username) {
                sql_condition += " and s.username='" + username + "'";
            }
            sql_condition += " order by s.solution_id DESC";

            intRowCount = solutionService.countSolutions(sql_count
                    + sql_condition);
            pageCount = ((intRowCount + pageSize - 1) / pageSize);// 计算出总页数
            if (page < 1) {
                page = 1;
            }
            if (page > pageCount) {
                page = pageCount;
            }
            Integer from = (page - 1) * pageSize;
            solutionList = solutionService.querySolutions(from, pageSize,
                    sql_query + sql_condition);

            List<CProblem> problemList_ = new ArrayList<CProblem>();
            timeOutList = new ArrayList<Integer>();
            isPrivateList = new ArrayList<Integer>();
            Date dt = new Date();
            if (null != solutionList) {
                Integer access_ = new Integer(0);
                for (Solution s : solutionList) {
                    problemList_.add(cproblemService.queryProblemByPid(
                            s.getProblem_id(), s.getContest_id()));
                    if (s.getSubmit_date().getTime() > contest_.getEnd_time()
                            .getTime()) {
                        timeOutList.add(1);
                    } else {
                        timeOutList.add(0);
                    }
                    if (contest_.getEnd_time().getTime() > dt.getTime()) {// 未结束
                        // isPrivateList.add(1);
                        access_ = 1;
                    } else {
                        // isPrivateList.add(0);
                        access_ = 0;
                    }

                    if (access_.equals(0)) {
                        User user_ = new User();
                        user_ = userService.queryUser(s.getUsername());
                        if (user_ != null) {
                            if (user_.getOpensource().equals("N")) {
                                access_ = 1;
                            }
                        }
                    }
                    isPrivateList.add(access_);
                }
            }
            problemList = problemList_;

            List<Integer> volume = new ArrayList<Integer>();
            if (pageCount < 7) {
                for (Integer i = 1; i <= pageCount; i++) {
                    volume.add(i);
                }
            } else {
                volume.add(1);
                if (page > 4) {
                    volume.add(0); // 省略号
                }
                Integer start_i = new Integer(2);
                Integer end_i = new Integer(pageCount - 1);
                if (page - 2 > 2) {
                    start_i = page - 2;
                }
                if (page + 2 < pageCount) {
                    end_i = page + 2;
                }

                for (Integer i = start_i; i <= end_i; i++) {
                    volume.add(i);
                }
                if (page < pageCount - 3) {
                    volume.add(0); // 省略号
                }

                volume.add(pageCount);
            }
            pageList = volume;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("queryStatusList: {}", e);
            return ERROR;
        }
        return SUCCESS;
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

    public SolutionService getSolutionService() {
        return solutionService;
    }

    public void setSolutionService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }

    public void setSolutionList(List<Solution> solutionList) {
        this.solutionList = solutionList;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getVerdictId() {
        return verdictId;
    }

    public void setVerdictId(Integer verdictId) {
        this.verdictId = verdictId;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIntRowCount() {
        return intRowCount;
    }

    public void setIntRowCount(Integer intRowCount) {
        this.intRowCount = intRowCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CProblemService getCproblemService() {
        return cproblemService;
    }

    public void setCproblemService(CProblemService cproblemService) {
        this.cproblemService = cproblemService;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public List<CProblem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<CProblem> problemList) {
        this.problemList = problemList;
    }

}
