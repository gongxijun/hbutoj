package com.hbut.common.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

public class RedirectAction {

    /**
     *
     */
    private static final long serialVersionUID = 6769837690387947674L;
    private static final Logger logger = LoggerFactory.getLogger(RedirectAction.class);
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void execute() throws Exception {

        try {
            url = (String) ActionContext.getContext().getSession()
                    .get("session_url");
            // System.out.println(url);
            if (null == url) {
                url = ServletActionContext.getRequest().getContextPath();
            }
            ServletActionContext.getResponse().sendRedirect(url);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(" 重定向异常{}", e);
        }
    }
}
