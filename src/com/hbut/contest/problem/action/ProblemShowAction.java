package com.hbut.contest.problem.action;

import com.hbut.contest.problem.service.CProblemService;
import com.hbut.contest.problem.vo.CProblem;
import com.hbut.problem.service.ProblemService;
import com.hbut.problem.vo.Problem;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class ProblemShowAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ProblemShowAction.class);
    private CProblem cproblem;
    private Problem problem;
    private String num = "A";
    private CProblemService cproblemService;
    private ProblemService problemService;
    private Integer contestId = 0;

    public String problemShow() throws Exception {
        try {
            cproblem = cproblemService.queryProblemByNum(num, contestId);
            if (null == cproblem) {

                return ERROR;
            }
            problem = problemService.queryProblem(cproblem.getProblem_id());
            if (null == problem) {

                return ERROR;
            }
        } catch (Exception e) {
            // TODO: handle exception

            logger.error("problemShow: {}", e);
            return ERROR;
        }

        return SUCCESS;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public CProblem getCproblem() {
        return cproblem;
    }

    public void setCproblem(CProblem cproblem) {
        this.cproblem = cproblem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }

    public CProblemService getCproblemService() {
        return cproblemService;
    }

    public void setCproblemService(CProblemService cproblemService) {
        this.cproblemService = cproblemService;
    }

    public ProblemService getProblemService() {
        return problemService;
    }

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

}
