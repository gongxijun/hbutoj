package com.hbut.common.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.OnlineUsers;

public class LogoutAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 3552864426020089250L;

    private static final Logger logger = LoggerFactory.getLogger(LogoutAction.class);

    public String execute() throws Exception {
        try {
            String url = new String();
            url = (String) ActionContext.getContext().getSession()
                    .get("session_url");
            if (url == null || "".equals(url)) {
                url = "enter";
            }
            String name = (String) ActionContext.getContext().getSession()
                    .get("session_username").toString();
            if (name != null && name.length() != 0) {
                OnlineUsers.offlineUser(name);
            }

            ActionContext actionContext = ActionContext.getContext();

            actionContext.getSession().clear();

            ActionContext.getContext().getSession().put("session_url", url);
            return SUCCESS;

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(" 退出异常", e);
            return ERROR;
        }
    }
}
