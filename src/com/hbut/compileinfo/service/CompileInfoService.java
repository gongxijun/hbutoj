package com.hbut.compileinfo.service;

import com.hbut.compileinfo.vo.CompileInfo;

public interface CompileInfoService {
    public CompileInfo queryCompileInfo(Integer solutionId);

    public void save(CompileInfo compileInfo);
}
