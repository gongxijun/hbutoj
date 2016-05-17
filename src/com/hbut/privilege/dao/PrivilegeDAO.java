package com.hbut.privilege.dao;

import com.hbut.privilege.vo.Privilege;

import java.util.List;

public interface PrivilegeDAO {

    public void save(Privilege privilege);

    public void delete(Privilege privilege);

    public Privilege query(Integer  privilegeId);

    public Privilege query(String queryString);

    public List<Privilege> query();

    public List<Privilege> queryByRight(String rightstr);
}
