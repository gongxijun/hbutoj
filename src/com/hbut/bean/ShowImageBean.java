package com.hbut.bean;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class ShowImageBean {

    private final static Logger logger = LoggerFactory.getLogger(ShowImageBean.class);

    public static String getImagePath(String username) {
        try {
            String path = new String();

            return path;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(" 显示图片异常 ", e);
            return null;
        }
    }
}
