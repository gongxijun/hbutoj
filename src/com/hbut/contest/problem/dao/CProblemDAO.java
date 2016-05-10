package com.hbut.contest.problem.dao;

import java.util.List;

import com.hbut.contest.problem.vo.CProblem;

public interface CProblemDAO {
	public List<CProblem> queryProblems(Integer contestId);

	public void save(CProblem problem);

	public void delete(CProblem problem);

	public CProblem queryProblemByNum(String num, Integer contestId);

	public Integer countProblems(Integer contestId);

	public CProblem queryProblemByPid(Integer problemId, Integer contestId);

}
