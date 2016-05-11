package com.hbut.bean;

import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.Config;
import com.util.MyApplicationContextUtil;
import com.util.StreamHandler;
import com.util.freemarker.MyFreeMarker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class QuartzJob {

    private final Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    @SuppressWarnings("deprecation")
    public void work() {
        // System.out.println(new Date()+":"+" Generate Top 10.");
        try {
            // System.out.println(Thread.currentThread().getContextClassLoader().getResource("").toExternalForm());
            String root = Thread.currentThread().getContextClassLoader()
                    .getResource("").toString();

            root = root.replaceAll("file:/", "").replaceAll("%20", " ")
                    .replaceAll("/classes/", "");

            // System.out.println(root);
            List<User> users = new ArrayList<User>();
            UserService userService = (UserService) MyApplicationContextUtil
                    .getContext().getBean("userService");

            users = userService.getStandings(0, 10, "solved");

            Map map = new HashMap();    //如果后面的开发者用这个版本，建议使用推荐使用Guava Maps.newHashMap() ;
            map.put("topusers", users);
            map.put("date", new Date());
            MyFreeMarker.generator(root, "topusers.ftl", "topusers.html",
                    "topusers", map);

			/*
             *
			 * Other OJ contests
			 */

            root = root.replaceAll("file:/", "").replaceAll("%20", " ")
                    .replaceAll("WEB-INF", "");
            System.out.println("root {}" + root);
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

                // String path = ServletActionContext.getRequest()
                // .getSession().getServletContext().getRealPath("/");
                File f = new File(root + "otheroj.json");
                StreamHandler.write(f, content);

            } catch (MalformedURLException e) {
                // ...
                logger.error("MalformedURLException 抓取URL时抛出异常", e);
            } catch (IOException e) {
                // ...
                System.out.println("get other oj contest error");
                logger.error("获取其他网站信息失败", e);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("top 10 error");
            logger.error("定时器异常", e);
        }
    }
}
