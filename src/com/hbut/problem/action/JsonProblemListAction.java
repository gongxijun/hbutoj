package com.hbut.problem.action;

import com.hbut.bean.ProblemInfoBean;
import com.hbut.problem.service.ProblemService;
import com.hbut.problem.vo.Problem;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.json.annotations.JSON;

import java.util.ArrayList;
import java.util.List;

public class JsonProblemListAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(JsonProblemListAction.class);
    private List<ProblemInfoBean> problems;
    private boolean success;
    private String error;
    private String searchString;
    private ProblemService problemService;

    public List<ProblemInfoBean> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemInfoBean> problems) {
        this.problems = problems;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String problemSet() throws Exception {
        try {
            problems = new ArrayList<ProblemInfoBean>();
            List<Problem> problemList_ = new ArrayList<Problem>();

            problemList_ = problemService.searchProblem(10, searchString);

            for (Problem p : problemList_) {
                ProblemInfoBean p_ = new ProblemInfoBean();
                p_.setProblemId(p.getProblem_id());
                p_.setTitle(p.getTitle());
               //System.out.println(p.getProblem_id());
               //System.out.println(p.toString());
               /*problemMap.put(p.getProblem_id().toString(), p.getTitle());*/
                problems.add(p_);
            }
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "Error.";
            logger.error(error, e);
            return SUCCESS;
        }
        success = true;
        return SUCCESS;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @JSON(deserialize = false, serialize = false)
    public ProblemService getProblemService() {
        return problemService;
    }

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

}
