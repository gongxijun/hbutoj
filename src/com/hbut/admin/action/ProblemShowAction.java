package com.hbut.admin.action;

import com.hbut.problem.service.ProblemService;
import com.hbut.problem.vo.Problem;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class ProblemShowAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(ProblemShowAction.class);
    private Problem problem;
    private Integer problemId = 1000;
    private ProblemService problemService;

    public String problemShow() throws Exception {
        try {
            problem = problemService.queryProblem(problemId);

            if (null == problem) {
                ActionContext.getContext().put("tip", "No such problem.");
                return ERROR;
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error("内部出错", e);
            return ERROR;
        }

        return SUCCESS;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public ProblemService getProblemService() {
        return problemService;
    }

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

}
