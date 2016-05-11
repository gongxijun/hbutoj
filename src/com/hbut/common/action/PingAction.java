package com.hbut.common.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.OnlineUsers;

public class PingAction extends ActionSupport {

    /**
     *
     */
    private boolean success;
    private String error;
    private static final Logger logger = LoggerFactory.getLogger(PingAction.class);

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

    public void ping() throws Exception {
        try {

            String username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (username == null) {
                success = false;
                error = "You have logout.";
            }
            OnlineUsers.onlineUser(username);
            success = true;

        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "Unknown Error. {}";
            logger.error(error, e);
            return;
        }
    }
}
