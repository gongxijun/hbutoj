package com.hbut.admin.action;

import com.hbut.bean.TopUsersBean;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.Config;
import com.util.StreamHandler;
import com.util.freemarker.MyFreeMarker;
import org.apache.struts2.ServletActionContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TopUsersAction extends ActionSupport {

    private final static Logger logger = LoggerFactory.getLogger(TopUsersAction.class);
    private static final long serialVersionUID = 1L;


    public String MakeTopUsers() throws Exception {

        try {
            List<User> users = new ArrayList<User>();
            users = TopUsersBean.getTopUsers();
            Map map = new HashMap();
            map.put("topusers", users);
            map.put("date", new Date());
            String root = ServletActionContext.getRequest().getRealPath(
                    "/WEB-INF");
            MyFreeMarker.generator(root, "topusers.ftl", "topusers.html",
                    "topusers", map);

			/*
             * OJ Contests
			 */

            try {
                URL url = new URL(Config.getValue("OTHEROJ"));
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line;
                String content = new String();

                while ((line = reader.readLine()) != null) {
                    // ...
                    // System.out.println(line);
                    content += line;
                }
                reader.close();

                String path = ServletActionContext.getRequest().getSession()
                        .getServletContext().getRealPath("/");
                File f = new File(path + "otheroj.json");

                StreamHandler.write(f, content);

            } catch (MalformedURLException e) {
                // Todo: handle exception
                logger.error("内部出错", e);

            } catch (IOException e) {
                //Todo: handle exception
                logger.error("内部出错", e);
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error("内部出错", e);
            return ERROR;
        }
        return SUCCESS;
    }

}
