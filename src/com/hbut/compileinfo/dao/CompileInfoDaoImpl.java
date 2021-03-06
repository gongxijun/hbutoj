package com.hbut.compileinfo.dao;

import com.hbut.compileinfo.vo.CompileInfo;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class CompileInfoDaoImpl extends HibernateDaoSupport implements
        CompileInfoDAO {

    public CompileInfo queryCompileInfo(Integer solutionId) {
        // TODO Auto-generated method stub
        Integer[] param = new Integer[]{solutionId};
        String sql = "from CompileInfo as ci where ci.solution_id=?";
        List<CompileInfo> list = getHibernateTemplate().find(sql, param);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public void save(CompileInfo compileInfo) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().saveOrUpdate(compileInfo);
    }

}
