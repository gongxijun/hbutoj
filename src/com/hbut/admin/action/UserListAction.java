package com.hbut.admin.action;

import com.google.common.collect.Lists;
import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

public class UserListAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private List<User> usersList;
    private Integer page = 1;

    private String order;
    private List<Integer> pageList;
    private Integer pageSize = 100;

    private final static Logger logger = LoggerFactory.getLogger(UserListAction.class);

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String userList() throws Exception {

        try {
            if (pageSize > 100) {
                pageSize = 100;
            }
            Integer intRowCount = userService.countUsers();
            Integer pageCount = ((intRowCount + pageSize - 1) / pageSize);// 计算出总页数
            if (page < 1) {
                page = 1;
            }
            if (page > pageCount) {
                page = pageCount;
            }

            Integer from = (page - 1) * pageSize;
            usersList = userService.queryUsers(from, pageSize);

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
        // System.out.println(jsonResult);
        return SUCCESS;
    }

}
