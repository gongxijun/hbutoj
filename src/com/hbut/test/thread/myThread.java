package com.hbut.test.thread;

import com.hbut.user.service.UserService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.MyApplicationContextUtil;

import java.io.IOException;

public class myThread extends Thread {

    Logger logger = LoggerFactory.getLogger(myThread.class);

    public void run() {

        while (true) {

            // System.out.println("Thread...");
            // ApplicationContext applicationContext = new
            // ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = (UserService) MyApplicationContextUtil
                    .getContext().getBean("userService");
            logger.info("U:" + userService.countUsers());
            Integer runId = JudgeQueue.pollGCC();

            try {

                // System.out.println("No judge...");
                Thread.sleep(2000);  //休眠2s
            } catch (Exception e) {
                logger.error("POLL线程异常 {}", e);
            }

            if (runId == null) {
                try {
                    // System.out.println("No judge...");
                    Thread.sleep(10);
                } catch (Exception e) {
                    logger.error("获取队列Id异常 {}", e);
                }

            } else {
                logger.info("Start judge..." + runId
                        + ", and Queue left " + JudgeQueue.getsize());
                // .......start
                //todo 此处需要改成配置路径文件xml
                //todo D:/github/OJ/Client.exe
                String[] cmd = {"C:/OJ/Client.exe", Integer.toString(runId)};
                try {
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    //e.printStackTrace();
                    logger.error("启动评判内核出错： {}", e);
                }
            }

        }
    }
}
