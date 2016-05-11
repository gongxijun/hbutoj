package com.hbut.solution_source.dao;

import com.hbut.solution_source.vo.Solution_source;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class Solution_sourceDaoImpl extends HibernateDaoSupport implements
        Solution_sourceDAO {

    public Solution_source querySolutionSource(Integer solutionId) {
        // TODO Auto-generated method stub
        Integer[] param = new Integer[]{solutionId};
        String sql = "from Solution_source as ss where ss.solution_id=?";
        List<Solution_source> list = getHibernateTemplate().find(sql, param);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public void save(Solution_source solutionSource) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().save(solutionSource);
    }

}
