package com.hbut.contest.problem.action;

import com.hbut.contest.problem.service.CProblemService;
import com.hbut.contest.problem.vo.CProblem;
import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProblemAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(ProblemAction.class);

    private ContestService contestService;
    private CProblem problem;
    private Integer problemId = 1000;
    private String num = "A";
    private CProblemService cproblemService;

    private Integer contestId = 0;

    public ContestService getContestService() {
        return contestService;
    }

    public void setContestService(ContestService contestService) {
        this.contestService = contestService;
    }

    public String problemAdd() throws Exception {
        try {
            // 需要不重复的题目?
            CProblem problem_ = new CProblem();
            // System.out.println(problem.getTitle()+problem.getProblem_id()+problem.getContest_id());
            problem_.setTitle(problem.getTitle());
            problem_.setProblem_id(problem.getProblem_id());
            problem_.setNum(problem.getNum());
            problem_.setContest_id(problem.getContest_id());
            problem_.setPoint(problem.getPoint());
            cproblemService.save(problem_);

        } catch (Exception e) {
            // TODO: handle exception
            logger.error("problemAdd: {} ", e);
            return ERROR;
        }

        return SUCCESS;
    }

    public String problemBeforeModify() throws Exception {
        try {

            problem = cproblemService.queryProblemByNum(num, contestId);

        } catch (Exception e) {
            // TODO: handle exception

            logger.error("problemBeforeModify: {}", e);

            return ERROR;
        }
        return SUCCESS;
    }

    public String problemModify() throws Exception {
        try {

            cproblemService.save(problem);

        } catch (Exception e) {
            // TODO: handle exception

            logger.error("problemModify: {}", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String problemDelete() throws Exception {
        try {
            Contest contest_ = new Contest();
            contest_ = contestService.queryContest(contestId, "USER");
            if (contest_ == null) {
                return ERROR;
            }
            /*
             * if (contest_.getStart_time().before(new Date())) {
			 * this.addFieldError("tip",
			 * "you can't delete problem after contest start."); return ERROR; }
			 */

            List<CProblem> problemList_ = cproblemService
                    .queryProblems(contestId);
            boolean flag = false;
            List<String> num_ = new ArrayList<String>();
            for (CProblem p : problemList_) {
                num_.add(p.getNum());
            }
            for (int i = 0; i < problemList_.size(); i++) {
                if (flag) {
                    CProblem problem__ = problemList_.get(i);
                    problem__.setNum(num_.get(i - 1));
                    cproblemService.save(problem__);
                }
                if (problemList_.get(i).getNum().equals(num) && flag == false) {
                    CProblem problem_ = cproblemService.queryProblemByNum(num,
                            contestId);
                    if (problem_ == null) {
                        this.addFieldError("tip", "No such problem.");
                        return ERROR;
                    }
                    cproblemService.delete(problem_);
                    flag = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception

            logger.error("problemDelete: {}", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public CProblem getProblem() {
        return problem;
    }

    public void setProblem(CProblem problem) {
        this.problem = problem;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
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

}
