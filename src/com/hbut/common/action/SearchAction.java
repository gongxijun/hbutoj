package com.hbut.common.action;

import com.google.common.collect.Lists;
import com.hbut.message.service.MessageService;
import com.hbut.message.vo.Message;
import com.hbut.problem.service.ProblemService;
import com.hbut.problem.vo.Problem;
import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class SearchAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 3552864426020089250L;
    private UserService userService;
    private MessageService messageService;
    private ProblemService problemService;
    private String type = "all";
    private String word;
    private Integer size = 0;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    private List<Problem> problemList;
    private List<Message> messageList;
    private List<User> userList;

    public String search() throws Exception {

        try {
            // System.out.println(word+" "+word_);
            if (word == null || word.trim().length() == 0) {
                this.addFieldError("word", "Input the keyword!");
                return INPUT;
            }
            //word = new String(word.getBytes("GB2312"), "UTF8");
            String word_ = word;
            userList = Lists.newArrayList();
            problemList = Lists.newArrayList();
            messageList = Lists.newArrayList();
            String sql;

            if (type.equals("user")) {
                // user
                sql = "from User u where u.username like ? or u.nickname like ? order by u.username ASC";
                userList = userService.query(sql, word_);
            } else if (type.equals("problem")) {
                // problem
                sql = "from Problem p where p.problem_id like ? or p.title like ? or p.tag like ? order by p.problem_id ASC";
                problemList = problemService.query(sql, word_);
            } else if (type.equals("topic")) {
                // topic
                sql = "from Message m where m.message_id=m.root_id and m.title like ? order by m.message_id DESC";
                messageList = messageService.query(sql, word_);
            } else {
                // user
                sql = "from User u where u.username like ? or u.nickname like ? order by u.username ASC";
                userList = userService.query(sql, word_);
                // problem
                sql = "from Problem p where p.problem_id like ? or p.title like ? or p.tag like ? order by p.problem_id ASC";
                problemList = problemService.query(sql, word_);
                // topic
                sql = "from Message m where m.message_id=m.root_id and m.title like ? order by m.message_id DESC";
                messageList = messageService.query(sql, word_);
                type = "all";
            }
            size = userList.size() + problemList.size() + messageList.size();
        } catch (Exception e) {
            // TODO: handle exception
            this.addFieldError("tip", "Search Error!");
            return ERROR;
        }
        return SUCCESS;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public ProblemService getProblemService(){
            return problemService;
    }

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}

