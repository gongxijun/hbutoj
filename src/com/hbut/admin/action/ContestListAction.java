package com.hbut.admin.action;

import com.google.common.collect.Lists;
import com.hbut.contest.attend.service.AttendService;
import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class ContestListAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    private ContestService contestService;
    private AttendService attendService;
    private Integer page = 1; // ended
    private List<Integer> pageList; // ended
    private String order;
    private Integer pageSize = 100; // ended
    private Integer intRowCount = 0; // ended

    private List<Contest> contestList;

    public List<Contest> getContestList()
    {
        return contestList;
    }

    public void setContestList(List<Contest> contestList) {
        this.contestList = contestList;
    }

    public String contestSet() throws Exception {

        if (pageSize > 100) {
            pageSize = 100;
        }
        intRowCount = contestService.countContests(0, "ADMIN");

        Integer pageCount = ((intRowCount + pageSize - 1) / pageSize);// 统计页面数量

        if (page < 1) {
            page = 1;
        }
        if (page > pageCount) {
            page = pageCount;
        }
        Integer from = (page - 1) * pageSize;
        contestList = contestService.queryContests(from, pageSize, 0, order,
                "ADMIN");

        List<Integer> volume = Lists.newArrayList();
        for (Integer i = 1; i <= pageCount; i++) {
            volume.add(i);
        }

        pageList = volume;

        return SUCCESS;
    }

    public ContestService getContestService() {
        return contestService;
    }

    public void setContestService(ContestService contestService) {
        this.contestService = contestService;
    }

    public AttendService getAttendService() {
        return attendService;
    }

    public void setAttendService(AttendService attendService) {
        this.attendService = attendService;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIntRowCount() {
        return intRowCount;
    }

    public void setIntRowCount(Integer intRowCount) {
        this.intRowCount = intRowCount;
    }

}
