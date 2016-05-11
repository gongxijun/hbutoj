package com.hbut.message.action;

import com.hbut.message.service.MessageService;
import com.hbut.message.vo.Message;
import com.hbut.vote.service.VoteService;
import com.hbut.vote.vo.Vote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.json.annotations.JSON;

import java.sql.Date;

public class JsonMessageVoteAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(JsonMessageVoteAction.class);
    private MessageService messageService;
    private VoteService voteService;

    private Integer messageId;
    private Integer vote;
    private boolean success;
    private String error;

    public String vote() throws Exception {
        try {

            // System.out.println(messageId+" "+vote);
            success = true;
            String username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (null == username || "".equals(username)) {
                success = false;
                error = "You must login first.";
                return SUCCESS;
            }
            Message message = new Message();
            message = messageService.queryMessage(messageId);
            if (message == null) {
                success = false;
                error = "No such topic you vote to.";
                return SUCCESS;
            }

            if (true == voteService.isVoteBefore(username, messageId)) {
                success = false;
                error = "You can't vote twice.";
                return SUCCESS;
            }
            Integer vote__ = -1;
            if (vote > 0)
                vote__ = 1;
            Vote vote_ = new Vote();
            vote_.setMessage_id(messageId);
            vote_.setUsername(username);
            vote_.setVote(vote__);
            vote_.setVote_date(new Date(0));
            voteService.save(vote_);

            message.setVotes(message.getVotes() + vote__);
            messageService.saveMessage(message);

        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            error = "Unknown error.";
            logger.error(error, e);
            return SUCCESS;
        }
        success = true;

        return SUCCESS;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

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

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @JSON(deserialize = false, serialize = false)
    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @JSON(deserialize = false, serialize = false)
    public VoteService getVoteService() {
        return voteService;
    }

    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

}
