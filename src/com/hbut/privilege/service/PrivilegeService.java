package com.hbut.privilege.service;

import com.hbut.privilege.vo.Privilege;

import java.util.List;

public interface PrivilegeService {
    public void save(Privilege privilege);

    public void delete(Privilege privilege);

    public Privilege query(String queryString);

    public Privilege query(Integer privilegeId);

    public Privilege queryByName(String username);

    public List<Privilege> query();

    public List<Privilege> queryByRight(String rightstr);
}
