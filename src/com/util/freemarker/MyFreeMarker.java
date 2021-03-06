package com.util.freemarker;

import com.google.common.collect.Maps;
import com.hbut.bean.LatestTopicBean;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyFreeMarker {

    private static Logger logger = LoggerFactory.getLogger(MyFreeMarker.class);

    public static void generator(String templatesPath, String ftlName,
                                 String desName, String objName, Map objMap) throws IOException,
            TemplateException {
        File file_ = new File(templatesPath + "\\templates");
        if (false == file_.exists()) {
            logger.info(templatesPath + "\\templates"
                    + " is not existed");
            return;
        }

        Configuration cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File(templatesPath
                    + "\\templates"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("generator {}", e);
        }

        Template t = cfg.getTemplate(ftlName);

        // this.getClass().getClassLoader().getResource("/").getPath();

        Writer out = new OutputStreamWriter(new FileOutputStream(templatesPath
                + "\\templates\\" + desName), "gb2312"); //todo 请保持代码编码格式 gb2312
            t.process(objMap, out);
    }

    public static void main(String[] args) throws Exception {

        System.out.println(Thread.currentThread().getContextClassLoader()
                .getResource(""));

        System.out.println(MyFreeMarker.class.getClassLoader().getResource(""));

        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(MyFreeMarker.class.getResource(""));
        System.out.println(MyFreeMarker.class.getResource("/"));
        System.out.println(MyFreeMarker.class.getResourceAsStream("/"));
        // Class文件所在路径
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));

        List<LatestTopicBean> topi = new ArrayList<LatestTopicBean>();
        LatestTopicBean latestTopicBean = new LatestTopicBean();
        latestTopicBean.setUsername("Koko");
        latestTopicBean.setTopicId(11);
        latestTopicBean.setTitle("Hello");
        latestTopicBean.setDate(new Date());
        topi.add(latestTopicBean);
        LatestTopicBean latestTopicBean1 = new LatestTopicBean();
        latestTopicBean1.setUsername("Solo");
        latestTopicBean1.setTopicId(12);
        latestTopicBean1.setTitle("Hello world");
        latestTopicBean1.setDate(new Date());
        topi.add(latestTopicBean1);
        Map map = Maps.newHashMap();
        map.put("topics", topi);
        // String path = System.getProperty("user.dir");
        // generator("D:\\tomcat6\\webapps\\hbut\\WEB-INF","latesttopic.ftl","latesttopic.html","topics",map);
        System.out.println("Successfull................");
    }
}
