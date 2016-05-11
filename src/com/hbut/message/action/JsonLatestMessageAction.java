package com.hbut.message.action;

import com.hbut.message.service.MessageService;
import com.hbut.message.vo.Message;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.json.annotations.JSON;

import java.util.ArrayList;
import java.util.List;

public class JsonLatestMessageAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(JsonLatestMessageAction.class);
    private MessageService messageService;
    private List<Message> latestMessages;
    private boolean success;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String latestMessages() throws Exception {
        try {
            latestMessages = new ArrayList<Message>();
            latestMessages = messageService.queryLatestMessages(null, 1, 20);
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "load latest topic failed.";
            logger.error(error, e);
            return SUCCESS;
        }
        success = true;
        return SUCCESS;
    }

    public List<Message> getLatestMessages() {
        return latestMessages;
    }

    public void setLatestMessages(List<Message> latestMessages) {
        this.latestMessages = latestMessages;
    }

    @JSON(deserialize = false, serialize = false)
    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

}
