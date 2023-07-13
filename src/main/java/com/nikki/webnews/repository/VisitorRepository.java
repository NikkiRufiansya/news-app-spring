package com.nikki.webnews.repository;

import com.nikki.webnews.model.Visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findByDate(LocalDate date);

    @Query("SELECT v FROM Visitor v ORDER BY v.date DESC")
    List<Visitor> getVisitorData();
}
