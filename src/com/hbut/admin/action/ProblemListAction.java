package com.hbut.admin.action;

import com.google.common.collect.Lists;
import com.hbut.problem.service.ProblemService;
import com.hbut.problem.vo.Problem;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

public class ProblemListAction extends ActionSupport {


    /**
     *
     */

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ProblemListAction.class);
    private List<Problem> problemList;
    private List<Integer> pageList;
    private Integer page = 1;
    private String order;
    private Integer pageSize = 100;
    private Integer intRowCount = 0;

    private String ojName;

    public String getOjName() {
        return ojName;
    }

    public void setOjName(String ojName) {
        this.ojName = ojName;
    }

    private ProblemService problemService;

    public String problemSet() throws Exception {

        try {

            if (pageSize > 100) { pageSize = 100; }
            intRowCount = problemService.countProblems("admin", ojName);

            Integer pageCount = ((intRowCount + pageSize - 1) / pageSize);// 计算出总页数

            if (page < 1) { page = 1; }
            if (page > pageCount ) { page = pageCount; }
            Integer from = (page - 1) * pageSize;

            problemList = problemService.queryProblems(from, pageSize, order,
                    ojName, "admin");

            List<Integer> volume = Lists.newArrayList();
            for (Integer i = 1; i <= pageCount; i++) {
                volume.add(i);
            }
            pageList = volume;

        } catch (Exception e) {

            // TODO: handle exception
            logger.error("内部出错", e);
            return ERROR;

        }

        return SUCCESS;
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

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public ProblemService getProblemService() {
        return problemService;
    }

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

}
