package com.hbut.news.service;

import com.hbut.news.vo.News;

import java.util.List;

public interface NewsService {
    public void save(News news);

    public List<News> queryNews(int from, int pageSize, String sql);

    public News queryNews(String sql);

    public Integer countNews(String sql);
}
