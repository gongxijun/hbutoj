package com.hbut.compileinfo.service;

import com.hbut.compileinfo.dao.CompileInfoDAO;
import com.hbut.compileinfo.vo.CompileInfo;

public class CompileInfoServiceImpl implements CompileInfoService {
	private CompileInfoDAO compileInfoDao;

	public CompileInfoDAO getCompileInfoDao() {
		return compileInfoDao;
	}

	public void setCompileInfoDao(CompileInfoDAO compileInfoDao) {
		this.compileInfoDao = compileInfoDao;
	}

	public CompileInfo queryCompileInfo(Integer solutionId) {
		// TODO Auto-generated method stub
		return compileInfoDao.queryCompileInfo(solutionId);
	}

	public void save(CompileInfo compileInfo) {
		// TODO Auto-generated method stub
		compileInfoDao.save(compileInfo);
	}

}
