package com.hbut.solution.action;

import com.hbut.contest.problem.service.CProblemService;
import com.hbut.contest.problem.vo.CProblem;
import com.hbut.contest.service.ContestService;
import com.hbut.solution.service.SolutionService;
import com.hbut.solution.vo.Solution;
import com.hbut.solution_source.service.Solution_sourceService;
import com.hbut.solution_source.vo.Solution_source;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.Config;

import java.io.IOException;
import java.util.Date;

public class ContestSubmitionAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -5701824724377184171L;
    private static final Logger logger = LoggerFactory.getLogger(ContestSolutionListAction.class);
    private SolutionService solutionService;
    private ContestService contestService;
    private CProblemService cproblemService;
    private Solution_sourceService solutionSourceService;
    private Solution solution;
    private String source;
    private Integer language;
    private String num;
    private Integer contestId;

    public String submitSolution() throws Exception {
        try {
            // System.out.println(num+" "+language+" "+source);
            String username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (null == username) {
                this.addFieldError("tip", "Your must login first.");
                return INPUT;
            }

            Date dt_prevSubmit = (Date) ActionContext.getContext().getSession()
                    .get("session_submit");
            Date dt = new Date(0);

            if (dt_prevSubmit != null) {
                if (dt.getTime() - dt_prevSubmit.getTime() < 3000) { // 限制5s一次提交
                    System.out.println(username + " submit twice at 3 second.");
                    // this.addFieldError("tip", "");
                    return "success";
                }
            }
            ActionContext.getContext().getSession().put("session_submit", dt);

            if (source.length() < 10 || null == source) {
                this.addFieldError("tip", "Source must at least 10 chars.");
                return INPUT;
            }
            if (source.length() > 65535) {
                this.addFieldError("tip", "Source must at most 65535 chars.");
                return INPUT;
            }

            CProblem problem = new CProblem();
            problem = cproblemService.queryProblemByNum(num, contestId);
            if (null == problem) {
                this.addFieldError("tip", "No such problem.");
                return INPUT;
            }

            Solution solution_ = new Solution();

            solution_.setUsername(username);
            solution_.setContest_id(contestId);
            solution_.setProblem_id(problem.getProblem_id());
            solution_.setLanguage(language);
            solution_.setSubmit_date(new Date(0));
            solution_.setCode_length(source.length());

            solution = solution_;

            solutionService.save(solution);

            // System.out.println("sid="+solution.getSolution_id());

            if (null == solution.getSolution_id()) {
                // Skip the judge
                this.addFieldError("tip", "Submit failed,please retry.");
                return INPUT;
            }
            Solution_source solutionSource = new Solution_source();
            solutionSource.setSolution_id(solution.getSolution_id());
            solutionSource.setSource(source);
            solutionSourceService.save(solutionSource);

            String[] cmd = {Config.getValue("OJ_PATH") + "Client.exe",
                    Integer.toString(solution.getSolution_id()),
                    Integer.toString(solution.getLanguage()),
                    Config.getValue("OJ_INI_PATH")};
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            // TODO: handle exception
            this.addFieldError("tip", "Submit failed,please retry.");
            logger.error("submitSolution {}", e);
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

    public CProblemService getCproblemService() {
        return cproblemService;
    }

    public void setCproblemService(CProblemService cproblemService) {
        this.cproblemService = cproblemService;
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

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Solution_sourceService getSolutionSourceService() {
        return solutionSourceService;
    }

    public void setSolutionSourceService(
            Solution_sourceService solutionSourceService) {
        this.solutionSourceService = solutionSourceService;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public SolutionService getSolutionService() {
        return solutionService;
    }

    public void setSolutionService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

}
