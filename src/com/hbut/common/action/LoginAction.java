package com.hbut.common.action;

import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.OnlineUsers;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 登陆处理页面
 *
 * @author gxjun
 */
public class LoginAction extends ActionSupport {

    private static final long serialVersionUID = 3552864426020089250L;
    private static final Logger logger = LoggerFactory
            .getLogger(LoginAction.class);

    private UserService userService;
    private String handle;
    private String password;
    private String url;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String execute() throws Exception {
        try {

            if (false == userService.isUsernameExist(handle)) {

                logger.warn("检验用户是否存在 , Date{},hanldle login is not exist!",
                        String.valueOf(new Date(0)));
                this.addFieldError("handle", "username is not exist.");
                return INPUT;
            }

            User user_ = new User();
            user_ = userService.checkLogin(handle, password);
            if (null != user_) {
                ActionContext.getContext().getSession()
                        .put("session_username", user_.getUsername());
                // ServletActionContext.getResponse().getWriter().print(1);

                OnlineUsers.onlineUser(user_.getUsername());

                user_.setLastlogin(new Timestamp(System.currentTimeMillis()));
                userService.save(user_);

                // for(String s:OnlineUserList.list()){
                // System.out.println(s);
                // }

                return SUCCESS;
            } else {
                // ServletActionContext.getResponse().getWriter().print(0);
                logger.warn("Date{},hanldle login ,but password is invalid.",
                        String.valueOf(new Date(0)));
                this.addFieldError("password", "password is invalid.");
                return INPUT;
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("Exception", e);
            return ERROR;
        }
    }
}
