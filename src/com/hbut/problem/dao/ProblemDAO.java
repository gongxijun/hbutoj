package com.hbut.problem.dao;

import java.util.List;

import com.hbut.problem.vo.Problem;

public interface ProblemDAO {
	public List<Problem> queryProblems(Integer from, Integer pageSize,
			String order, String ojName, String role);

	public void save(Problem problem);

	public Problem queryProblem(Integer problemId);

	public Integer countProblems(String role, String ojName);

	public List<Problem> searchProblem(Integer pageSize, String searchString);

	public List<Problem> query(String sql, String word);
}
