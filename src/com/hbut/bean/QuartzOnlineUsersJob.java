package com.hbut.bean;

import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.MyApplicationContextUtil;
import com.util.OnlineUsers;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class QuartzOnlineUsersJob {

    private final static Logger logger = LoggerFactory.getLogger(QuartzOnlineUsersJob.class);

    public void work() {
        try {

            UserService userService = (UserService) MyApplicationContextUtil
                    .getContext().getBean("userService");

            Map<String, OnlineUserBean> mou = new HashMap<String, OnlineUserBean>();
            mou = OnlineUsers.getOnlineUsers();

            System.out.println("QuartzOnlineUsersJob start... size="
                    + mou.size());

            Set set = mou.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String username = (String) it.next();
                User u = new User();

                System.out.println(username);
                if (username == null) {
                    System.out.println("null of name " + username);
                    it.remove();
                    continue;
                }

                u = userService.queryUser(username);
                if (u == null) {
                    System.out.println("null of user " + username);
                    it.remove();
                    continue;
                }

                u.setLastaccesstime((Timestamp) mou.get(username)
                        .getLastAccessTime());
                userService.save(u);

				/* ���ߵ���Ҫ�޳� */
                if (mou.get(username).getStatusFlag() == 0) {
                    System.out.println("removeUser " + username
                            + " , because of offline...");
                    it.remove();
                }

                // System.out.println("Next...");
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(" work Exception", e);
            System.out.println("update online users error...");
        }
    }
}
