package com.hbut.mail.action;

import com.hbut.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class NewMailAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(NewMailAction.class);
    private UserService userService;
    private String username;

    public String newMail() throws Exception {
        try {
            String username_ = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (username_ == null) {
                ActionContext.getContext().put("tip", "You must login first.");
                return LOGIN;
            }
            /*
             * if(userService.queryUser(username)==null){
			 * ActionContext.getContext().put("tip", "No such user."); return
			 * ERROR; }
			 */
        } catch (Exception e) {
            // TODO: handle exception
            ActionContext.getContext().put("tip", "Unknown Error.");
            logger.error("newMail: {}", e);
            return ERROR;
        }

        return SUCCESS;
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

}
