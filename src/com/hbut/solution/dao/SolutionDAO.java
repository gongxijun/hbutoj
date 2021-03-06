package com.hbut.solution.dao;

import com.hbut.solution.vo.Solution;

import java.util.Date;
import java.util.List;

public interface SolutionDAO {
    public Solution querySolution(Integer solutionId);

    public List<Solution> querySolutions(Integer from, Integer pageSize,
                                         String sql);

    public Integer countSolutions(String sql);

    public void save(Solution solution);

    public List<Object> query(String sql);

    public List<Integer> getBydaily(Date date, Integer size);

    public List<Integer> getBymonthly(Date date, Integer size);
}
