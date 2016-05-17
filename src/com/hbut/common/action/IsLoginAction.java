package com.hbut.common.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class IsLoginAction {
    public void execute() throws Exception {
        String username = (String) ActionContext.getContext().getSession()
                .get("session_username");
        if (null != username) {
            // actionContext.put("tip","Login");
            ServletActionContext.getResponse().getWriter().print(1);
        } else {
            ServletActionContext.getResponse().getWriter().print(0);
        }

    }
}
