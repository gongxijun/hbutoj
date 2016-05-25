package com.hbut.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.StreamHandler;
import org.apache.struts2.ServletActionContext;

/**
 * @Author XiJun.Gong
 * @DATE 2016/5/25.
 * aim:   com.hbut.admin.action
 */
public class FaqEditAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String content;
    private final static Logger logger = LoggerFactory.getLogger(FaqEditAction.class);


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String faqEdit() throws Exception {

        try {
            String path = ServletActionContext.getRequest().getSession()
                    .getServletContext().getRealPath("/");
            content = StreamHandler.read(path
                    + "WEB-INF\\templates\\faq.html");
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("faqEdit 内部出错", e);
            return ERROR;
        }
        return SUCCESS;
    }
}
