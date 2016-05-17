package com.hbut.listener;

import com.hbut.thread.TimerThread;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimerListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(TimerListener.class);
    private TimerThread timerThead = new TimerThread();

    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        logger.info("TimerThead. Close..");
    }

    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        try {
            // MyApplicationContextUtil.getContext().get;
            timerThead.setDaemon(true);
            timerThead.start();
            logger.info("----TimerThead Has Been Initialized----");

        } catch (Exception e) {
            logger.info("----Start TimerThead Error----");
        }
    }

}
