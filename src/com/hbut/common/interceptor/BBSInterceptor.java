package com.hbut.common.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.util.Config;
import com.hbut.privilege.service.PrivilegeService;

public class BBSInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = -5718204L;
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

        // 存在sql注入隐患
        String queryString = "from Privilege p where p.username='" + username
                + "' and p.rightstr='HEAD'";
        if (privilegeService.query(queryString) != null) {
            return invocation.invoke();
        }
        // 查询管理员权限
        queryString = "from Privilege p where p.username='" + username
                + "' and p.rightstr='ADMIN'";
        if (privilegeService.query(queryString) != null) {
            return invocation.invoke();
        }

        if ("YES".equals(Config.getValue("OPENSOURCE"))) {
            return invocation.invoke();
        }

        ActionContext.getContext().put("tip", "This operation is now closed.");
        return ActionSupport.ERROR;
    }
}
