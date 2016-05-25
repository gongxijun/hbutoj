package com.hbut.admin.action;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.StreamHandler;
import com.util.freemarker.MyFreeMarker;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

/**
 * @Author XiJun.Gong
 * @DATE 2016/5/25.
 * aim:   com.hbut.admin.action
 */
public class FaqMakerAction  extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String content;
    private final static Logger logger = LoggerFactory.getLogger(FaqMakerAction.class);

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String faqmaker() throws Exception {

        try {
            if (content == null) {
                content = "Welcome To HBUT Online Judge";
                try {
                    String path = ServletActionContext.getRequest()
                            .getSession().getServletContext().getRealPath("/");
                    // System.out.println(path+"WEB-INF\\templates\\homepage.html");
                    content = StreamHandler.read(path
                            + "WEB-INF\\templates\\faq.html");
                } catch (Exception e) {
                    // TODO: handle exception
                    logger.error("faqmaker 内部出错", e);
                }
            }

            Map map = Maps.newHashMap();
            map.put("content", content);
            String root = ServletActionContext.getRequest().getRealPath(
                    "/WEB-INF");

            MyFreeMarker.generator(root, "faq.ftl", "faq.html",
                    "content", map);

        } catch (Exception e) {
            // TODO: handle exception
            logger.error("内部出错", e);
            return ERROR;
        }
        return SUCCESS;
    }

}
