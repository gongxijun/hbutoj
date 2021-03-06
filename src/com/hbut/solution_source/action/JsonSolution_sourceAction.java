package com.hbut.solution_source.action;

import com.hbut.contest.problem.service.CProblemService;
import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.hbut.problem.service.ProblemService;
import com.hbut.solution.service.SolutionService;
import com.hbut.solution.vo.Solution;
import com.hbut.solution_source.service.Solution_sourceService;
import com.hbut.solution_source.vo.Solution_source;
import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.Config;
import com.util.StreamHandler;
import org.apache.struts2.json.annotations.JSON;

import java.io.File;
import java.util.Date;

public class JsonSolution_sourceAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(JsonSolution_sourceAction.class);
    private Solution_sourceService solutionSourceService;
    private SolutionService solutionService;
    private ProblemService problemService;
    private CProblemService cproblemService;
    private ContestService contestService;
    private UserService userService;

    private Integer solutionId = 0;
    private Solution_source solutionSource;
    private Solution solution;
    private String problemTitle;
    private boolean success;
    private String error;
    private String verdict;

    private String problemId;
    private String className;
    private String contestTitle;
    private String judgeLog;


    public String getJudgeLog() {
        return judgeLog;
    }

    public void setJudgeLog(String judgeLog) {
        this.judgeLog = judgeLog;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContestTitle() {
        return contestTitle;
    }

    public void setContestTitle(String contestTitle) {
        this.contestTitle = contestTitle;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String solutionSource() throws Exception {
        try {

            logger.info("Source[" + solutionId + "] is open via ajax...");
            Solution_source solutionSource_ = new Solution_source();


            solutionSource_ = solutionSourceService
                    .querySolutionSource(solutionId);
            if (null == solutionSource_) {
                success = false;
                error = "No such solution source.";
                return SUCCESS;
            }
            Solution solution_ = new Solution();
            solution_ = solutionService.querySolution(solutionId);
            if (null == solution_) {
                success = false;
                error = "No such solution source.";
                return SUCCESS;
            }

            String username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (username == null) {
                //需要登录才能查看，源代码
                success = false;
                error = "YOU HAVE NO PRIVILEGE , " +
                        "PLEASE TO LOGIN FIRST !";
                return SUCCESS;
            }

            if (solution_.getContest_id() > 0) {
                // 检查比赛是否over
                Contest contest_ = new Contest();
                contest_ = contestService.queryContest(
                        solution_.getContest_id(), "ADMIN");
                if (contest_ == null) {
                    success = false;
                    error = "No such contest.";
                    // ActionContext.getContext().put("tip", "");
                    return SUCCESS;
                }
                contestTitle = contest_.getTitle();
                Date dt = new Date();
                if (contest_.getEnd_time().getTime() > dt.getTime()) { // 比赛没有结束
                    if (!username.equals(solution_.getUsername())) {
                        // ActionContext.getContext().put("tip",
                        // "You can't view such source on a running contest.");
                        success = false;
                        error = "You can't view such source on a running contest.";
                        return SUCCESS;
                    }
                    judgeLog = "You can not view judge-log on a running contest.";
                } else if (userService.isSuperUser(username)) {
                    try {
                        File file;
                        judgeLog = new String();
                        file = new File(Config.getValue("OJ_JUDGE_LOG")
                                + "judge-log-" + solutionId + ".log");
                        judgeLog = StreamHandler.read(file);
                    } catch (Exception e) {
                        // TODO: handle exception
                        logger.error("solutionSource: {}", e);
                    }
                }
            } else if (userService.isSuperUser(username)) {
                try {
                    File file;
                    judgeLog = new String();
                    file = new File(Config.getValue("OJ_JUDGE_LOG")
                            + "judge-log-" + solutionId + ".log");
                    judgeLog = StreamHandler.read(file);

                } catch (Exception e) {
                    // TODO: handle exception
                    logger.error("solutionSource: {}", e);
                }
            }

            judgeLog = (judgeLog == null) ? new String() : judgeLog;
            User user_ = new User();
            user_ = userService.queryUser(solution_.getUsername());
            if (user_ != null) {
                if (!username.equals(solution_.getUsername())) {
                    if ("NO".equals(Config.getValue("OPENSOURCE"))) {
                        success = false;
                        error = "This operation is now closed by Administrator.";
                        return SUCCESS;
                    }

                    if (user_.getOpensource().equals("N")) {
                        success = false;
                        error = "This source isn't open for you.You can write mail to "
                                + solution_.getUsername();
                        return SUCCESS;
                    }
                }
            }

            String problemTitle_ = new String();
            problemId = new String();
            if (solution_.getContest_id() > 0) {
                problemId = cproblemService.queryProblemByPid(
                        solution_.getProblem_id(), solution_.getContest_id())
                        .getNum();
                problemTitle_ = cproblemService.queryProblemByPid(
                        solution_.getProblem_id(), solution_.getContest_id())
                        .getTitle();

            } else {
                problemId = solution_.getProblem_id().toString();
                problemTitle_ = problemService.queryProblem(
                        solution_.getProblem_id()).getTitle();
            }

            // System.out.println(problemId+problemTitle_);

            if (null == problemTitle_) {
                success = false;
                error = "No such problem.";
                return SUCCESS;
            }
            solution = solution_;
            solutionSource = solutionSource_;
            problemTitle = problemTitle_;
            verdict = getText("verdict" + solution_.getVerdict());

			/*
             * For JS
			 */
            solutionSource.setSource(solutionSource_.getSource()
                    .replaceAll("<", "&lt;").replaceAll(">", "&gt;"));

            String className_[] = {"cpp", "cpp", "cpp", "cpp", "java",
                    "csharp", "fsharp", "delphi", "python", "ruby", "perl",
                    "lua", "tcl", "pike", "haskell", "php", "bf", "bf", "go",
                    "scala", "jscript", "groovy"};

            className = "brush:" + className_[solution.getLanguage() - 1] + ";";

            success = true;
            return SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "Unknown Error.";
            logger.error("solutionSource: {}", e);
            return SUCCESS;
        }
    }

    @JSON(deserialize = false, serialize = false)
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @JSON(deserialize = false, serialize = false)
    public CProblemService getCproblemService() {
        return cproblemService;
    }

    public void setCproblemService(CProblemService cproblemService) {
        this.cproblemService = cproblemService;
    }

    @JSON(deserialize = false, serialize = false)
    public Solution_sourceService getSolutionSourceService() {
        return solutionSourceService;
    }

    public void setSolutionSourceService(
            Solution_sourceService solutionSourceService) {
        this.solutionSourceService = solutionSourceService;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Solution_source getSolutionSource() {
        return solutionSource;
    }

    public void setSolutionSource(Solution_source solutionSource) {
        this.solutionSource = solutionSource;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @JSON(deserialize = false, serialize = false)
    public SolutionService getSolutionService() {
        return solutionService;
    }

    public void setSolutionService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @JSON(deserialize = false, serialize = false)
    public ProblemService getProblemService() {
        return problemService;
    }

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    @JSON(deserialize = false, serialize = false)
    public ContestService getContestService() {
        return contestService;
    }

    public void setContestService(ContestService contestService) {
        this.contestService = contestService;
    }
}
