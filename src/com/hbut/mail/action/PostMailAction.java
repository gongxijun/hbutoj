package com.hbut.mail.action;

import com.hbut.mail.service.MailService;
import com.hbut.mail.vo.Mail;
import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.Config;
import com.util.Html2Text;
import com.util.MailUtil;
import org.apache.struts2.json.annotations.JSON;

import java.util.Date;

public class PostMailAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PostMailAction.class);
    private MailService mailService;
    private UserService userService;
    private Integer mailId;
    private Integer reply;
    private String title;
    private String content;
    private String toUser;
    private boolean success;
    private String error;

    public String postMail() throws Exception {

        try {
            String username = new String();
            username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (null == username || "".equals(username)) {
                success = false;
                error = "You must login first.";
                return SUCCESS;
            }
            if (null == title || title.trim().length() == 0) {
                success = false;
                error = "Title shouldn't be empty.";
                return SUCCESS;
            }
            if (null == content || content.trim().length() == 0) {
                success = false;
                error = "Content shouldn't be empty.";
                return SUCCESS;
            }
            if (null == toUser || toUser.trim().length() == 0) {
                success = false;
                error = "Receive-user shouldn't be empty.";
                return SUCCESS;
            }
            User user_ = new User();

            user_ = userService.queryUser(toUser);
            if (user_ == null) {
                success = false;
                error = "No such user you send to.";
                return SUCCESS;
            }
            // System.out.println(title+content);

            Date dt_prevSubmit = (Date) ActionContext.getContext().getSession()
                    .get("session_submit");
            Date dt = new Date();

            if (dt_prevSubmit != null) {
                // System.out.println(dt.getTime()-dt_prevSubmit.getTime());
                if (dt.getTime() - dt_prevSubmit.getTime() < 5000) { // 限制5s一次提交
                    System.out.println(username
                            + " post-mail twice at 5 second.");
                    // this.addFieldError("tip", "");
                    success = true;
                    return SUCCESS;
                }
            }
            ActionContext.getContext().getSession().put("session_submit", dt);

            Mail mail_ = new Mail();
            mail_.setTitle(title);
            mail_.setContent(content);
            mail_.setFrom_user(username);
            mail_.setTo_user(user_.getUsername());
            mail_.setReply(reply);
            mailService.save(mail_);

            try {
                /* 提醒用户注册邮箱 */
                MailUtil sendMail = new MailUtil();
                sendMail.setHost(Config.getValue("MAIL_HOST")); // 发邮件服务器
                sendMail.setUserName(Config.getValue("MAIL_USERNAME")); // 用户名
                sendMail.setPassWord(Config.getValue("MAIL_PSW")); // 密码
                sendMail.setTo(user_.getEmail()); // 发送到
                sendMail.setFrom(Config.getValue("MAIL_FROM")); // 发送邮箱
                sendMail.setSubject(title); // 标题
                String content_ = new String();
                content_ = "Hi! " + username + " , new mail from " + username
                        + "\n " + Config.getValue("DOMAIN") + "/mails/"
                        + mail_.getMail_id() + "\n\n"
                        + Html2Text.RemoveHtml(content);

                sendMail.setContent(content_); // 邮件内容
                sendMail.sendMail();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("send mail fail via new-mail");
                logger.error("postMail: {}", e);
                // success = true;
                // return SUCCESS;
            }

        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "Unknown error.";
            logger.error("postMail: {} " + error, e);
            return SUCCESS;
        }
        success = true;

        return SUCCESS;
    }

    @JSON(deserialize = false, serialize = false)
    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @JSON(deserialize = false, serialize = false)
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
