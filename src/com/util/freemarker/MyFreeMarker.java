package com.util.freemarker;

import com.hbut.bean.LatestTopicBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyFreeMarker {

    public static void generator(String templatesPath, String ftlName,
                                 String desName, String objName, Map objMap) throws IOException,
            TemplateException {
        File file_ = new File(templatesPath + "\\templates");
        if (false == file_.exists()) {
            System.out.println(templatesPath + "\\templates"
                    + " is not existed");
            return;
        }

        Configuration cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File(templatesPath
                    + "\\templates"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Template t = cfg.getTemplate(ftlName);

        // this.getClass().getClassLoader().getResource("/").getPath();

        Writer out = new OutputStreamWriter(new FileOutputStream(templatesPath
                + "\\templates\\" + desName), "utf-8");
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
        Map map = new ConcurrentHashMap();
        map.put("topics", topi);
        // String path = System.getProperty("user.dir");
        // generator("D:\\tomcat6\\webapps\\hbut\\WEB-INF","latesttopic.ftl","latesttopic.html","topics",map);
        System.out.println("Successfull................");
    }
}
