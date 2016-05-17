package com.hbut.bean;

import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.util.MyApplicationContextUtil;

import java.util.List;

public class TopUsersBean {


    public static List<User> getTopUsers() {
        UserService userService = (UserService) MyApplicationContextUtil
                .getContext().getBean("userService");
        return userService.getStandings(0, 10, "solved");
    }
}
