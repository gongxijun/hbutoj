package com.hbut.listener;

import com.hbut.user.service.UserService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.OnlineUsers;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineHttpSessionListener implements HttpSessionListener {

    private static Logger logger = LoggerFactory
            .getLogger(OnlineHttpSessionListener.class);

    private UserService userService;
    private static int count;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public static int getOnlineCount() {
        return count;
    }

    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        // System.out.println("Someone visit:"+se.getSession().getAttribute("uname_session"));
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub

	/*	Preconditions.checkNotNull(se);*/
        String name = se.getSession().getAttribute("session_username")
                .toString();

        logger.info(name, "Do Log out...");

        if (name != null && name.length() > 0) {
            // us.logout(name);
            OnlineUsers.removeUser(name);
        }
    }
}
