package com.hbut.message.action;

import com.hbut.bean.MessageBean;
import com.hbut.message.service.MessageService;
import com.hbut.message.vo.Message;
import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Html2Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageListAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MessageService messageService;
    private UserService userService;
    private List<Integer> pageList;
    private Integer page = 1;
    private Integer intRowCount = 0;
    private Integer pageCount = 0;
    private Integer pageSize = 20;
    private List<MessageBean> messages;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String topicList() throws Exception {

        pageSize = pageSize > 20 ? 20 : pageSize;
        intRowCount = messageService.countRootMessages();
        pageCount = ((intRowCount + pageSize - 1) / pageSize);// 计算出总页数
        page = page < 1 ? 1 : page;
        page = page > pageCount ? pageCount : page;
        List<Message> messages_ = messageService.queryRootMessages(page, pageSize);

        messages = new ArrayList<MessageBean>();

        for (Message m_ : messages_) {

            //todo 存在bug indate,需要修复

            MessageBean mb_ = new MessageBean();
            mb_.setMessageId(m_.getMessage_id());
            mb_.setAuthor(m_.getCreate_user());
            mb_.setTitle(m_.getTitle());
            mb_.setOrderNum(m_.getOrderNum());
            mb_.setViews(m_.getViews());
            mb_.setVotes(m_.getVotes());
            mb_.setIn_date(m_.getIn_date());
            mb_.setFriendly_Date(getFriendlyDate(m_.getIn_date()));
            String bufString = new String();

            bufString = Html2Text
                    .RemoveHtml(((m_.getContent().length() > 2000) ? (m_
                            .getContent()).substring(0, 2000) : m_.getContent()));

            mb_.setContent_abstract(((bufString.length() > 500) ? (bufString)
                    .substring(0, 500) + "..." : bufString));

            String sql = "select count(m.message_id) from Message m where m.root_id="
                    + m_.getMessage_id();

            mb_.setComments(messageService.count(sql) - 1);

            Message lm = new Message();
            lm = messageService.getLatestReply(m_.getMessage_id());
            if (lm != null) {
                mb_.setLastReplyId(lm.getMessage_id());
                mb_.setLastReplyUser(lm.getCreate_user());
                mb_.setLastReplyDate(lm.getIn_date());
                mb_.setLastReplyFriendlyDate(getFriendlyDate(lm.getIn_date()));
            }

            User u = new User();
            u = userService.queryUser(m_.getCreate_user());
            if (u != null) {
                mb_.setAvatar(u.getAvatar());
            }
            messages.add(mb_);
        }

        List<Integer> volume = new ArrayList<Integer>();
        if (pageCount < 7) {
            for (Integer i = 1; i <= pageCount; i++) {
                volume.add(i);
            }
        } else {
            volume.add(1);
            if (page > 4) {
                volume.add(0); // 省略号
            }
            Integer start_i = new Integer(2);
            Integer end_i = new Integer(pageCount - 1);
            if (page - 2 > 2) {
                start_i = page - 2;
            }
            if (page + 2 < pageCount) {
                end_i = page + 2;
            }

            for (Integer i = start_i; i <= end_i; i++) {
                volume.add(i);
            }

            if (page < pageCount - 3) {
                volume.add(0); // 省略号
            }

            volume.add(pageCount);
        }

        pageList = volume;

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

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<MessageBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBean> messages) {
        this.messages = messages;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getIntRowCount() {
        return intRowCount;
    }

    public void setIntRowCount(Integer intRowCount) {
        this.intRowCount = intRowCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

}
