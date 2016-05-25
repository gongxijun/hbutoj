package com.hbut.compileinfo.action;

import com.hbut.common.action.JsonOnlineUserAction;
import com.hbut.compileinfo.service.CompileInfoService;
import com.hbut.compileinfo.vo.CompileInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.json.annotations.JSON;

public class JsonCompileInfoAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(JsonOnlineUserAction.class);
    private CompileInfoService compileInfoService;
    private Integer solutionId;
    private String errorInfo;
    private boolean success;
    private String error;

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCompileError() throws Exception {
        try {
            CompileInfo compileInfo_ = new CompileInfo();
            compileInfo_ = compileInfoService.queryCompileInfo(solutionId);
            if (null == compileInfo_) {
                success = false;
                error = "No such compile information.";
                return SUCCESS;
            }

            errorInfo = compileInfo_.getError().replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;").replaceAll("\n", "<br>");

        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "Unknown Error.";
            logger.error(error, e);
            return ERROR;
        }
        success = true;
        return SUCCESS;
    }

    @JSON(deserialize = false, serialize = false)
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
