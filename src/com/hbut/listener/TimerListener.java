package com.hbut.listener;

import com.hbut.thread.TimerThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimerListener implements ServletContextListener {

    private TimerThread timerThead = new TimerThread();

    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println("TimerThead. Close..");
    }

    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        try {
            // MyApplicationContextUtil.getContext().get;
            timerThead.setDaemon(true);
            timerThead.start();
            System.out.println("----TimerThead Has Been Initialized----");

        } catch (Exception e) {
            System.out.println("----Start TimerThead Error----");
        }
    }

}
