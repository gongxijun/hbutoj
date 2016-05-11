package com.hbut.user.dao;

import com.hbut.user.vo.User;

import java.util.List;

public interface UserDAO {
    public void save(User user);

    public boolean isUsernameExist(String username);

    public User getUserByEmail(String email);

    public User checkLogin(String username, String password);

    public List<User> queryUsers(Integer from, Integer pageSize);

    public List<User> getStandings(Integer from, Integer pageSize, String order);

    public User queryUser(String username);

    public Integer countUsers();

    public List<User> query(String sql, String word);

    public List<String> queryAllUserName(String sql, String q, Integer pageSize);

    public int getUserRank(User user);
}
