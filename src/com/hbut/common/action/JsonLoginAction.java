package com.hbut.common.action;

import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.OnlineUsers;
import org.apache.struts2.ServletActionContext;

import java.util.Date;

public class JsonLoginAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 3552864426020089250L;
    private final Logger logger = LoggerFactory.getLogger(JsonLoginAction.class);
    private UserService userService;
    private String username;
    private String password;
    private boolean success;
    private String error;

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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void loginEx() throws Exception {
        try {
            // System.out.println(username+ "+" +password);
            if (false == userService.isUsernameExist(username)) {
                success = false;
                error = "username is not exist.";
                return;
            }
            User user_ = new User();
            user_ = userService.checkLogin(username, password);
            if (null != user_) {

                OnlineUsers.onlineUser(user_.getUsername());

                user_.setLastlogin(new Date());
                userService.save(user_);

                ActionContext.getContext().getSession()
                        .put("session_username", user_.getUsername());
                ServletActionContext.getResponse().getWriter().print(1);
                success = true;

                return;
            } else {
                ServletActionContext.getResponse().getWriter().print(0);
                success = false;
                error = "password is invalid.";
                //Todo 给出警告通知
                logger.warn(error);
                return;
            }
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "Unknown Error.";
            logger.error(error, e);
            return;
        }
    }
}
