package com.hbut.admin.action;

import com.hbut.bean.MessageBean;
import com.hbut.message.service.MessageService;
import com.hbut.message.vo.Message;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.freemarker.MyFreeMarker;
import org.apache.struts2.ServletActionContext;

import java.util.*;

public class RecentActionAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private MessageService messageService;
    private List<Message> latestMessages;

    private final static Logger logger = LoggerFactory.getLogger(RecentActionAction.class);

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<Message> getLatestMessages() {
        return latestMessages;
    }

    public void setLatestMessages(List<Message> latestMessages) {
        this.latestMessages = latestMessages;
    }

    public String MakeRecentAction() throws Exception {

        try {
            // latest topic
            latestMessages = new ArrayList<Message>();
            latestMessages = messageService.queryLatestMessages(null, 0, 20);
            List<MessageBean> topics = new ArrayList<MessageBean>();

            for (Message m_ : latestMessages) {
                MessageBean mb = new MessageBean();
                mb.setAuthor(m_.getCreate_user());
                mb.setTitle(m_.getTitle());
                mb.setIn_date(m_.getIn_date());
                mb.setFriendly_Date(getFriendlyDate(m_.getIn_date()));
                mb.setMessageId(m_.getMessage_id());
                mb.setRootId(m_.getRoot_id());
                mb.setParentId(m_.getParent_id());
                topics.add(mb);
            }
            Map map = new HashMap();
            map.put("topics", topics);
            // ActionContext.getContext().get
            String root = ServletActionContext.getRequest().getRealPath(
                    "/WEB-INF");

            MyFreeMarker.generator(root, "latesttopic.ftl", "latesttopic.html",
                    "topics", map);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("内部出错", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String getFriendlyDate(Date time) {
        if (time == null)
            return getText("unknown");
        int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);
        if (ct < 3600)
            return Math.max(ct / 60, 1) + getText("minutes_before");
        if (ct >= 3600 && ct < 86400)
            return ct / 3600 + getText("hours_before");
        if (ct >= 86400 && ct < 2592000) { // 86400 * 30
            int day = ct / 86400;
            if (day > 1) {
                return day + getText("days_before");
            }
            return getText("yesterday");
        }
        if (ct >= 2592000 && ct < 31104000) // 86400 * 30
            return ct / 2592000 + getText("months_before");
        return ct / 31104000 + getText("years_before");
    }

}
