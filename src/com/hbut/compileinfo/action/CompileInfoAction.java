package com.hbut.compileinfo.action;

import com.hbut.compileinfo.service.CompileInfoService;
import com.hbut.compileinfo.vo.CompileInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class CompileInfoAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private CompileInfoService compileInfoService;
    private Integer solutionId;
    private String errorInfo;
    private static final Logger logger = LoggerFactory.getLogger(CompileInfoAction.class);

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getCompileError() throws Exception {
        try {
            CompileInfo compileInfo_ = new CompileInfo();
            compileInfo_ = compileInfoService.queryCompileInfo(solutionId);
            if (null == compileInfo_) {
                ActionContext.getContext().put("tip", "No such Compile Info.");
                return ERROR;
            }
            errorInfo = compileInfo_.getError();
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("getCompileError : {}", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public CompileInfoService getCompileInfoService() {
        return compileInfoService;
    }

    public void setCompileInfoService(CompileInfoService compileInfoService) {
        this.compileInfoService = compileInfoService;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }
}
