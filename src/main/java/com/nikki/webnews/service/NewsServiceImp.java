package com.nikki.webnews.service;

import com.nikki.webnews.model.News;
import com.nikki.webnews.repository.NewsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NewsServiceImp implements NewsService  {

    @Autowired
    private NewsRepository newsRepository;


    @Override
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public int getTotalNews() {
        return newsRepository.getTotalNewsCount();
    }
}
