/**
 *
 */
package com.hbut.test.thread;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

public class JudgeQueue {

    private static ConcurrentLinkedQueue<Integer> QueueGCC = new ConcurrentLinkedQueue<Integer>();
    private static Logger logger = LoggerFactory.getLogger(JudgeQueue.class);

    public static int getsize() {
        try
        {
            return QueueGCC.size();
        } catch (NullPointerException e) {
          logger.error("getSize获取队列名称 {}", e);
        }
        return 0;
    }

    public static boolean addGCC(int runId) {
        try {
            QueueGCC.add(runId);
            // System.out.println("add"+runId);
            // System.out.println("size0:"+QueueGCC.size());
            return true;
        } catch (NullPointerException e) {
            logger.error("队列为空 :{}", e);
            return false;
        }
    }

    public static Integer pollGCC() {
        return QueueGCC.poll();
    }

    public static boolean isGCCEmpty() {
        return QueueGCC.isEmpty();
    }
}
