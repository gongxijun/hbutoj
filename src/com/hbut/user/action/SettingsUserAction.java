package com.hbut.user.action;


import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.DateUtil;

import java.util.Date;

public class SettingsUserAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 8183005531295919951L;
    private static final Logger logger = LoggerFactory.getLogger(SettingsUserAction.class);


    private UserService userService;

    private String username;
    private User user;
    private String birthday;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String settings() throws Exception {
        try {
            username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (null == username || "".equals(username)) {
                return LOGIN;
            }
            user = userService.queryUser(username);

            try {

                birthday = DateUtil.DateToString(user.getBirthday() == null ?
                        new Date() : user.getBirthday(), "yyyy-MM-dd");
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("settings: {}", e);
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error("settings: {}", e);
            return ERROR;
        }
        return SUCCESS;
    }
}
