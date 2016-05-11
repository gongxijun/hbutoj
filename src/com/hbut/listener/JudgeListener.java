package com.hbut.listener;

import com.hbut.test.thread.myThread;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JudgeListener implements ServletContextListener {


    private myThread gcc = new myThread();
    private static Logger logger = LoggerFactory.getLogger(JudgeListener.class);

    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        logger.info("Thread. Close..");
    }

    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        try {
            gcc.setDaemon(true);
            gcc.start();
            logger.info("----Judge Core Has Been Initialized----");
        } catch (Exception e) {
            logger.error("----Start Judge Core Error----", e);
        }
    }

}
