package com.hbut.mail.service;

import com.hbut.mail.vo.Mail;

import java.util.List;

public interface MailService {
    public void save(Mail mail);

    public List<Mail> queryMails(int from, int pageSize, String sql);

    public Mail queryMail(Integer mailId);

    public Integer countMails(String sql);

    public void changeStatus(Integer mailId);
}
