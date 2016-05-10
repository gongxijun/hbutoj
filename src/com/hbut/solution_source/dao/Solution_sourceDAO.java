package com.hbut.solution_source.dao;

import com.hbut.solution_source.vo.Solution_source;

public interface Solution_sourceDAO {
	public Solution_source querySolutionSource(Integer solutionId);

	public void save(Solution_source solutionSource);

}
