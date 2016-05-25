package com.hbut.privilege.service;

import com.google.common.base.Strings;
import com.hbut.privilege.dao.PrivilegeDAO;
import com.hbut.privilege.vo.Privilege;

import java.util.List;

public class PrivilegeServiceImpl implements PrivilegeService {

    private PrivilegeDAO privilegeDao;

    public PrivilegeDAO getPrivilegeDao() {
        return privilegeDao;
    }

    public void setPrivilegeDao(PrivilegeDAO privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    public void delete(Privilege privilege) {
        // TODO Auto-generated method stub
        privilegeDao.delete(privilege);
    }

    public Privilege query(String queryString) {
        // TODO Auto-generated method stub
        return privilegeDao.query(queryString);
    }

    public void save(Privilege privilege) {
        // TODO Auto-generated method stub
        privilegeDao.save(privilege);
    }

    public Privilege query(Integer privilegeId) {
        return privilegeDao.query(privilegeId);
    }

    @Override
    public Privilege queryByName(String username) {
        if (Strings.isNullOrEmpty(username))
            return null;
        return privilegeDao.queryByName(username);
    }

    public List<Privilege> query() {
        return privilegeDao.query();
    }

    public List<Privilege> queryByRight(String rightstr) {
        return privilegeDao.queryByRight(rightstr);
    }
}
