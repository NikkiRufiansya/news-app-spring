package com.nikki.webnews.service;

import com.nikki.webnews.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    void saveNews(News news);
    List<News> getAllNews();
    Optional<News> getNewsById(Long id);
    int getTotalNews();

}
