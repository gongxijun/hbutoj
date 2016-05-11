package com.hbut.tags.service;

import com.hbut.tags.dao.TagsDAO;
import com.hbut.tags.vo.Tags;

import java.util.List;

public class TagsServiceImpl implements TagsService {

    private TagsDAO tagsDao;

    public TagsDAO getTagsDao() {
        return tagsDao;
    }

    public void setTagsDao(TagsDAO tagsDao) {
        this.tagsDao = tagsDao;
    }

    public Integer counts(String sql) {
        // TODO Auto-generated method stub
        return tagsDao.counts(sql);
    }

    public List<Tags> query(String sql) {
        // TODO Auto-generated method stub
        return tagsDao.query(sql);
    }

    public List<Tags> query(String sql, String keyword) {
        // TODO Auto-generated method stub
        return tagsDao.query(sql, keyword);
    }

    public List<Tags> queryTags(Integer from, Integer pageSize, String order) {
        // TODO Auto-generated method stub
        return tagsDao.queryTags(from, pageSize, order);
    }

    public Tags queryTag(String tagName) {
        return tagsDao.queryTag(tagName);
    }

    public void save(Tags tags) {
        // TODO Auto-generated method stub
        tagsDao.save(tags);
    }

    public Tags queryTag(Integer tagId) {
        return tagsDao.queryTag(tagId);
    }

    public List<String> queryAllTagsName(String sql, String keyword,
                                         Integer pageSize) {
        return tagsDao.queryAllTagsName(sql, keyword, pageSize);
    }
}
