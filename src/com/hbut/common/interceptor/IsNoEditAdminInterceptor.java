package com.hbut.common.interceptor;

import com.hbut.privilege.service.PrivilegeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


public class IsNoEditAdminInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -5718204L;
    private static final Logger logger = LoggerFactory.getLogger(IsNoEditAdminInterceptor.class);
    private PrivilegeService privilegeService;

    public PrivilegeService getPrivilegeService() {
        return privilegeService;
    }

    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    public String intercept(ActionInvocation invocation) throws Exception {


        ActionContext actionContext = invocation.getInvocationContext();
        String username = (String) actionContext.getSession().get(
                "session_username");

        if (null != username) {

            // 超级管理员
            String queryString = "from Privilege p where p.username='"
                    + username + "' and p.rightstr='HEAD'";
            if (privilegeService.query(queryString) != null) {
                return invocation.invoke();
            }
            // 管理员
            queryString = "from Privilege p where p.username='" + username
                    + "' and p.rightstr='ADMIN'";
            if (privilegeService.query(queryString) != null) {
                return invocation.invoke();
            }
            // 无编辑管理员
            queryString = "from Privilege p where p.username='" + username
                    + "' and p.rightstr='NOEDIT_ADMIN'";
            if (privilegeService.query(queryString) != null) {
                return invocation.invoke();
            }
        }

        actionContext.put("tip", "You have no privilege");
        logger.info("用户越权操作，{}", username);
        return ActionSupport.ERROR;
    }
}
