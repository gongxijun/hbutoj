package com.util.test;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class MailClientFolder {

    public static void main(String[] args) {

        /**
         *  1. 发邮件
         * 把配置文件内容加载到prop中
         */

        Properties prop = new Properties();
        try {
            prop.load(MailClientFolder.class.getClassLoader().getResourceAsStream("email_template.properties"));
            System.out.println(MailClientFolder.class.getClassLoader());
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        /*
		 * 登录邮件服务器，得到session
		 */
        String host = prop.getProperty("host");//服务器主机名
        String name = prop.getProperty("username");//登录名
        String pass = prop.getProperty("password");//登录密码
        Session session = MailUtils.createSession(host, name, pass);

		/*
		 * 创建Mail对象
		 */
        String from = prop.getProperty("from");
        //String to = "vipgxjun@163.com";
        String to = "1005267096@qq.com";
        String subject = prop.getProperty("subject");
        // MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
        // 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "去死吧"); 返回“你好张三，你去死吧！”
        String content = MessageFormat.format(prop.getProperty("content"), "dadadadas");
        Mail mail = new Mail(from, to, subject, content);
		/*
		 * 发送邮件
		 */
        try {
            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


