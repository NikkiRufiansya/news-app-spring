package com.nikki.webnews.repository;

import com.nikki.webnews.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT COUNT(id) FROM News")
    int getTotalNewsCount();


}
