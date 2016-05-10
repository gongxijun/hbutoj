package com.hbut.compileinfo.dao;

import com.hbut.compileinfo.vo.CompileInfo;

public interface CompileInfoDAO {
	public CompileInfo queryCompileInfo(Integer solutionId);

	public void save(CompileInfo compileInfo);
}
