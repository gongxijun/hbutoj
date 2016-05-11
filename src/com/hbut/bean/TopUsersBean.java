package com.hbut.bean;

import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.util.MyApplicationContextUtil;

import java.util.ArrayList;
import java.util.List;

public class TopUsersBean {

    public static List<User> getTopUsers() {
        List<User> users = new ArrayList<User>();

        UserService userService = (UserService) MyApplicationContextUtil
                .getContext().getBean("userService");
        // System.out.println("U:"+userService.countUsers());
        users = userService.getStandings(0, 10, "solved");
        return users;
    }
}
