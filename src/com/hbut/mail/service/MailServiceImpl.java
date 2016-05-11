package com.hbut.mail.service;

import com.hbut.mail.dao.MailDAO;
import com.hbut.mail.vo.Mail;

import java.util.List;

public class MailServiceImpl implements MailService {

    private MailDAO mailDao;

    public MailDAO getMailDao() {
        return mailDao;
    }

    public void setMailDao(MailDAO mailDao) {
        this.mailDao = mailDao;
    }

    public void changeStatus(Integer mailId) {
        // TODO Auto-generated method stub
        mailDao.changeStatus(mailId);
    }

    public Integer countMails(String sql) {
        // TODO Auto-generated method stub
        return mailDao.countMails(sql);
    }

    public Mail queryMail(Integer mailId) {
        // TODO Auto-generated method stub
        return mailDao.queryMail(mailId);
    }

    public List<Mail> queryMails(int from, int pageSize, String sql) {
        // TODO Auto-generated method stub
        return mailDao.queryMails(from, pageSize, sql);
    }

    public void save(Mail mail) {
        // TODO Auto-generated method stub
        mailDao.save(mail);
    }

}
