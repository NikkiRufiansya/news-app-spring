package com.nikki.webnews.repository;

import com.nikki.webnews.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
