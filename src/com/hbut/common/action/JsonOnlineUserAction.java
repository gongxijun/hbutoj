package com.hbut.common.action;

import com.google.common.collect.Maps;
import com.hbut.bean.OnlineUserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.OnlineUsers;

import java.util.Map;

public class JsonOnlineUserAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 3552864426020089250L;
    private final static Logger logger = LoggerFactory.getLogger(JsonOnlineUserAction.class);

    private boolean success;
    private String error;
    private Map<String, OnlineUserBean> online_users;

    public Map<String, OnlineUserBean> getOnline_users() {
        return online_users;
    }

    public void setOnline_users(Map<String, OnlineUserBean> onlineUsers) {
        online_users = onlineUsers;
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

    public String onlineUsers() throws Exception {
        try {
            //
            online_users = Maps.newHashMap();
            online_users = OnlineUsers.getOnlineUsers();
            // System.out.println(usersList.size());
            success = true;
            return SUCCESS;

        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "get online users error.";
            logger.error(error, e);
            return SUCCESS;
        }
    }
}
