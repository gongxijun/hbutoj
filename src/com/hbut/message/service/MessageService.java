package com.hbut.message.service;

import com.hbut.message.vo.Message;

import java.util.List;

public interface MessageService {

    public void saveMessage(Message message);

    public Message queryMessage(Integer messageId);

    public Message getLatestReply(Integer messageId);

    public List<Message> query(String sql);

    public List<Message> queryRootMessages(Integer pageNow, Integer pageSize);

    public List<Message> queryMessagesByRootId(Integer rootId);

    public List<Message> queryChildMessages(Integer parentId);

    public List<Message> queryLatestMessages(String username, Integer pageNow,
                                             Integer size);

    public Integer count(String sql);

    public Integer countMessages();

    public Integer countRootMessages();

    public List<Message> query(String sql, String word);
}
