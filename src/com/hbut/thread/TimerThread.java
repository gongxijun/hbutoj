package com.hbut.thread;

import com.hbut.bean.TopUsersBean;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.freemarker.MyFreeMarker;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimerThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(TimerThread.class);

    public void run() {

        while (true) {
            List<User> users = new ArrayList<User>();
            users = TopUsersBean.getTopUsers();
            Map map = new HashMap();
            map.put("topusers", users);

            try {
                MyFreeMarker.generator("NULL", "topusers.ftl", "topusers.html",
                        "topusers", map);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                logger.error("run: {}", e1);

            } catch (TemplateException e1) {
                // TODO Auto-generated catch block
                logger.error("run: {}", e1);
            }

            try {
                Thread.sleep(60000);
            } catch (Exception e) {
                logger.error("run: {}", e);
            }
        }
    }
}
