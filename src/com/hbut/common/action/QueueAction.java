package com.hbut.common.action;

import com.hbut.test.thread.JudgeQueue;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class QueueAction extends ActionSupport {

    private static final long serialVersionUID = 3552864426020089250L;
    private static final Logger logger = LoggerFactory.getLogger(QueueAction.class);
    private Integer solutionId;

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String execute() throws Exception {

        JudgeQueue.addGCC(solutionId);
        logger.info("Push one , size=" + JudgeQueue.getsize());
        return SUCCESS;
    }
}
