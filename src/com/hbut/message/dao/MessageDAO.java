package com.hbut.message.dao;

import com.hbut.message.vo.Message;

import java.util.List;

public interface MessageDAO {

    public void save(Message message);

    public Message queryMessage(Integer messageId);

    public List<Message> query(String sql);

    public Message getLatestReply(Integer messageId);

    public Integer count(String sql);

    public Integer countMessages();

    public Integer countRootMessages();

    public List<Message> queryRootMessages(Integer pageNow, Integer pageSize);

    public List<Message> queryChildMessages(Integer parentId);

    public List<Message> queryMessagesByRootId(Integer rootId);

    public List<Message> queryLatestMessages(String username, Integer pageNow,
                                             Integer size);

    public List<Message> query(String sql, String word);
}
