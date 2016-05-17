/**
 *
 */
package com.hbut.common.interceptor;

import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.Date;

public class ContestInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -57112204L;
    private static final Logger logger = LoggerFactory.getLogger(ContestInterceptor.class);
    private ContestService contestService;

    public ContestService getContestService() {
        return contestService;
    }

    public void setContestService(ContestService contestService) {
        this.contestService = contestService;
    }

    public String intercept(ActionInvocation invocation) throws Exception {

        try {
            ActionContext actionContext = invocation.getInvocationContext();
            Integer contestId = (Integer) actionContext.getValueStack()
                    .findValue("contestId");
            if (contestId == null) {
                return "error";
            }

            Contest contest = contestService.queryContest(contestId, "USER");

            if (contest == null) {
                return "error";
            }
            // System.out.println("----"+contest.getStart_time());
            Date nowDate = new Date();

            if (nowDate.getTime() < contest.getStart_time().getTime()) {
                actionContext.getValueStack().setValue("contestId", contestId);
                ActionContext.getContext().put("tip", "Contest isn't start!");
                return "contestPending";
            } else {
                return invocation.invoke();
            }

        } catch (Exception e) {
            // TODO: handle exception

            ActionContext.getContext().put("tip", "Operation error!");
            logger.error("Operation error: {}", e);
            return "error";

        }
    }
}
